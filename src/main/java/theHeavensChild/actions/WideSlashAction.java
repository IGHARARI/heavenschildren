package theHeavensChild.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import theHeavensChild.powers.ArrangementsPower;
import theHeavensChild.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class WideSlashAction extends AbstractGameAction {

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
    public static final String[] TEXT = uiStrings.TEXT;
    private AbstractPlayer p;

    public WideSlashAction(int amount) {
        this.p = AbstractDungeon.player;
        this.setValues(AbstractDungeon.player, AbstractDungeon.player, amount);
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.EXHAUST;
    }

    public void update() {
        if (this.duration == this.startDuration) {
            if (this.p.discardPile.isEmpty() || this.amount <= 0) {
                this.isDone = true;
                return;
            }
            if (this.p.discardPile.size() == this.amount) {
                ArrayList<AbstractCard> cardsToMove = new ArrayList<>();
                for (AbstractCard c : this.p.discardPile.group)
                    cardsToMove.add(c);
                for (AbstractCard c : cardsToMove) {
                    c.lighten(false);
                    c.target_x = Settings.WIDTH/2;
                    c.target_y = Settings.HEIGHT/2;
                    addToBot(new ExhaustSpecificCardAction(c, p.discardPile));
                }
                this.isDone = true;
                return;
            }
            AbstractDungeon.gridSelectScreen.open(this.p.discardPile, this.amount, TEXT[0], false);
            tickDuration();
            return;
        }
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            ArrayList<AbstractCard> cardsSelected = new ArrayList<>();
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                c.lighten(false);
                c.unhover();
                c.target_x = Settings.WIDTH/2;
                c.target_y = Settings.HEIGHT/2;
                cardsSelected.add(c);
                addToBot(new ExhaustSpecificCardAction(c, p.discardPile));
            }
            for (AbstractCard c : this.p.discardPile.group) {
                if (cardsSelected.contains(c)) continue;
                c.unhover();
                c.target_x = CardGroup.DISCARD_PILE_X;
                c.target_y = 0.0F;
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.player.hand.refreshHandLayout();
        }
        tickDuration();
        if (this.isDone)
            for (AbstractCard c : this.p.hand.group)
                c.applyPowers();
    }
}