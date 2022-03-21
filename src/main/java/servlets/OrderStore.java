package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;

import database.OrderStoreDBAO;
import database.RecordDBAO;
import enums.TransportationType;

/**
 * Servlet implementation class OrderStore
 */
@WebServlet("/OrderStore")
public class OrderStore extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderStore() {
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
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// String userName = request.getParameter("userName");
		String pickup = request.getParameter("pickup");
		String dropoff = request.getParameter("dropoff");
		String price = request.getParameter("price");
		String type = request.getParameter("type");
		String paymentMethod = request.getParameter("paymentMethod");
		String pickupDate = request.getParameter("pickupDate");
		String driver = request.getParameter("driver");
		String route = pickup + " -> " + dropoff;
		boolean result = false;
		String sessionUserName = null;
		// add random id for record create
//		Random r = new Random();
//		int recordId = r.nextInt(100000);
		
//		if (type == null || "".equals(type)) {
//			response.getWriter().write("<script language=javascript>alert('You must choose a transportation type');window.location='transportation'</script>");		
//			return;
//		}
//		if (pickup == null || "".equals(pickup)) {
//			response.getWriter().write("<script language=javascript>alert('You must choose a pickup location');window.location='transportation'</script>");
//			return;
//		}
//		if (dropoff == null || "".equals(dropoff)) {
//			response.getWriter().write("<script language=javascript>alert('You must choose a dropoff location');window.location='transportation'</script>");
//			return;
//		}
//		if (pickupDate == null || "".equals(pickupDate)) {
//			response.getWriter().write("<script language=javascript>alert('You must choose a pickup date');window.location='transportation'</script>");
//			return;
//		}
		
		try {
			HttpSession httpSession = request.getSession();
			System.out.println("orderStoreSessionID" + httpSession.getId());

			sessionUserName = (String) httpSession.getAttribute("userName");
			System.out.println("OrderStoreSessionUserName " + sessionUserName);
			System.out.println("TranportPrice " + price);

			OrderStoreDBAO OrderStore = new OrderStoreDBAO();
			result = OrderStore.create(sessionUserName, pickup, dropoff, price, type, paymentMethod, pickupDate,
					driver);

			RecordDBAO createNewRecord = new RecordDBAO();
//			int transportationType =  TransportationType.getByValue(type);
			int transportationType;
			switch (type) {
			case "LORRY":
				transportationType = 2;
				break;
			case "VAN":
				transportationType = 1;
				break;
			case "MPV":
				transportationType = 0;
				break;
			default:
				transportationType = -1;

			}
			System.out.println("transportationType " + type);
			System.out.println("transportationType2 " + transportationType);
			createNewRecord.createRecord(1, pickupDate, route, driver, transportationType, price,sessionUserName);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result) {
			System.out.print("Order Saved");
			out.println("<br> <b>Order is scccessful</b>");
			// request.getRequestDispatcher("/bookstore").forward(request,response);
			response.getWriter()
					.write("<script language=javascript>alert('order success');window.location='order.jsp'</script>");
//			response.sendRedirect("Payment.jsp?userName="+sessionUserName+"&price="+price+"&pickup="+pickup+"&dropoff="+dropoff);
//			response.sendRedirect("driverInfo.jsp?userName=" + sessionUserName + "&price=" + price + "&pickup=" + pickup
//					+ "&dropoff=" + dropoff);
			response.sendRedirect("records.jsp");

			return;
		} else {
			response.sendRedirect("order.jsp");
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
		doGet(request, response);
	}

}
