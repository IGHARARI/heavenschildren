package theHeavensChild.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeavensChild.HeavensChildMod;
import theHeavensChild.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class ArrangementsPower extends AbstractEasyPower {
    public static final String POWER_ID = HeavensChildMod.makeID("Arrangements");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;

    private static final List<AbstractCard> cardsToPlay = new ArrayList<>();

    public ArrangementsPower(AbstractCard cardToPlay) {
        super(NAME, PowerType.BUFF, false, AbstractDungeon.player, 1);
        cardsToPlay.add(cardToPlay);
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        for (AbstractCard c : cardsToPlay) {
            AbstractMonster randomTarget = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(
                    (AbstractMonster) null,
                    true,
                    AbstractDungeon.cardRandomRng);

            removeFromPile(c);
            AbstractDungeon.getCurrRoom().souls.remove(c);
            AbstractDungeon.player.limbo.group.add(c);
            c.current_y = -200.0F * Settings.scale;
            c.target_x = (float)Settings.WIDTH / 2.0F + 200.0F * Settings.xScale;
            c.target_y = (float)Settings.HEIGHT / 2.0F;
            c.targetAngle = 0.0F;
            c.lighten(false);
            c.drawScale = 0.12F;
            c.targetDrawScale = 0.75F;
            c.applyPowers();
            addToBot(new NewQueueCardAction(c, randomTarget, false, true));
            addToBot(new UnlimboAction(c));
        }
        cardsToPlay.clear();
        addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, this));
    }

    private void removeFromPile(AbstractCard c) {
        AbstractPlayer p = AbstractDungeon.player;
        if (p.drawPile.contains(c)) {
            p.drawPile.group.remove(c);

        } else if (p.discardPile.contains(c)) {
            p.discardPile.group.remove(c);

        } else if (p.hand.contains(c)) {
            p.hand.group.remove(c);

        } else if (p.limbo.contains(c)) {
            p.limbo.group.remove(c);

        } else if (p.exhaustPile.contains(c)) {
            p.exhaustPile.group.remove(c);
        } else {
            System.out.println("Card was NOWHERE WHAT THE FUCK");
        }
    }

    public void addCardToPlay(AbstractCard cardToPlay) {
        cardsToPlay.add(cardToPlay);
    }
    @Override
    public void updateDescription() {
        description = powerStrings.DESCRIPTIONS[0];
        boolean first = true;
        for (AbstractCard c : cardsToPlay) {
            if (!first) description += ", ";
            description += c.name;
            first = false;
        }
        description += ".";
    }
}
