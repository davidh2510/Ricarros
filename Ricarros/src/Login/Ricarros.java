package Login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;

public class Ricarros extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args,String user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ricarros frame = new Ricarros(user);
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
	public Ricarros(String user) {
		// Configuración del frame principal
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		// Botón para ir a la pantalla de venta de coches
		JButton Vende = new JButton("Vende tu carro");
		Vende.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		Vende.setBounds(262, 228, 155, 36);
		contentPane.add(Vende);
		// Manejadores de eventos para los botones
		Vende.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vender.main(null,user);
				dispose();
			}});
		
		JButton compra = new JButton("Compra tu carro");
		compra.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		compra.setBounds(484, 228, 155, 36);
		contentPane.add(compra);
		compra.addActionListener(new ActionListener() {
			// Acción cuando se hace clic en el botón "Vende tu carro"
			public void actionPerformed(ActionEvent e) {
				Comprar.main(null,user);
				dispose();
			}});
		
		JButton perfil = new JButton("Perfil");
		perfil.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		perfil.setBounds(673, 479, 155, 36);
		contentPane.add(perfil);
		perfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Perfil.main(null,user);
				dispose();
			}});
		
		JButton cerrarsesion = new JButton("Cerrar sesión");
		cerrarsesion.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		cerrarsesion.setBounds(40, 479, 155, 36);
		contentPane.add(cerrarsesion);
		cerrarsesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inicio.main(null);
				dispose();
			}});
		
		JButton btnNewButton_4 = new JButton("Alquila tu carro");
		btnNewButton_4.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		btnNewButton_4.setBounds(262, 273, 155, 36);
		contentPane.add(btnNewButton_4);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Alquiler.main(null,user);
				dispose();
			}});
		
		JLabel logo = new JLabel("");
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setForeground(new Color(0, 0, 0));
		logo.setBackground(new Color(240, 240, 240));
		ImageIcon img=new ImageIcon("ricarros2.png");
		logo.setIcon(img);
		logo.setBounds(349, 10, 190, 190);
		contentPane.add(logo);
	}
}
