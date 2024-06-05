package p2;

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
 * Servlet implementation class AdminStorage
 */
@WebServlet("/AdminStorage")
public class AdminStorage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminStorage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw=response.getWriter();
		String uname=request.getParameter("usrname");
		String email=request.getParameter("emid");
		String pass=request.getParameter("pasd");
		try
		{
			//1.Loading a Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			pw.println("loaded a driver");
			//2.Establish a connection
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/admin","root","tiger");
			pw.println("connection is established");
			//3.Prepare a Statement
			Statement st=con.createStatement();
					st.execute("insert into signup values('"+uname+"','"+email+"','"+pass+"')");
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
