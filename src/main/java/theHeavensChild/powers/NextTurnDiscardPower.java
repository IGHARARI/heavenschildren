package theHeavensChild.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theHeavensChild.HeavensChildMod;

public class NextTurnDiscardPower extends AbstractEasyPower {
    public static final String POWER_ID = HeavensChildMod.makeID("NextTurnDiscard");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;

    public NextTurnDiscardPower(AbstractCreature owner, int amount) {
        super(NAME, PowerType.DEBUFF, false, owner, amount);
        updateDescription();
        priority = 21;
    }

    @Override
    public void atStartOfTurnPostDraw() {
        this.flash();
        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
        addToBot(new DiscardAction(owner, owner, amount, false));
    }

    @Override
    public void updateDescription() {
        String[] powerDesc = powerStrings.DESCRIPTIONS;
        description = powerDesc[0] + amount + (amount > 1? powerDesc[2] : powerDesc[1]) + powerDesc[3];
    }
}
