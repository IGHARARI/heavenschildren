package theHeavensChild.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theHeavensChild.HeavensChildMod;
import theHeavensChild.util.Wiz;

public class HeavyLiftingPower extends AbstractTwoAmountEasyPower {
    public static final String POWER_ID = HeavensChildMod.makeID("HeavyLifting");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;

    public HeavyLiftingPower(AbstractCreature owner, int amount) {
        super(NAME, PowerType.BUFF, true, owner, amount, 0);
        updateDescription();
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            amount2++;
            updateDescription();
        }
    }

    @Override
    public void atStartOfTurn() {
        flash();
        Wiz.applyToSelf(new StrengthPower(AbstractDungeon.player, amount2 * amount));
        addToBot(new RemoveSpecificPowerAction(owner, owner, this.ID));
    }

    @Override
    public void updateDescription() {
        description = powerStrings.DESCRIPTIONS[0] + amount + powerStrings.DESCRIPTIONS[1] + powerStrings.DESCRIPTIONS[2] + amount2;
    }
}
