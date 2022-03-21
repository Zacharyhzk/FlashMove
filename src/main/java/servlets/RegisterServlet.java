package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.AccountDBAO;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String nickName = request.getParameter("nickName");
		String phone = request.getParameter("phone");
		boolean result = false ;
		
		if (!isEmail(email)) {
			response.getWriter().write("<script language=javascript>alert('Fail to validate Email');window.location='register.jsp'</script>");
			return;
		}
		
		if (!isNumeric(phone) || phone.length() != 8) {
			response.getWriter().write("<script language=javascript>alert('Fail to validate phone');window.location='register.jsp'</script>");
			return;
		}
		
		try {
			AccountDBAO account = new AccountDBAO();
			result = account.create(userName, password, nickName, phone, email);
		} catch (Exception e) {
			e.printStackTrace();	
		}		
				
		if (result){
			System.out.print("Registration is scccessful");
			out.println("<br> <b>Registration is scccessful</b>");
			//request.getRequestDispatcher("/bookstore").forward(request,response);
			response.getWriter().write("<script language=javascript>alert('register success');window.location='login.jsp'</script>");
			return;
		} else { 		
			response.sendRedirect("register.jsp");
			return;
		}
		
	}
	
	private boolean isEmail(String email) {
		String reg = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
		Pattern regex = Pattern.compile(reg);
		Matcher matcher = regex.matcher(email);
		boolean isMatched = matcher.matches();
		return isMatched;
	}
	
	public boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]+");
		Matcher isNum = pattern.matcher(str);
		if(!isNum.matches()) {
			return false;
		}
		return true;
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
