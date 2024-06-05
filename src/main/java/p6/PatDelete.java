package p6;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PatDelete
 */
@WebServlet("/PatDelete")
public class PatDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		int pid = Integer.parseInt(request.getParameter("pid"));
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
		
			ResultSet rs=st.executeQuery("select * from patient where pid="+pid+"");
			if(rs.next())
			{
				st.execute("Delete from patient WHERE pid = " + pid);
				response.sendRedirect("success.html");
			}
			else
			{
				pw.println("invalid patient id");
			}
			
			con.close();
			pw.println("connection is relased");
		}
		catch(Exception e)
		{
			pw.println(e);
		}
	}

}
