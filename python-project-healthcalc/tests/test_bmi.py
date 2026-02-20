import pytest
from healthcalc.health_calc_impl import HealthCalcImpl
from healthcalc.exceptions import InvalidHealthDataException


class TestBMI:

    @pytest.fixture(autouse=True)  # Equivalente a @BeforeEach en JUnit
    def set_up(self):
        """Se ejecuta antes de cada test."""
        self.health_calc = HealthCalcImpl()

    def test_body_mass_index_valido(self):
        """Cálculo de BMI con valores estándar válidos"""
        weight = 70.0
        height = 1.75
        expected_bmi = 70.0 / (1.75 ** 2)

        result = self.health_calc.body_mass_index(weight, height)

        # pytest.approx es el equivalente a assertEquals con delta (0.01) en JUnit
        assert result == pytest.approx(expected_bmi, abs=0.01)

    def test_body_mass_index_peso_cero(self):
        """Lanzar excepción cuando el peso es cero"""
        weight = 0
        height = 1.70

        with pytest.raises(InvalidHealthDataException):
            self.health_calc.body_mass_index(weight, height)

    def test_body_mass_index_altura_cero(self):
        """Lanzar excepción cuando la altura es cero"""
        with pytest.raises(InvalidHealthDataException):
            self.health_calc.body_mass_index(70, 0)

    def test_body_mass_index_negativos(self):
        """Lanzar excepción cuando los valores son negativos (Equivalente a assertAll)"""
        weight = -70
        height = 1.70

        with pytest.raises(InvalidHealthDataException):
            self.health_calc.body_mass_index(weight, height)

        weight = -70
        height = 1.70
        with pytest.raises(InvalidHealthDataException):
            self.health_calc.body_mass_index(weight, height)

        weight = 70
        height = -1.70
        with pytest.raises(InvalidHealthDataException):
            self.health_calc.body_mass_index(weight, height)

    # --- Tests de Límites e Invalidación ---

    @pytest.mark.parametrize("weight", [-10.0, 0.0, 0.99], ids=lambda x: f"Peso mínimo inválido: {x}kg")
    def test_peso_minimo_imposible(self, weight: float):
        """Lanzar excepción cuando el peso es negativo o menor que 1kg."""
        height = 1.70

        with pytest.raises(InvalidHealthDataException):
            self.health_calc.body_mass_index(weight, height)

    @pytest.mark.parametrize("weight", [700.1, 1000.0, 5000.0], ids=lambda x: f"Peso máximo inválido: {x}kg")
    def test_peso_maximo_imposible(self, weight: float):
        """Lanzar excepción cuando el peso es extremadamente alto."""
        height = 1.70

        with pytest.raises(InvalidHealthDataException):
            self.health_calc.body_mass_index(weight, height)

    @pytest.mark.parametrize("height", [-0.50, 0.0, 0.29], ids=lambda x: f"Altura mínima inválida: {x}m")
    def test_altura_minima_imposible(self, height: float):
        """Lanzar excepción cuando la altura es negativa o menor que 30cm."""
        weight = 70

        with pytest.raises(InvalidHealthDataException):
            self.health_calc.body_mass_index(weight, height)

    @pytest.mark.parametrize("height", [3.01, 3.50, 5.00], ids=lambda x: f"Altura máxima inválida: {x}m")
    def test_altura_maximo_imposible(self, height: float):
        """Lanzar excepción cuando la altura es extremadamente alta."""
        weight = 70
        
        with pytest.raises(InvalidHealthDataException):
            self.health_calc.body_mass_index(weight, height)