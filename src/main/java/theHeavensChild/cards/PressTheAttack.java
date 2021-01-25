package theHeavensChild.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Iterator;

import static theHeavensChild.HeavensChildMod.makeID;

public class PressTheAttack extends AbstractEasyCard {
    public final static String ID = makeID("PressTheAttack");

    private static final int BASE_COST = 1;
    private final int BASE_DMG = 7;
    private final int UPG_DMG = 3;
    private final int BASE_EXTRA_DMG = 3;
    private final int UPG_EXTRA_DMG = 2;
    private final int DRAW_AMOUNT = 1;


    public PressTheAttack() {
        super(ID, BASE_COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = BASE_DMG;
        secondDamage = baseSecondDamage = BASE_EXTRA_DMG;
        magicNumber = baseMagicNumber = DRAW_AMOUNT;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        damageEnemy(m, AttackEffect.BLUNT_LIGHT);
        addToBot(new DrawCardAction(magicNumber, new AbstractGameAction() {

            @Override
            public void update() {

                for (AbstractCard c : DrawCardAction.drawnCards) {
                    if (c.type == CardType.ATTACK) {
                        damageEnemy(m, secondDamage, AttackEffect.BLUNT_HEAVY);
                        break;
                    }
                }

                this.isDone = true;
            }
        }));
    }

    public void upgradeVariables() {
        upgradeSecondDamage(UPG_EXTRA_DMG);
        upgradeDamage(UPG_DMG);
    }
}