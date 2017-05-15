package it.polimi.ingsw.ps06.model;

public class Types {
	
	public enum Action 
	{
		NULL,
		
		TOWER_GREEN_1,
		TOWER_GREEN_2,
		TOWER_GREEN_3,
		OWER_GREEN_4,
		
		TOWER_BLUE_1,
		TOWER_BLUE_2,
		TOWER_BLUE_3,
		TOWER_BLUE_4,
		
		TOWER_YELLOW_1,
		TOWER_YELLOW_2,
		TOWER_YELLOW_3,
		TOWER_YELLOW_4,
		
		TOWER_PURPLE_1,
		TOWER_PURPLE_2,
		TOWER_PURPLE_3,
		TOWER_PURPLE_4,

		CONCIL_SPACE,
		
		HARVEST_1,
		HARVEST_2,
		
		PRODUCTION_1,
		PRODUCTION_2,
		
		MARKET_1,
		MARKET_2,
		MARKET_3,
		MARKET_4
	}
	
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
	
	public enum Privileges
	{
		BONUS_1,
		BONUS_2,
		BONUS_3,
		BONUS_4
	}
}
