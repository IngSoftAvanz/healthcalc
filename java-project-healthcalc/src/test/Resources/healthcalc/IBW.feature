Feature: Cálculo del IBW de un número
  Como usuario ede la aplicación HealthCalc
  Quiero calcular El Ideal Body Weight (IBW) de una persona basándome en su altura y género
  Para obtener información de mi salud.

  Background:
    Given la calculadora de salud está iniciada
    And el usuario debe haber seleccionado la métrica de cálculo de IBW

  @HighPriority
  Scenario Outline: Verificar cálculos exitosos estándar
    Given el usuario ingresa una altura de <altura> cm
    And el género de la persona es <genero>
    When ejecuto la operación de cálculo de IBW
    Then el resultado debe ser <resultado_esperado> kg

    Examples:
      | altura | género | resultado_esperado |
      | 170    | m | 70.0     |
      | 160    | f | 55.0     |
      | 180    | m | 80.0     |
      | 165    | f | 60.0     |

  @EdgeCase
  Scenario Outline: Cálculo del IBW en los límites biológicos
    Given el usuario ingresa una altura de <altura> cm
    And el género de la persona es <genero>
    When ejecuto la operación de cálculo de IBW
    Then el resultado debe ser <resultado_esperado> kg

    Examples:
      | altura | género | resultado_esperado |
      | 50     | m | 0.0      |
      | 50     | f | 0.0      |

  @ErrorHandling @InvalidHeight 
  Scenario Outline: Intento de cálculo con altura inválida
    Given el usuario ingresa una altura de <altura> cm inválida
    And el género de la persona es <genero> válido
    When ejecuto la operación de cálculo de IBW
    Then el sistema debe lanzar una excepción

  Examples:
      | altura | género |
      | -10    | m |
      | 300    | f |

    @ErrorHandling @InvalidGender
    Scenario Outline: Intento de cálculo con género inválido
    Given el usuario ingresa una altura de <altura> cm válida
    And el género de la persona es <genero> inválido
    When ejecuto la operación de cálculo de IBW
    Then el sistema debe lanzar una excepción

    Examples:
        | altura | género |
        | 170    | r   |
        | 160    | p |
        | 180    | c    |
        | 165    | g      |
