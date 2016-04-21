package clients.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clients.traitement.ClientsTraitements;

/**
 * @api {post} /client/del Supprimer un client
 * @apiName DelClient
 * @apiGroup Clients
 * @apiVersion 2.0.0
 * 
 * @apiParam {String} mail Adresse mail d'un client.
 * @apiParam {String} mdp Mot de passe d'un client.
 * 
 * @apiSuccess {JSON} JSON Un JSON vide qui signifie que la suppression s'est bien déroulée
 * 
 * @apiSuccessExample Reponse si succes:
 *		{ }  

 */

public class SupClientServlet extends HttpServlet  {
	 
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse rep) {
		try {
			String mail = req.getParameter("mail"); 
			String mdp = req.getParameter("mdp");  
			
			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();
			out.println(ClientsTraitements.DelClient (mail,mdp));

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

