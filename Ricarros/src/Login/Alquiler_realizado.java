package Login;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class Alquiler_realizado extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args,String user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Alquiler_realizado frame = new Alquiler_realizado(user);
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
	public Alquiler_realizado(String user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 361, 189);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Compra_realizada = new JLabel("Alquiler realizado");
		Compra_realizada.setForeground(new Color(255, 255, 255));
		Compra_realizada.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		Compra_realizada.setHorizontalAlignment(SwingConstants.CENTER);
		Compra_realizada.setBounds(55, 11, 215, 66);
		contentPane.add(Compra_realizada);
		
		JButton Volver = new JButton("SIU");
		Volver.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		Volver.setBounds(98, 88, 124, 34);
		contentPane.add(Volver);
		Volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Alquiler.main(null,user);
				dispose();
			}});
	}
}
