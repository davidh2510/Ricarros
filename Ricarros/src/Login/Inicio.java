package Login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import java.awt.Color;

public class Inicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
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
	public Inicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton Iniciarsesion = new JButton("Iniciar sesión");
		Iniciarsesion.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		Iniciarsesion.setBounds(374, 261, 136, 37);
		contentPane.add(Iniciarsesion);
		Iniciarsesion.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login.main(null);
			}
		});
		
		JButton Registrarse = new JButton("Registrarte");
		Registrarse.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		Registrarse.setBounds(374, 338, 136, 37);
		contentPane.add(Registrarse);
		Registrarse.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				dispose();
				Registro.main(null);
			}
		});
		
		JLabel logo = new JLabel("");
		ImageIcon img=new ImageIcon("ricarros2.png");
		logo.setIcon(img);
		logo.setBounds(349, 10, 190, 190);
		contentPane.add(logo);
	}

}
