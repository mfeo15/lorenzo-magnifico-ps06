package it.polimi.ingsw.ps06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import it.polimi.ingsw.ps06.controller.MenuController;
import it.polimi.ingsw.ps06.view.Menu;
import it.polimi.ingsw.ps06.view.MenuCLI;
import it.polimi.ingsw.ps06.view.MenuGUI;


public class App 
{
    public static void main( String[] args ) throws IOException
    {
        System.out.println( "Lorenzo il magnifico!");
        
        setup();
    }
    
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
