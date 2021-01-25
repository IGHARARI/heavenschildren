package theHeavensChild.cards.democards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theHeavensChild.actions.EasyXCostAction;
import theHeavensChild.cards.AbstractEasyCard;
import theHeavensChild.util.Wiz;

import static theHeavensChild.util.Wiz.*;
import static theHeavensChild.HeavensChildMod.makeID;

public class EasyXCostDemo extends AbstractEasyCard {
    public final static String ID = makeID("EasyXCostDemo");
    // intellij stuff attack, enemy, rare, , , , , 0, 1

    public EasyXCostDemo() {
        super(ID, -1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 0;
        baseDamage = 5;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.addToBot(new EasyXCostAction(this, (effect, params) -> {
            for (int i = 0; i < effect + params[0]; i++) {
                damageEnemyTop(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
            }
            applyToSelfTop(new StrengthPower(p, effect + params[0]));
            return true;
        }, magicNumber));
    }

    public void upgradeVariables() {
        upgradeMagicNumber(1);
        setUpgradeDescription();
    }
}