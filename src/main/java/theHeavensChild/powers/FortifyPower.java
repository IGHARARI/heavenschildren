package theHeavensChild.powers;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theHeavensChild.cards.Fortify;

import static theHeavensChild.HeavensChildMod.makeID;

public class FortifyPower extends TwoAmountPower {

    public static final String POWER_ID = makeID(FortifyPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public FortifyPower(int blockEachTurn, int forNTurns){
        name = NAME;
        ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        this.amount = blockEachTurn;
        this.amount2 = forNTurns;
        type = PowerType.BUFF;
        isTurnBased = true;
        loadRegion("flameBarrier");
        updateDescription();
    }

    public void atStartOfTurn() {
        addToBot(new GainBlockAction(owner, amount));
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead() && amount2 == 1) { addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        } else {
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    amount2--;
                    updateDescription();
                }
            });
        }
    }

    @Override
    public void updateDescription() { description = amount2 == 1 ? String.format(DESCRIPTIONS[0], amount) : String.format(DESCRIPTIONS[1], amount2, amount); }

}
