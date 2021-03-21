package com.aplikasipinjaman.java.sql;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

public class Main {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/pinjaman_kpr";
	static final String DB_User ="root";
	static final String DB_Password ="";
	
	
	static Connection conn;
	static Statement stat;
	static ResultSet rs;
	
	static InputStreamReader data = new InputStreamReader(System.in);
	static BufferedReader input = new BufferedReader(data);
	static Tampilan tampilan = new Tampilan();
	
	
	

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
		
		tampilan.setVisible(true);
	}
	
	static void showMenu() {
		System.out.println("\n========= MENU UTAMA =========");
	    System.out.println("1. Insert Pinjaman");
	    System.out.println("2. Show Data");
	    System.out.println("0. Keluar");
	    System.out.println("");
	    System.out.print("PILIHAN> ");
	    
	    try {
	        int pilihan = Integer.parseInt(input.readLine());

	        switch (pilihan) {
	            case 0:
	                System.exit(0);
	                break;
	            case 1:
	                insertPinjaman();
	                break;
	            case 2:
	                showData();
	                break;
	            default:
	                System.out.println("Pilihan salah!");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }  
	}
	
	static void insertPinjaman() {
		try {
	        System.out.print("Tanggal: ");
	        String tanggal = input.readLine();
	        System.out.print("Platfon: ");
	        int platfon = Integer.parseInt(input.readLine());
	        System.out.print("Bunga: ");
	        int bunga = Integer.parseInt(input.readLine());
	        System.out.print("Lama Pinjaman: ");
	        int lamaPinjaman = Integer.parseInt(input.readLine());
	        
	        String sql = "INSERT INTO skema_pinjaman (tanggal, totalAngsuran,) VALUE('%s', '%s')";
	        sql = String.format(sql, platfon, bunga, lamaPinjaman);
	        
	        stat.execute(sql);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	static void showData() {
		String sql = "SELECT * from skema_pinjaman";
		try {
			rs = stat.executeQuery(sql);
			
			System.out.println("+--------------------------------+");
	        System.out.println("|        DATA PINJAMAN KPR       |");
	        System.out.println("+--------------------------------+");
	        while (rs.next()) {
	            int angsuranke = rs.getInt("angsuranke");
	            Date tanggal = rs.getDate("tanggal");
	            double totalAngsuran = rs.getDouble("totalAngsuran");
	            double angsuranPokok = rs.getDouble("angsuranPokok");
	            double angsuranBunga = rs.getDouble("angsuranBunga");
	            double sisaPinjaman = rs.getDouble("sisaPinjaman");
	            
	            System.out.println(String.format("%d.%tD %s -- (%s)", angsuranke, tanggal, totalAngsuran,angsuranPokok,angsuranBunga,sisaPinjaman));
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
