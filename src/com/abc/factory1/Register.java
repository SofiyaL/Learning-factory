package com.abc.factory1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		String name =request.getParameter("name");
		
		String url="jdbc:mysql://Localhost:3306/student";
		String user="root";
		String pwd="root";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rst = null;
		String firstname=request.getParameter("fn").trim();
		String lastname = request.getParameter("ln").trim();
		String email= request.getParameter("em").trim();
		String password= request.getParameter("pwd");
        String qry1="insert into registrations values (?,?,?,?)";
		

		if(firstname==null || firstname=="" || lastname==null || lastname=="" 
				|| email==null || email=="" || password==null || password=="") {
			out.println("<h2>ALL FEILD ARE MANDATORY</h2>");
			RequestDispatcher dispatcher = request.getRequestDispatcher("Register.html");
			dispatcher.include(request, response);
		}
		else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection(url,user,pwd);
				pstmt=con.prepareStatement(qry1);
				pstmt.setString(1, firstname );
				pstmt.setString(2,lastname );
				pstmt.setString(3, email );
				pstmt.setString(4, password );
				
				pstmt.executeUpdate();
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
				dispatcher.forward(request, response);
				
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
		}
		
	
		}
		
		
	
	
	
	
	
}

