package database;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import javax.sql.*;

import javax.naming.*;
import java.util.*;
import exception.*;
import enums.RecordType;
import enums.TransportationType;

public class RecordDBAO {
	private static final Map<Integer, String> typeMap = new HashMap();
	private ArrayList records;
	Connection con;
	private boolean conFree = true;

	// Database configuration
	public static String url = "jdbc:mysql://localhost:3306/FlashMove";
	public static String dbdriver = "com.mysql.jdbc.Driver";
	public static String username = "root";
	public static String password = "12345678zZ";

	public RecordDBAO() throws Exception {
		try {
			/*
			 * Context initCtx = new InitialContext(); Context envCtx = (Context)
			 * initCtx.lookup("java:comp/env"); DataSource ds = (DataSource)
			 * envCtx.lookup("jdbc/BookDB"); con = ds.getConnection();
			 */
			Class.forName(dbdriver);
			con = DriverManager.getConnection(url, username, password);

		} catch (Exception ex) {
			System.out.println("Exception in RecordDBAO: " + ex);
			throw new Exception("Couldn't open connection to database: " + ex.getMessage());
		}
	}

	public void remove() {
		try {
			con.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	protected synchronized Connection getConnection() {
		while (conFree == false) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}

		conFree = false;
		notify();

		return con;
	}

	protected synchronized void releaseConnection() {
		while (conFree == true) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		conFree = true;
		notify();
	}

	public List getRecords() throws Exception {
		records = new ArrayList();

		try {
			String selectStatement = "select * " + "from tb_record order by id desc";
			getConnection();

			PreparedStatement prepStmt = con.prepareStatement(selectStatement);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				RecordDetails recordsDetails = new RecordDetails(rs.getInt(1), rs.getInt(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7));
				recordsDetails.setStatusName(RecordType.getByKey(recordsDetails.getStatus()));
				recordsDetails.setTypeName(TransportationType.getByKey(recordsDetails.getType()));
				records.add(recordsDetails);
				System.out.println("Debugging message22");
			}

			prepStmt.close();
		} catch (SQLException ex) {
			throw new RuntimeException(ex.getMessage());
		}

		releaseConnection();

		return records;
	}

//	public void createRecord(int id, int status, String deliver_date, String route, String driver, int type,
//			String price) throws NoSuchAlgorithmException {
//		try {
//
//			String sqlStatement = "insert into tb_record(id,status,deliver_date,route,driver,type,price) values (?,?,?,?,?,?,?);";
//			getConnection();
//
//			PreparedStatement prepStmt = con.prepareStatement(sqlStatement);
//			prepStmt.setInt(1, id);
//			prepStmt.setInt(2, status);
//			prepStmt.setString(3, deliver_date);
//			prepStmt.setString(4, route);
//			prepStmt.setString(5, driver);
//			prepStmt.setInt(6, type);
//			prepStmt.setString(7, price);
//			prepStmt.executeUpdate();
//			prepStmt.close();
//			releaseConnection();
//
//		} catch (SQLException ex) {
//			releaseConnection();
//			ex.printStackTrace();
//		}
//	}

	public List getRecordsByUserName(String userName) throws Exception {
		records = new ArrayList();

		try {
			String selectStatement = "select * " + "from tb_record where user_name=? order by id desc";
			getConnection();

			PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userName);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				RecordDetails recordsDetails = new RecordDetails(rs.getInt(1), rs.getInt(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7));
				recordsDetails.setStatusName(RecordType.getByKey(recordsDetails.getStatus()));
				recordsDetails.setTypeName(TransportationType.getByKey(recordsDetails.getType()));
				records.add(recordsDetails);
				System.out.println("Debugging message22");
			}

			prepStmt.close();
		} catch (SQLException ex) {
			throw new RuntimeException(ex.getMessage());
		}

		releaseConnection();

		return records;
	}
	public void createRecord(int status, String deliver_date, String route, String driver, int type,
			String price, String user_name) throws NoSuchAlgorithmException {
		try {

			String sqlStatement = "insert into tb_record(status,deliver_date,route,driver,type,price,user_name) values (?,?,?,?,?,?,?);";
			getConnection();

			PreparedStatement prepStmt = con.prepareStatement(sqlStatement);
			prepStmt.setInt(1, status);
			prepStmt.setString(2, deliver_date);
			prepStmt.setString(3, route);
			prepStmt.setString(4, driver);
			prepStmt.setInt(5, type);
			prepStmt.setString(6, price);
			prepStmt.setString(7, user_name);
			prepStmt.executeUpdate();
			prepStmt.close();
			releaseConnection();

		} catch (SQLException ex) {
			releaseConnection();
			ex.printStackTrace();
		}
	}
	
	public void cancelRecord(int id, int status) throws NoSuchAlgorithmException {
		try {

			String sqlStatement = "update tb_record SET status =? where id =? " ;
			getConnection();

			PreparedStatement prepStmt = con.prepareStatement(sqlStatement);
			prepStmt.setInt(1, status);
			prepStmt.setInt(2, id);
			prepStmt.executeUpdate();
			prepStmt.close();
			releaseConnection();

		} catch (SQLException ex) {
			releaseConnection();
			ex.printStackTrace();
		}
	}
}
