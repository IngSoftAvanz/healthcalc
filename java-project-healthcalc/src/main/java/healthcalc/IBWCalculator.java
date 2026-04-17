package healthcalc;

public class IBWCalculator {
    public double calcularIBW(int height, String gender){
        double weight = 0.0;
        if ("hombre".equals(gender)) {
	        weight = (height - 100) - ((height - 150) / 4.0);
        } else {
	        weight = (height - 100) - ((height - 150) / 2.5);
        }
        return weight;
	}
}