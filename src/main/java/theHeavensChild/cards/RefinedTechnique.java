package theHeavensChild.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Iterator;

import static theHeavensChild.HeavensChildMod.makeID;

public class RefinedTechnique extends AbstractEasyCard {
    public final static String ID = makeID("RefinedTechnique");

    private static final int BASE_COST = 2;
    private final int BASE_DMG = 6;
    private final int BASE_ADD_DMG = 1;
    private final int UPG_ADD_DMG = 1;


    public RefinedTechnique() {
        super(ID, BASE_COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = BASE_DMG;
        magicNumber = baseMagicNumber = BASE_ADD_DMG;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        damageEnemy(m, AttackEffect.BLUNT_LIGHT);
    }

    public static int countCards() {
        int count = 0;

        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type == CardType.ATTACK) {
                ++count;
            }
        }
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.type == CardType.ATTACK) {
                ++count;
            }
        }
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c.type == CardType.ATTACK) {
                ++count;
            }
        }

        return count;
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.magicNumber * countCards();
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.magicNumber * countCards();
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public void upgradeVariables() {
        upgradeMagicNumber(UPG_ADD_DMG);
    }
}