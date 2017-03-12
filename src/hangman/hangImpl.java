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
 * @author TheGeek & TheBlackKoala
 */
@WebService(endpointInterface = "hangman.HangI")

public class hangImpl implements HangI {
    private final int range = 1000000000;
    private ArrayList<Integer> ids = new ArrayList<Integer>();
    private ArrayList<String> muligeOrd = new ArrayList<String>();
    private ArrayList<String> ordene = new ArrayList<String>();
    private ArrayList<String> brugteBogstaver = new ArrayList<String>();
    private ArrayList<String> synligtOrd = new ArrayList<String>();
    private ArrayList<Integer> antalForkerteBogstaver = new ArrayList<Integer>();
    private ArrayList<Boolean> sidsteBogstavVarKorrekt = new ArrayList<Boolean>();
    private ArrayList<Boolean> spilletErVundet = new ArrayList<Boolean>();
    private ArrayList<Boolean> spilletErTabt = new ArrayList<Boolean>();
    private ArrayList<Boolean> spilIgen = new ArrayList<Boolean>();
    private int highscore;

    @Override
    public int newSession(){
        while (true){
            int id = (int)(Math.random() * range);
            if(ids.indexOf(id)==-1){
                ids.add(id);
                ordene.add("");
                brugteBogstaver.add("");
                synligtOrd.add("");
                antalForkerteBogstaver.add(0);
                sidsteBogstavVarKorrekt.add(false);
                spilletErVundet.add(true);
                spilletErTabt.add(true);
                spilIgen.add(true);
                return id;
            }
        }
    }
    
    public hangImpl(){
    }

    @Override
    public String getBrugteBogstaver(int id) {
        return brugteBogstaver.get(ids.indexOf(id));
    }
    
    @Override
    public void hentOrdFraDRREST(int id) {
       
        OrdDr_REST rest = new OrdDr_REST();
        
          
         
        muligeOrd = rest.hentOrd();
        System.out.println("alleOrd " + rest.alleOrd);
        System.out.println("Ord hentet fra dr: " + muligeOrd);
        nullstill(id);
    }
    
    

    @Override
    public String Getordet(int id) {
        return ordene.get(ids.indexOf(id));
    }

    @Override
    public String getSynligtOrd(int id) {
        return synligtOrd.get(ids.indexOf(id));
    }

    @Override
    public int getAntalForkertBogstaver(int id) {
        return   antalForkerteBogstaver.get(ids.indexOf(id));
    }

    @Override
    public boolean erSidsteBogstavKorrekt(int id) {
        return sidsteBogstavVarKorrekt.get(ids.indexOf(id));
    }

    @Override
    public boolean erSpilletVundet(int id) {
        return spilletErVundet.get(ids.indexOf(id));
    }

    @Override
    public boolean erSpilletTabt(int id) {
        return spilletErTabt.get(ids.indexOf(id));
    }

    @Override
    public boolean erSpilletSlut(int id) {
        return spilletErTabt.get(ids.indexOf(id)) || spilletErVundet.get(ids.indexOf(id));
    }

    @Override
    public void nullstill(int id) {
        int ind = ids.indexOf(id);
        brugteBogstaver.set(ind, "");
        antalForkerteBogstaver.set(ind, 0);
        spilletErVundet.set(ind,false);
        spilletErTabt.set(ind,false);
        ordene.set(ind, muligeOrd.get(new Random().nextInt(muligeOrd.size())));
        opdaterSynligtord(id);
        //  opdaterSynligtOrd();
    }

    @Override
    public void opdaterSynligtord(int id) {
        int ind = ids.indexOf(id);
        String ordCheck = "";
        String brugte = brugteBogstaver.get(ind);
        String ord = ordene.get(ind);
        boolean vundet = true;
        for (int n = 0; n < ord.length(); n++) {
            String bogstav = ord.substring(n, n + 1);
            if (brugte.contains(bogstav)) {
                ordCheck = ordCheck + bogstav;
            } else {
                ordCheck = ordCheck + "*";
                vundet = false;
            }
        }
        spilletErVundet.set(ind,vundet);
        synligtOrd.set(ind, ordCheck);
    }

    @Override
    public void gætBogstav(String bogstav, int id) {
        if (bogstav.length() != 1) return;
        int ind = ids.indexOf(id);
        System.out.println("Der gættes på bogstavet: " + bogstav);
        if (brugteBogstaver.get(ind).contains(bogstav)) return;
        if (spilletErVundet.get(ind) || spilletErTabt.get(ind)) return;

        brugteBogstaver.set(ind, brugteBogstaver.get(ind) + bogstav);

        if (ordene.get(ind).contains(bogstav)) {
            sidsteBogstavVarKorrekt.set(ind, true);
            System.out.println("Bogstavet var korrekt: " + bogstav);
        } else {
            // Vi gættede på et bogstav der ikke var i ordet.
            sidsteBogstavVarKorrekt.set(ind, false);
            System.out.println("Bogstavet var IKKE korrekt: " + bogstav);
            antalForkerteBogstaver.set(ind, antalForkerteBogstaver.get(ind) + 1);
            if (antalForkerteBogstaver.get(ind) > 6) {
                spilletErTabt.set(ind, true);
            }
        }
        opdaterSynligtord(id);
    }

    @Override
    public boolean vilSpiligen(String svar, int id) {
        if(svar.equals("y")){
            nullstill(id);
            return true;
        }
        else {
            int ind = ids.indexOf(id);
            ids.remove(ind);
            ordene.remove(ind);
            brugteBogstaver.remove(ind);
            synligtOrd.remove(ind);
            antalForkerteBogstaver.remove(ind);
            sidsteBogstavVarKorrekt.remove(ind);
            spilletErVundet.remove(ind);
            spilletErTabt.remove(ind);
            spilIgen.remove(ind);
            return false;
        }
    }
}
