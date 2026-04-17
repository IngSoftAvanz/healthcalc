package healthcalc;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HealthCalcControlador {

	private HealthCalcView vista;
	private BMICalculator bmiCalc;
	private IBWCalculator ibwCalc;
	private MAPCalculator mapCalc;

	public HealthCalcControlador(HealthCalcView vista) {
		this.vista = vista;
		this.bmiCalc = new BMICalculator();
		this.ibwCalc = new IBWCalculator();
		this.mapCalc = new MAPCalculator();

		iniciarListeners();
	}

	private void iniciarListeners() {
		
		vista.getBtnCalcularBMI().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vista.getLblErrorBMI().setText("");
				vista.getLblResultadoBMI().setText("Resultado: ---");
				vista.getLblClasificacionBMI().setText("Clasificacion: ---");
				
				try {
					String pesoStr = vista.getTxtPeso().getText().trim();
					String altStr = vista.getTxtAltura().getText().trim();
					
					if (pesoStr.isEmpty() || altStr.isEmpty()) {
		                vista.getLblErrorBMI().setText("Error: debe introducir peso y altura.");
		                return;
		            }
					
					double peso = Double.parseDouble(pesoStr);
		            double alturaCm = Double.parseDouble(altStr);
		            
		            if (peso <= 0 || alturaCm <= 0) {
                        vista.getLblErrorBMI().setText("Error: los valores deben ser mayores a cero.");
                        return;
                    }
		            
		            //convertir a metros
		            double alturaM = alturaCm / 100.0;
		            
		            //cálculo
		            double bmi = bmiCalc.calcular(peso, alturaM);
		            vista.getLblResultadoBMI().setText("Resultado: " + String.format("%.2f", bmi));
		            //clasificación
		            String clasif = bmiCalc.clasificar(bmi);
		            vista.getLblClasificacionBMI().setText("Clasificación: " + clasif);
				}
				catch (NumberFormatException ex) {
					vista.getLblErrorBMI().setText("Error: ingrese valores numéricos válidos.");
				}
			}
		});

		vista.getBtnCalcularIBW().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				vista.getLblErrorIBW().setText("");
				vista.getLblResultadoIBW().setText("Resultado: ---");

				try {
					String altStr = vista.getTxtAlturaIBW().getText().trim();
					
					if (altStr.isEmpty()) {
						vista.getLblErrorIBW().setText("Error: debe introducir la altura.");
						return;
					}

					int altura = Integer.parseInt(altStr);
					
					if (altura <= 0) {
						vista.getLblErrorIBW().setText("Error: la altura debe ser mayor a cero.");
						return;
					}

					String sexoSeleccionado = (String) vista.getComboSexo().getSelectedItem();
					String sexo = sexoSeleccionado.toLowerCase();

					double ibw = ibwCalc.calcularIBW(altura, sexo);
					
					vista.getLblResultadoIBW().setText("Resultado: " + String.format("%.2f", ibw) + " kg");

				} 
				catch (NumberFormatException ex) {
					vista.getLblErrorIBW().setText("Error: ingrese una altura válida.");
				}
			}
		});
		
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
