package theHeavensChild.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theHeavensChild.powers.LambdaPower;
import theHeavensChild.util.Wiz;

import static theHeavensChild.HeavensChildMod.makeID;
import static theHeavensChild.util.Wiz.applyToSelf;

public class PullThrough extends AbstractEasyCard {
    public final static String ID = makeID("PullThrough");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,
    private final int BASE_BLOCK = 10;
    private final int UPG_BLOCK = 4;
    private final int BASE_DISCARD = 1;


    public PullThrough() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        block = baseBlock = BASE_BLOCK;
        magicNumber = baseMagicNumber = BASE_DISCARD;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        gainBlock();
        applyToSelf(new LambdaPower(cardStrings.EXTENDED_DESCRIPTION[0], AbstractPower.PowerType.BUFF, false, p, magicNumber) {
            @Override
            public void atStartOfTurnPostDraw() {
                this.flash();
                addToBot(new DiscardAction(p, p, amount, false));
                addToBot(new RemoveSpecificPowerAction(p, p, this));
            }

            @Override
            public void updateDescription() {
                String[] descriptions = cardStrings.EXTENDED_DESCRIPTION;
                description = descriptions[1] + amount + (amount > 1? descriptions[3] : descriptions[2]);
            }
        });
    }

    public void upgradeVariables() {
        upgradeBlock(UPG_BLOCK);
    }
}