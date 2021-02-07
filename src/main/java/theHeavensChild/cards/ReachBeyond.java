package theHeavensChild.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeavensChild.powers.FrenzyPower;
import theHeavensChild.powers.ReachBeyondPower;

import static theHeavensChild.HeavensChildMod.makeID;

public class ReachBeyond extends AbstractEasyCard {
    public final static String ID = makeID(ReachBeyond.class.getSimpleName());

    private static final int COST = 3;
    private static final int UPG_COST = 2;
    private static final int AMOUNT = 1;

    public ReachBeyond() { super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF); }
    public void use(AbstractPlayer p, AbstractMonster m) { addToBot(new ApplyPowerAction(p, p, new ReachBeyondPower(AMOUNT))); }
    public void upgradeVariables() { upgradeBaseCost(UPG_COST); }
}