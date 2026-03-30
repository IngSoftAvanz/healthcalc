from behave import given, when, then
from healthcalc.health_calc_impl import HealthCalcImpl
from healthcalc.exceptions import InvalidHealthDataException


@given('que el sistema HealthCalc está operativo')
def step_sistema_operativo(context):
    context.calc = HealthCalcImpl()


@given('que el peso es {peso:d} kg')
def step_peso(context, peso):
    context.peso = peso


@given('que la altura es {altura:d} cm')
def step_altura(context, altura):
    context.altura = altura


@when('el sistema calcula el índice de masa corporal')
def step_calcular_bmi(context):
    try:
        context.resultado = context.calc.bmi(context.peso, context.altura)
        context.excepcion = None
    except Exception as e:
        context.resultado = None
        context.excepcion = e


@then('el resultado debe coincidir con la fórmula del BMI')
def step_resultado_bmi(context):
    altura_metros = context.altura / 100
    esperado = context.peso / (altura_metros ** 2)
    assert abs(context.resultado - esperado) < 0.01


@then('el sistema debe lanzar la excepción "InvalidHealthDataException"')
def step_excepcion(context):
    assert isinstance(context.excepcion, InvalidHealthDataException)