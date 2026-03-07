package healthcalc;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        HealthCalc calc = new HealthCalcImpl();
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Introduce peso (kg): ");
            double p = sc.nextDouble();
            System.out.print("Introduce altura (m): ");
            double a = sc.nextDouble();
            double imc = calc.bmi(p, a);
            System.out.println("Tu IMC es: " + imc + " (" + calc.bmiClassification(imc) + ")");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}


