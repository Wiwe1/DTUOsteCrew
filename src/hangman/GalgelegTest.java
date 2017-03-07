package hangman;

public class GalgelegTest {

  public static void main(String[] args) {

    HangI spil = new hangImpl();
    
/*
    try {
      spil.hentOrdFraDr();
    } catch (Exception e) {
      e.printStackTrace();
    }
*/
 

    spil.gætBogstav("e");
  

    spil.gætBogstav("a");
   
    System.out.println("" + spil.getAntalForkertBogstaver());
    
    System.out.println("" + spil.getSynligtOrd());
      System.out.println(spil.Getordet());
    if (spil.erSpilletSlut()) return;

    spil.gætBogstav("i");
    
    if (spil.erSpilletSlut()) return;

    spil.gætBogstav("s");
 
    if (spil.erSpilletSlut()) return;

    spil.gætBogstav("r");
   
    if (spil.erSpilletSlut()) return;

    spil.gætBogstav("l");
   
    if (spil.erSpilletSlut()) return;

    spil.gætBogstav("b");

    if (spil.erSpilletSlut()) return;

    spil.gætBogstav("o");
 
    if (spil.erSpilletSlut()) return;

    spil.gætBogstav("t");

    if (spil.erSpilletSlut()) return;

    spil.gætBogstav("n");
   
    if (spil.erSpilletSlut()) return;

    spil.gætBogstav("m");
   
    if (spil.erSpilletSlut()) return;

    spil.gætBogstav("y");
  
    if (spil.erSpilletSlut()) return;

    spil.gætBogstav("p");

    if (spil.erSpilletSlut()) return;

    spil.gætBogstav("g");
   
    if (spil.erSpilletSlut()) return;
  }
}
