package webservice;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Requete {

	public static final String BASEURL = "http://data.keolis-rennes.com/json/?";
	public static final String KEY = "1RJLZ38TUFZSWTW";
	private String cmd;
	private String version;
	private Map<String,String> params = new HashMap<String,String>();
	
	public Requete(String cmd, String version) {
		this.cmd = cmd;
		this.version = version;
	}
	
	public void addParam(String key, String value) {
		params.put(key, value);
	}
	
	public String getURL() {
		String url = BASEURL;
		url = BASEURL + "cmd="+cmd;
		url = ajouterParametre(url, "version", version, false);
		url = ajouterParametre(url, "key", KEY, false);
		for (Entry<String,String> entry : params.entrySet()) {
			url = ajouterParametre(url, entry.getKey(), entry.getValue(), true);
		}
		return url;
	}
	
	private static String ajouterParametre(String url,String key, String value,boolean subparam) {
		return url + "&" + (subparam ? "param["+key+"]" : key) + "=" + value;
	}
}
