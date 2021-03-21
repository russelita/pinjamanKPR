package com.aplikasipinjaman.java.sql;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.Component;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;
import java.awt.TextField;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Button;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Tampilan extends JFrame {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/pinjaman_kpr";
	static final String DB_User ="root";
	static final String DB_Password ="";
	
	
	static Connection conn;
	static Statement stat;
	static ResultSet rs;
	
	static InputStreamReader data = new InputStreamReader(System.in);
	static BufferedReader input = new BufferedReader(data);
	
	public String dateChooser;
	
	
	Date tanggal ;
	int lama,platfon;
	double bunga;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,DB_User,DB_Password);
			
			stat = conn.createStatement();
			
			while(!conn.isClosed()) {
				showMenu();
			}
			
			stat.close();
		    conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tampilan frame = new Tampilan();
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
	public Tampilan() {
		setBackground(Color.WHITE);
		setTitle("Data Pinjaman");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 503, 299);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setAlignmentY(Component.TOP_ALIGNMENT);
		contentPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		contentPane.setBounds(new Rectangle(20, 20, 150, 30));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label label = new Label("DATA PINJAMAN");
		label.setAlignment(Label.CENTER);
		label.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		label.setBounds(108, 10, 250, 41);
		contentPane.add(label);
		
		Label dateP = new Label("Tanggal mulai pinjaman");
		dateP.setBounds(21, 80, 124, 22);
		contentPane.add(dateP);
		
		Label platfon = new Label("Platfon");
		platfon.setBounds(21, 108, 47, 22);
		contentPane.add(platfon);
		
		Label bunga = new Label("Bunga");
		bunga.setBounds(21, 140, 47, 22);
		contentPane.add(bunga);
		
		Label lamaP = new Label("Lama pinjaman");
		lamaP.setBounds(21, 174, 124, 22);
		contentPane.add(lamaP);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd MMMM yyyy");
		dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				dateChooser.getDate();
			}
		});
		dateChooser.getCalendarButton().setBounds(53, 0, 21, 22);
		dateChooser.setBounds(166, 80, 74, 22);
		contentPane.add(dateChooser);
		dateChooser.setLayout(null);
		
		
		TextField tplatfon = new TextField();
		tplatfon.setBounds(166, 108, 74, 22);
		contentPane.add(tplatfon);
		
		TextField tbunga = new TextField();
		tbunga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tbunga.setText("");
			}
		});
		tbunga.setBounds(166, 136, 74, 22);
		contentPane.add(tbunga);
		
		TextField tpinjaman = new TextField();
		tpinjaman.setBounds(166, 165, 74, 22);
		contentPane.add(tpinjaman);
		
		Button button = new Button("Lihat tabel");
		button.setBounds(202, 225, 70, 22);
		contentPane.add(button);
	}
}
