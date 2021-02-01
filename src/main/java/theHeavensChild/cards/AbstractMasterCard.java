package theHeavensChild.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public abstract class AbstractMasterCard extends AbstractEasyCard {
    public AbstractMasterCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target);
    }

    public AbstractMasterCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
        super(cardID, cost, type, rarity, target, color);
    }

    public boolean satisfiesMasterCheck() {
        return false;
    }

    @Override
    public void triggerOnGlowCheck() {
        if (satisfiesMasterCheck() && !freeToPlayOnce) {
            if (cost != 0) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
            } else {
                this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
            }
        }
    }

    protected void refundEnergyIfMasterAchieved() {
        if (satisfiesMasterCheck() && !freeToPlayOnce) {
            if (cost > 0) {
                addToBot(new GainEnergyAction(cost));
            } else if (cost == -1) {
                addToBot(new GainEnergyAction(EnergyPanel.totalCount));
            }
        }
    }
}
