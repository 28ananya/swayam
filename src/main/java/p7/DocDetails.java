package p7;

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
 * Servlet implementation class DocDetails
 */
@WebServlet("/DocDetails")
public class DocDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DocDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw=response.getWriter();
		int id=Integer.parseInt(request.getParameter("doid"));
		
		String fname=request.getParameter("first_name");
		String mname=request.getParameter("middle_name");
		String lname=request.getParameter("Last_name");
		String dob=request.getParameter("doc_dob");
		String gen=request.getParameter("gender");
		String doa=request.getParameter("appointment_time");
		String mem=request.getParameter("desg");
		try
		{
			//1.Loading a Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			pw.println("loaded a driver");
			//2.Establish a connection
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/staff","root","tiger");
			pw.println("connection is established");
			//3.Prepare a Statement
			Statement st=con.createStatement();
		st.execute("insert into doctor values("+id+",'"+fname+"','"+mname+"','"+lname+"','"+dob+"','"+gen+"','"+doa+"','"+mem+"')");
		response.sendRedirect("success.html");
			pw.println("SUCCESSFULLY INSERTED");
			con.close();
			pw.println("connection is relased");
		}
		catch(Exception e)
		{
			pw.println(e);
		}
		
	}

}
