package p5;

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
 * Servlet implementation class Patupdate
 */
@WebServlet("/Patupdate")
public class Patupdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Patupdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw=response.getWriter();
		int pid = Integer.parseInt(request.getParameter("pid"));
		String name = request.getParameter("name");
        String req="";
      
        String selectedDetail = request.getParameter("patientDetails");

        switch (selectedDetail) {
        case "pid":
            req = "pid";
            break;
        case "pname":
            req = "pname";
            break;
        case "page":
            req = "page";
            break;
        case "pemail":
            req = "pemail";
            break;
        case "pnumber":
            req = "pnumber";
            break;
        case "pblood":
            req = "pblood";
            break;
        case "pbill":
            req = "pbill";
            break;
        
        default:
          
            break;
    }

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
				st.execute("UPDATE patient SET " + req + " = '" + name + "' WHERE pid = " + pid);
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
