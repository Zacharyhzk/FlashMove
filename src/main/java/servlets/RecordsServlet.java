
package servlets;

import java.io.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.*;
import database.RecordDBAO;

/**
 * Servlet implementation class RecordsServlet
 */
@WebServlet("/RecordsServlet")
public class RecordsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	
	public RecordsServlet() {
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
		
//		String sessionUserName = null;

//		try {
//			TransportationDBAO transportationDBAO = new TransportationDBAO();
//			transDetails = transportationDBAO.getCars();
//			if (transDetails != null) {
//				result = true;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		try {
////			HttpSession httpSession = request.getSession();
////			System.out.println("recordSessionID" + httpSession.getId());
////
////			sessionUserName = (String) httpSession.getAttribute("userName");
////			System.out.println("recordSessionUserName " + sessionUserName);
////
//////			OrderStoreDBAO OrderStore = new OrderStoreDBAO();
////			result = OrderStore.create(sessionUserName, pickup, dropoff, price, type, paymentMethod, pickupDate,
////					driver);
////
////			RecordDBAO createNewRecord = new RecordDBAO();
//////			int transportationType =  TransportationType.getByValue(type);
////			int transportationType;
////			switch (type) {
////			case "LORRY":
////				transportationType = 2;
////				break;
////			case "VAN":
////				transportationType = 1;
////				break;
////			case "MPV":
////				transportationType = 0;
////				break;
////			default:
////				transportationType = -1;
////
////			}
////			System.out.println("transportationType " + type);
////			System.out.println("transportationType2 " + transportationType);
////			createNewRecord.createRecord(1, pickupDate, route, driver, transportationType, price);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		int cancelRecordId = Integer.parseInt(request.getParameter("cancelBtn"));
		try {		
			RecordDBAO cancelSelectRecord = new RecordDBAO();
			cancelSelectRecord.cancelRecord(cancelRecordId,2);
			
			System.out.println("cancelSelectRecord " + cancelRecordId);
			response.sendRedirect("records.jsp");
		} catch (Exception e) {
			e.printStackTrace();
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