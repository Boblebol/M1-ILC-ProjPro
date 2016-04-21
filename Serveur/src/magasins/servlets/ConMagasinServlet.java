package magasins.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import magasins.traitement.MagasinsTraitements;

/**
 * @api {post} /magasin/con Connecter un magasin
 * @apiName ConMagasin
 * @apiGroup Magasins
 * @apiVersion 2.0.0
 * 
 * @apiParam {String} mail Adresse mail d'un magasin.
 * @apiParam {String} mdp Mot de passe d'un magasin.
 * 
 * @apiSuccess {JSON} JSON Un JSON contenant l'addresse mail, l'id en base et le nom du magasin qui viens de se connecter
 * 
 * @apiSuccessExample Reponse si succes:
 *{ 
 *	"mail": "Magasin10@exemple.com",
 * 	"id": 44,
 * 	"nom": "nomMagasin",
 * }
 * 
 */

public class ConMagasinServlet  extends HttpServlet  {
	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse rep) {
		try {
			String mail = req.getParameter("mail");  
			String mdp = req.getParameter("mdp");  
			
			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();
			out.println(MagasinsTraitements.connexionMagasin(mail, mdp));

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
