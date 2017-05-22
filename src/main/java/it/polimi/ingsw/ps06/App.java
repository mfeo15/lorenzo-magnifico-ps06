package it.polimi.ingsw.ps06;

import java.util.Scanner;

import it.polimi.ingsw.ps06.controller.MenuControllerCLI;
import it.polimi.ingsw.ps06.controller.MenuControllerGUI;
import it.polimi.ingsw.ps06.view.CLI.MenuCLI;
import it.polimi.ingsw.ps06.view.GUI.TestGUI;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Lorenzo il magnifico!");
        
        setup();
    }
    
    public static void setup() {
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
