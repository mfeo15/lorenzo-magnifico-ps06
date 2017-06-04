package it.polimi.ingsw.ps06;

import java.io.IOException;
import java.util.Scanner;

import it.polimi.ingsw.ps06.controller.RoomController;


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
    		
    		Client client = new Client(host, 12345);
    		RoomController c = new RoomController(client);
    		client.addNewObserver(c);
    		
    		client.start();
    		
    		/*
    		System.out.print("Press 1 for CLI or 2 for GUI > ");
        	
        	switch(Integer.parseInt(s.nextLine())) {
        	case 1:
        		MenuCLI viewCLI = new MenuCLI(s);
        		MenuControllerCLI controllerCLI = new MenuControllerCLI(viewCLI);
        		controllerCLI.init();
        		break;
        	case 2:
        		MenuGUI viewGUI = new MenuGUI();
        		MenuControllerGUI controllerGUI = new MenuControllerGUI(viewGUI);
        		controllerGUI.init();
        		break;
        	}
        	*/
    	}	
    }
}
