package healthcalc;



public class MAPCalculator {

	public double calcular(double sistolica, double diastolica) {
		double map = ((sistolica + 2 * diastolica)/ 3.0);
		return map;
		
	}

	public String clasificar(double map) {
		String clasif = "";
		if (map < 70) {
			
			clasif = "Baja";
			
		} else if (map <= 105) {
			clasif = "Normal";
			
		} else {
			
			clasif = "Alta";
		}
		return clasif;
	}
}