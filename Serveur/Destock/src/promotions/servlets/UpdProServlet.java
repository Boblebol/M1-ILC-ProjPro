package promotions.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import promotions.traitement.PromotionsTraitements;

public class UpdProServlet extends HttpServlet  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse rep) {
		try {
			
			String ref = req.getParameter("ref"); 
			String marque = req.getParameter("marque"); 
			String nomprod = req.getParameter("nomprod"); 
			String categorie = req.getParameter("categorie");  
			String description = req.getParameter("description"); 
			String ancienprixStr = req.getParameter("ancienprix"); 
			String nouveauprixStr = req.getParameter("nouveauprix"); 
			String dureeStr = req.getParameter("duree"); 
			String active = req.getParameter("active"); 
			String idmagStr = req.getParameter("idmag");  
			float ancienprix = Float.parseFloat(ancienprixStr);
			float nouveauprix = Float.parseFloat(nouveauprixStr);
			int duree = Integer.parseInt(dureeStr);
			int idmag = Integer.parseInt(idmagStr);
			java.sql.Date creation = new Date(Calendar.getInstance().getTimeInMillis());
			
			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();
			out.println(PromotionsTraitements.updatePromotion(ref, marque, nomprod, categorie, description, ancienprix, nouveauprix, creation, duree, active, idmag));

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



