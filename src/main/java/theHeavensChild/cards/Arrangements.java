package theHeavensChild.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeavensChild.actions.ArrangementsDiscardAction;
import theHeavensChild.powers.HeavyLiftingPower;
import theHeavensChild.powers.LambdaPower;
import theHeavensChild.powers.NextTurnPowerPower;

import static theHeavensChild.HeavensChildMod.makeID;
import static theHeavensChild.util.Wiz.applyToSelf;

public class Arrangements extends AbstractEasyCard {
    public final static String ID = makeID("Arrangements");

    private static final int BASE_COST = 1;
    private static final int DRAW_AMOUNT = 1;
    private static final int UPG_DRAW_AMOUNT = 1;

    private static final int DISCARD_AMOUNT = 1;


    public Arrangements() {
        super(ID, BASE_COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = DRAW_AMOUNT;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(magicNumber));
        addToBot(new ArrangementsDiscardAction(DISCARD_AMOUNT));
    }

    public void upgradeVariables() {
        upgradeMagicNumber(UPG_DRAW_AMOUNT);
        setUpgradeDescription();
    }
}