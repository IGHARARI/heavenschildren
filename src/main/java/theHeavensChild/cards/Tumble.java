package theHeavensChild.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import theHeavensChild.powers.LambdaPower;
import theHeavensChild.powers.NextTurnDiscardPower;

import static theHeavensChild.HeavensChildMod.makeID;
import static theHeavensChild.util.Wiz.applyToSelf;

public class Tumble extends AbstractEasyCard {
    public final static String ID = makeID("Tumble");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,
    private final int BASE_BLOCK = 6;
    private final int UPG_BLOCK = 3;
    private final int BASE_DRAW = 2;
    private final int BASE_DISCARD = 1;

    private static final int COST = 1;

    public Tumble() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        block = baseBlock = BASE_BLOCK;
        magicNumber = baseMagicNumber = BASE_DRAW;
        m2 = baseM2 = BASE_DISCARD;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        gainBlock();
        applyToSelf(new NextTurnDiscardPower(p, m2));
        applyToSelf(new DrawCardNextTurnPower(p, magicNumber));
    }

    public void upgradeVariables() {
        upgradeBlock(UPG_BLOCK);
    }
}