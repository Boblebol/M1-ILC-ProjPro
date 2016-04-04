package preferences.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import preferences.traitement.PreferencesTraitements;

public class DelCatPrefServlet extends HttpServlet  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse rep) {
		try {
			String idClientStr = req.getParameter("idClient"); 
			String categorie = req.getParameter("categorie");  
			int idClient = Integer.parseInt(idClientStr);

			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();
			out.println(PreferencesTraitements.DelPrefCat(idClient, categorie));

		} catch (Exception e) {
			e.printStackTrace();
			rep.setContentType("text/plain");
			PrintWriter out;
			try {
				out = rep.getWriter();
				out.println(e.getMessage());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}