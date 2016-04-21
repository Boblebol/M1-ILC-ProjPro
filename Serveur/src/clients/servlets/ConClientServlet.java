package clients.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clients.traitement.ClientsTraitements;

/**
 * @api {post} /client/con Connecter un client
 * @apiName ConClient
 * @apiGroup Clients
 * @apiVersion 2.0.0
 * 
 * @apiParam {String} mail Adresse mail d'un client.
 * @apiParam {String} mdp Mot de passe d'un client.
 * 
 * @apiSuccess {JSON} JSON Un JSON contenant l'id en base, le mail, le nom et le prénom du client qui viens de se connecter.
 * 
 * @apiSuccessExample Reponse si succes:
 *{ 
 *	"mail": "nom.prenom@exemple.com",
 * 	"id": 23,
 * 	"nom": "nom",
 * 	"prenom": "prenom"
 * } 
 */

public class ConClientServlet extends HttpServlet  {
	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse rep) {
		try {
			String mail = req.getParameter("mail"); 
			String mdp = req.getParameter("mdp");  
			
			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();
			out.println(ClientsTraitements.connexionClient(mail, mdp));

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
