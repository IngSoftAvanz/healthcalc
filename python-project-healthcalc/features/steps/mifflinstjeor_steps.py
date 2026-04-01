from behave import given, when, then
from healthcalc.health_calc_impl import HealthCalcImpl
from healthcalc.exceptions import InvalidHealthDataException

@given('que el peso es {peso:f} kg')
def step_peso(context, peso):
    context.peso = peso


@given('que la altura es {altura:f} cm')
def step_altura(context, altura):
    context.altura = altura


@given('que la edad es {edad:d} años')
def step_edad(context, edad):
    context.edad = edad




@when('el sistema calcula el metabolismo basal con Mifflin-St Jeor')
def step_calcular(context):
    try:
        context.resultado = context.calc.bmr(
            context.peso,
            context.altura,
            context.edad,
            context.sexo
        )
        context.excepcion = None
    except Exception as e:
        context.resultado = None
        context.excepcion = e


@then('el resultado debe coincidir con la fórmula de Mifflin-St Jeor')
def step_resultado(context):
    if context.sexo == "male":
        esperado = 10 * context.peso + 6.25 * context.altura - 5 * context.edad + 5
    else:
        esperado = 10 * context.peso + 6.25 * context.altura - 5 * context.edad - 161

    assert abs(context.resultado - esperado) < 0.01


@then('el sistema debe lanzar la excepción "InvalidHealthDataException"')
def step_error(context):
    assert isinstance(context.excepcion, InvalidHealthDataException)
