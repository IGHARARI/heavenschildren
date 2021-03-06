package theHeavensChild.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theHeavensChild.powers.LambdaPower;
import theHeavensChild.powers.NextTurnDiscardPower;
import theHeavensChild.util.Wiz;

import static theHeavensChild.HeavensChildMod.makeID;
import static theHeavensChild.util.Wiz.applyToSelf;

public class PullThrough extends AbstractEasyCard {
    public final static String ID = makeID("PullThrough");

    private final int BASE_BLOCK = 10;
    private final int UPG_BLOCK = 4;
    private final int BASE_DISCARD = 1;

    private static final int COST = 1;

    public PullThrough() {
        super(ID, COST, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        block = baseBlock = BASE_BLOCK;
        magicNumber = baseMagicNumber = BASE_DISCARD;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        gainBlock();
        applyToSelf(new NextTurnDiscardPower(p, magicNumber));
    }

    public void upgradeVariables() {
        upgradeBlock(UPG_BLOCK);
    }
}