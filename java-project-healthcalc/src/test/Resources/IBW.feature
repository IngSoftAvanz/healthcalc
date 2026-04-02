Feature: Cálculo del IBW de un número
  Como usuario ede la aplicación HealthCalc
  Quiero calcular El Ideal Body Weight (IBW) de una persona basándome en su altura y género
  Para obtener información de mi salud.

  Background:
    Given la calculadora de salud debe estar iniciada
    And el usuario debe haber seleccionado la métrica de cálculo de IBW

  @HighPriority
  Scenario Outline: Verificar cálculos exitosos estándar
    Given la altura de la persona es <altura> cm
    And el género de la persona es <género>
    When ejecuto la operación de cálculo de IBW
    Then el resultado debe ser <resultado_esperado> kg

    Examples:
      | altura | género | resultado_esperado |
      | 170    | masculino | 70.0     |
      | 160    | femenino  | 55.0     |
      | 180    | masculino | 80.0     |
      | 165    | femenino  | 60.0     |

  @EdgeCase
  Scenario Outline: Cálculo del IBW en los límites biológicos
    Given el usuario ingresa una altura de <altura> cm
    And el género de la persona es <género>
    When ejecuto la operación de cálculo de IBW
    Then el resultado debe ser <resultado_esperado> kg

    Examples:
      | altura | género | resultado_esperado |
      | 50     | masculino | 0.0      |
      | 50     | femenino  | 0.0      |

  @ErrorHandling @InvalidHeight 
  Scenario Outline: Intento de cálculo con altura inválida
    Given la altura de la persona es <altura> cm inválida
    And el género de la persona es <género> válido
    When ejecuto la operación de cálculo de IBW
    Then el sistema debe lanzar una excepción

  Examples:
      | altura | género |
      | -10    | masculino |
      | 300    | femenino  |

    @ErrorHandling @InvalidGender
    Scenario Outline: Intento de cálculo con género inválido
    Given la altura de la persona es <altura> cm válida
    And el género de la persona es <género> inválido
    When ejecuto la operación de cálculo de IBW
    Then el sistema debe lanzar una excepción

    Examples:
        | altura | género |
        | 170    | otro   |
        | 160    | 12 |
        | 180    | null    |
        | 165    | ""      |
