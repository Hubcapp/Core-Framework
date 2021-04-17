package orsc.enumerations;

public enum InputXAction {
	ACT_0(0),
	TRADE_OFFER(1),
	TRADE_REMOVE(2),
	BANK_WITHDRAW(3),
	BANK_DEPOSIT(4),
	SHOP_BUY(5),
	SHOP_SELL(6),
	DUEL_STAKE(7),
	DUEL_REMOVE(8),
	SKIP_TUTORIAL(9),
	EXIT_BLACK_HOLE(10),
	DROP_X(11),
	TEAM_DUEL_STAKE_X(12),
	TEAM_DUEL_REMOVE_X(13),
	INVITE_CLAN_PLAYER(14),
	KICK_CLAN_PLAYER(15),
	CLAN_DELEGATE_LEADERSHIP(16),
	CLAN_LEAVE(17),
	INVITE_PARTY_PLAYER(18),
	KICK_PARTY_PLAYER(19),
	PARTY_DELEGATE_LEADERSHIP(20),
	PARTY_LEAVE(21),
	INCPOINTS0_X(22),
	INCPOINTS1_X(23),
	INCPOINTS2_X(24),
	INCPOINTS3_X(25),
	INCPOINTS4_X(26),
	INCPOINTS5_X(27),
	REDUCEPOINTS0_X(28),
	REDUCEPOINTS1_X(29),
	REDUCEPOINTS2_X(30),
	REDUCEPOINTS3_X(31),
	REDUCEPOINTS4_X(32),
	REDUCEPOINTS5_X(33),
	SAVEPRESET_X(34),
	LOADPRESET_X(35),
	POINTS2GP(36),
	REDUCELEVELS0_X(37),
	REDUCELEVELS1_X(38),
	REDUCELEVELS2_X(39),
	REDUCELEVELS3_X(40),
	REDUCELEVELS4_X(41),
	REDUCELEVELS5_X(42),
	INCLEVELS0_X(43),
	INCLEVELS1_X(44),
	INCLEVELS2_X(45),
	INCLEVELS3_X(46),
	INCLEVELS4_X(47),
	INCLEVELS5_X(48);

	public final int id;

	private InputXAction(int id) {
		this.id = id;
	}

	public boolean requiresNumeric() {
		return (id >= TRADE_OFFER.id && id <= DUEL_REMOVE.id)
			|| id == EXIT_BLACK_HOLE.id
			|| id == DROP_X.id;
	}
}
