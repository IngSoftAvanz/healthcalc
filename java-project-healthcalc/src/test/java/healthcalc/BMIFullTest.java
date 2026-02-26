package healthcalc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import healthcalc.exceptions.InvalidHealthDataException;

@DisplayName("Tests BMI versión FULL")
public class BMIFullTest {
    private HealthCalc healthCalc;

    @BeforeEach
	void setUp() {
		healthCalc = new HealthCalcImpl();
	}

    @Nested
    @DisplayName("Clasificación BMI - Versión FULL")
    class BMIClassificationFullTests {
        @ParameterizedTest(name = "BMI {0} debe ser clasificado como Severe thinness")
        @ValueSource(doubles = {10.0, 15.5, 15.9, 15.99})
        @DisplayName("Validación de categoría Severe thinness (Delgadez severa)")
        void testSevereThinness(double bmi) throws InvalidHealthDataException {
            String expected = "Severe thinness";
            
            String result = healthCalc.bmiClassification(bmi);

            assertEquals(expected, result);
        }

        @ParameterizedTest(name = "BMI {0} debe ser clasificado como Moderate thinness")
        @ValueSource(doubles = {16.0, 16.5, 16.9, 16.99})
        @DisplayName("Validación de categoría Moderate thinness (Delgadez moderada)")
        void testModerateThinness(double bmi) throws InvalidHealthDataException {
            String expected = "Moderate thinness";
            
            String result = healthCalc.bmiClassification(bmi);

            assertEquals(expected, result);
        }

        @ParameterizedTest(name = "BMI {0} debe ser clasificado como Mild thinness")
        @ValueSource(doubles = {17.0, 17.5, 18.4, 18.49})
        @DisplayName("Validación de categoría Mild thinness (Delgadez leve)")
        void testMildThinness(double bmi) throws InvalidHealthDataException {
            String expected = "Mild thinness";
            
            String result = healthCalc.bmiClassification(bmi);

            assertEquals(expected, result);
        }

        @ParameterizedTest(name = "BMI {0} debe ser clasificado como Normal weight")
        @ValueSource(doubles = {18.5, 20.0, 24.9, 24.99})
        @DisplayName("Validación de categoría Normal weight (Peso saludable)")
        void testNormalweight(double bmi) throws InvalidHealthDataException {
            String expected = "Normal weight";
            
            String result = healthCalc.bmiClassification(bmi);

            assertEquals(expected, result);
        }

        @ParameterizedTest(name = "BMI {0} debe ser clasificado como Overweight")
        @ValueSource(doubles = {25.0, 27.5, 29.9, 29.99})
        @DisplayName("Validación de categoría Overweight (Sobrepeso)")
        void testOverweight(double bmi) throws InvalidHealthDataException {
            String expected = "Overweight";

            String result = healthCalc.bmiClassification(bmi);

            assertEquals(expected, result);
        }

        @ParameterizedTest(name = "BMI {0} debe ser clasificado como Obese Class I (Moderate)")
        @ValueSource(doubles = {30.0, 32.5, 34.9, 34.99})
        @DisplayName("Validación de categoría Obese Class I (Moderate) (Obesidad moderada)")
        void testObeseClassI(double bmi) throws InvalidHealthDataException {
            String expected = "Obese Class I (Moderate)";

            String result = healthCalc.bmiClassification(bmi);

            assertEquals(expected, result);
        }
        
        @ParameterizedTest(name = "BMI {0} debe ser clasificado como Obese Class II (Severe)")
        @ValueSource(doubles = {35.0, 37.0, 39.9, 39.99})
        @DisplayName("Validación de categoría Obese Class II (Severe) (Obesidad severa)")
        void testObeseClassII(double bmi) throws InvalidHealthDataException {
            String expected = "Obese Class II (Severe)";

            String result = healthCalc.bmiClassification(bmi);

            assertEquals(expected, result);
        }

        @ParameterizedTest(name = "BMI {0} debe ser clasificado como Obese Class III (Morbid)")
        @ValueSource(doubles = {40.0, 45.0, 150.0})
        @DisplayName("Validación de categoría Obese Class III (Morbid) (Obesidad mórbida)")
        void testObeseClassIII(double bmi) throws InvalidHealthDataException {
            String expected = "Obese Class III (Morbid)";

            String result = healthCalc.bmiClassification(bmi);

            assertEquals(expected, result);
        }

        @ParameterizedTest(name = "BMI mínimo inválido: {0}")
        @ValueSource(doubles = {-50.0, -1.0, -0.01})
        @DisplayName("Bloqueo de valores de BMI negativos (Error de entrada)")
        void testMinimoImposible(double bmi) {
            assertThrows(InvalidHealthDataException.class, () -> healthCalc.bmiClassification(bmi));
        }

        @ParameterizedTest(name = "BMI máximo extremo: {0}")
        @ValueSource(doubles = {150.1, 200.0, 500.0})
        @DisplayName("Bloqueo de valores de BMI superiores al límite humano razonable (150)")
        void testMaximoImposible(double bmi) {
            assertThrows(InvalidHealthDataException.class, () -> healthCalc.bmiClassification(bmi));
        }
    }
}
