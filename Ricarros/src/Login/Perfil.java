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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;

public class Perfil extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args,String user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Perfil frame = new Perfil(user);
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
	public Perfil(String user) {
		Connection conn=null;
		try {
			conn=DriverManager.getConnection("jdbc:mariadb://localhost:3306/ricarros", "root","");
			conn.setAutoCommit(false);

			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 900, 600);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(0, 0, 0));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblNewLabel_1 = new JLabel("Nombre");
			lblNewLabel_1.setForeground(new Color(255, 255, 255));
			lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setBounds(328, 237, 81, 14);
			contentPane.add(lblNewLabel_1);
			
			JLabel lblNewLabel_3 = new JLabel(user);
			lblNewLabel_3.setForeground(new Color(255, 255, 255));
			lblNewLabel_3.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
			lblNewLabel_3.setBounds(509, 237, 178, 14);
			contentPane.add(lblNewLabel_3);
			
			JLabel logo = new JLabel("");
			ImageIcon img=new ImageIcon("ricarros2.png");
			logo.setIcon(img);
			logo.setBounds(349, 10, 190, 190);
			contentPane.add(logo);
			
			JButton Volver = new JButton("Menú");
			Volver.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
			Volver.setBounds(732, 28, 96, 31);
			contentPane.add(Volver);
			
			JScrollPane VENTA = new JScrollPane();
			VENTA.setBounds(30, 271, 379, 250);
			contentPane.add(VENTA);
			
			JLabel lblNewLabel = new JLabel("COCHES EN VENTAS");
			lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			VENTA.setColumnHeaderView(lblNewLabel);
			
			JPanel panel = new JPanel();
			VENTA.setViewportView(panel);
			panel.setLayout(null);
			Ventas_Alquiler(user,"Venta",panel,10);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(482, 271, 379, 250);
			contentPane.add(scrollPane);
			
			JLabel Compras = new JLabel("COMPRAS");
			Compras.setHorizontalAlignment(SwingConstants.CENTER);
			Compras.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
			scrollPane.setColumnHeaderView(Compras);
			
			JPanel panel_1 = new JPanel();
			scrollPane.setViewportView(panel_1);
			panel_1.setLayout(null);
			Ventas_Alquiler(user,"Alquiler",panel_1,10);
			
			
			
			Volver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Ricarros.main(null,user);
					dispose();
				}});
			
			
			
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void Ventas_Alquiler(String user, String accion,JPanel panel,int i) throws SQLException{
		final int espacio=15;//no puede ser menos de 13
		Connection conn;
		conn=DriverManager.getConnection("jdbc:mariadb://localhost:3306/ricarros", "root","");
		conn.setAutoCommit(false);
		if(accion.equalsIgnoreCase("Alquiler")) {
			String sql="Select * from alquiler where Nombre='"+user+"'";
			Statement stm=conn.createStatement();
			ResultSet rs=stm.executeQuery(sql);
			String alquiler="";
			while(rs.next()) {
				alquiler="";
				alquiler+=accion+"  "+ rs.getString("Modelo")+"  "+rs.getString("Marca")+"  "+rs.getInt("Pre")+"€  "+rs.getString("Fecha_Inicial")+"  "+rs.getString("Fecha_Final");
				JLabel Coches = new JLabel(alquiler);
				Coches.setBounds(10, i, 320, 13);
				panel.add(Coches);
				i+=espacio;
			}
			Ventas_Alquiler(user,"Compra",panel,i);
			
			
		}else if(accion.equalsIgnoreCase("Venta")){
			String sql="Select * from coches where usuario='"+user+"'";
			Statement stm=conn.createStatement();
			ResultSet rs=stm.executeQuery(sql);
			String alquiler="";
			while(rs.next()) {
				alquiler="";
				alquiler+=rs.getString("Marca")+"  "+rs.getString("Modelo")+"  "+rs.getInt("Precio")+"€  "+rs.getString("Km")+"km  "+rs.getString("Combustible")+"  "+((rs.getBoolean("Alquilar_Comprar")==false ? "Alquiler":"Compra"));
				JLabel Coches = new JLabel(alquiler);
				Coches.setBounds(10, i, 320, 13);
				panel.add(Coches);
				i+=espacio;
			}
			
		}else{
			String sql="Select * from compra where Nombre='"+user+"'";
			Statement stm=conn.createStatement();
			ResultSet rs=stm.executeQuery(sql);
			String alquiler="";
			while(rs.next()) {
				alquiler="";
				alquiler+=accion+"  "+rs.getString("Modelo")+" "+rs.getString("Marca")+" "+rs.getInt("Precio")+"€ "+rs.getString("Fecha");
				JLabel Coches = new JLabel(alquiler);
				Coches.setBounds(10, i, 320, 13);
				panel.add(Coches);
				i+=espacio;
			}
		}
	}
}
