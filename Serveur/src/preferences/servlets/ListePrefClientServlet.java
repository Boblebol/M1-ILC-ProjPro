package preferences.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import preferences.traitement.PreferencesTraitements;

/**
 * @api {post} /pref/liste Lister les préférences d'un client.
 * @apiName ListePrefClient
 * @apiGroup Preferences
 * @apiVersion 2.0.0
 * 
 * @apiParam {Integer} idClient Numero d'identification du client en base.
 *
 * @apiSuccess {JSON[]} JSON[] Un JSONARRAY composé de JSON contenant les préférences d'un client
 * 
 * @apiSuccessExample Reponse si succes:
 * 	[
 * 		{
 * 			"idAssociation":7,
 * 			"idClient":5,
 * 			"nomMagasin":"mag1",
 * 			"idMagasin":6
 * 		},
 * 		{
 * 			"idAssociation":8,
 * 			"categorie":"lolo",
 * 			"idClient":5
 * 		}
 * 	]
 */

public class ListePrefClientServlet extends HttpServlet  {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse rep) {
		try {
			String idClientStr = req.getParameter("idClient");  
			int idClient = Integer.parseInt(idClientStr);
			
			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();
			out.println(PreferencesTraitements.ListePref(idClient));

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
