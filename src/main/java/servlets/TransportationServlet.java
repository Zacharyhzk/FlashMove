package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.*;
//import exception.BookNotFoundException;


/**
 * Servlet implementation class TransportationServlet
 */
@WebServlet("/transportationServlet")
public class TransportationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TransportationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List transDetails = null;
		boolean result = false;

		try {
			TransportationDBAO transportationDBAO = new TransportationDBAO();
			transDetails = transportationDBAO.getCars();
			if (transDetails != null) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (result) {
			System.out.print("result true!transportation");
			request.setAttribute("list", transDetails);
			request.getRequestDispatcher("order.jsp").forward(request, response);				
			return;
		} else {
			response.sendRedirect("login.jsp");
			return;
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}