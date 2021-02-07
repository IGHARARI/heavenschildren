package theHeavensChild.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theHeavensChild.util.Wiz;

import static theHeavensChild.HeavensChildMod.makeID;

public class IgniteSpirit extends AbstractEasyCard {
    public final static String ID = makeID("IgniteSpirit");

    private static final int COST = 1;

    public IgniteSpirit() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SelectCardsAction(
                AbstractDungeon.player.discardPile.group,
                1,
                cardStrings.EXTENDED_DESCRIPTION[0],
                cardsPicked -> {
                    for (AbstractCard c : cardsPicked) {
                        int strGain = Wiz.getCardEffectiveCost(c);
                        if (upgraded) strGain += 1;
                        if (strGain > 0) {
                            Wiz.applyToSelfTop(new StrengthPower(p, strGain));
                        }
                        addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.discardPile));
                    }
                }
        ));
    }

    public void upgradeVariables() {
        setUpgradeDescription();
    }
}