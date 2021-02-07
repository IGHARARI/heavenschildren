package theHeavensChild.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;

import static theHeavensChild.HeavensChildMod.makeID;

public class ReachBeyondPower extends AbstractPower {
    public static final String POWER_ID = makeID(ReachBeyondPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractCard copy;

    public ReachBeyondPower(int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        this.amount = amount;
        type = PowerType.BUFF;
        isTurnBased = true;
        loadRegion("flameBarrier");
        updateDescription();
    }

    public void onExhaust(AbstractCard card) {
        flash();
        addToBot(new ApplyPowerAction(this.owner, this.owner, new EnergizedPower(this.owner, this.amount)));
    }
    @Override
    public void updateDescription() { this.description = String.format(DESCRIPTIONS[0], this.amount); }
}
