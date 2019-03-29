package com.capg.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter out=response.getWriter();
		String name=request.getParameter("uname");
		String rollno=request.getParameter("rollno");
		String dept=request.getParameter("dept");
		String college=request.getParameter("college");
	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MANISHA","oracle123");
			PreparedStatement preparedStatement = connection.prepareStatement("insert into register values(?,?,?,?)");
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, rollno);
			preparedStatement.setString(3, dept);
			preparedStatement.setString(4, college);
			int i=preparedStatement.executeUpdate();
			
			if(i==1) {
				RequestDispatcher dispatcher =request.getRequestDispatcher("/login.html");
				dispatcher.include(request, response);
				
					System.out.println("SUCCESS");
			}
				else {
					System.out.println("Invalid");
			}
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
