package healthcalc.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import java.awt.CardLayout;

public class Prueba extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prueba frame = new Prueba();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Prueba() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setToolTipText("");
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		tabbedPane.addTab("New tab", null, panel_1, null);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(10, 0, 353, 50);
		panel_1.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(10, 60, 353, 50);
		panel_1.add(textField_1);
		
		JButton btnNewButton = new JButton("Calcular");
		btnNewButton.setBounds(132, 121, 89, 23);
		panel_1.add(btnNewButton);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		tabbedPane.addTab("New tab", null, panel_1_1, null);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(10, 0, 353, 50);
		panel_1_1.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(10, 60, 353, 50);
		panel_1_1.add(textField_3);
		
		JButton btnNewButton_1 = new JButton("Calcular");
		btnNewButton_1.setBounds(132, 121, 89, 23);
		panel_1_1.add(btnNewButton_1);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setLayout(null);
		tabbedPane.addTab("New tab", null, panel_1_2, null);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(10, 0, 353, 50);
		panel_1_2.add(textField_4);
		
		JButton btnNewButton_2 = new JButton("Calcular");
		btnNewButton_2.setBounds(132, 121, 89, 23);
		panel_1_2.add(btnNewButton_2);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBounds(132, 73, 89, 37);
		panel_1_2.add(splitPane);
		
		JButton btnNewButton_4 = new JButton("H");
		splitPane.setLeftComponent(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("M");
		splitPane.setRightComponent(btnNewButton_5);

	}
}
