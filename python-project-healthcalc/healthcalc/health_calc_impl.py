import math
from healthcalc import HealthCalc, InvalidHealthDataException


class HealthCalcImpl(HealthCalc):

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


    def bmi_full_classification(self, bmi: float) -> str:
        """Calculate the FULL BMI classification of a person.

        :param bmi: Body Mass Index (kg/m2)
        :return: String classification (FULL)
        :raises InvalidHealthDataException: If bmi is out of range or not finite
        """
        # --- Input validation ---
        if not isinstance(bmi, (int, float)) or not math.isfinite(bmi):
            raise InvalidHealthDataException("BMI must be a finite real number.")
        if bmi <= 0:
            raise InvalidHealthDataException("BMI must be positive.")
        if bmi > 150:
            raise InvalidHealthDataException("BMI must be within a possible biological range [0-150].")

        # --- FULL classification ---
        if bmi < 16.0:
            return "Severe Thinness"
        if bmi < 17.0:
            return "Moderate Thinness"
        if bmi < 18.5:
            return "Mild Thinness"
        if bmi < 25.0:
            return "Normal"
        if bmi < 30.0:
            return "Overweight"
        if bmi < 35.0:
            return "Obesity Class I"
        if bmi < 40.0:
            return "Obesity Class II"
        return "Obesity Class III"

    def bmi(self, weight: float, height: float) -> float:
        if weight <= 0:
            raise InvalidHealthDataException("Weight must be positive.")
        if height <= 0:
            raise InvalidHealthDataException("Height must be positive.")
        if weight < 1 or weight > 700:
            raise InvalidHealthDataException("Weight must be within a possible biological range [1-700] kg.")
        if height < 0.30 or height > 3.00:
            raise InvalidHealthDataException("Height must be within a possible biological range [0.30-3.00] m.")
            
        return weight / (height ** 2)