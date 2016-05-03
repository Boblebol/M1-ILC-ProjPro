package magasins.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import magasins.traitement.MagasinsTraitements;

/**
 * @api {post} /magasin/det Recuperer les details d'un magasin
 * @apiName DetMagasin
 * @apiGroup Magasins
 * @apiVersion 2.0.0
 * 
 * @apiParam {Integer} id Numero d'identification du magasin en base.
 * @apiParam {String} nom Nom du magasin.
 * 
 * @apiSuccess {JSON} JSON Un JSON contenant l'adresse mail, l'adresse, le nom, l'id en base, la latitude et la longitude du magasin.
 * 
 * @apiSuccessExample Reponse si succes:
 *{ 
 *	"mailMagasin": "Magasin10@exemple.com",
 *	"nomMagasin": "nomMagasin",
 * 	"addresseMagasin": "17 rue de l'exemple 11111 Exemple"	
 * 	"idMagasin": 44,
 * 	"LatitudeMagasin": 22,
 * 	"LongitudeMagasin": 1
 * }
 */


public class DetailMagasinServlet extends HttpServlet  {
	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse rep) {
		try {
			String idStr = req.getParameter("id"); 
			String nom = req.getParameter("nom");  
			int id = Integer.parseInt(idStr);
			
			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();
			out.println(MagasinsTraitements.detailMagasin(id, nom));

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
