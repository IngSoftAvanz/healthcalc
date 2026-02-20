import pytest
from healthcalc.health_calc_impl import HealthCalcImpl
from healthcalc.exceptions import InvalidHealthDataException


class TestBMIClassification:

    @pytest.fixture(autouse=True)  # Equivalente a @BeforeEach en JUnit
    def set_up(self):
        """Se ejecuta antes de cada test."""
        self.health_calc = HealthCalcImpl()

    @pytest.mark.parametrize("bmi", [10.0, 18.4, 18.49], ids=lambda x: f"BMI {x} -> Underweight")
    def test_bmi_underweight(self, bmi: float):
        """Cálculo de clasificación BMI para Underweight."""
        assert self.health_calc.bmi_classification(bmi) == "Underweight"

    @pytest.mark.parametrize("bmi", [18.5, 22.0, 24.9, 24.99], ids=lambda x: f"BMI {x} -> Normal weight")
    def test_bmi_normal_weight(self, bmi: float):
        """Cálculo de clasificación BMI para Normal weight."""
        assert self.health_calc.bmi_classification(bmi) == "Normal weight"

    @pytest.mark.parametrize("bmi", [25.0, 27.5, 29.9, 29.99], ids=lambda x: f"BMI {x} -> Overweight")
    def test_bmi_overweight(self, bmi: float):
        """Cálculo de clasificación BMI para Overweight."""
        assert self.health_calc.bmi_classification(bmi) == "Overweight"

    @pytest.mark.parametrize("bmi", [30.0, 35.0, 50.0], ids=lambda x: f"BMI {x} -> Obesity")
    def test_bmi_obesity(self, bmi: float):
        """Cálculo de clasificación BMI para Obesity."""
        assert self.health_calc.bmi_classification(bmi) == "Obesity"

    # --- Tests de Límites e Invalidación ---

    @pytest.mark.parametrize("bmi", [-50.0, -1.0, -0.01], ids=lambda x: f"BMI negativo: {x}")
    def test_bmi_classification_minimo_imposible(self, bmi: float):
        """Lanzar excepción cuando el BMI es negativo."""
        with pytest.raises(InvalidHealthDataException):
            self.health_calc.bmi_classification(bmi)

    @pytest.mark.parametrize("bmi", [150.1, 200.0, 500.0], ids=lambda x: f"BMI máximo extremo: {x}")
    def test_bmi_classification_maximo_imposible(self, bmi: float):
        """Lanzar excepción cuando el BMI es extremadamente alto."""
        with pytest.raises(InvalidHealthDataException):
            self.health_calc.bmi_classification(bmi)