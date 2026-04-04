package healthcalc; // Debe coincidir con la carpeta donde está este archivo

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
// 1. Apuntamos a la carpeta de resources donde está el .feature
@SelectPackages("healthcalc")
// 2. Apuntamos al paquete exacto donde está FactorialSteps.java
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "healthcalc.steps")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
public class RunCucumberTest { 
}