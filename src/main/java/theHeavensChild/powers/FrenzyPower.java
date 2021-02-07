package theHeavensChild.powers;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theHeavensChild.HeavensChildMod;

import static theHeavensChild.HeavensChildMod.makeID;

public class FrenzyPower extends AbstractPower {
    public static final String POWER_ID = makeID(FrenzyPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractCard copy;
    public FrenzyPower(AbstractCard frenzy, int amount){
        name = NAME;
        ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        this.amount = amount;
        type = PowerType.BUFF;
        isTurnBased = true;
        copy = frenzy;
        loadRegion("flameBarrier");
        updateDescription();
    }
    @Override
    public void atStartOfTurnPostDraw() {
        this.flash();
        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
        addToBot(new MakeTempCardInHandAction(copy, amount));
    }

    @Override
    public void updateDescription() { this.description = String.format(DESCRIPTIONS[0], this.amount); }
}
