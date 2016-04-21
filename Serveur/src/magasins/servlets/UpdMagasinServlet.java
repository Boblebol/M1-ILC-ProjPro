package magasins.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import magasins.traitement.MagasinsTraitements;

/**
 * @api {post} /magasin/upd Mettre à jour un magasin
 * @apiName UpdateMagasin
 * @apiGroup Magasins
 * @apiVersion 2.0.0
 * 
 * @apiParam {String} mail Adresse mail d'un magasin.
 * @apiParam {String} mdp Mot de passe d'un magasin.
 * @apiParam {String} nom Nouveau nom de d'un magasin.
 * @apiParam {String} adresse Nouvelle adresse d'un magasin.
 *
 * @apiSuccess {JSON} JSON Un JSON Un JSON vide qui signifie que la modification s'est bien déroulée
 * 
 *
 * @apiSuccessExample Reponse si succes:
 * 		{ }
 */

public class UpdMagasinServlet extends HttpServlet  {
	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse rep) {
		try {
			String mail = req.getParameter("mail"); 
			String mdp = req.getParameter("mdp"); 
			String nom = req.getParameter("nom"); 
			String adresse = req.getParameter("adresse");  
			
			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();
			out.println(MagasinsTraitements.updateMagasin(mail, mdp, nom, adresse));

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
