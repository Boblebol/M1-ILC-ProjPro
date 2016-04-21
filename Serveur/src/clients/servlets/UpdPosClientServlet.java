package clients.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clients.traitement.ClientsTraitements;

/**
 * @api {post} /client/updPos Mettre à jour la position d'un client
 * @apiName UpdPosClient
 * @apiGroup Clients
 * @apiVersion 2.0.0
 *
 * @apiParam {String} mail Adresse mail d'un client.
 * @apiParam {Integer} longitude Nouvelle longitude d'un client.
 * @apiParam {Integer} lattitude Nouvelle lattitude d'un client.
 * 
 * @apiSuccess {JSON} JSON Un JSON vide qui signifie que la modification s'est bien déroulée
 * 
 * @apiSuccessExample Reponse si succes:
 *     { }  

 */

public class UpdPosClientServlet  extends HttpServlet  {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse rep) {
		try {
			String mail = req.getParameter("mail");  
			String longitudeStr = req.getParameter("longitude");
			String lattitudeStr = req.getParameter("lattitude");
			int longitude = Integer.parseInt(longitudeStr);
			int lattitude = Integer.parseInt(lattitudeStr);

			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();
			out.println(ClientsTraitements.updatePosClient (mail,longitude,lattitude));

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