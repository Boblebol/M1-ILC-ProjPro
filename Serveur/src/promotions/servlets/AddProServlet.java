package promotions.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import promotions.traitement.PromotionsTraitements;

/**
 * @api {post} /promo/add Ajouter une promotion.
 * @apiName AddPromo
 * @apiGroup Promotions
 * @apiVersion 2.0.0
 * 
 * @apiParam {String} ref Nom de la promotion.
 * @apiParam {String} marque Marque du produit.
 * @apiParam {String} nomprod Nom du produit.
 * @apiParam {String} categorie Categorie du produit.
 * @apiParam {String} description Description du produit.
 * @apiParam {Float} ancienprix Ancien prix du produit.
 * @apiParam {Float} nouveauprix Nouveau prix du produit.
 * @apiParam {Integer} duree Durée en jour de la promotion.
 * @apiParam {Integer} active La promotion est-elle active ? (1 ou 0)
 * @apiParam {Integer} idmag Numero d'identification du magasin qui poste la promotion en base.
 * 
 * @apiSuccess {JSON} JSON Un JSON vide qui signifie que l'ajout s'est bien déroulé
 * 
 * @apiSuccessExample Reponse si succes:
 * 		{ }
 */

public class AddProServlet extends HttpServlet  {
	
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
			String activeStr = req.getParameter("active"); 
			String idmagStr = req.getParameter("idmag");  
			float ancienprix = Float.parseFloat(ancienprixStr);
			float nouveauprix = Float.parseFloat(nouveauprixStr);
			int duree = Integer.parseInt(dureeStr);
			int idmag = Integer.parseInt(idmagStr);
			int active = Integer.parseInt(activeStr);
			//java.sql.Date creation = new Date(Calendar.getInstance().getTimeInMillis());
			
			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();
			out.println(PromotionsTraitements.AddPromotion(ref, marque, nomprod, categorie, description, ancienprix, nouveauprix, /*creation,*/ duree, active, idmag));

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
