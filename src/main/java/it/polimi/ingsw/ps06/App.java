package it.polimi.ingsw.ps06;

import java.io.IOException;
import java.util.Scanner;

import it.polimi.ingsw.ps06.controller.MenuController;
import it.polimi.ingsw.ps06.view.GUI.Menu;
import it.polimi.ingsw.ps06.view.GUI.MenuCLI;
import it.polimi.ingsw.ps06.view.GUI.MenuGUI;


public class App 
{
    public static void main( String[] args ) throws IOException
    {
        System.out.println( "Lorenzo il magnifico!");
        
        setup();
    }
    
    public static void setup() throws IOException {
    	Scanner s = new Scanner(System.in);
    	
    	System.out.print("Press 1 for Server or 2 for Client > ");
    	if ( Integer.parseInt(s.nextLine()) == 1 ) 
    	{
    		SocketServer server;
    		try {
    			server = new SocketServer();
    			server.start();
    		} catch (IOException e) {
    			System.err.println("Impossibile inizializzare il server: " + e.getMessage() + "!");
    		}
    
    		s.close();
    		return;
    	} 
    	else 
    	{	
    		System.out.print("Insert the Server IP > ");
    		
    		String host = s.nextLine();
    		Client client = Client.getInstance(host, 12345);
    		
    		(new Thread(client)).start();
    		
    		System.out.print("Press 1 for CLI or 2 for GUI > ");
    		Menu menuView = ( Integer.parseInt(s.nextLine()) == 1 ) ? new MenuCLI(s) : new MenuGUI();
				
			MenuController controller = new MenuController(menuView);
			menuView.addNewObserver(controller);
			menuView.show();

      }
    }
}
