package theHeavensChild.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeavensChild.HeavensChildMod;

import java.util.Iterator;

import static theHeavensChild.HeavensChildMod.makeID;

public class Revenge extends AbstractEasyCard {
    public final static String ID = makeID("Revenge");

    private static final int BASE_COST = 1;
    private final int BASE_DMG = 6;
    private final int UPG_DMG = 2;

    public Revenge() {
        super(ID, BASE_COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = BASE_DMG;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        damageEnemy(m, AttackEffect.SLASH_HORIZONTAL);
        if (HeavensChildMod.receivedAttackDamageLastTurn) {
            damageEnemy(m, AttackEffect.SLASH_DIAGONAL);
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        if (HeavensChildMod.receivedAttackDamageLastTurn) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void triggerOnExhaust() {
        addToBot(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, magicNumber, DamageInfo.DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
    }

    public void upgradeVariables() {
        upgradeDamage(UPG_DMG);
    }
}