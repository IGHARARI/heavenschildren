package theHeavensChild.cards.democards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theHeavensChild.cards.AbstractEasyCard;
import theHeavensChild.powers.LambdaPower;
import theHeavensChild.util.Wiz;

import static theHeavensChild.HeavensChildMod.makeID;
import static theHeavensChild.util.Wiz.applyToSelf;

public class InlinePowerDemo extends AbstractEasyCard {

    public final static String ID = makeID("InlinePowerDemo");
    // intellij stuff power, self, uncommon

    private static final int MAGIC = 4;
    private static final int UPG_MAGIC = 2;

    public InlinePowerDemo() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower("Strike+, Block+", AbstractPower.PowerType.BUFF, false, p, magicNumber) {
            @Override
            public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
                if (card.hasTag(CardTags.STRIKE)) {
                    return damage + amount;
                }
                return damage;
            }

            @Override
            public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
                flash();
                Wiz.addToBot(new GainBlockAction(owner, amount));
            }

            @Override
            public void updateDescription() {
                description = "#yStrikes deal #b" + amount + " additional damageEnemy. At the end of your turn, gain #b" + amount + " #yBlock.";
            }
        });
    }

    public void upgradeVariables() {
        upgradeMagicNumber(UPG_MAGIC);
    }
} 