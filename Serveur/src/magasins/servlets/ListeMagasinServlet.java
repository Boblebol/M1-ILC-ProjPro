package magasins.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import magasins.traitement.MagasinsTraitements;

/**
 * @api {post} /magasin/liste Lister les magasins
 * @apiName ListeMagasin
 * @apiGroup Magasins
 * @apiVersion 2.0.0
 * 
 * @apiSuccess {JSON[]} JSON[] Tableau de JSON contenant le nom, l'adresse et l'id d'un magasins.
 * 
 * @apiSuccessExample Reponse si succes:
 * 	[
 * 		{
 * 			"nomMagasin":"ExempleA",
 * 			"addresseMagasin":"12 rue A",
 * 			"idMagasin":1
 * 		},
 * 		{
 * 			"nomMagasin":"ExempleB",
 * 			"addresseMagasin":"18 rue B",
 * 			"idMagasin":2
 * 		}
 * 	]
 */

public class ListeMagasinServlet extends HttpServlet  {
	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse rep) {
		try { 
			
			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();
			out.println(MagasinsTraitements.listeMagasin());

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
