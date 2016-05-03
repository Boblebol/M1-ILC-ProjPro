package promotions.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import promotions.traitement.PromotionsTraitements;

/**
 * @api {post} /promo/det Recuperer les details d'une promotion et de son magasin
 * @apiName DetailPromo
 * @apiGroup Promotions
 * @apiVersion 2.0.0
 * 
 * @apiParam {Integer} idPromo Numero d'identification de la promotion en base.
 *
 * @apiSuccess {JSON} JSON Un JSON contenant toutes les informations de la promotion et de son magasin
 * 
 * @apiSuccessExample Reponse si succes:
 *	{
 *		"categorie":"prroduit menager",
 *		"ancienPrix":12,
 *		"referencePromo":"refExemple",
 *		"nouveauPrix":3,
 *		"idMagasin":1,
 *		"idPromo":1,
 *		"nomProduit":"ProduitDeMarque",
 *		"dureeValidite":3,
 *		"marquePromo":"Marque",
 *		"statut":"1",
 *		"nomMagasin":"ExempleMagasin",
 *		"adresseMagasin":"22 Rue de l'exemple 00000 ExempleCity",
 *		"latitude":66,78776,
 *		"longitude":69,7877
 *	}
 */

public class DetailProServlet extends HttpServlet  {
	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse rep) {
		try {
			
			String idPromoStr = req.getParameter("idPromo"); 
			int idPromo = Integer.parseInt(idPromoStr);
			
			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();
			out.println(PromotionsTraitements.detailPromotion(idPromo));

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
