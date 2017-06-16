package it.polimi.ingsw.ps06.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import it.polimi.ingsw.ps06.controller.BoardController;
import it.polimi.ingsw.ps06.model.DevelopementCard;
import it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;
import it.polimi.ingsw.ps06.model.events.BoardHasLoaded;
import it.polimi.ingsw.ps06.model.events.EventClose;
import it.polimi.ingsw.ps06.model.events.EventLeaderActivated;
import it.polimi.ingsw.ps06.model.events.EventLeaderDiscarded;
import it.polimi.ingsw.ps06.model.events.EventLeaderPlayed;
import it.polimi.ingsw.ps06.model.events.EventMemberPlaced;
import it.polimi.ingsw.ps06.model.events.StoryBoard2PersonalView;

public class BoardCLI extends Observable implements Board {
	
	private Scanner input;
	
	private String[] player = new String[5];
    private String playerColor="G";
    private int blackValue;
    private int orangeValue;
    private int whiteValue;
    private int playerNumber;
    private int roundNumber;
    private int periodNumber;
    private int roundPlayerID;
    private int ex1;
    private int ex2;
    private int ex3;
    private int harvestIndex=1, productionIndex=1, councilIndex=0;
    private int lead1, lead2, lead3, lead4;
    private int coinV, woodV, stoneV, servantV;
    private int usedMember;
    private int leaderState1, leaderState2, leaderState3, leaderState4;
	
	public BoardCLI(Scanner input) {
		
		this.input = input;
	}
	
	public void addNewControllerObserver(BoardController controller) {
		addObserver(controller);
	}
	
	public void printTowers(ArrayList<DevelopementCard> t) {
		printTowerASCII();
		
	}
	
	public void show() {
		
		printTowerASCII();
		System.out.println();
		System.out.println(" --- Firenze, 1500. Benvenuto in questa magnifica città. --- ");
		System.out.println();
		System.out.println(" Per ottenere una lista dei comandi possibili premi \"?\".");
		System.out.println();
		System.out.print(" > ");
		
		hasLoaded();
		while(true) readInput();
		
	}
	
	public void readInput() {
		String s = input.nextLine();
		
		Action chosenAction;
		
		if(("spots").equals(s)) giveSpots();
		if(("actions").equals(s)) giveActions();
		if(("?").equals(s)) giveInfo();
		if(("exit").equals(s)) notifyExit();
		
		if(("info").equals(s)) printPoints(0);
		if(("info1").equals(s)) printPoints(1);
		if(("info2").equals(s)) printPoints(2);
		if(("info3").equals(s)) printPoints(3);
		if(("info4").equals(s)) printPoints(4);
		if(("info5").equals(s)) printPoints(5);
		
		if(("attiva1").equals(s)) notifyLeaderActivation(1);
		if(("attiva2").equals(s)) notifyLeaderActivation(2);
		if(("attiva3").equals(s)) notifyLeaderActivation(3);
		if(("attiva4").equals(s)) notifyLeaderActivation(4);
		
		if(("scarta1").equals(s)) notifyLeaderDiscard(1);
		if(("scarta2").equals(s)) notifyLeaderDiscard(2);
		if(("scarta3").equals(s)) notifyLeaderDiscard(3);
		if(("scarta4").equals(s)) notifyLeaderDiscard(4);
		
		if(("piazza1").equals(s)) notifyLeaderPlacement(1);
		if(("piazza2").equals(s)) notifyLeaderPlacement(2);
		if(("piazza3").equals(s)) notifyLeaderPlacement(3);
		if(("piazza4").equals(s)) notifyLeaderPlacement(4);
		
		
		for(int j=0; j<25; j++)
		if( s.equalsIgnoreCase(( ((Action.getAction(j)).toString())) )){
			System.out.print(" > ");
			String s1 = String.valueOf(input.nextLine());
			int index = Integer.parseInt(s1.replaceAll("[\\D]", ""));
			notifyAction(Action.valueOf(s.toUpperCase()),index);
		}
		
		
	}

	public void giveInfo() {
		System.out.println("----- LISTA COMANDI -----");
		System.out.println();
		System.out.println("Per eseguire un azione digita un comando composto nel seguente modo: \"market_1 familiare1\"");
		System.out.println("Ricorda che l'ordine dei colori è: Nero(1) Bianco(2) Arancio(3) Neutro(4)");
		System.out.println("Per una lista delle posizioni del gioco digita \"spots\"");
		System.out.println("Per una lista di azioni extra digita: \"actions\"");
		System.out.println();
		System.out.print(" > ");

		while(true) readInput();
	}
	
	public void giveSpots() {
		System.out.println("----- LISTA POSIZIONI -----");
		System.out.println();
		System.out.println("market_1 ~ market_4 | council_space | production_1 ~ production_2 | harvest_1 ~ harvest_2 | tower_green1 ~ tower_purple_4");
		System.out.println("Ricorda che i posti della torre vanno da sinistra a destra nel seguente ordine: green-blue-yellow-purple; dal basso verso l'alto!");
		System.out.println();
		System.out.print(" > ");

		while(true) readInput();
	}
	
	public void giveActions() {
		System.out.println("----- LISTA AZIONI EXTRA -----");
		System.out.println();
		System.out.println("--LEADER-- Scarta1 ~ Scarta4 (Leader) | Attiva1 ~ Attiva4 (Leader) | Piazza1 ~ Piazza4 ");
		System.out.println("--ALTRO-- Info1 ~ Info5 (Per controllare carte e risorse di un avversario)");
		System.out.println("Se non conosci il tuo codice giocatore puoi usare semplicemente il comando Info !");
		System.out.println();
		System.out.print(" > ");

		while(true) readInput();
	}
	
	private void printTowerASCII() {
		
		System.out.println("");
		System.out.print("                                ");
		System.out.println("      |>>>		"); 	 System.out.print("      |>>>	"); 	System.out.print("      |>>>	");
		System.out.println("      |			");		 System.out.print("      |		"); 	System.out.print("      |		");
		System.out.println(" |;|_|;|_|;|	");	 	 System.out.print(" |;|_|;|_|;|	");		System.out.print(" |;|_|;|_|;|	");
		System.out.println(" \\.    .  /	"); 	 System.out.print(" \\.    .  /	");		System.out.print(" \\.    .  /	");
		System.out.println("  \\:  .  /		"); 	 System.out.print("  \\:  .  /	"); 	System.out.print("  \\:  .  /	");
		System.out.println("   ||:   |		"); 	 System.out.print("   ||:   |	");		System.out.print("   ||:   |	");
		System.out.println("   ||:.  |		");	 	 System.out.print("   ||:.  |	");		System.out.print("   ||:.  |	");
		System.out.println("   ||:  .|		");		 System.out.print("   ||:  .|	");		System.out.print("   ||:  .|	");
		System.out.println("   ||:   |		");	 	 System.out.print("   ||:   |	");		System.out.print("   ||:   |	");
		System.out.println("   ||: , |		");	 	 System.out.print("   ||: , |	");		System.out.print("   ||: , |	");
		System.out.println("   ||:   |		");		 System.out.print("   ||:   |	");		System.out.print("   ||:   |	");
		System.out.println("   ||: . |		");	 	 System.out.print("   ||: . |	");		System.out.print("   ||: . |	");
		System.out.println("  _||_   |		");	 	 System.out.print("  _||_   |	");		System.out.print("  _||_   |	");
		System.out.println("-~    ~`--		");	 	 System.out.print("-~    ~`--	");		System.out.print("-~    ~`--	");
	}
	
	public void printPoints(int index) {
		if (index==0) System.out.println("--> Coin: "+coinV+" Wood: "+woodV+" Stone: "+stoneV+" Servant:"+servantV);
		startGame(index);
	}

	@Override
	public void setHarvestIndex(int value) {
		this.harvestIndex=value;
		
	}

	@Override
	public void setProductionIndex(int value) {
		this.productionIndex=value;
		
	}

	@Override
	public void setCouncilIndex(int value) {
		this.councilIndex=value;
		
	}

	@Override
	public void setPeriodRound(int period, int round) {
		this.periodNumber=period;
		this.roundNumber=round;
		
	}

	@Override
	public void setPlayerColor(String s) {
		this.playerColor=s;
		
	}

	@Override
	public void setPlayersNames(String s, int index) {
		this.player[index]=s;
		
	}

	@Override
	public void setCurrentPlayerID(int id) {
		this.roundPlayerID=id;
		
	}

	@Override
	public void setDices(int black, int white, int orange) {
		this.blackValue=black;
		this.whiteValue=white;
		this.orangeValue=orange;
		
	}

	@Override
	public void setExcommunicationTiles(int code1, int code2, int code3) {
		this.ex1=code1;
		this.ex2=code2;
		this.ex3=code3;
		
	}

	@Override
	public void setCards(int[] cards) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activateLeaders() {
		leaderState1=2;
		leaderState2=2;
		leaderState3=2;
		leaderState4=2;
		
	}

	@Override
	public void setLeaders(int code1, int code2, int code3, int code4) {
		this.lead1=code1;
		this.lead2=code2;
		this.lead3=code3;
		this.lead4=code4;
		
	}

	@Override
	public void setPersonalResources(int coin, int wood, int stone, int servant) {
		this.coinV=coin;
		this.woodV=wood;
		this.stoneV=stone;
		this.servantV=servant;
		
	}

	@Override
	public void startTimer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyExit() {
		setChanged();
		EventClose close = new EventClose();
		notifyObservers(close);
		
	}

	@Override
	public void notifyAction(Action chosenAction, int memberIndex) {
		System.out.println("--> Evento registrato: familiare "+memberIndex+" in "+chosenAction.toString());
		setChanged();
		EventMemberPlaced memberPlaced=null;
		
		switch(memberIndex){
		case 0:
			memberPlaced = new EventMemberPlaced(chosenAction,ColorPalette.DICE_BLACK);
			break;
		case 1:
			memberPlaced = new EventMemberPlaced(chosenAction,ColorPalette.DICE_WHITE);
			break;
		case 2:
			memberPlaced = new EventMemberPlaced(chosenAction,ColorPalette.DICE_ORANGE);
			break;
		case 3:
			memberPlaced = new EventMemberPlaced(chosenAction,ColorPalette.UNCOLORED);
			break;
		}
		
		notifyObservers(memberPlaced);
		
	}

	@Override
	public void notifyLeaderDiscard(int index) {
		setChanged();
		EventLeaderDiscarded leaderDiscarded;
		leaderDiscarded = new EventLeaderDiscarded(index);
		notifyObservers(leaderDiscarded);
		
	}

	@Override
	public void notifyLeaderPlacement(int index) {
		setChanged();
		EventLeaderPlayed leaderPlayed;
		leaderPlayed = new EventLeaderPlayed(index);
		notifyObservers(leaderPlayed);
		
	}
	

	@Override
	public void notifyLeaderActivation(int index) {
		setChanged();
		EventLeaderActivated leaderActivated;
		leaderActivated = new EventLeaderActivated(index);
		notifyObservers(leaderActivated);
		
	}

	@Override
	public void notifyTimesUp() {
		
		
	}

	@Override
	public void addNewObserver(Observer o) {
		addObserver(o);
		
	}

	@Override
	public void startGame(int index) {
		setChanged();
		StoryBoard2PersonalView storyBoard;
		storyBoard = new StoryBoard2PersonalView(new PersonalViewCLI(input, index));
		notifyObservers(storyBoard);
		
	}

	@Override
	public void setPlayerNumber(int number) {
		this.playerNumber=number;
		
	}


	@Override
	public void addMember(Action chosenAction, ColorPalette color, int playerIndex) throws IOException {
		
	int memberIndex=0;
	
		switch(color){
			case DICE_BLACK:
				memberIndex=1;
				break;
			case DICE_WHITE:
				memberIndex=2;
				break;
			case DICE_ORANGE:
				memberIndex=3;
				break;
			case UNCOLORED:
				memberIndex=4;
				break;
		}
		
		
		System.out.println("--> Il familiare"+memberIndex+" è stato piazzato dal giocatore"+playerIndex+" in "+chosenAction.toString()+" con successo!");
		System.out.println("");
		
	}
	
	

	@Override
	public void setOwnerPlayerIndex(int index) {
		// TODO Auto-generated method stub
		
	}

	
	
	@Override
	public void setRound() {
		System.out.println("--> I familiari sono tornati al loro posto originario");
		
	}
	
	

	@Override
	public void showErrorLog(String s) {
		System.out.println("--> "+s);
		
	}

	@Override
	public void hasLoaded() {
		setChanged();
		BoardHasLoaded roomLoaded = new BoardHasLoaded();
		notifyObservers(roomLoaded);
		
	}
	
	
	
}
