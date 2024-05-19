package Login;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField user;
	private JPasswordField password;
	private String contraseña;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		user = new JTextField();
		user.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		user.setToolTipText("Introduce el usuario");
		user.setBounds(464, 266, 126, 20);
		contentPane.add(user);
		user.setColumns(10);
		
		password = new JPasswordField();
		password.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		password.setToolTipText("Introduce la contraseña");
		password.setBounds(464, 316, 126, 20);
		contentPane.add(password);
		
		JLabel Usuario = new JLabel("Usuario");
		Usuario.setForeground(new Color(255, 255, 255));
		Usuario.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		Usuario.setHorizontalAlignment(SwingConstants.CENTER);
		Usuario.setBounds(326, 269, 96, 14);
		contentPane.add(Usuario);
		
		JLabel Contraseña = new JLabel("Contraseña");
		Contraseña.setForeground(new Color(255, 255, 255));
		Contraseña.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		Contraseña.setHorizontalAlignment(SwingConstants.CENTER);
		Contraseña.setBounds(326, 319, 96, 14);
		contentPane.add(Contraseña);
		
		JButton Entrar = new JButton("Entrar");
		Entrar.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		Entrar.setBounds(405, 374, 89, 23);
		contentPane.add(Entrar);
		Entrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				contraseña=new String(password.getPassword());
				
				if(user.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Usuario o contraseña erróneo", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(contraseña.isBlank()) {
					JOptionPane.showMessageDialog(null, "Usuario o contraseña erróneo", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					Connection conn=null;
					try {
						conn=DriverManager.getConnection("jdbc:mariadb://localhost:3306/ricarros", "root","");
						conn.setAutoCommit(false);
						String sql="SELECT * from usuarios where Nombre='"+user.getText()+"' and Contraseña='"+contraseña+"'";
						Statement stm=conn.createStatement();
						ResultSet rs=stm.executeQuery(sql);
						if(rs.next()) {
							Ricarros.main(null,user.getText());
							dispose();
						}else {
							JOptionPane.showMessageDialog(null, "Usuario o contraseña erróneo", "Error", JOptionPane.ERROR_MESSAGE);
						}
						conn.commit();
					}catch(SQLException ex) {
						System.out.println(ex.getMessage());
					}
				}
			}
			
		});
		
		JButton Volver = new JButton("Volver");
		Volver.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		Volver.setBounds(405, 424, 89, 23);
		contentPane.add(Volver);
		Volver.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				dispose();
				Inicio.main(null);
			}
		});
		
		JLabel logo = new JLabel("");
		ImageIcon img=new ImageIcon("ricarros2.png");
		logo.setIcon(img);
		logo.setBounds(349, 10, 190, 190);
		contentPane.add(logo);
	
		
	}
}
