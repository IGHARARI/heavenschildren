package theHeavensChild.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeavensChild.powers.OverstrainPower;
import theHeavensChild.util.Wiz;

import static theHeavensChild.HeavensChildMod.makeID;

public class CrazySwing extends AbstractEasyCard {
    public final static String ID = makeID("CrazySwing");

    private static final int BASE_COST = 2;
    private final int BASE_DMG = 7;
    private final int UPG_DMG = 3;
    private final int BASE_AOE_DMG = 5;
    private final int UPG_AOE_DMG = 5;


    public CrazySwing() {
        super(ID, BASE_COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = BASE_AOE_DMG;
        baseSecondDamage = secondDamage = BASE_DMG;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        damageEnemy(m, secondDamage, AttackEffect.BLUNT_LIGHT);
        damageAll(AttackEffect.BLUNT_HEAVY);
    }

    @Override
    public void applyPowers() {
        int originalBaseDmg = baseDamage;
        int originalBaseSecondDmg = baseSecondDamage;
        baseSecondDamage = -1;

        baseDamage = originalBaseSecondDmg;
        isMultiDamage = false;
        super.applyPowers();
        System.out.println("Second damage on Apply powers " + damage);

        secondDamage = damage;

        baseDamage = originalBaseDmg;
        isMultiDamage = true;
        super.applyPowers();

        baseSecondDamage = originalBaseSecondDmg;
        isSecondDamageModified = (secondDamage != baseSecondDamage);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int originalBaseDmg = baseDamage;
        int originalBaseSecondDmg = baseSecondDamage;
        baseSecondDamage = -1;

        baseDamage = originalBaseSecondDmg;
        isMultiDamage = false;
        super.calculateCardDamage(mo);
        System.out.println("Second damage on Calc card dam " + damage);

        secondDamage = damage;

        baseDamage = originalBaseDmg;
        isMultiDamage = true;
        super.calculateCardDamage(mo);

        baseSecondDamage = originalBaseSecondDmg;
        isSecondDamageModified = (secondDamage != baseSecondDamage);
    }

    public void upgradeVariables() {
        upgradeDamage(UPG_AOE_DMG);
        upgradeSecondDamage(UPG_DMG);
    }
}