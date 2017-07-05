package it.polimi.ingsw.ps06.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.controller.MenuController;
import it.polimi.ingsw.ps06.model.events.EventClose;
import it.polimi.ingsw.ps06.model.events.StoryBoard2Room;


public class MenuCLI extends Observable implements Menu {
	
	private BufferedReader input;
	private boolean cond = true;
	
	public MenuCLI(BufferedReader input) {
		
		this.input = input;
	}

	public BufferedReader getScanner() {
		return input;
	}
	

	public void addNewControllerObserver(MenuController controller) {
		addObserver(controller);
	}
	
	@Override
	public void show() throws IOException{
		
		System.out.println("\n\n\n\n");
		
		System.out.println("  ..                                                                               ");
		System.out.println("  x .d88\"                                                                           ");
		System.out.println("   5888R          u.      .u    .                 u.    u.        ..           u.   ");
		System.out.println("   '888R    ...ue888b   .d88B :@8c       .u     x@88k u@88c.    .@88i    ...ue888b  ");
		System.out.println("    888R    888R Y888r =\"8888f8888r   ud8888.  ^\"8888\"\"8888\"   \"\"%888>   888R Y888r ");
		System.out.println("    888R    888R I888>   4888>'88\"  :888'8888.   8888  888R      '88%    888R I888> ");
		System.out.println("    888R    888R I888>   4888> '    d888 '88%\"   8888  888R    ..dILr~`  888R I888> ");
		System.out.println("    888R    888R I888>   4888>      8888.+\"      8888  888R   '\".-%88b   888R I888> ");
		System.out.println("    888R   u8888cJ888   .d888L .+   8888L        8888  888R    @  '888k u8888cJ888  ");
		System.out.println("   .888B .  \"*888*P\"    ^\"8888*\"    \'8888c. .+  \"*88*\" 8888\"  8F   8888  \"*888*P\"   ");
		System.out.println("   ^*888%     \'Y\"          \"Y\"       \"88888%      \"\"   \'Y\"   \'8    8888    \'Y\"      ");
		System.out.println("    \"%                                \"YP'                  '8    888F             ");
		System.out.println("                                                              %k  <88F              ");
		System.out.println("                                                               \"+:*%`               ");
		
		System.out.println("   .          ..                                                              .                 .                          ");
		System.out.println("   @88>  x .d88\"                                                              @88>     oec :    @88>                        ");
		System.out.println("   %8P    5888R      ..    .     :                               u.    u.     %8P     @88888    %8P                    u.   ");
		System.out.println("    .     '888R    .888: x888  x888.        u          uL      x@88k u@88c.    .      8\"*88%     .          .    ...ue888b  ");
		System.out.println("  .@88u    888R   ~`8888~'888X`?888f`    us888u.   .ue888Nc.. ^\"8888\"\"8888\"  .@88u    8b.      .@88u   .udR88N   888R Y888r ");
		System.out.println(" ''888E`   888R     X888  888X '888>  .@88 \"8888\" d88E`\"888E`   8888  888R  ''888E`  u888888> ''888E` <888'888k  888R I888> ");
		System.out.println("   888E    888R     X888  888X '888>  9888  9888  888E  888E    8888  888R    888E    8888R     888E  9888 \'Y\"   888R I888> ");
		System.out.println("   888E    888R     X888  888X '888>  9888  9888  888E  888E    8888  888R    888E    8888P     888E  9888       888R I888> ");
		System.out.println("   888E    888R     X888  888X '888>  9888  9888  888E  888E    8888  888R    888E    *888>     888E  9888      u8888cJ888  ");
		System.out.println("   888&   .888B .  \"*88%\"\"*88\" '888!` 9888  9888  888& .888E   \"*88*\" 8888\"   888&    4888      888&  ?8888u../  \"*888*P\"   ");
		System.out.println("   R888\"  ^*888%     `~    \"    `\"`   \"888*\"\"888\" *888\" 888&     \"\"   \'Y\"     R888\"   \'888      R888\"  \"8888P\'     'Y\"      ");
		System.out.println("    \"\"      \"%                         ^Y\"   ^Y'   `\"   \"888E                  \"\"      88R       \"\"      \"P\'                ");
		System.out.println("                                                  .dWi   `88E                          88>                                  ");
		System.out.println("                                                  4888~  J8%                           48                                   ");
		System.out.println("                                                   ^\"===*\"`                            '8                                   ");
																				 
		
		System.out.println(" █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ ");

		System.out.println();
		System.out.println(" 1) Nuova Partita");
		System.out.println(" 2) Esci");
		System.out.println();
		System.out.print(" > ");

		while (cond)
			try {
				readInput();
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}
	}
	
	public void readInput() throws NumberFormatException, IOException {
		
		int i = Integer.parseInt(input.readLine());

		switch (i) {
			case 1: startGame(); break;
			case 2: notifyExit(); break;
			default: System.out.println("ERRORE");
		}
	}
	
	public void showErrorMessage(String messageError) {
		System.out.println();
		System.out.print(messageError);
	}

	@Override
	public void addNewObserver(Observer o) {
		addObserver(o);
	}

	@Override
	public void startGame() {
		cond=false;
		setChanged();
		StoryBoard2Room storyBoard = new StoryBoard2Room(new RoomCLI(input));
		notifyObservers(storyBoard);
		
	}


	@Override
	public void notifyExit() {
		setChanged();
		EventClose close = new EventClose();
		notifyObservers(close);
	} 
	
}
