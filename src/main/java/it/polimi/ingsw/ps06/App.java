package it.polimi.ingsw.ps06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import it.polimi.ingsw.ps06.controller.MenuController;
import it.polimi.ingsw.ps06.networking.Client;
import it.polimi.ingsw.ps06.networking.SocketServer;
import it.polimi.ingsw.ps06.view.Menu;
import it.polimi.ingsw.ps06.view.MenuCLI;
import it.polimi.ingsw.ps06.view.MenuGUI;

/**
 * Classe di partenza dell'appliazione
 *
 * @author  ps06
 * @version 1.1
 * @since   2017-05-01
 */
public class App 
{
	
	
    /**
     * Metodo main dell'applicazione
     * 
     * @param 	args			possibili parametri in ingresso - non richiesti e gestiti
     * 
     * @throws 	IOException		se il metodo setup() lancia un eccezione
     */
    public static void main( String[] args ) throws IOException
    {
        System.out.println( "Lorenzo il magnifico!");
        
        setup();
    }
    
    /**
     * <p>Metodo per il setup iniziale dell'applicativo.</p>
     * <p>Si occupa di impostare Server/Client, indirizzo ip di connessione, interfaccia CLI/GUI</p>
     * 
     * @throws IOException	se lo stream di lettura dall'utente in fase di setup genera errori
     */
    public static void setup() throws IOException {
    	InputStreamReader reader = new InputStreamReader(System.in);
    	BufferedReader in = new BufferedReader(reader);
    	
    	System.out.print("\n" + "Press 1 for Server or 2 for Client > ");
    	if ( Integer.parseInt(in.readLine()) == 1 ) 
    	{
    		SocketServer.getInstance().start();
    
    		in.close();
    		return;
    	} 
    	
    	else 
    	{	
    		System.out.print("Insert the Server IP > ");
    		
    		String host = in.readLine();
    		Client.getInstance().setupParameters(host, 12345);
    		
    		System.out.print("\n" + "Press 1 for CLI or 2 for GUI > ");
    		Menu menuView = ( Integer.parseInt(in.readLine()) == 1 ) ? new MenuCLI(in) : new MenuGUI();
    		
    		System.out.println("\n" + "\n");
				
			MenuController controller = new MenuController(menuView);
			menuView.addNewObserver(controller);
			menuView.show();
      }
    }
}
