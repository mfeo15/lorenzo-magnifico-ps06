package it.polimi.ingsw.ps06;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


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
    	if ( Integer.parseInt(s.nextLine()) == 1 ) {
    		SocketServer server;
    		try {
    			server = new SocketServer();
    			server.run();
    		} catch (IOException e) {
    			System.err.println("Impossibile inizializzare il server: " + e.getMessage() + "!");
    		}
    
    		s.close();
    		return;
    	} else {
    		
    		//Connection c = new Connection( new Socket("127.0.0.1", 12345) );
    		
    		Socket socket = new Socket("127.0.0.1", 12345);
    		//Executors.newFixedThreadPool(128).submit(c);
    	}
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
