package promotions.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import promotions.traitement.PromotionsTraitements;

/**
 * @api {post} /promo/ListeGeoCat Recuperer la liste des promotions d'une categorie dans le rayon d'un endroit donné 
 * @apiName ListeGeoProCat
 * @apiGroup Promotions
 * @apiVersion 2.0.0
 *
 * @apiParam {String} categorie Categorie de promotions à chercher.
 * @apiParam {Integer} distance Rayon de recherche de promotions autour d'un point de référence donné.
 * @apiParam {Float} lattitude Latitude du point de référence.
 * @apiParam {Float} longitude Longitude du point de référence.
 *
 * @apiSuccess {JSON[]} JSON[] Tableau de JSON contenant le nom, l'id et l'adresse du magasin, l'id et la description d'une promotion.
 * 
 * @apiSuccessExample Reponse si succes:
 * 	[
 * 		{
 * 			"referencePromo":"refExemple",
 * 			"idMagasin":1,
 * 			"idPromo":1,
 * 			"description" : "une courte desc.",
 * 			"adresse" : "AdresseDuMagasin"
 * 		},
 * 		{
 * 			"referencePromo":"ref2",
 * 			"idMagasin":0,
 * 			"idPromo":2,
 * 			"description" : "une courte desc.",
 * 			"adresse" : "AdresseDuMagasin"
 * 		}
 * 	]
 */

public class ListeGeoProCatServlet  extends HttpServlet  {
	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse rep) {
		try {
			
			String categorie = req.getParameter("categorie"); 
			String distanceStr = req.getParameter("distance"); 
			String latitudeStr = req.getParameter("lattitude");
			String longitudeStr = req.getParameter("longitude");
			int distance = Integer.parseInt(distanceStr);
			float latitude = Float.parseFloat(latitudeStr);
			float longitude = Float.parseFloat(longitudeStr);
			
			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();
			out.println(PromotionsTraitements.listePromotionCategorieProche(categorie, distance, latitude, longitude));

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
