package miConversor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import miConversor.miConversor.Moneda;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class miConversor {

	private JFrame frame;
	private JTextField txt;
	private JButton btn;
	private JComboBox cmb;
	private JLabel lbl;
	
	public enum Moneda{
		pesos_dolar,
		pesos_euro,
		pesos_libra,
		dolar_pesos,
		euro_pesos,
		libra_pesos,
	}
	
	public double dolar = 4.118;
	public double euro = 4.477;
	public double libra = 5.244;
	
	public double valorInput = 0.00;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					miConversor window = new miConversor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public miConversor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextField txt = new JTextField();
		txt.setBounds(10, 11, 86, 20);
		frame.getContentPane().add(txt);
		txt.setColumns(10);
		
		cmb = new JComboBox<Moneda>();
		cmb.setModel(new DefaultComboBoxModel<>(Moneda.values()));
		cmb.setBounds(10, 42, 106, 22);
		frame.getContentPane().add(cmb);
		
		//evento boton
		btn = new JButton("Convertir");
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Convertir();
			}
		});
		btn.setBounds(161, 42, 89, 23);
		frame.getContentPane().add(btn);
		
		lbl = new JLabel("00.00");
		lbl.setBounds(182, 14, 86, 14);
		frame.getContentPane().add(lbl);
	}
	
	public void Convertir() {
		if(Validar(txt.getText())) {
			Moneda moneda = (Moneda)cmb.getSelectedItem();
			
			switch (moneda) {
			
			case pesos_dolar:
				PesosAMoneda(dolar);
				break;
			case pesos_euro:
				PesosAMoneda(euro);
				break;
			case pesos_libra:
				PesosAMoneda(libra);
				break;
			case dolar_pesos:
				MonedaAPesos(dolar);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + moneda);
			
		}
		
	}
  }
	
	public void PesosAMoneda(double moneda) {
		double res = valorInput / moneda;
		lbl.setText(Redondear(res));
	}
	public void MonedaAPesos(double moneda) {
		double res = valorInput * moneda;
		lbl.setText(Redondear(res));
	}
	
	public String Redondear(double valor) {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(valor);
	}
	
	public boolean Validar(String texto) {
		try  {
			double x = Double.parseDouble(texto);
			if (x > 0);
			valorInput = x;
			return true;
		}catch(NumberFormatException e) {
			lbl.setText("Solamente números !!");
			return false;
		}
	}
}


