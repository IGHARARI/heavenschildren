package theHeavensChild.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static theHeavensChild.HeavensChildMod.makeID;

public class ReplicatePower extends TwoAmountPower {

    public static final String POWER_ID = makeID(ReplicatePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static int IdOffset;
    private AbstractCard c;
    private int PLAY_AGAIN_AMOUNT = 1;
    public ReplicatePower(){
        name = NAME;
        // Silly offset things we have to do to prevent overrides.
        ID = POWER_ID + IdOffset;
        IdOffset++;
        this.owner = AbstractDungeon.player;
        this.amount = PLAY_AGAIN_AMOUNT;
        type = PowerType.BUFF;
        isTurnBased = true;
        loadRegion("flameBarrier");
        updateDescription();
    }

    public void atStartOfTurn() {
        for(int i = 0; i < amount; i += 1) {
            if(c != null){ addToBot(new NewQueueCardAction(c, true, true, true)); }
        }
        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type.equals(AbstractCard.CardType.SKILL) && c == null) {
            c = card.makeStatEquivalentCopy();
            updateDescription();
        }
    }

    @Override
    public void updateDescription() { description = c != null ? String.format(DESCRIPTIONS[1], c.name) : DESCRIPTIONS[0];  }

}