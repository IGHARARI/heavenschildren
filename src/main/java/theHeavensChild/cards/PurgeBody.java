package theHeavensChild.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theHeavensChild.actions.QuickStepAction;
import theHeavensChild.powers.NextTurnDiscardPower;

import static theHeavensChild.HeavensChildMod.makeID;
import static theHeavensChild.util.Wiz.applyToSelf;

public class PurgeBody extends AbstractEasyCard {
    public final static String ID = makeID(PurgeBody.class.getSimpleName());

    private final int BLOCK = 16;
    private final int UPG_PLUS_BLOCK = 4;

    private final int DISCARD = 1;

    private static final int COST = 1;

    public PurgeBody() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        block = baseBlock = BLOCK;
        magicNumber = baseMagicNumber = DISCARD;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                for(AbstractPower pow: p.powers){
                    if(pow.type == AbstractPower.PowerType.DEBUFF){
                        addToTop(new RemoveSpecificPowerAction(p, p, pow));
                    }
                    this.isDone = true;
                }
            }
        });
        gainBlock();
        applyToSelf(new NextTurnDiscardPower(p, magicNumber));
    }
    public void upgradeVariables() {
        upgradeBlock(UPG_PLUS_BLOCK);
    }
}