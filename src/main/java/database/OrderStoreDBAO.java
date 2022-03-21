
package database;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;
import exception.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
// The instance of BookDBAO gets created when the application
// is deployed. It maintains the Connection object to the
// database. The Connection object is created from DataSource
// object, which is retrieved through JNDI.
// For more information on DataSource, please see
// http://java.sun.com/j2se/1.4.2/docs/api/javax/sql/DataSource.html.
public class OrderStoreDBAO {
	private ArrayList drivers;
    Connection con;
    private boolean conFree = true;
    
    // Database configuration
    public static String url = "jdbc:mysql://localhost:3306/FlashMove";
    public static String dbdriver = "com.mysql.jdbc.Driver";
    public static String username = "root";
    public static String password = "12345678zZ";
    
    public OrderStoreDBAO() throws Exception {
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
        	System.out.println("Exception in AccountDBAO: " + ex);
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
    
    public boolean authenticate(String userName, String password)  throws NoSuchAlgorithmException {
    	boolean status = false;
        try {
            //String selectStatement = "select * from accounts where id = ? and password = ?";
        	String selectStatement = "select * from order_detail where user_name = ?";      	
            getConnection();           
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userName);
            //prepStmt.setString(2, password);
            
            ResultSet rs = prepStmt.executeQuery();
            
            if (rs.next()) {
              	String hashPasswordDB = rs.getString("password");
//              	String saltDB = rs.getString("salt");
              	
//               	 if (hashPasswordDB.equals(hashPassword(password + saltDB))) {
//               		status = true;  
//               	 }
              	if (password.equals(hashPasswordDB)) {
              		status = true;
              	}
            }        
            prepStmt.close();
            releaseConnection();       
        } catch (SQLException ex) {
            releaseConnection();
            ex.printStackTrace();
        }
        return status;
    }
    
    public boolean create(String userName, String pickup, String dropoff, String price, String type, String paymentMethod,String pickupDate,String driver ) throws NoSuchAlgorithmException {
    	boolean status = false;
        try {
        	String salt = getSalt();
          	String passwordHashedSalted = hashPassword(password + salt);
          	
          	String sqlStatement = "insert into order_detail(user_name,pickup,dropoff,price,type,date,payment,driver) values (?,?,?,?,?,?,?,?);";  
            getConnection();
            
            PreparedStatement prepStmt = con.prepareStatement(sqlStatement);
            prepStmt.setString(1, userName);
            prepStmt.setString(2, pickup);
            prepStmt.setString(3, dropoff);
            prepStmt.setString(4, price);
            prepStmt.setString(5, type);
            prepStmt.setString(6, pickupDate);
            prepStmt.setString(7, paymentMethod);
            prepStmt.setString(8, driver);
            int x = prepStmt.executeUpdate();
            
            if (x == 1) {
            	status = true;       
            } 
            
            prepStmt.close();
            releaseConnection();
           
        } catch (SQLException ex) {
            releaseConnection();
            ex.printStackTrace();
        }
        return status;
    }
    public static String hashAndSaltPassword(String password)
          	 throws NoSuchAlgorithmException {
          	 String salt = getSalt();
          	 return hashPassword(password + salt);
       }
       
       public static String getSalt() {
   		 Random r = new SecureRandom();
   		 byte[] saltBytes = new byte[32];
   		 r.nextBytes(saltBytes);
   		 return Base64.getEncoder().encodeToString(saltBytes);
   	}
       
       public static String hashPassword(String password)
       		   throws NoSuchAlgorithmException {
       		 	MessageDigest md = MessageDigest.getInstance("SHA-256");
       		 	md.reset();
       		 	md.update(password.getBytes());
       		 	byte[] mdArray = md.digest();
       		 	StringBuilder sb = new StringBuilder(mdArray.length * 2);
       		 	for (byte b : mdArray) {
       		 		int v = b & 0xff;
       		 		if (v < 16) {
       		 			sb.append('0');
       		 		}

       				sb.append(Integer.toHexString(v));
       		 	}
       		 	return sb.toString();
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
//              driverDetail.setTypeName(TransportationType.getByKey(driverDetail.getDriverName()));
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