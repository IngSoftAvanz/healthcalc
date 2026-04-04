# languaje: es
Característica: Cálculo del peso corporal ideal (IBW)

    Como usuario
    Quiero calcular el peso corporal inicial usando la fórmula de Lorentz a partir de mi sexo y mi altura
    Para conocer mi peso óptimo.


    Escenario: Calcular el IBW con valores correctos
        Dado un sexo de "hombre"
        Y una altura de "180" cm
        Cuando solicito calcular el IBW
        Entonces el sistema muestra el valor "72.5" kg

    Escenario: Error por sexo distinto a hombre o mujer
        Dado un sexo de "fluido"
        Y una altura de "180" cm
        Cuando solicito calcular el IBW
        Entonces el sistema informa de que los datos no son los adecuados

    Escenario: Error por valores negativos o iguales a cero
        Dado un sexo de "mujer"
        Y una altura de "0" cm
        Cuando solicito calcular el IBW
        Entonces el sistema informa de que los datos no son los adecuados

    Escenario: Error por valores fuera de los límites físicos
        Dado un sexo de "mujer"
        Y una altura de "750" cm
        Cuando solicito calcular el IBW
        Entonces el sistema informa de que los datos no son los adecuados