
package hangman;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;


/**
 *
 * @author Oliver
 */
public class OrdDr_REST {
    
    ArrayList<String> ord;
        String alleOrd ="";
    
    public ArrayList<String> hentOrd(){
        
        ord = new ArrayList<String>();
        Client client = ClientBuilder.newClient();
        Response res = client.target("http://www.dr.dk/mu-online/api/1.3/list"
                + "/view/mostviewed?channel=dr1&channeltype=TV&limit=5&offset=0").
                request(MediaType.APPLICATION_JSON).get();
        
        String svar = res.readEntity(String.class);
    
        try {
            JSONObject json = new JSONObject(svar);
            System.out.println("længde" +  json.getJSONArray("Items").length());
            for (int i = 0; i < json.getJSONArray("Items").length(); i++) {
                 alleOrd +=  (json.getJSONArray("Items").getJSONObject(i).getString("SeriesTitle"));
                 alleOrd += " ";
            }
           
            alleOrd = alleOrd.toLowerCase();
            ord.clear();
            ord.addAll(new HashSet<String>(Arrays.asList(alleOrd.split(" "))));
            
        } catch (JSONException ex) {
            
            ex.printStackTrace();
        }
    
        return ord;
    }
   
    
}
