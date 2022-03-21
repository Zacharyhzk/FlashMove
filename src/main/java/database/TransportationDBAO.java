/*
 * Copyright (c) 2006 Sun Microsystems, Inc.  All rights reserved.  U.S.
 * Government Rights - Commercial software.  Government users are subject
 * to the Sun Microsystems, Inc. standard license agreement and
 * applicable provisions of the FAR and its supplements.  Use is subject
 * to license terms.
 *
 * This distribution may include materials developed by third parties.
 * Sun, Sun Microsystems, the Sun logo, Java and J2EE are trademarks
 * or registered trademarks of Sun Microsystems, Inc. in the U.S. and
 * other countries.
 *
 * Copyright (c) 2006 Sun Microsystems, Inc. Tous droits reserves.
 *
 * Droits du gouvernement americain, utilisateurs gouvernementaux - logiciel
 * commercial. Les utilisateurs gouvernementaux sont soumis au contrat de
 * licence standard de Sun Microsystems, Inc., ainsi qu'aux dispositions
 * en vigueur de la FAR (Federal Acquisition Regulations) et des
 * supplements a celles-ci.  Distribue par des licences qui en
 * restreignent l'utilisation.
 *
 * Cette distribution peut comprendre des composants developpes par des
 * tierces parties. Sun, Sun Microsystems, le logo Sun, Java et J2EE
 * sont des marques de fabrique ou des marques deposees de Sun
 * Microsystems, Inc. aux Etats-Unis et dans d'autres pays.
 */


package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enums.TransportationType;

public class TransportationDBAO {
	private static final Map<Integer, String> typeMap = new HashMap();
    private ArrayList cars;
    private ArrayList drivers;
    Connection con;
    private boolean conFree = true;
    
    // Database configuration
    public static String url = "jdbc:mysql://localhost:3306/FlashMove";
    public static String dbdriver = "com.mysql.jdbc.Driver";
    public static String username = "root";
    public static String password = "12345678zZ";
    
    public TransportationDBAO() throws Exception {
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
            System.out.println("Exception in TransportationDBAO: " + ex);
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
    
    public List getCars() throws Exception {
        cars = new ArrayList();
        
        try {
            String selectStatement = "select * " + "from tb_transportation";
            getConnection();
            
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            
            while (rs.next()) {
                TransDetail transDetail =
                        new TransDetail(rs.getInt(1), rs.getBigDecimal(2),
                        rs.getString(3), rs.getInt(4), rs.getString(5));
                
                transDetail.setTypeName(TransportationType.getByKey(transDetail.getType()));
                cars.add(transDetail);
            }
            
            prepStmt.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        
        releaseConnection();
        
        return cars;
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
            }
            
            prepStmt.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        
        releaseConnection();
        
        return drivers;
    }
    
}
