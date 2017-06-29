package it.polimi.ingsw.ps06.model;

public class Types {
	
	public enum Action 
	{
		NULL(0),
		
		TOWER_GREEN_1(1),
		TOWER_GREEN_2(2),
		TOWER_GREEN_3(3),
		TOWER_GREEN_4(4),
		
		TOWER_BLUE_1(5),
		TOWER_BLUE_2(6),
		TOWER_BLUE_3(7),
		TOWER_BLUE_4(8),
		
		TOWER_YELLOW_1(9),
		TOWER_YELLOW_2(10),
		TOWER_YELLOW_3(11),
		TOWER_YELLOW_4(12),
		
		TOWER_PURPLE_1(13),
		TOWER_PURPLE_2(14),
		TOWER_PURPLE_3(15),
		TOWER_PURPLE_4(16),

		COUNCIL_SPACE(17),
		
		HARVEST_1(18),
		HARVEST_2(19),
		
		PRODUCTION_1(20),
		PRODUCTION_2(21),
		
		MARKET_1(22),
		MARKET_2(23),
		MARKET_3(24),
		MARKET_4(25),
		MARKET_5(26);
		
		private int index;
		private Action(int index) { this.index=index; }
		
		public static Action getAction(int index){
			for (Action a : Action.values()) {
				if(a.index == index) return a;
			}
			throw new IllegalArgumentException("Action not found");
		}
		
		public int getIndex(){
			return index;
		}
		
		public ActionCategory getActionCategory() {
			if ( this.index >= 1 && this.index <= 4 )
				return ActionCategory.TOWER_GREEN;
			
			if ( this.index >= 5 && this.index <= 8 )
				return ActionCategory.TOWER_BLUE;
			
			if ( this.index >= 9 && this.index <= 12 )
				return ActionCategory.TOWER_YELLOW;
			
			if ( this.index >= 13 && this.index <= 16 )
				return ActionCategory.TOWER_PURPLE;
			
			if ( this.index == 17 )
				return ActionCategory.COUNCIL;
			
			if ( this.index == 18 || this.index == 19 )
				return ActionCategory.HARVEST;
			
			if ( this.index == 20 || this.index == 21 )
				return ActionCategory.PRODUCTION;
			
			if ( this.index >= 22 && this.index <= 26 )
				return ActionCategory.MARKET;
			
			return null;
		}
	}
	
	public enum ActionCategory { TOWER_GREEN, TOWER_BLUE, TOWER_YELLOW, TOWER_PURPLE, MARKET, PRODUCTION, HARVEST, COUNCIL }
	
	public enum ColorPalette 
	{
		 UNCOLORED,
		    
		 CARD_GREEN,
		 CARD_BLUE,
		 CARD_YELLOW,
		 CARD_PURPLE,
		    
		 DICE_ORANGE,
		 DICE_BLACK,
		 DICE_WHITE
	}
	
	public enum PointsKind 
	{
		VICTORY_POINTS,
		MILITARY_POINTS,
		FAITH_POINTS,	
	}
	
	public enum MaterialsKind 
	{
		STONE,
		WOOD,
		COIN,
		SERVANT	
	}
	
	public enum CardType {
		LEADER,
		BUILDING,
		TERRITORY,
		CHARACTER,
		VENTURE,
		EXCOMMUNICATIONTILE
	}
	
	public enum CouncilPrivilege {
		
		BONUS_1(0), //Wood e Stone
		BONUS_2(1), // 2 Servants
		BONUS_3(2), // 2 Coins
		BONUS_4(3), // 2 Military
		BONUS_5(4); // 1 Faith

		private int index;
		private CouncilPrivilege(int index) { this.index=index; }
		
		public static CouncilPrivilege getPrivilege(int index){
			for (CouncilPrivilege a : CouncilPrivilege.values()) {
				if(a.index == index) return a;
			}
			throw new IllegalArgumentException("Action not found");
		}
		
		public int getIndex(){
			return index;
		}
	}
	
	public enum LeaderStates {
		IN_HAND, 
		ON_TABLE_FACE_DOWN, 
		ON_TABLE_FACE_UP, 
		DISCARDED
	}
}
