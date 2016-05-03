package promotions.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import promotions.traitement.PromotionsTraitements;

/**
 * @api {post} /promo/listeCat Recuperer la liste des promotions d'une categorie donnee
 * @apiName ListePromoCat
 * @apiGroup Promotions
 * @apiVersion 2.0.0
 * 
 * @apiParam {String} categorie Categorie de promotions à chercher.
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

public class ListeProCatServlet extends HttpServlet  {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse rep) {
		try {

			String cat = req.getParameter("cat"); 
			
			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();
			out.println(PromotionsTraitements.listePrommotionsCat(cat));

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