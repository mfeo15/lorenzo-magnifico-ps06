package it.polimi.ingsw.ps06.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import it.polimi.ingsw.ps06.controller.BoardController;
import it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;
import it.polimi.ingsw.ps06.model.cards.DevelopementCard;
import it.polimi.ingsw.ps06.model.events.BoardFrozenStatus;
import it.polimi.ingsw.ps06.model.events.BoardHasLoaded;
import it.polimi.ingsw.ps06.model.events.EventClose;
import it.polimi.ingsw.ps06.model.events.EventLeaderActivated;
import it.polimi.ingsw.ps06.model.events.EventLeaderDiscarded;
import it.polimi.ingsw.ps06.model.events.EventLeaderPlayed;
import it.polimi.ingsw.ps06.model.events.EventMemberPlaced;
import it.polimi.ingsw.ps06.model.events.StoryBoard2PersonalView;
import it.polimi.ingsw.ps06.networking.messages.MessageObtainPersonalBoardStatus;

public class BoardCLI extends Observable implements Board {
	
	//private Scanner input;
	
	private String[] player = new String[5];
	private int[] cardsCodes = new int[16];
	
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
    private int coinV, woodV, stoneV, servantV, victoryV, militaryV,faithV;
    private int usedMember;
    private int leaderState1, leaderState2, leaderState3, leaderState4;
    
    private BufferedReader input;
    
    private boolean cond = true;
	
	public BoardCLI(BufferedReader input) {
		
		this.input = input;
	}
	
	public void addNewControllerObserver(BoardController controller) {
		addObserver(controller);
	}
	
	
	public void read () throws IOException {
		
		String s = input.readLine();
		
		if (s == null)
			return;

		if(("spots").equals(s)) giveSpots();
		if(("actions").equals(s)) giveActions();
		if(("?").equals(s)) giveInfo();
		if(("status").equals(s)) giveGameStatus();
		if(("towers").equals(s)) giveTowerStatus();

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

		if(("action").equals(s))
		{
			System.out.print("Che azione? > ");
			String s1 = String.valueOf(input.readLine());
			for(int j=0; j<25; j++)
				if( s1.equalsIgnoreCase(( ((Action.getAction(j)).toString())) )){
					System.out.print("Che familiare? (Numero) > ");
					String s2 = String.valueOf(input.readLine());
					int index = Integer.parseInt(s2.replaceAll("[\\D]", ""));
					
					System.out.print("Quanti servi vuoi usare? > ");
					int servants = Integer.parseInt(input.readLine());
					notifyAction(Action.valueOf(s1.toUpperCase()),index,servants);
				}
		}
	}
	
	public void show() {
		
		printTowers();
		System.out.println();
		System.out.println(" --- Firenze, 1500. Benvenuto in questa magnifica città. --- ");
		System.out.println();
		System.out.println(" Per ottenere una lista dei comandi possibili premi \"?\".");
		System.out.println();
		System.out.print(" > ");
		
		hasLoaded();
		
		while (cond)
			try {
				read();
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}
	}
	
	public void giveTowerStatus() {
		
		System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  ");
		System.out.println("");
		System.out.println("	 Territori		Personaggi	     Edifici		Sviluppo");
		System.out.println("");
		System.out.println("	     "+cardsCodes[4]+"			    "+cardsCodes[7]+"			"+cardsCodes[11]+"		    "+cardsCodes[15]);
		System.out.println("");
		System.out.println("	     "+cardsCodes[2]+"			    "+cardsCodes[6]+"			"+cardsCodes[10]+"		    "+cardsCodes[14]);
		System.out.println("");
		System.out.println("	     "+cardsCodes[1]+"			    "+cardsCodes[5]+"			"+cardsCodes[9]+"		    "+cardsCodes[13]);
		System.out.println("");
		System.out.println("	     "+cardsCodes[0]+"			    "+cardsCodes[4]+"			"+cardsCodes[8]+"		    "+cardsCodes[12]);
		System.out.println("");
		System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  ");
		
		
	}
	
	public void giveGameStatus() {
		
		System.out.println();
		System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
		System.out.println("");
		System.out.println("	 BLACK			 WHITE			ORANGE");
		printDice(this.blackValue,this.whiteValue,this.blackValue);
		System.out.println("");
		System.out.println("Turno: " + this.roundNumber + "\t Periodo: " + this.periodNumber);
		System.out.println("");
		System.out.println("Current Player: " +player[this.roundPlayerID] );
		System.out.println("");
		System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
		System.out.println();
		System.out.print(" > ");
	}

	public void giveInfo() {
		System.out.println("----- LISTA COMANDI -----");
		System.out.println();
		System.out.println("Per eseguire un azione digita il comando \"action\"");
		System.out.println("Ricorda che l'ordine dei colori è: Nero(1) Bianco(2) Arancio(3) Neutro(4)");
		System.out.println("Per una lista delle posizioni del gioco digita: \"spots\"");
		System.out.println("Per una lista di azioni extra digita: \"actions\"");
		System.out.println("Per una panoramica sulla situazione della partita digita: \"status\"");
		System.out.println("La situazione delle torri è mostrata digitando: \"towers\"");
		System.out.println();
		System.out.print(" > ");
	}
	
	public void giveSpots() {
		System.out.println("----- LISTA POSIZIONI -----");
		System.out.println();
		System.out.println("market_1 ~ market_4 | council_space | production_1 ~ production_2 | harvest_1 ~ harvest_2 | tower_green1 ~ tower_purple_4");
		System.out.println("Ricorda che i posti della torre vanno da sinistra a destra nel seguente ordine: green-blue-yellow-purple; dal basso verso l'alto!");
		System.out.println();
		System.out.print(" > ");
	}
	
	public void giveActions() {
		System.out.println("----- LISTA AZIONI EXTRA -----");
		System.out.println();
		System.out.println("--LEADER-- Scarta1 ~ Scarta4 (Leader) | Attiva1 ~ Attiva4 (Leader) | Piazza1 ~ Piazza4 ");
		System.out.println("--ALTRO-- Info1 ~ Info5 (Per controllare carte e risorse di un avversario)");
		System.out.println("Se non conosci il tuo codice giocatore puoi usare semplicemente il comando Info !");
		System.out.println();
		System.out.print(" > ");
	}
	
	private void printTowers() {
		
		System.out.println("");
		System.out.print("                                                 ");
		System.out.println("      |>>>		"); 	 System.out.print("      |>>>	"); 	System.out.print("      |>>>	"); 	System.out.print("      |>>>		");
		System.out.println("      |			");		 System.out.print("      |		"); 	System.out.print("      |		");		System.out.print("      |		");
		System.out.println(" |;|_|;|_|;|	");	 	 System.out.print(" |;|_|;|_|;|	");		System.out.print(" |;|_|;|_|;|	");		System.out.print(" |;|_|;|_|;|	");
		System.out.println(" \\.    .  /	"); 	 System.out.print(" \\.    .  /	");		System.out.print(" \\.    .  /	");		System.out.print(" \\.    .  /	");
		System.out.println("  \\:  .  /		"); 	 System.out.print("  \\:  .  /	"); 	System.out.print("  \\:  .  /	");		System.out.print("  \\:  .  /	");
		System.out.println("   ||:   |		"); 	 System.out.print("   ||:   |	");		System.out.print("   ||:   |	");		System.out.print("   ||:   |	");
		System.out.println("   ||:.  |		");	 	 System.out.print("   ||:.  |	");		System.out.print("   ||:.  |	");		System.out.print("   ||:.  |	");
		System.out.println("   ||:  .|		");		 System.out.print("   ||:  .|	");		System.out.print("   ||:  .|	");		System.out.print("   ||:  .|	");
		System.out.println("   ||:   |		");	 	 System.out.print("   ||:   |	");		System.out.print("   ||:   |	");		System.out.print("   ||:   |	");
		System.out.println("   ||: , |		");	 	 System.out.print("   ||: , |	");		System.out.print("   ||: , |	");		System.out.print("   ||: , |	");
		System.out.println("   ||:   |		");		 System.out.print("   ||:   |	");		System.out.print("   ||:   |	");		System.out.print("   ||:   |	");
		System.out.println("   ||: . |		");	 	 System.out.print("   ||: . |	");		System.out.print("   ||: . |	");		System.out.print("   ||: . |	");
		System.out.println("  _||_   |		");	 	 System.out.print("  _||_   |	");		System.out.print("  _||_   |	");		System.out.print("  _||_   |	");
		System.out.println("-~    ~`--		");	 	 System.out.print("-~    ~`--	");		System.out.print("-~    ~`--	");		System.out.print("-~    ~`--	");
	}
	
	
	
	private void printDice(int... dices) {
		
		for (int j=0; j<5;j++) {
			
			System.out.print("\t");

			for (int d : dices) {
				switch(d) {
				case 1:
					if (j==0) System.out.print("------- \t\t");
					if (j==1) System.out.print("|     | \t\t");
					if (j==2) System.out.print("|  o  | \t\t");
					if (j==3) System.out.print("|     | \t\t");
					if (j==4) System.out.print("------- \t\t");
					break;
				case 2:
					if (j==0)System.out.print("------- \t\t");
					if (j==1)System.out.print("| o   | \t\t");
					if (j==2)System.out.print("|     | \t\t");
					if (j==3)System.out.print("|   o | \t\t");
					if (j==4)System.out.print("------- \t\t");
					break;
				case 3:
					if (j==0) System.out.print("------- \t\t");
					if (j==1) System.out.print("| o   | \t\t");
					if (j==2) System.out.print("|  o  | \t\t");
					if (j==3) System.out.print("|   o | \t\t");
					if (j==4) System.out.print("------- \t\t");
					break;
				case 4:
					if (j==0) System.out.print("------- \t\t");
					if (j==1) System.out.print("| o o | \t\t");
					if (j==2) System.out.print("|     | \t\t");
					if (j==3) System.out.print("| o o | \t\t");
					if (j==4) System.out.print("------- \t\t");
					break;
				case 5:
					if (j==0) System.out.print("------- \t\t");
					if (j==1) System.out.print("| o o | \t\t");
					if (j==2) System.out.print("|  o  | \t\t");
					if (j==3) System.out.print("| o o | \t\t");
					if (j==4) System.out.print("------- \t\t");
					break;
				case 6:
					if (j==0) System.out.print("------- \t\t");
					if (j==1) System.out.print("| o o | \t\t");
					if (j==2) System.out.print("| o o | \t\t");
					if (j==3) System.out.print("| o o | \t\t");
					if (j==4) System.out.print("------- \t\t");
					break;
				}
			}
			System.out.print("\n");
		}
	}
	
	public void printPoints(int index) {
		if (index==0) System.out.println("--> Coin: "+coinV+" Wood: "+woodV+" Stone: "+stoneV+" Servant:"+servantV+" Victory:"+victoryV+" Military:"+militaryV+" Faith:"+faithV);
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
		this.blackValue = black;
		this.whiteValue = white;
		this.orangeValue = orange;
		
	}

	@Override
	public void setExcommunicationTiles(int code1, int code2, int code3) {
		this.ex1=code1;
		this.ex2=code2;
		this.ex3=code3;
		
	}

	@Override
	public void setCards(int[] cards) {
		this.cardsCodes = cards;
		
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
	public void setPersonalResources(int coin, int wood, int stone, int servant, int victory, int military, int faith) {
		
		this.coinV=coin;
		this.woodV=wood;
		this.stoneV=wood;
		this.servantV=servant;
		this.victoryV=victory;
		this.militaryV=military;
		this.faithV=faith;
		
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
	public void notifyAction(Action chosenAction, int memberIndex, int servants) {
		System.out.println("--> Evento registrato: familiare "+memberIndex+" in "+chosenAction.toString()+", sono stati usati "+servants+" servi");
		setChanged();
		EventMemberPlaced memberPlaced=null;
		
		switch(memberIndex){
		case 0:
			memberPlaced = new EventMemberPlaced(chosenAction,ColorPalette.DICE_BLACK,servants);
			break;
		case 1:
			memberPlaced = new EventMemberPlaced(chosenAction,ColorPalette.DICE_WHITE,servants);
			break;
		case 2:
			memberPlaced = new EventMemberPlaced(chosenAction,ColorPalette.DICE_ORANGE,servants);
			break;
		case 3:
			memberPlaced = new EventMemberPlaced(chosenAction,ColorPalette.UNCOLORED,servants);
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
		System.out.println("--> Tempo scaduto!");
		System.out.println();
		System.out.print(" > ");
		
	}

	@Override
	public void addNewObserver(Observer o) {
		addObserver(o);
		
	}

	@Override
	public void startGame(int index) {
		
		setChanged();
		MessageObtainPersonalBoardStatus obtainPbStatus = new MessageObtainPersonalBoardStatus(index); 
		notifyObservers(obtainPbStatus);
		
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
		
		switch(chosenAction){
	
    	case TOWER_GREEN_1:
    		cardsCodes[0]=-1;
    	case TOWER_GREEN_2:
    		cardsCodes[1]=-1;
    	case TOWER_GREEN_3:
    		cardsCodes[2]=-1;
    	case TOWER_GREEN_4:
    		cardsCodes[3]=-1;
    		
    	case TOWER_BLUE_1:
    		cardsCodes[4]=-1;
    	case TOWER_BLUE_2:
    		cardsCodes[5]=-1;
    	case TOWER_BLUE_3:
    		cardsCodes[6]=-1;
    	case TOWER_BLUE_4:
    		cardsCodes[7]=-1;
    		
    	case TOWER_YELLOW_1:
    		cardsCodes[8]=-1;
    	case TOWER_YELLOW_2:
    		cardsCodes[9]=-1;
    	case TOWER_YELLOW_3:
    		cardsCodes[10]=-1;
    	case TOWER_YELLOW_4:
    		cardsCodes[11]=-1;

    	case TOWER_PURPLE_1:
    		cardsCodes[12]=-1;
    	case TOWER_PURPLE_2:
    		cardsCodes[13]=-1;
    	case TOWER_PURPLE_3:
    		cardsCodes[14]=-1;
    	case TOWER_PURPLE_4:
    		cardsCodes[15]=-1;
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
		BoardHasLoaded boardLoaded = new BoardHasLoaded();
		notifyObservers(boardLoaded);
	}

	@Override
	public void unfreeze() {
		setChanged();
		BoardFrozenStatus frozen = new BoardFrozenStatus(false);
		notifyObservers(frozen);
		
	}

	@Override
	public void freeze() {
		setChanged();
		BoardFrozenStatus frozen = new BoardFrozenStatus(true);
		notifyObservers(frozen);
		
	}

	@Override
	public void excommunicate(int tileNumber, int playerIndex) {
		System.out.println("--> Il giocatore numero "+playerIndex+" è appena stato scomunicato!");
		
	}

	@Override
	public void setOrder(int[] players) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	@Override
	public void setResourcesPersonalView(int coin, int wood, int stone, int servant, int victory, int military,
			int faith) {
		System.out.println("--> Il giocatore ha Coin: "+coin+" Wood: "+wood+" Stone: "+stone+" Servant:"+servant+" Victory:"+victory+" Military:"+military+" Faith:"+faith);
		System.out.println();
		System.out.print(" >");
		
	}

	@Override
	public void setTerritoryCardPersonalView(int id, int index) {
		System.out.println("--> Il giocatore ha il territorio con ID:"+id);
		System.out.println();
		System.out.print(" >");
		
	}

	@Override
	public void setBuildingCardPersonalView(int id, int index) {
		System.out.println("--> Il giocatore ha l'edificio con ID:"+id);
		System.out.println();
		System.out.print(" >");
		
	}

	@Override
	public void hasLoadedPersonalView() {
		// Nothing
		
	}

	public static void main(String[] args) {
		BoardCLI m = new BoardCLI( new BufferedReader(new InputStreamReader(System.in)));
		m.show();
	}
}
