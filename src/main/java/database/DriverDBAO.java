package database;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;
import exception.*;
//import cart.*;
//import enums.TransportationType;

public class DriverDBAO {
	private static final Map<Integer, String> typeMap = new HashMap();
    private ArrayList drivers;
    Connection con;
    private boolean conFree = true;
    
    // Database configuration
    public static String url = "jdbc:mysql://localhost:3306/FlashMove";
    public static String dbdriver = "com.mysql.jdbc.Driver";
    public static String username = "root";
    public static String password = "12345678zZ";
    
    public DriverDBAO() throws Exception {
        try {
            /*
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/BookDB");
            con = ds.getConnection();
            */
            Class.forName(dbdriver);
            con = DriverManager.getConnection(url, username, password);
            
        } catch (Exception ex) {
            System.out.println("Exception in DriverDBAO: " + ex);
            throw new Exception("Couldn't open connection to database: " +
                    ex.getMessage());
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
    
    public List getDrivers() throws Exception {
    	drivers = new ArrayList();
        
        try {
            String selectStatement = "select * " + "from tb_driver";
            getConnection();
            
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            
            while (rs.next()) {
            	DriverDetails driverDetails =
                        new DriverDetails(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getFloat(4));
//            	driverDetail.setTypeName(TransportationType.getByKey(driverDetail.getDriverName()));
            	drivers.add(driverDetails);
            	System.out.println("Debugging message");
            }
            
            prepStmt.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        
        releaseConnection();
        
        return drivers;
    }
    
    
}
