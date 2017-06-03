package it.polimi.ingsw.ps06;

import java.io.IOException;
import java.util.Scanner;

import it.polimi.ingsw.ps06.controller.MenuControllerCLI;
import it.polimi.ingsw.ps06.controller.MenuControllerGUI;
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
    }
}
