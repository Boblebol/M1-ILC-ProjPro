package clients.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clients.traitement.ClientsTraitements;

/**
 * @api {post} /client/add Ajouter un nouveau client
 * @apiName AddClient
 * @apiGroup Clients
 * @apiVersion 2.0.0
 * 
 * @apiParam {String} mail Adresse mail du nouveau client.
 * @apiParam {String} mdp Mot de passe du nouveau client.
 * @apiParam {String} nom Nom du nouveau client.
 * @apiParam {String} prenom Prenom du nouveau client.
 * 
 *  * @apiSuccess {JSON} JSON Un JSON vide qui signifie que l'ajout s'est bien déroulé
 * 
 * @apiSuccessExample Reponse si succes:
 * 		{ }  
 */

public class AddClientServlet  extends HttpServlet  {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse rep) {
		try {
			String mail = req.getParameter("mail"); 
			String mdp = req.getParameter("mdp"); 
			String nom = req.getParameter("nom"); 
			String prenom = req.getParameter("prenom");  
			
			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();
			out.println(ClientsTraitements.AddClient(mail,mdp,nom,prenom,0,0));

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
