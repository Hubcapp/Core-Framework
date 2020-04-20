package com.openrsc.server.plugins.misc;

import com.openrsc.server.constants.ItemId;
import com.openrsc.server.constants.NpcId;
import com.openrsc.server.constants.Skills;
import com.openrsc.server.model.entity.GroundItem;
import com.openrsc.server.model.entity.npc.Npc;
import com.openrsc.server.model.entity.player.Player;
import com.openrsc.server.model.entity.update.ChatMessage;
import com.openrsc.server.plugins.Functions;
import com.openrsc.server.plugins.triggers.*;

import static com.openrsc.server.plugins.Functions.*;

public class Zamorak implements TalkNpcTrigger, TakeObjTrigger, AttackNpcTrigger, PlayerRangeNpcTrigger, SpellNpcTrigger {

	@Override
	public void onTakeObj(Player owner, GroundItem item) {
		if (item.getID() == ItemId.WINE_OF_ZAMORAK.id() && item.getX() == 333 && item.getY() == 434) {
			Npc zam = Functions.ifnearvisnpc(owner, 7, NpcId.MONK_OF_ZAMORAK.id(), NpcId.MONK_OF_ZAMORAK_MACE.id());
			if (zam != null && !zam.inCombat()) {
				owner.face(zam);
				zam.face(owner);
				applyCurse(owner, zam);
			}
		}
	}

	@Override
	public boolean blockTakeObj(Player p, GroundItem i) {
		if (i.getID() == ItemId.WINE_OF_ZAMORAK.id()) {
			Npc zam = Functions.ifnearvisnpc(p, 7, NpcId.MONK_OF_ZAMORAK.id(), NpcId.MONK_OF_ZAMORAK_MACE.id());
			return zam != null && !zam.inCombat();
		}
		return false;
	}

	@Override
	public boolean blockAttackNpc(Player p, Npc n) {
		return n.getID() == NpcId.MONK_OF_ZAMORAK.id() || n.getID() == NpcId.MONK_OF_ZAMORAK_MACE.id();
	}

	@Override
	public void onAttackNpc(Player p, Npc zamorak) {
		if (zamorak.getID() == NpcId.MONK_OF_ZAMORAK.id() || zamorak.getID() == NpcId.MONK_OF_ZAMORAK_MACE.id()) {
			applyCurse(p, zamorak);
		}
	}

	@Override
	public boolean blockSpellNpc(Player p, Npc n) {
		return n.getID() == NpcId.MONK_OF_ZAMORAK.id() || n.getID() == NpcId.MONK_OF_ZAMORAK_MACE.id();
	}

	@Override
	public void onSpellNpc(Player p, Npc zamorak) {
		if (zamorak.getID() == NpcId.MONK_OF_ZAMORAK.id() || zamorak.getID() == NpcId.MONK_OF_ZAMORAK_MACE.id()) {
			applyCurse(p, zamorak);
		}
	}

	@Override
	public boolean blockPlayerRangeNpc(Player p, Npc n) {
		return n.getID() == NpcId.MONK_OF_ZAMORAK.id() || n.getID() == NpcId.MONK_OF_ZAMORAK_MACE.id();
	}

	@Override
	public void onPlayerRangeNpc(Player p, Npc zamorak) {
		if (zamorak.getID() == NpcId.MONK_OF_ZAMORAK.id() || zamorak.getID() == NpcId.MONK_OF_ZAMORAK_MACE.id()) {
			applyCurse(p, zamorak);
		}
	}

	private void applyCurse(Player owner, Npc zam) {
		owner.setBusy(true);
		zam.getUpdateFlags().setChatMessage(new ChatMessage(zam, "A curse be upon you", owner));
		delay(2200);
		owner.message("You feel slightly weakened");
		int dmg = (int) Math.ceil(((owner.getSkills().getMaxStat(Skills.HITS) + 20) * 0.05));
		owner.damage(dmg);
		int[] stats = {Skills.ATTACK, Skills.DEFENSE, Skills.STRENGTH};
		for(int affectedStat : stats) {
			/* How much to lower the stat */
			int lowerBy = (int) Math.ceil(((owner.getSkills().getMaxStat(affectedStat) + 20) * 0.05));
			/* New current level */
			final int newStat = Math.max(0, owner.getSkills().getLevel(affectedStat) - lowerBy);
			owner.getSkills().setLevel(affectedStat, newStat);
		}
		delay(500);
		zam.setChasing(owner);
		owner.setBusy(false);
	}

	@Override
	public boolean blockTalkNpc(Player p, Npc n) {
		return n.getID() == NpcId.MONK_OF_ZAMORAK.id() || n.getID() == NpcId.MONK_OF_ZAMORAK_MACE.id();
	}

	@Override
	public void onTalkNpc(Player p, Npc n) {
		if (n.getID() == NpcId.MONK_OF_ZAMORAK.id() || n.getID() == NpcId.MONK_OF_ZAMORAK_MACE.id()) {
			if (n.getID() == NpcId.MONK_OF_ZAMORAK.id()) {
				npcsay(p, n, "Save your speech for the altar");
			} else {
				npcsay(p, n, "Who are you to dare speak to the servants of Zamorak ?");
			}
			n.setChasing(p);
		}
	}
}
