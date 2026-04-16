package healthcalc;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HealthCalcControlador {

	private HealthCalcView vista;
	private IBWCalculator ibwCalc;

	public HealthCalcControlador(HealthCalcView vista) {
		this.vista = vista;
		this.ibwCalc = new IBWCalculator();

		iniciarListeners();
	}

	private void iniciarListeners() {

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
					vista.getLblErrorIBW().setText("Error: ingrese una altura numérica válida (entero).");
				}
			}
		});
	}
}
