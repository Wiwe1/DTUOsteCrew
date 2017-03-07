/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import static java.lang.System.in;
import java.rmi.Naming;
import java.rmi.Remote;
import java.util.Scanner;
import javax.xml.ws.Endpoint;
import brugerautorisation.transport.soap.BrugeradminImpl;
import brugerautorisation.server.Brugerdatabase;


/**
 *
 * @author TheGeek
 */
public class server {
    
   public static void main(String [] args)throws Exception{
       
       HangI hang = new hangImpl();
       Brugerdatabase db = Brugerdatabase.getInstans();
       BrugeradminImpl brgimpl = new BrugeradminImpl();
       brgimpl.db=db;

		
    // Ipv6-addressen [::] svarer til Ipv4-adressen 0.0.0.0, der matcher alle maskinens netkort og 
		Endpoint.publish("http://ubuntu4.javabog.dk:9902/galgelegtjeneste", hang);
             //Endpoint.publish("http://ubuntu4.javabog.dk:9972/BrugerLogin",brgimpl );
		System.out.println("Galgelegtjeneste publiceret.");
           	System.out.println("Bruger ting");
       
       
   }
    
   
    
}
