package settings.traitementSettings;

import org.json.JSONObject;

public class TraitementTools {
	
	static JSONObject ret;
	
	
	/**
	 * JSON d'erreur
	 * 
	 * @param message
	 * @param code_erreur
	 * @return un JSON avec le message d'erreur et son code
	 */
	public static JSONObject JSONerreur(String message, int code_erreur) {
		try {
			ret = new JSONObject();
			ret.put("error", message);
			ret.put("error_code", code_erreur);
			return ret;
		} catch (Exception e) {
			return (null);
		}
	}

	
	/**
	 * Le JSON ok
	 * 
	 * @return un JSON vide
	 */
	public static JSONObject JSONok(){
		return new JSONObject();
	}
	
	
	/**
	 * JSON d'erreur dans la BD
	 * 
	 * @param string
	 * @return un JSON avec le message provenant de la BD et le code d'erreur
	 *         999
	 */
	public static JSONObject JSONBDerreur(String string) {
		try {
			JSONObject jo = new JSONObject();
			jo.put("error", "Erreur dans la BD -> " + string);
			jo.put("error_code", 999);
			return jo;
		} catch (Exception e) {
			return (null);
		}
	}
}
