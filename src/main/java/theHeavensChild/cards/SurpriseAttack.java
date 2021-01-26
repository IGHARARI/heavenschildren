package theHeavensChild.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeavensChild.HeavensChildMod;

import static theHeavensChild.HeavensChildMod.makeID;

public class SurpriseAttack extends AbstractEasyCard {
    public final static String ID = makeID("SurpriseAttack");

    private static final int BASE_COST = 1;
    private final int BASE_DMG = 7;
    private final int UPG_DMG = 3;
    private final int BASE_DISCARD_DMG = 10;


    public SurpriseAttack() {
        super(ID, BASE_COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = BASE_DMG;
        secondDamage = baseSecondDamage = BASE_DISCARD_DMG;
    }

    @Override
    public void triggerOnGlowCheck() {
        if (GameActionManager.totalDiscardedThisTurn > 0) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (GameActionManager.totalDiscardedThisTurn > 0) {
            damageEnemy(m, secondDamage, AttackEffect.BLUNT_LIGHT);
        } else {
            damageEnemy(m, AttackEffect.BLUNT_LIGHT);
        }
    }

    public void upgradeVariables() {
        upgradeDamage(UPG_DMG);
        upgradeSecondDamage(UPG_DMG);
    }
}