package com.abc.factory1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String email= request.getParameter("em");
		String password = request.getParameter("pwd");
		
		if(email==null || email=="" || password==null || password=="") {
			out.println("<h3>All fields are mandatory</h3>");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.html");
			dispatcher.include(request, response);
		}
		else {
			String fname= CheckUser.input(email,password);
			if(fname==null) {
				out.println("<h3>Invalid user</h3>");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.html");
				dispatcher.include(request, response);
			}
			else {
				out.println("<h2>Welcome</h2>");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
				dispatcher.forward(request, response);
			}
			
			
		}
		
	}

}

