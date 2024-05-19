package Login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Color;

public class Vender extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMarca;
	private JTextField txtModelo;
	private JTextField txtKm;
	private JTextField txtPrecio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args,String user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vender frame = new Vender(user);
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
	public Vender(String user) {
		String com[] = {"Gasolina", "diesel", "electrico", "hibrido"};
		String tipo[]= {"Alquiler","Compra"};
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		// Etiqueta de título
		JLabel lblVender = new JLabel("Vende tu carro");
		lblVender.setForeground(new Color(255, 255, 255));
		lblVender.setHorizontalAlignment(SwingConstants.CENTER);
		lblVender.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblVender.setBounds(362, 48, 165, 22);
		contentPane.add(lblVender);
		// Campos de texto y etiquetas para la información del carro
		JLabel lblMarca = new JLabel("Marca");
		// (siguen más definiciones similares para los demás campos)
		lblMarca.setForeground(new Color(255, 255, 255));
		lblMarca.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblMarca.setHorizontalAlignment(SwingConstants.CENTER);
		lblMarca.setBounds(49, 99, 109, 27);
		contentPane.add(lblMarca);
		
		txtMarca = new JTextField();
		txtMarca.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		txtMarca.setBounds(198, 99, 99, 27);
		contentPane.add(txtMarca);
		txtMarca.setColumns(10);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setForeground(new Color(255, 255, 255));
		lblModelo.setHorizontalAlignment(SwingConstants.CENTER);
		lblModelo.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblModelo.setBounds(49, 134, 109, 27);
		contentPane.add(lblModelo);
		
		txtModelo = new JTextField();
		txtModelo.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		txtModelo.setBounds(198, 134, 99, 27);
		contentPane.add(txtModelo);
		txtModelo.setColumns(10);
		
		JLabel lblCombustible = new JLabel("Combustible");
		lblCombustible.setForeground(new Color(255, 255, 255));
		lblCombustible.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblCombustible.setHorizontalAlignment(SwingConstants.CENTER);
		lblCombustible.setBounds(49, 171, 109, 27);
		contentPane.add(lblCombustible);
		
		JLabel lblKm = new JLabel("Km");
		lblKm.setForeground(new Color(255, 255, 255));
		lblKm.setHorizontalAlignment(SwingConstants.CENTER);
		lblKm.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblKm.setBounds(49, 248, 109, 27);
		contentPane.add(lblKm);
		
		txtKm = new JTextField();
		txtKm.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		txtKm.setBounds(198, 248, 99, 27);
		contentPane.add(txtKm);
		txtKm.setColumns(10);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setForeground(new Color(255, 255, 255));
		lblPrecio.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecio.setBounds(49, 285, 109, 27);
		contentPane.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		txtPrecio.setBounds(198, 285, 99, 27);
		contentPane.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		JComboBox<String> BoxCombustible = new JComboBox(com);
		BoxCombustible.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		BoxCombustible.setBounds(198, 171, 99, 27);
		contentPane.add(BoxCombustible);
		String combustible=(String) BoxCombustible.getSelectedItem();
		
		
		JButton Volver = new JButton("Menú");
		Volver.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		Volver.setBounds(664, 399, 155, 31);
		contentPane.add(Volver);
		 // Manejadores de eventos para el botón y otros componentes
		Volver.addActionListener(new ActionListener() {
			 // Acción cuando se hace clic en el botón "Menú"
			public void actionPerformed(ActionEvent e) {
				Ricarros.main(null,user);
				dispose();
			}});
		
		JLabel logo = new JLabel("");
		ImageIcon img=new ImageIcon("ricarros2.png");
		logo.setIcon(img);
		logo.setBounds(583, 48, 190, 190);
		contentPane.add(logo);
		
		JComboBox<String> Boxtipo = new JComboBox(tipo);
		Boxtipo.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		Boxtipo.setBounds(198, 211, 99, 27);
		contentPane.add(Boxtipo);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipo.setForeground(Color.WHITE);
		lblTipo.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblTipo.setBounds(49, 211, 109, 27);
		contentPane.add(lblTipo);
		
		
		JButton Añadir = new JButton("Añadir carro");
		Añadir.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		Añadir.setBounds(49, 401, 155, 31);
		contentPane.add(Añadir);
		// Manejador de evento para el botón "Añadir carro"
		Añadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtMarca.getText().isBlank()) {
						JOptionPane.showMessageDialog(null, "Marca no valida", "Error", JOptionPane.ERROR_MESSAGE);
					}else if(txtModelo.getText().isBlank()){
						JOptionPane.showMessageDialog(null, "Modelo no valida", "Error", JOptionPane.ERROR_MESSAGE);
					}else if(txtPrecio.getText().isBlank()||Integer.parseInt(txtPrecio.getText())<=0){
						JOptionPane.showMessageDialog(null, "Precio no valido", "Error", JOptionPane.ERROR_MESSAGE);
					}else if(txtKm.getText().isBlank()||Integer.parseInt(txtKm.getText())<0){
						JOptionPane.showMessageDialog(null, "Kilometros no validos", "Error", JOptionPane.ERROR_MESSAGE);
					}else {
					Connection conn=null;
					try {
						 // Validación de los datos ingresados por el usuario
	                    // y posterior inserción en la base de datos
						conn=DriverManager.getConnection("jdbc:mariadb://localhost:3306/ricarros", "root","");
						conn.setAutoCommit(false);
						if(((String) Boxtipo.getSelectedItem()).equalsIgnoreCase("Alquiler")) {
							String sql="INSERT INTO coches Values (?,?,?,?,?,?,?,?)";
							PreparedStatement stm=conn.prepareStatement(sql);
							stm.setString(1, combustible);
							stm.setInt(2, Integer.parseInt(txtKm.getText()));
							stm.setBoolean(3, false);
							stm.setBoolean(4, true);
							stm.setInt(5, Integer.parseInt(txtPrecio.getText()));
							stm.setString(6, txtModelo.getText());
							stm.setString(7, txtMarca.getText());
							stm.setString(8, user);
							stm.executeUpdate();
							conn.commit();
						}else {
							String sql="INSERT INTO coches Values (?,?,?,?,?,?,?,?)";
							PreparedStatement stm=conn.prepareStatement(sql);
							stm.setString(1, combustible);
							stm.setInt(2, Integer.parseInt(txtKm.getText()));
							stm.setBoolean(3, true);
							stm.setBoolean(4, true);
							stm.setInt(5, Integer.parseInt(txtPrecio.getText()));
							stm.setString(6, txtModelo.getText());
							stm.setString(7, txtMarca.getText());
							stm.setString(8, user);
							stm.executeUpdate();
							conn.commit();
						} 
					}catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Ricarros.main(null,user);
					dispose();
			}}catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "No pongas letras en valores númericos porfavor :3", "Error", JOptionPane.ERROR_MESSAGE);
			}
			}});
		
		
	}
}
