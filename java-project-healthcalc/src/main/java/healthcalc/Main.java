package healthcalc;


import java.awt.EventQueue;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HealthCalcView vista = new HealthCalcView();
					HealthCalcControlador controlador = new HealthCalcControlador(vista);
					vista.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
