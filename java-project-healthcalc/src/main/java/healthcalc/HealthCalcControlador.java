package healthcalc;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HealthCalcControlador {

	private HealthCalcView vista;
	private MAPCalculator mapCalc;

	public HealthCalcControlador(HealthCalcView vista) {
		this.vista = vista;
		this.mapCalc = new MAPCalculator();

		iniciarListeners();
	}

	private void iniciarListeners() {

		
		vista.getBtnCalcularMAP().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				vista.getLblErrorMAP().setText("");
				vista.getLblResultadoMAP().setText("Resultado: ---");
				vista.getLblClasificacionMAP().setText("Clasificación: ---");
				
				
				
				//CU
				try {
					String sisStr = vista.getTxtSistolica().getText().trim();
					String diaStr = vista.getTxtDiastolica().getText().trim();
					if (sisStr.isEmpty() || diaStr.isEmpty()) {
						
						vista.getLblErrorMAP().setText("Error: debe introducir ambas presiones.");
						return;
					}
					
					double sistolica = Double.parseDouble(sisStr);
					double diastolica = Double.parseDouble(diaStr);
					if (sistolica <= 0 || diastolica <= 0) {
						vista.getLblErrorMAP().setText("Error: los valores deben ser mayores a cero.");
						return;
					}
					
					if (sistolica <= diastolica) {
						vista.getLblErrorMAP().setText("Error: la sistólica debe ser mayor que la diastólica.");
						
						return;
					}
					
					
					//normal
					
					//calculo
					double map = mapCalc.calcular(sistolica, diastolica);
					vista.getLblResultadoMAP().setText("Resultado: " + String.format("%.2f", map) + " mmHg");
					
					//clasif.
					String clasif = mapCalc.clasificar(map);
					vista.getLblClasificacionMAP().setText("Clasificación: " + clasif);
				} 
				catch (NumberFormatException ex) {
					
					vista.getLblErrorMAP().setText("Error: ingrese valores numéricos válidos.");
				}
			}
		});
	}
}