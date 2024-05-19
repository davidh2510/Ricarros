package Login;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class Alquiler extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args,String user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Alquiler frame = new Alquiler(user);
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
	public Alquiler(String user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Alquila tu carro");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(362, 48, 165, 22);
		contentPane.add(lblNewLabel);
		
		JLabel logo = new JLabel("");
		ImageIcon img=new ImageIcon("ricarros2.png");
		logo.setIcon(img);
		logo.setBounds(583, 48, 190, 190);
		contentPane.add(logo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 106, 517, 447);
		contentPane.add(scrollPane);
		String marcas[];
		try {
			marcas = GetMarcas(user);
			JComboBox<String> comboBox = new JComboBox(marcas);
			comboBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
			scrollPane.setColumnHeaderView(comboBox);
			comboBox.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	try {
	            		JPanel panel = new JPanel();
	        			scrollPane.setViewportView(panel);
	        			panel.setLayout(null);
						Coches(user,(String) comboBox.getSelectedItem(),panel,10);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            }
	        });
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JButton Volver = new JButton("Menú");
		Volver.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		Volver.setBounds(677, 455, 96, 31);
		contentPane.add(Volver);
		Volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ricarros.main(null,user);
				dispose();
			}});
	}

	private String[] GetMarcas(String user) throws SQLException {
		Connection conn=DriverManager.getConnection("jdbc:mariadb://localhost:3306/ricarros", "root","");
		conn.setAutoCommit(false);
		String sql="Select Distinct Marca from coches where Usuario!='"+user+"' and Alquilar_comprar=false";
		Statement stm=conn.createStatement();
		ResultSet rs=stm.executeQuery(sql);
		rs.last(); // Mover a la última fila
        int count = rs.getRow(); // Obtener el número de filas
        rs.beforeFirst(); // Volver antes de la primera fila
		String[] marcas=new String[count];
		int i=0;
		while (rs.next()) {
			marcas[i++] = rs.getString("Marca");
		}
		conn.close();
		return marcas;
	}
	
	public void Coches(String user, String accion,JPanel panel,int i) throws SQLException{
		final int espacio=22;//no puede ser menos de 13
		Connection conn;
		conn=DriverManager.getConnection("jdbc:mariadb://localhost:3306/ricarros", "root","");
		conn.setAutoCommit(false);
		String sql="Select * from coches where Marca='"+accion+"' and Usuario!='"+user+"' and Alquilar_Comprar=false";
		Statement stm=conn.createStatement();
		ResultSet rs=stm.executeQuery(sql);
		String alquiler="";
		while(rs.next()) {
			String seleccion=rs.getString("Modelo")+" "+rs.getString("Marca")+" "+rs.getInt("Precio")+" "+rs.getString("Usuario")+" "+rs.getString("Km");
			alquiler="";
			alquiler+=rs.getString("Modelo")+"  "+rs.getString("Marca")+"  "+rs.getInt("Precio")+"€  "+rs.getString("Km")+"km  "+rs.getString("Combustible")+"  "+rs.getString("Usuario");
			JLabel lblNewLabel_1 = new JLabel(alquiler);
			lblNewLabel_1.setBounds(10, i, 367, 20);
			panel.add(lblNewLabel_1);
			
			JButton comprar = new JButton("Alquilar");
			comprar.setBounds(398, i, 92, 20);
			panel.add(comprar);
			comprar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					comprar(seleccion,user);
					Eliminar(seleccion,user);
					Alquiler_realizado.main(null,user);
					dispose();
				}});
	
			i+=espacio;
		}
		conn.close();
	}
	private void comprar(String seleccion,String user) {
		Connection conn=null;
		String[] split= seleccion.split(" ");

		try {
			conn=DriverManager.getConnection("jdbc:mariadb://localhost:3306/ricarros", "root","");
			conn.setAutoCommit(false);
				String sql="INSERT INTO Alquiler Values (?,?,?,?,?,?)";
				PreparedStatement stm=conn.prepareStatement(sql);
				Date fechain=new Date(System.currentTimeMillis());
				Date fechafin=fechain.valueOf(LocalDate.now().plusMonths(1));
				stm.setString(1, split[0]);
				stm.setString(2, split[1]);
				stm.setDouble(6, Double.parseDouble(split[2]));
				stm.setDate(4, new Date(System.currentTimeMillis()));
				stm.setDate(5, fechafin);
				stm.setString(3, user);
				stm.executeUpdate();
				conn.commit();
		}catch(SQLException e1){
			// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	}
	
	public void Eliminar(String seleccion,String user) {
		Connection conn=null;
		String[] split= seleccion.split(" ");

		try {
			conn=DriverManager.getConnection("jdbc:mariadb://localhost:3306/ricarros", "root","");
			conn.setAutoCommit(false);
				String sql="Delete from coches where Usuario='"+split[3]+"' and Marca='"+split[1]+"' and Modelo='"+split[0]+"' and Alquilar_comprar=false";
				Statement stm=conn.createStatement();
				stm.execute(sql);
				conn.commit();
	}catch(SQLException e1){
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}
	
}