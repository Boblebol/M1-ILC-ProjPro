package promotions.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import promotions.traitement.PromotionsTraitements;

/**
 * @api {post} /promo/del Supprimer une promotion
 * @apiName DelPromo
 * @apiGroup Promotions
 * @apiVersion 2.0.0
 * 
 * @apiParam {String} ref Nom de la promotion.
 * @apiParam {Integer} idmag Numero d'identification du magasin qui poste la promotion en base.
 *
 * @apiSuccess {JSON} JSON Un JSON vide qui signifie que la suppression s'est bien déroulé
 *
 * @apiSuccessExample Reponse si succes:
 * 		{ }
 */

public class DelProServlet extends HttpServlet  {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse rep) {
		try {
			
			String ref = req.getParameter("ref"); 
			String idmagStr = req.getParameter("idmag"); 
			int idmag = Integer.parseInt(idmagStr);
			
			
			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();
			out.println(PromotionsTraitements.DelPromotion(ref, idmag));

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
