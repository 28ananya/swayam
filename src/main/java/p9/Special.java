package p9;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Special
 */
@WebServlet("/Special")
public class Special extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Special() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        out.println("<!DOCTYPE html>");
	        out.println("<html>");
	        out.println("<head>");
	        out.println("<title>Doctor Information</title>");
	        out.println("</head>");
	        out.println("<body>");
	        out.println("<h2>Doctor Information</h2>");
	        out.println("<table border='1'>");
	        out.println("<thead>");
	        out.println("<tr>");
	        out.println("<th>Doctor ID</th>");
	        out.println("<th>First Name</th>");
	        out.println("<th>Middle Name</th>");
	        out.println("<th>Last Name</th>");
	        out.println("<th>Date of Birth</th>");
	        out.println("<th>Gender</th>");
	        out.println("<th>Date of Appointment</th>");
	        out.println("<th>Designation</th>");
	        out.println("</tr>");
	        out.println("</thead>");
	        out.println("<tbody>");

	        Connection conn = null;
	        Statement stmt = null;
	        ResultSet rs = null;
	        
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/staff", "root", "tiger");
	            stmt = conn.createStatement();
	            rs = stmt.executeQuery("SELECT * FROM doctor");

	            while (rs.next()) {
	                out.println("<tr>");
	                out.println("<td>" + rs.getInt("docid") + "</td>");
	                out.println("<td>" + rs.getString("fname") + "</td>");
	                out.println("<td>" + rs.getString("mname") + "</td>");
	                out.println("<td>" + rs.getString("lname") + "</td>");
	                out.println("<td>" + rs.getString("dob") + "</td>");
	                out.println("<td>" + rs.getString("gen") + "</td>");
	                out.println("<td>" + rs.getString("doa") + "</td>");
	                out.println("<td>" + rs.getString("des") + "</td>");
	                out.println("</tr>");
	            }
	        } catch (Exception e) {
	            out.println("<tr><td colspan='8'>Error: " + e.getMessage() + "</td></tr>");
	        } finally {
	            try {
	                if (rs != null) rs.close();
	                if (stmt != null) stmt.close();
	                if (conn != null) conn.close();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }

	        out.println("</tbody>");
	        out.println("</table>");
	        out.println("</body>");
	        out.println("</html>");
	}

}
