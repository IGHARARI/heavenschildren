package theHeavensChild.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeavensChild.powers.ForceOfNaturePower;
import theHeavensChild.powers.ReachBeyondPower;

import static theHeavensChild.HeavensChildMod.makeID;

public class ForceOfNature extends AbstractEasyCard {
    public final static String ID = makeID(ForceOfNature.class.getSimpleName());

    private static final int COST = 3;
    private static final int UPG_COST = 2;

    public ForceOfNature() { super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF); }
    public void use(AbstractPlayer p, AbstractMonster m) { addToBot(new ApplyPowerAction(p, p, new ForceOfNaturePower())); }
    public void upgradeVariables() { upgradeBaseCost(UPG_COST); }
}