package com.abc.factory1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class user{
	protected String fname;
	protected String lname;
	protected String email;
	protected String pass;
}
public class CheckUser {
        public static String input(String email,String password) {
        	 String name=null;
        	 String url="jdbc:mysql://Localhost:3306/student";
     		String user="root";
     		String pwd="root";
     		Connection con=null;
     		PreparedStatement pstmt=null;
     		ResultSet rst = null;
     		String qry="select firstname from registrations where email=? and password=?";
     		try {
    			Class.forName("com.mysql.jdbc.Driver");
    			con=DriverManager.getConnection(url,user,pwd);
    			pstmt=con.prepareStatement(qry);
    			pstmt.setString(1, email);
    			pstmt.setString(2,password);
    			rst=pstmt.executeQuery();
    			while(rst.next()) {
    				name=rst.getString(1);
    			}
    			
    		}
    		catch(ClassNotFoundException | SQLException e) {
    			e.printStackTrace();
    		}
    		
    		finally {
    			try {
    				if(rst!=null) {
    					rst.close();
    				}
    				if(pstmt!=null) {
    					pstmt.close();
    				}
    				if(con!=null) {
    					con.close();
    				}
    			}
    			catch(SQLException e) {
    				e.printStackTrace();
    			}
    		}
    	
    	return name;
    	}
 }

