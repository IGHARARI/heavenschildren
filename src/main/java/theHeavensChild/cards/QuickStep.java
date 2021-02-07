package theHeavensChild.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeavensChild.actions.QuickStepAction;

import static theHeavensChild.HeavensChildMod.makeID;

public class QuickStep extends AbstractEasyCard {
    public final static String ID = makeID(QuickStep.class.getSimpleName());

    private final int DEX = 1;
    private final int DRAW = 2;
    private final int UPG_PLUS_DRAW = 1;

    private static final int COST = 1;

    public QuickStep() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = DRAW;
        m2 = baseM2 = DEX;
    }

    public void use(AbstractPlayer p, AbstractMonster m) { addToBot(new DrawCardAction(magicNumber, new QuickStepAction(m2))); }
    public void upgradeVariables() {
        upgradeMagicNumber(UPG_PLUS_DRAW);
    }
}