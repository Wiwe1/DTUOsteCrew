/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author TheGeek
 */
@WebService(endpointInterface = "hangman.HangI")
public class hangImpl implements HangI {
    

 private ArrayList<String> muligeOrd = new ArrayList<String>();
  private String ordet;
  private ArrayList<String> brugteBogstaver = new ArrayList<String>();
  private String synligtOrd;
  private int antalForkerteBogstaver;
  private boolean sidsteBogstavVarKorrekt;
  private boolean spilletErVundet;
  private boolean spilletErTabt;    
  
  public hangImpl(){
      
       muligeOrd.add("bil");
    muligeOrd.add("computer");
    muligeOrd.add("programmering");
    muligeOrd.add("motorvej");
    muligeOrd.add("busrute");
    muligeOrd.add("gangsti");
    muligeOrd.add("skovsnegl");
    muligeOrd.add("solsort");
    nullstill();
      
  }

    @Override
    public ArrayList<String> getBrugteBogstaver() {
               return brugteBogstaver;
    }

    @Override
    public String Getordet() {
        return ordet;
    }

    @Override
    public String getSynligtOrd() {
       return synligtOrd;
    }

    @Override
    public int getAntalForkertBogstaver() {
     return   antalForkerteBogstaver;
    }

    @Override
    public boolean erSidsteBogstavKorrekt() {
        return sidsteBogstavVarKorrekt;
    }

    @Override
    public boolean erSpilletVundet() {
     return spilletErVundet;
    }

    @Override
    public boolean erSpilletTabt() {
        return spilletErTabt;
    }

    @Override
    public boolean erSpilletSlut() {
             return spilletErTabt || spilletErVundet;
    }

    @Override
    public void nullstill() {
       brugteBogstaver.clear();
    antalForkerteBogstaver = 0;
    spilletErVundet = false;
    spilletErTabt = false;
    ordet = muligeOrd.get(new Random().nextInt(muligeOrd.size()));
       opdaterSynligtord();
    //  opdaterSynligtOrd();
    }

    @Override
    public void opdaterSynligtord() {
        synligtOrd = "";
    spilletErVundet = true;
    for (int n = 0; n < ordet.length(); n++) {
      String bogstav = ordet.substring(n, n + 1);
      if (brugteBogstaver.contains(bogstav)) {
        synligtOrd = synligtOrd + bogstav;
      } else {
        synligtOrd = synligtOrd + "*";
        spilletErVundet = false;
      }
    }
    }

    @Override
    public void gætBogstav(String bogstav) {
      if (bogstav.length() != 1) return;
    System.out.println("Der gættes på bogstavet: " + bogstav);
    if (brugteBogstaver.contains(bogstav)) return;
    if (spilletErVundet || spilletErTabt) return;

    brugteBogstaver.add(bogstav);

    if (ordet.contains(bogstav)) {
      sidsteBogstavVarKorrekt = true;
      System.out.println("Bogstavet var korrekt: " + bogstav);
    } else {
      // Vi gættede på et bogstav der ikke var i ordet.
      sidsteBogstavVarKorrekt = false;
      System.out.println("Bogstavet var IKKE korrekt: " + bogstav);
      antalForkerteBogstaver = antalForkerteBogstaver + 1;
      if (antalForkerteBogstaver > 6) {
        spilletErTabt = true;
      }
    }
   opdaterSynligtord();
    }
    
   
    

    
    
}
