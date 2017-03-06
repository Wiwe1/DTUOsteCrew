
package hangman;


import java.util.ArrayList;
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
    
    public ArrayList<String> hentOrd(){
        
        ord = new ArrayList<String>();
        Client client = ClientBuilder.newClient();
        Response res = client.target("http://www.dr.dk/mu-online/api/1.3/list"
                + "/view/mostviewed?channel=dr1&channeltype=TV&limit=5&offset=0").
                request(MediaType.APPLICATION_JSON).get();
        
        String svar = res.readEntity(String.class);
        
        try {
            JSONObject json = new JSONObject(svar);
            
            ord.add(json.getJSONArray("Items").getJSONObject(0).getString("SeriesTitle"));
            ord.add(json.getJSONArray("Items").getJSONObject(1).getString("SeriesTitle"));
            ord.add(json.getJSONArray("Items").getJSONObject(2).getString("SeriesTitle"));
            
            
            
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        
        return ord;
    }
   
    
}
