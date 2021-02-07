package theHeavensChild.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;

public class QuickStepAction extends AbstractGameAction {
    private int dex;

    public QuickStepAction(int dexterity) {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        this.dex = dexterity;
    }

    public void update() {
        for (AbstractCard c : DrawCardAction.drawnCards) {
            if (c.type == AbstractCard.CardType.SKILL) {
                addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, dex), dex));
            }
        }
        this.isDone = true;
    }
}
