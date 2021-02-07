package theHeavensChild.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BurstPower;
import theHeavensChild.actions.QuickStepAction;
import theHeavensChild.powers.ReplicatePower;

import static theHeavensChild.HeavensChildMod.makeID;

public class Replicate extends AbstractEasyCard {
    public final static String ID = makeID(Replicate.class.getSimpleName());
    private static final int COST = 1;
    private static final int UPG_COST = 0;
    public Replicate() { super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF); }
    public void use(AbstractPlayer p, AbstractMonster m) { addToBot(new ApplyPowerAction(p, p, new ReplicatePower())); }
    public void upgradeVariables() {
        upgradeBaseCost(UPG_COST);
    }
}