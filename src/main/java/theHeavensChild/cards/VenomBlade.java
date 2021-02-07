package theHeavensChild.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.DamageCallbackAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import theHeavensChild.util.Wiz;

import static theHeavensChild.HeavensChildMod.makeID;

public class VenomBlade extends AbstractEasyCard {
    public final static String ID = makeID("VenomBlade");

    private static final int BASE_COST = 1;
    private final int BASE_DMG = 6;
    private final int UPG_DMG = 2;


    public VenomBlade() {
        super(ID, BASE_COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = BASE_DMG;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        DamageInfo damageInfo = new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL);
        addToBot(
                new DamageCallbackAction(
                        m,
                        damageInfo,
                        AttackEffect.SLASH_HORIZONTAL,
                        (damageDealt) -> {
                            Wiz.applyToEnemy(m, new PoisonPower(m, p, damageDealt/2), AttackEffect.POISON);
                        }
                )
        );
    }

    public void upgradeVariables() {
        upgradeDamage(UPG_DMG);
    }
}