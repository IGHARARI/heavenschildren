package theHeavensChild.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeavensChild.actions.BladeHurricaneAction;
import theHeavensChild.powers.FrenzyPower;

import static theHeavensChild.HeavensChildMod.makeID;

public class BladeHurricane extends AbstractEasyCard {
    public final static String ID = makeID(Frenzy.class.getSimpleName());

    private final int DAMAGE = 7;
    private final int UPG_DAMAGE = 2;
    private static final int DRAW = 5;
    private static final int COST = 2;
    public BladeHurricane() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        damage = baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = DRAW;
        this.exhaust = true;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(magicNumber));
        addToBot(new BladeHurricaneAction(this));
    }
    public void upgradeVariables() { upgradeDamage(UPG_DAMAGE); }
}