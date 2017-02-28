/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebService;
/**
 *
 * @author TheGeek
 */
@WebService
public interface HangI {
    @WebMethod public ArrayList<String> getBrugteBogstaver();
    @WebMethod  public String Getordet();
      @WebMethod public String getSynligtOrd();
    @WebMethod  public int getAntalForkertBogstaver();
     @WebMethod public boolean erSidsteBogstavKorrekt();
     @WebMethod public boolean erSpilletVundet();
      @WebMethod public boolean erSpilletTabt();
    @WebMethod  public boolean erSpilletSlut();
    @WebMethod  public void nullstill();
     @WebMethod public void opdaterSynligtord();
     @WebMethod public void gætBogstav(String bogstav);
     
     
    
}
