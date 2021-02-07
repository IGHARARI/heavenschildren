package theHeavensChild.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theHeavensChild.actions.InfernoTempestAction;

import static theHeavensChild.HeavensChildMod.makeID;

public class InfernoTempest extends AbstractEasyCard {
    public final static String ID = makeID(InfernoTempest.class.getSimpleName());

    private final int STRENGTH = 1;
    private final int UPG_PLUS_STRENGTH = 1;
    private final int DAMAGE = 4;
    private final int UPG_PLUS_DAMAGE = 1;
    private static final int COST = 1;

    public InfernoTempest() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        damage = baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = STRENGTH;
    }
    public void triggerOnExhaust() { addToTop(new InfernoTempestAction(this)); }
    public void use(AbstractPlayer p, AbstractMonster m) { addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber))); }
    public void upgradeVariables() {
        upgradeDamage(UPG_PLUS_DAMAGE);
        upgradeMagicNumber(UPG_PLUS_STRENGTH);
    }

}