package theHeavensChild.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeavensChild.powers.HeavyLiftingPower;

import static theHeavensChild.HeavensChildMod.makeID;
import static theHeavensChild.util.Wiz.applyToSelf;

public class GracefulFlip extends AbstractEasyCard {
    public final static String ID = makeID("GracefulFlip");

    private static final int BASE_COST = 1;
    private static final int UPG_COST = 0;
    private static final int BASE_DRAW_AMOUNT = 4;


    public GracefulFlip() {
        super(ID, BASE_COST, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        magicNumber = baseMagicNumber = BASE_DRAW_AMOUNT;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(magicNumber));
        if (magicNumber > 0) upgradeMagicNumber(-1);
    }

    public void upgradeVariables() {
        upgradeBaseCost(UPG_COST);
    }
}