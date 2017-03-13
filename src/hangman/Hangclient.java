/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import brugerautorisation.transport.soap.Brugeradmin;
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

    public static void main(String[] arg) throws MalformedURLException {
        URL url = new URL("http://ubuntu4.javabog.dk:9902/galgelegtjeneste?wsdl");
        QName qname = new QName("http://hangman/", "hangImplService");
        int session;
        Service service = Service.create(url, qname);
        HangI hang = service.getPort(HangI.class);
//         BrugeradminImpl brgimpl = service.getPort(BrugeradminImpl.class);
        session = hang.newSession();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Du skal logge ind før, at du kan spille Galgeleg");
        while (true) {
            System.out.println("Indtast dit brugernavn (studie-nr.)");
            String bruger = scanner.nextLine();

            System.out.println("Indtast dit password");
            String password = scanner.nextLine();

            if (validate(bruger, password)) {
                break;
            }
        }
        System.out.println("Hej" + hang.erSpilletSlut(session));
        
        do{
        while (!hang.erSpilletSlut(session)) {

            System.out.println("Du skal gætte følgende ord: " + hang.getSynligtOrd(session));
            int antalforkertgæt = hang.getAntalForkertBogstaver(session);
     
            String bogstav = scanner.nextLine();
            hang.gætBogstav(bogstav, session);

            if (hang.erSpilletTabt(session) == true) {
                System.out.println("Spillet er tabt: " + hang.Getordet(session));
            }
            
            System.out.println("Brugte bogstaver" + hang.getBrugteBogstaver(session));

        }
            System.out.println("Spil igen?(y/n)");
            
        }while(hang.vilSpiligen(scanner.next(), session));

    }

    public static boolean validate(String studienummer, String kodeord) throws MalformedURLException {

        URL url = new URL("http://javabog.dk:9901/brugeradmin?wsdl");
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
