package promotions.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import promotions.traitement.PromotionsTraitements;

/**
 * @api {post} /promo/listeMag Recuperer la liste des promotions d'un magasin donnee
 * @apiName ListePromoMag
 * @apiGroup Promotions
 * @apiVersion 2.0.0
 *
 * @apiParam {Integer} idMag Identifiant en base du magasin dont on veut les promotions.
 *
 * @apiSuccess {JSON[]} JSON[] Tableau de JSON contenant le nom, l'id du magasin et l'id d'une promotion.
 * 
 * @apiSuccessExample Reponse si succes:
 * 	[
 * 		{
 * 			"referencePromo":"refExemple",
 * 			"idMagasin":1,
 * 			"idPromo":1
 * 		},
 * 		{
 * 			"referencePromo":"ref2",
 * 			"idMagasin":0,
 * 			"idPromo":2
 * 		}
 * 	]
 */

public class ListeProMagServlet extends HttpServlet  {
	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse rep) {
		try {

			String idMagStr = req.getParameter("idMag"); 
			int idMag = Integer.parseInt(idMagStr);
			
			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();
			out.println(PromotionsTraitements.listePromotionMagasin(idMag));

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