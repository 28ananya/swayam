package p4;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Details
 */
@WebServlet("/Details")
public class Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
PrintWriter pw = response.getWriter();
        
        // Retrieve form parameters
        int pid = Integer.parseInt(request.getParameter("pid"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String bloodgroup = request.getParameter("bloodgroup");
        int bill = Integer.parseInt(request.getParameter("bill"));
        try
		{
			//1.Loading a Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			pw.println("loaded a driver");
			//2.Establish a connection
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","tiger");
			pw.println("connection is established");
			//3.Prepare a Statement
			Statement st=con.createStatement();
			st.execute("insert into patient values("+pid+",'"+name+"',"+age+",'"+email+"','"+phone+"','"+bloodgroup+"',"+bill+")");
					response.sendRedirect("success.html");
			con.close();
			pw.println("connection is relased");
		}
		catch(Exception e)
		{
			pw.println(e);
		}
	}

}
