package preferences.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import preferences.traitement.PreferencesTraitements;

/**
 * @api {post} /pref/delMag Supprimer un magasin dans les préférences d'un client.
 * @apiName DelPrefMag
 * @apiGroup Preferences
 * @apiVersion 2.0.0
 * 
 * @apiParam {Integer} idClient Numero d'identification du client en base.
 * @apiParam {Integer} idMagasin Numero d'identification du magasin en base.
 *
 * @apiSuccess {JSON} JSON Un JSON vide qui signifie que la modification s'est bien déroulée
 * 
 * @apiSuccessExample Reponse si succes:
 * 		{ }
 */

public class DelMagPrefServlet extends HttpServlet  {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse rep) {
		try {
			String idClientStr = req.getParameter("idClient"); 
			String idMagStr = req.getParameter("idMagasin");  
			int idClient = Integer.parseInt(idClientStr);
			int idMagasin = Integer.parseInt(idMagStr);
			
			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();
			out.println(PreferencesTraitements.DelPrefMagasin(idClient, idMagasin));

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
