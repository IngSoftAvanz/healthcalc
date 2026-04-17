package healthcalc;

public class BMICalculator {

    public double calcular(double peso, double altura) {
        double bmi = peso / (altura * altura);
        return bmi;
    }

    public String clasificar(double bmi) {
        if (bmi < 16.0) {
            return "Severe thinness";
        } else if (bmi < 17.0) {
            return "Moderate thinness";
        } else if (bmi < 18.5) {
            return "Mild thinness";
        } else if (bmi < 25.0) {
            return "Normal weight";
        } else if (bmi < 30.0) {
            return "Overweight";
        } else if (bmi < 35.0) {
            return "Obese Class I (Moderate)";
        } else if (bmi < 40.0) {
            return "Obese Class II (Severe)";
        } else {
            return "Obese Class III (Morbid)";
        }
    }
}
