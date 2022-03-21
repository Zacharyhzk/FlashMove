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
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		boolean result = false ;
		
		try {
			AccountDBAO account = new AccountDBAO();
			result = account.authenticate(userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		if (result){
			System.out.println("result true!");
			HttpSession httpSession= request.getSession();
			System.out.println("SessionID"+ httpSession.getId());
			
			System.out.println("userName "+ userName);
			httpSession.setAttribute("userName", userName);

			response.getWriter().write("<script language=javascript>alert('login success')</script>");
//			request.getRequestDispatcher("/transportation").forward(request,response);
			response.sendRedirect("/FlashMove/transportation");
//			response.sendRedirect("/FlashMove/driver");
			return;
		} else {
			response.getWriter().write("<script language=javascript>alert('login fail')</script>");
			response.sendRedirect("login.jsp");
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