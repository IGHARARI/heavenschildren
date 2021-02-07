package theHeavensChild.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeavensChild.actions.QuickStepAction;
import theHeavensChild.powers.FrenzyPower;

import static theHeavensChild.HeavensChildMod.makeID;

public class Frenzy extends AbstractEasyCard {
    public final static String ID = makeID(Frenzy.class.getSimpleName());

    private final int DAMAGE = 3;
    private final int UPG_DAMAGE = 2;
    private static final int COST = 1;
    private static final int AMOUNT = 1;
    private static final int HITS = 2;

    public Frenzy() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        damage = baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = HITS;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < magicNumber; i+= 1) { addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn))); }
        addToBot(new ApplyPowerAction(p, p, new FrenzyPower(this.makeStatEquivalentCopy(), AMOUNT)));
    }
    public void upgradeVariables() { upgradeDamage(UPG_DAMAGE); }
}