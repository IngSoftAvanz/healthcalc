from healthcalc import HealthCalc, InvalidHealthDataException
from healthcalc.person import Person
from healthcalc.basal_metabolic_index import BasalMetabolicIndex
from healthcalc.ideal_body_weight import IdealBodyWeight


class HealthCalcImpl(HealthCalc, BasalMetabolicIndex, IdealBodyWeight):

    # Almacena una instancia única:
    _instance = None

    def __new__(cls, *args, **kwargs):
        if not cls._instance:
            # En caso de que no exista, se crea:
            cls._instance = super(HealthCalcImpl, cls).__new__(cls)
        return cls._instance

    # -- Multi-drive support: drive swapping:

    def weight_to_kg(self, weight: float, unit: str) -> float:
        if unit.lower() in ["lb", "pounds"]:
            return weight * 0.453592
        return weight

    def height_to_cm(self, height: float, unit: str) -> float:
        if unit.lower() in ["in", "inches"]:
            return height * 2.54
        return height

    # --- BMI metric implementation:

    def bmi_classification(self, bmi: float) -> str:
        if bmi < 0:
            raise InvalidHealthDataException("BMI cannot be negative.")
        if bmi > 150:
            raise InvalidHealthDataException("BMI must be within a possible biological range [0-150].")

        result = "Obesity"
        if bmi < 18.5:
            result = "Underweight"
        elif bmi < 25:
            result = "Normal weight"
        elif bmi < 30:
            result = "Overweight"
        return result

    def bmi(self, weight: float, height: float, weight_unit: str = "kg", height_unit: str = "m") -> float:
        # Unit conversion integration
        weight = self.weight_to_kg(weight, weight_unit)
        if height_unit.lower() != "m":
            height = self.height_to_cm(height, height_unit) / 100

        if weight <= 0:
            raise InvalidHealthDataException("Weight must be positive.")
        if height <= 0:
            raise InvalidHealthDataException("Height must be positive.")
        if weight < 1 or weight > 700:
            raise InvalidHealthDataException("Weight must be within a possible biological range [1-700] kg.")
        if height < 0.30 or height > 3.00:
            raise InvalidHealthDataException("Height must be within a possible biological range [0.30-3.00] m.")

        return weight / (height ** 2)

    # -- IBW metric implementation (Lorentz formula)

    def ibw_lorentz_metric(self, height: float, gender: str, height_unit: str = "cm") -> float:
        height = self.height_to_cm(height, height_unit)

        # We converted the gender to lowercase to avoid problems when it is entered in uppercase or mixed case.
        gender = gender.lower()

        # We checked for realistic height limits.
        if height < 40 or height > 300:
            raise InvalidHealthDataException("Height must be between 40 and 300 cm for IBW.")

        # We verified that the entered gender is correct.
        if gender not in ["male", "female"]:
            raise InvalidHealthDataException("The gender must be 'male' or 'female'.")

        # Application of the gender-specific adjustment factor.
        if gender == "male":
            result = (height - 100) - ((height - 150) / 4.0)
        else:
            result = (height - 100) - ((height - 150) / 2.0)

        return result

    # --- Metabolic rates & energy metrics implementation:

    def bmr_metric(self, weight: float, age: int, gender: str, weight_unit: str = "kg") -> float:
        weight = self.weight_to_kg(weight, weight_unit)

        if weight <= 0 or age <= 0:
            raise InvalidHealthDataException("The parameters entered must be positive.")

        if weight < 1 or weight > 700:
            raise InvalidHealthDataException("Weight must be within a possible biological range [1-700] kg.")

        gender = gender.lower()

        # Specific equations are used depending on gender and age range.
        if gender not in ["male", "female"]:
            raise InvalidHealthDataException("The gender must be 'male' or 'female'.")

        if gender == "male":
            if 18 <= age <= 30:
                return (15.057 * weight) + 692.2
            elif 30 < age <= 60:
                return (11.472 * weight) + 873.1
            else:
                return (11.711 * weight) + 587.7

        else:  # female
            if 18 <= age <= 30:
                return (14.818 * weight) + 486.6
            elif 30 < age <= 60:
                return (8.126 * weight) + 845.6
            else:
                return (9.082 * weight) + 658.5

    # --- Person-based metric methods for Practice 7:

    def bmi_from_person(self, person: Person) -> float:
        return self.bmi(person.weight(), person.height())

    def bmi_category_from_person(self, person: Person):
        bmi_value = self.bmi_from_person(person)
        return self.bmi_classification(bmi_value)

    def ibw_from_person(self, person: Person) -> float:
        return self.ibw_lorentz_metric(person.height(), person.gender().value)

    def bmr_from_person(self, person: Person) -> float:
        return self.bmr_metric(person.weight(), person.age(), person.gender().value)