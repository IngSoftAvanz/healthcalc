package healthcalc.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import healthcalc.HealthCalcImpl;
import healthcalc.exceptions.InvalidHealthDataException;

public class IBWSteps {

    private HealthCalcImpl calculadora = new HealthCalcImpl();
    private String sexo;
    private int altura;
    private double resultadoIBW;
    private boolean errorLanzado;

    @Dado("la calculadora de IBW está iniciada")
    public void la_calculadora_de_IBW_está_iniciada() {
        calculadora = new HealthCalcImpl();
    }
    
    @Dado("una altura de {int} cm")
    public void la_altura_es_cm(int altura) {
        this.altura = altura;
    }

    @Y("un sexo de {string}")
    public void el_sexo_es(String sexo) {
        this.sexo = sexo;
    }
    
    @Cuando("solicito calcular el IBW")
    public void ejecuto_la_operación_de_IBW() {
        try {
            resultadoIBW = calculadora.ibw(altura, sexo);
            errorLanzado = false;
        } catch (Exception e) {
            errorLanzado = true;
        }
    }

    @Entonces("el sistema muestra el valor {double} kg")
    public void verificarIBW(double ibwEsperado) {
        assertEquals(ibwEsperado, resultadoIBW, 0.01);
    }

    @Entonces("el sistema informa que los valores no son los adecuados")
    public void verificarError() {
        assertEquals(true, errorLanzado);
    }

}