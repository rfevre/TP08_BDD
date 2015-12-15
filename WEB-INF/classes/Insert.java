import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/servlet/Insert")
public class Insert extends HttpServlet
{
    public void service( HttpServletRequest req, HttpServletResponse res ) 
	throws ServletException, IOException
    {
	PrintWriter out = res.getWriter();
	res.setContentType( "text/html" );
	out.println("<!doctype html>");
    
        Connection con = null;
	
	try {
	    //Enregistrement du Driver
	    Class.forName("org.postgresql.Driver");
	    
	    //Connexion a la base
	    String url = "jdbc:postgresql://psqlserv/da2i";
	    String nom = "fevrer";
	    String mdp = "moi";
	    con = DriverManager.getConnection(url,nom,mdp);

	    out.println("<body><h1>connexion OK</h1>");

	    Statement stmt = con.createStatement();
	    String nomBis= req.getParameter("nom");
	    String prenomBis= req.getParameter("prenom");

	    String requete = "insert into personnes values ('"+nomBis+"','"+prenomBis+"')";
	    
	    stmt.executeUpdate(requete);
	}
	
	catch(Exception e) {
	    out.println(e.getMessage());
	}	
	finally {
	    try {
		out.println("</body></html>");
		con.close();
	    }
	    catch(Exception e) {
		System.out.println(e.getMessage());
	    }
	}
    }
}
