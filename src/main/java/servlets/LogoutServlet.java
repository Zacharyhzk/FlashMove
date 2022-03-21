package servlets;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.AccountDBAO;


/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/LoginServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		boolean result = false;
		try {
			HttpSession session = request.getSession();
			session.invalidate();
			result = true;
			System.out.println("session " + String.valueOf(session) + " clean");
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		if (result){
			System.out.println("result true!");
//			response.getWriter().write("<script language=javascript>alert('logout success')</script>");
			response.sendRedirect("login.jsp");
//			response.sendRedirect("/FlashMove/driver");
			return;
		} else {
			response.getWriter().write("<script language=javascript>alert('logout fail')</script>");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}