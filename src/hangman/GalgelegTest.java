package hangman;

import java.util.Scanner;

public class GalgelegTest {

  public static void main(String[] args) {

    HangI hang = new hangImpl();
    Scanner scanner = new Scanner(System.in);
 
   
   
    System.out.println("" + hang.getAntalForkertBogstaver());
    
    System.out.println("" + hang.getSynligtOrd());
      System.out.println("Du skal gætte: " + hang.Getordet());
      
      do{
        while (!hang.erSpilletSlut()) {

            System.out.println("Du skal gætte følgende ord: " + hang.getSynligtOrd());
            int antalforkertgæt = hang.getAntalForkertBogstaver();
     
            String bogstav = scanner.nextLine();
            hang.gætBogstav(bogstav);

            if (hang.erSpilletTabt() == true) {
                System.out.println("Spillet er tabt: " + hang.Getordet());
            }
            
            System.out.println("Brugte bogstaver" + hang.getBrugteBogstaver());

        }
           System.out.println("Spil igen? (y/n)");
            
        }while(hang.vilSpiligen(scanner.next()));
      
   
  }
}
