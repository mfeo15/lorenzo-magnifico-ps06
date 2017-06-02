package it.polimi.ingsw.ps06.model;

public class EventParser implements EventVisitor {

	@Override
	public void visit(MemberPlaced memberPlaced) {
		
		switch (memberPlaced.getAction()) {
		case TOWER_GREEN_1:
		case CONCIL_SPACE:
			break;
		case HARVEST_1:
			break;
		case HARVEST_2:
			break;
		case MARKET_1:
			break;
		case MARKET_2:
			break;
		case MARKET_3:
			break;
		case MARKET_4:
			break;
		case MARKET_5:
			break;
		case NULL:
			break;
		case PRODUCTION_1:
			break;
		case PRODUCTION_2:
			break;
		case TOWER_BLUE_1:
			break;
		case TOWER_BLUE_2:
			break;
		case TOWER_BLUE_3:
			break;
		case TOWER_BLUE_4:
			break;
		case TOWER_GREEN_2:
			break;
		case TOWER_GREEN_3:
			break;
		case TOWER_GREEN_4:
			break;
		case TOWER_PURPLE_1:
			break;
		case TOWER_PURPLE_2:
			break;
		case TOWER_PURPLE_3:
			break;
		case TOWER_PURPLE_4:
			break;
		case TOWER_YELLOW_1:
			break;
		case TOWER_YELLOW_2:
			break;
		case TOWER_YELLOW_3:
			break;
		case TOWER_YELLOW_4:
			break;
		default:
			break;
		}
	}

	@Override
	public void visit(LeaderDiscarded leaderDiscarded) {
		// TODO Auto-generated method stub

	}
}
