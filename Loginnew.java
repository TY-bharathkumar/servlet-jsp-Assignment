package com.tc.LoginModule;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Loginnew
 */
@WebServlet("/Loginnew")
public class Loginnew extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Loginnew() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String password2;
		response.setContentType("text/html");
		String username1=request.getParameter("username");
		String password1=request.getParameter("password");	
		PrintWriter out=response.getWriter();
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			/*Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);*/
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ems?user=root&password=root");
			String query="select * from ems.login";
			pstmt=con.prepareStatement(query);
			rs=pstmt.executeQuery();
			password2 = rs.getString("password1");
			while(rs.next()){
				if(password2!=null&&password1.equals(password2)){
					out.println(username1+"has sussfully log in");
				}
				else{
					out.println("credentials are not correct");
				}
			}

		}catch(Exception e){
			e.printStackTrace();	
		}
		finally{
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(pstmt!=null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

}
