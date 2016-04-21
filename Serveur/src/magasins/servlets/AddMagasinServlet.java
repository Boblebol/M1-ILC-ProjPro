package magasins.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import magasins.traitement.MagasinsTraitements;

/**
 * @api {post} /magasin/add Ajouter un nouveau magasin
 * @apiName AddMagasin
 * @apiGroup Magasins
 * @apiVersion 2.0.0
 * 
 * @apiParam {String} mail Adresse mail du nouveau magasin.
 * @apiParam {String} mdp Mot de passe du nouveau magasin.
 * @apiParam {String} nom Nom du nouveau magasin.
 * @apiParam {String} adresse Adresse du nouveau magasin.
 * @apiParam {Float} latitude Latitude du nouveau magasin.
 * @apiParam {Float} longitude Longitude du nouveau magasin.
 * 
 * @apiSuccess {JSON} JSON Un JSON vide qui signifie que l'ajout s'est bien déroulé
 * 
 * @apiSuccessExample Reponse si succes:
 * 		{ }
 */

public class AddMagasinServlet extends HttpServlet  {
	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse rep) {
		try {
			String mail = req.getParameter("mail"); 
			String mdp = req.getParameter("mdp"); 
			String nom = req.getParameter("nom"); 
			String adresse = req.getParameter("adresse"); 
			String latitudeStr = req.getParameter("latitude");
			String longitudeStr = req.getParameter("longitude");
			float latitude = Float.parseFloat(latitudeStr);
			float longitude = Float.parseFloat(longitudeStr);
			
			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();
			out.println(MagasinsTraitements.AddMagasin (mail, mdp, nom, adresse,latitude,longitude));

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
