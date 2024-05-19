package Login;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class Registro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField user;
	private JPasswordField password;
	private JTextField Telefono;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro frame = new Registro();
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
	public Registro() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		user = new JTextField();
		user.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		user.setToolTipText("Introduce el usuario");
		user.setBounds(453, 246, 126, 20);
		contentPane.add(user);
		user.setColumns(10);
		
		password = new JPasswordField();
		password.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		password.setToolTipText("Introduce la contraseña");
		password.setBounds(453, 279, 126, 20);
		contentPane.add(password);
		
		Telefono = new JTextField();
		Telefono.setToolTipText("Introduce el usuario");
		Telefono.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		Telefono.setColumns(10);
		Telefono.setBounds(453, 309, 126, 20);
		contentPane.add(Telefono);
		
		JLabel Usuario = new JLabel("Usuario");
		Usuario.setForeground(new Color(255, 255, 255));
		Usuario.setBackground(new Color(0, 0, 0));
		Usuario.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		Usuario.setHorizontalAlignment(SwingConstants.CENTER);
		Usuario.setBounds(317, 249, 94, 14);
		contentPane.add(Usuario);
		
		JLabel Contraseña = new JLabel("Contraseña");
		Contraseña.setForeground(new Color(255, 255, 255));
		Contraseña.setBackground(new Color(0, 0, 0));
		Contraseña.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		Contraseña.setHorizontalAlignment(SwingConstants.CENTER);
		Contraseña.setBounds(317, 281, 94, 14);
		contentPane.add(Contraseña);
		
		JLabel Telfono = new JLabel("Teléfono");
		Telfono.setForeground(new Color(255, 255, 255));
		Telfono.setBackground(new Color(0, 0, 0));
		Telfono.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		Telfono.setHorizontalAlignment(SwingConstants.CENTER);
		Telfono.setBounds(317, 314, 94, 14);
		contentPane.add(Telfono);
		
		JButton Entrar = new JButton("Registrarse");
		Entrar.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		Entrar.setBounds(394, 374, 134, 20);
		contentPane.add(Entrar);
		Entrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String contraseña=new String(password.getPassword());
				
				if(user.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Usuario no válido", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(contraseña.isBlank()) {
					JOptionPane.showMessageDialog(null, "Contraseña no válida", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(!Telefono.getText().matches("[679][0-9]{8}")) {
					JOptionPane.showMessageDialog(null, "Teléfono no válido", "Error", JOptionPane.ERROR_MESSAGE);
				} else
					try {
						if(Lista_Usuario.Validartel(user.getText())) {
							JOptionPane.showMessageDialog(null, "Usuario ya en uso", "Error", JOptionPane.ERROR_MESSAGE);
						}else{
							Connection conn=null;
							try {
								conn=DriverManager.getConnection("jdbc:mariadb://localhost:3306/ricarros", "root","");
								conn.setAutoCommit(false);
								String sql="INSERT INTO usuarios(Nombre,Contraseña,Telefono) VALUES (?,?,?)";
								PreparedStatement stm=conn.prepareStatement(sql);
								stm.setString(1, user.getText());
								stm.setString(2, contraseña);
								stm.setString(3, Telefono.getText());
								stm.executeUpdate();
								conn.commit();
								Solucion2.main(null);
								dispose();
							}catch(SQLException ex) {
								System.out.println(ex.getMessage());
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
			}
			
		});
		
		JButton Volver = new JButton("Volver");
		Volver.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		Volver.setBounds(394, 404, 134, 23);
		contentPane.add(Volver);
		
		JLabel logo = new JLabel("New label");
		ImageIcon img=new ImageIcon("ricarros2.png");
		logo.setIcon(img);
		logo.setBounds(349, 10, 190, 190);
		contentPane.add(logo);
		
		Volver.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				dispose();
				Inicio.main(null);
			}
		});
		
	}
}
