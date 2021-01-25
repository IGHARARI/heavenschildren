package theHeavensChild.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import theHeavensChild.powers.ArrangementsPower;
import theHeavensChild.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class ArrangementsDiscardAction extends AbstractGameAction {

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction");
    public static final String[] TEXT = uiStrings.TEXT;
    private AbstractPlayer p;
    private static final float DURATION = Settings.ACTION_DUR_XFAST;

    private List<AbstractCard> cardsDiscarded = new ArrayList<>();

    public ArrangementsDiscardAction(int amount) {
        this.p = AbstractDungeon.player;
        this.setValues(AbstractDungeon.player, AbstractDungeon.player, amount);
        this.actionType = ActionType.DISCARD;
        this.duration = DURATION;
    }

    public void update() {
        if (this.duration == DURATION) {
            if (AbstractDungeon.getMonsters().areMonstersBasicallyDead() || p.hand.isEmpty()) {
                this.isDone = true;
                return;
            }

            if (this.p.hand.size() > this.amount) {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, false);
            } else {
                AbstractCard c = this.p.hand.getTopCard();
                this.p.hand.moveToDiscardPile(c);
                c.triggerOnManualDiscard();
                GameActionManager.incrementDiscard(false);
                handleCardChosen(c);
            }

            AbstractDungeon.player.hand.applyPowers();
            this.tickDuration();
            return;
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            AbstractCard cardChosen = null;
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                cardChosen = c;
                this.p.hand.moveToDiscardPile(c);
                c.triggerOnManualDiscard();
                GameActionManager.incrementDiscard(false);
            }

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            handleCardChosen(cardChosen);
        }

        this.tickDuration();
    }

    private void handleCardChosen(AbstractCard cardChosen) {
        if (cardChosen != null) {
            if (AbstractDungeon.player.hasPower(ArrangementsPower.POWER_ID)) {
                ArrangementsPower arrangements = (ArrangementsPower)AbstractDungeon.player.getPower(ArrangementsPower.POWER_ID);
                arrangements.addCardToPlay(cardChosen);
                arrangements.flash();
                arrangements.updateDescription();
            } else {
                Wiz.applyToSelf(new ArrangementsPower(cardChosen));
            }
        } else {
            // What the hell happened here.
        }
    }

}