package theHeavensChild.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.VerticalImpactEffect;
import theHeavensChild.powers.NextTurnDiscardPower;
import theHeavensChild.util.Wiz;

import static theHeavensChild.HeavensChildMod.makeID;

public class FullBodyTackle extends AbstractEasyCard {
    public final static String ID = makeID("FullBodyTackle");

    private static final int BASE_COST = 2;
    private final int BASE_DMG = 18;
    private final int UPG_DMG = 4;
    private final int BASE_WEAK = 2;
    private final int UPG_WEAK = 1;

    private final int BASE_DISCARD = 2;


    public FullBodyTackle() {
        super(ID, BASE_COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = BASE_DMG;
        magicNumber = baseMagicNumber = BASE_WEAK;
        m2 = baseM2 = BASE_DISCARD;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new VerticalImpactEffect(m.hb.cX + m.hb.width / 2.0F, m.hb.cY - m.hb.height / 4.0F)));
        damageEnemy(m, damage, AttackEffect.BLUNT_HEAVY);
        Wiz.applyToEnemy(m, new WeakPower(m, magicNumber, false));
        Wiz.applyToSelf(new NextTurnDiscardPower(p, m2));
    }

    public void upgradeVariables() {
        upgradeDamage(UPG_DMG);
        upgradeMagicNumber(UPG_WEAK);
    }
}