/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import brugerautorisation.transport.soap.Brugeradmin;
import java.rmi.Naming;
import java.util.Scanner;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
/**
 *
 * @author TheGeek
 */
public class Hangclient {
    
    public static void main(String [] arg)throws MalformedURLException{
  URL url = new URL("http://ubuntu4.javabog.dk:9972/galgelegtjeneste?wsdl");
        QName qname = new QName("http://hangman/", "hangImplService");
        
         Service service = Service.create(url, qname);
        HangI  hang = service.getPort(HangI.class); 
//         BrugeradminImpl brgimpl = service.getPort(BrugeradminImpl.class);
        
        
             Scanner scanner = new Scanner(System.in);
  System.out.println("Du skal logge ind før, at du kan spille Galgeleg");
       while (true) {
            System.out.println("Indtast dit brugernavn (studie-nr.)");
            String bruger = scanner.nextLine();
            
            System.out.println("Indtast dit password");
            String password = scanner.nextLine();
            
            
            if(validate(bruger,password))
                break;
        }      
        
        

  while(hang.erSpilletSlut()==false){
       
      System.out.println("Du skal gætte følgende ord: "+hang.getSynligtOrd());
   int antalforkertgæt=  hang.getAntalForkertBogstaver() ;
        Scanner sc = new Scanner(System.in);
       String bogstav =  sc.nextLine();
       
     // hang.gætBogstav(bogstav);
      hang.gætBogstav("x");
         hang.gætBogstav("q");
            hang.gætBogstav("w");
               hang.gætBogstav("y");
                  hang.gætBogstav("p");
                     hang.gætBogstav("v");
                        hang.gætBogstav("z");
                           hang.gætBogstav("å");
                           
                       if(hang.erSpilletTabt()==true){
                           
                           System.out.println("Spillet er tabt: "+hang.Getordet());
                           
                       }    
     System.out.println(hang.getBrugteBogstaver());
      
           
       
       
  
     System.out.println("bø");
      
  }
 
     
        
    }
     
        public static boolean validate(String studienummer,String kodeord)throws MalformedURLException{
            
         URL url = new URL("http://ubuntu4.javabog.dk:9972/brugeradmin?wsdl");
        QName qname = new QName("http://soap.transport.brugerautorisation/", "BrugeradminImplService");
        Service service = Service.create(url, qname);
        Brugeradmin ba = service.getPort(Brugeradmin.class);
            try {
               ba.hentBruger(studienummer, kodeord);
               return true;
                
            } catch (Exception e) {
                System.out.println("forkert brugernavn");
               
                e.printStackTrace();
            }
          
             return false;
        }
        
        
            
            
        }    
            
    
    
    
    

