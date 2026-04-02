Feature: Cálculo del Índice de Masa Corporal (BMI)
  Como usuario de la aplicación HealthCalc
  Quiero que el sistema clasifique mi Índice de Masa Corporal (BMI) en categorías clínicas estándar
  Para obtener información clínica precisa sobre mi estado de salud

  Background:
    Given la calculadora de salud está iniciada
    And el usuario ha seleccionado la métrica de BMI para calcular

  @HighPriority
  Scenario Outline: Clasificación exitosa de los rangos de peso
    Given el sistema recibe un valor de BMI de <bmi>
    When el sistema intenta clasificar el valor
    Then el resultado debe ser <resultado_esperado>

    Examples:
      | bmi   | resultado_esperado   |
      | 15.0  | "Severe thinness"    |
      | 16.0  | "Moderate thinness"  |
      | 17.5  | "Mild  thinness"     |
      | 20.0  | "Normal weight"      |
      | 27.0  | "Overweight"         |
      | 32.0  | "Obese Class I"      |
      | 37.0  | "Obese Class II"     |
      | 42.0  | "Obese Class III"    |

  @EdgeCase
  Scenario Outline: Clasificación de valores de BMI en los límites de las categorías
    Given el sistema recibe un valor de BMI de <bmi>
    When el sistema intenta clasificar el valor
    Then el resultado debe ser <resultado_esperado>

    Examples:
      | bmi   | resultado_esperado   |
      | 16.0  | "Severe thinness"    |
      | 17.0  | "Moderate thinness"  |
      | 18.5  | "Mild  thinness"     |
      | 25.0  | "Normal weight"      |
      | 30.0  | "Overweight"         |
      | 35.0  | "Obese Class I"      |
      | 40.0  | "Obese Class II"     |
      | 45.0  | "Obese Class III"    |

  @ErrorHandling @InvalidBMI
  Scenario Outline: Intento de clasificación con un valor de BMI inválido o fuera de rango
    Given el sistema recibe un valor de BMI de <bmi_invalido>
    When el sistema intenta clasificar el valor
    Then el sistema debe lanzar una excepción de datos de salud inválido

    Examples:
      | bmi_invalido|
      | -1.0         |
      | 100000       |
      | 0            |