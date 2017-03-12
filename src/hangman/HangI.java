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
    @WebMethod public String getBrugteBogstaver(int id);
    @WebMethod public int newSession();
    @WebMethod public String Getordet(int id);
    @WebMethod public String getSynligtOrd(int id);
    @WebMethod public int getAntalForkertBogstaver(int id);
    @WebMethod public boolean erSidsteBogstavKorrekt(int id);
    @WebMethod public boolean erSpilletVundet(int id);
    @WebMethod public boolean erSpilletTabt(int id);
    @WebMethod public boolean erSpilletSlut(int id);
    @WebMethod public void nullstill(int id);
    @WebMethod public void opdaterSynligtord(int id);
    @WebMethod public void gætBogstav(String bogstav,int id);
    @WebMethod public void hentOrdFraDRREST(int id);
    @WebMethod public boolean vilSpiligen(String svar,int id);
    
    
}
