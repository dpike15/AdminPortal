package adminPortal;
/*
 * LOGIN SCREEN
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AdminLogin {

	private JFrame frmLogin;
	private JTextField txtUsername;
	private JTextField txtUsername_1;
	private JTextField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin window = new AdminLogin();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("LOGIN");
		frmLogin.setBounds(100,100,500,300);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		frmLogin.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(0, 0, 255));
		panel.setBackground(new Color(100, 149, 237));
		panel.setBounds(0, 0, 484, 68);
		frmLogin.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblFischerInternationalAdmin = new JLabel("FISCHER INTERNATIONAL ADMIN PORTAL");
		lblFischerInternationalAdmin.setHorizontalAlignment(SwingConstants.LEFT);
		lblFischerInternationalAdmin.setIcon(new ImageIcon("C:\\Users\\DF\\Desktop\\banner_logo_fischer09.jpg"));
		lblFischerInternationalAdmin.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 13));
		lblFischerInternationalAdmin.setBounds(0, 0, 484, 68);
		panel.add(lblFischerInternationalAdmin);
		lblFischerInternationalAdmin.setBackground(new Color(0, 128, 128));
		lblFischerInternationalAdmin.setForeground(new Color(255, 255, 255));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 67, 484, 195);
		frmLogin.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setText("host");
		txtUsername.setBounds(200, 38, 100, 20);
		panel_1.add(txtUsername);
		txtUsername.setColumns(10);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*
				 * host ex: localhost:3306/admin_salesform
				 * username ex: root
				 * database password
				 */
				String path=txtUsername.getText();
				String username=txtUsername_1.getText();
				String password=txtPassword.getText();
				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();

					try {
						Connection conn = DriverManager.getConnection(
								"jdbc:mysql://"+path+"?autoReconnect=true&useSSL-false", username, // user
																						// name
								password);
						Landing page=new Landing(path,username,password);
						frmLogin.dispose();
					}catch(SQLException e1){
						JOptionPane.showMessageDialog(null, "Invalid Database Input");

					}
				
			} catch (InstantiationException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IllegalAccessException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			} catch (ClassNotFoundException e4) {
				// TODO Auto-generated catch block
				e4.printStackTrace();
			}
			}});
		
		txtUsername_1 = new JTextField();
		txtUsername_1.setText("username");
		txtUsername_1.setBounds(200, 69, 100, 20);
		panel_1.add(txtUsername_1);
		txtUsername_1.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setText("password");
		txtPassword.setBounds(200, 100, 100, 20);
		panel_1.add(txtPassword);
		txtPassword.setColumns(10);
		btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnLogin.setForeground(new Color(240, 248, 255));
		btnLogin.setBackground(new Color(100, 149, 237));
		btnLogin.setBounds(200, 149, 100, 23);
		panel_1.add(btnLogin);
		
		JLabel lblDatabaseCredentials = new JLabel("DATABASE CREDENTIALS");
		lblDatabaseCredentials.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatabaseCredentials.setForeground(Color.DARK_GRAY);
		lblDatabaseCredentials.setBounds(150, 11, 200, 20);
		panel_1.add(lblDatabaseCredentials);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\DF\\Desktop\\banner-cloud.jpg"));
		lblNewLabel.setBounds(0, 0, 484, 195);
		panel_1.add(lblNewLabel);
	}
}
