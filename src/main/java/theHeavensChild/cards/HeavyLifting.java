package theHeavensChild.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theHeavensChild.powers.HeavyLiftingPower;
import theHeavensChild.powers.LambdaPower;

import static theHeavensChild.HeavensChildMod.makeID;
import static theHeavensChild.util.Wiz.applyToSelf;

public class HeavyLifting extends AbstractEasyCard {
    public final static String ID = makeID("HeavyLifting");

    private static final int BASE_COST = 1;
    private static final int UPG_COST = 0;
    private static final int STR_AMOUNT = 1;


    public HeavyLifting() {
        super(ID, BASE_COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = STR_AMOUNT;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new HeavyLiftingPower(p, 1));
    }

    public void upgradeVariables() {
        upgradeBaseCost(UPG_COST);
    }
}