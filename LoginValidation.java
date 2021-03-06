

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginValidation
 */
@WebServlet("/LoginValidation")
public class LoginValidation extends HttpServlet {
	public static final long serialVersionUID = 1;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Connection conn = null;
	    HttpSession session = request.getSession();
	    Statement st = null;
	    ResultSet rs = null;
	    String userInputName = request.getParameter("username");
	    String userInputPw = request.getParameter("password");
	    boolean correctInput = false; 
	    try {
	    	Class.forName("com.mysql.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost/roomreservationdatabase?user=root&password=Hutch2020!&useSSL=false");
	        st = conn.createStatement();
	        String query = "select* from user";
	        rs = st.executeQuery(query);
	        ArrayList<String> usernames = new ArrayList<String>();
	        ArrayList<String> passwords = new ArrayList<String>();
	       // ArrayList<Integer> studentIDs = new ArrayList<Integer>();
	        while (rs.next()) {
	        	String username = rs.getString("username");
	        	String password = rs.getString("password");
	        	//int studentID = rs.getInt("studentID");
	        	if(username.equals(userInputName))
	        	{
	        		if(password.equals(userInputPw)) {
	        			correctInput = true;
	        			break;
	        		}
	        	}
	        	
	        	System.out.println ("username = " + username);
	        	System.out.println ("password = " + password);
	        	//System.out.println ("studentID = " + studentID);
	        	usernames.add(username);
	        	passwords.add(password);
	        	
	        }
	       // System.out .println("something");
	        //request.setAttribute("fnames", fnames);
	        //request.setAttribute("lnames", lnames);
	        ///request.setAttribute("studentIDs", studentIDs);
	    } catch (SQLException sqle) {
	    	System.out.println (sqle.getMessage());
	    } catch (ClassNotFoundException cnfe) {
	    	System.out.println (cnfe.getMessage());
	    } finally {
	    	try {
	    		if (rs != null) {
	    			rs.close();
	    		}
	    		if (st != null) {
	    			st.close();
	    		}
	    		if (conn != null) {
	    			conn.close();
	    		}
	    	} catch (SQLException sqle) {
	    		System.out.println(sqle.getMessage());
	    	}
	    }  
	    
	   String nextJSP ="";
	    if(correctInput == false)
	    {	  
	    	nextJSP = "/Login.jsp";
	    	request.setAttribute("error", "Either email or password is incorrect. Please try again.");
	    	request.setAttribute("username", userInputName);
	    	request.setAttribute("password", userInputPw);
	    }
	    else {
	    	nextJSP = "/AccountPage.jsp";
	    	session.setAttribute("username", userInputName);
	    }
	    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		 dispatcher.forward(request,response);
	 
	    
	    
		
	}
}

