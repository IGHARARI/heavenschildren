package theHeavensChild.cards.democards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theHeavensChild.actions.EasyModalChoiceAction;
import theHeavensChild.cards.AbstractEasyCard;
import theHeavensChild.cards.EasyModalChoiceCard;
import theHeavensChild.util.Wiz;

import java.util.ArrayList;

import static theHeavensChild.HeavensChildMod.makeID;
import static theHeavensChild.util.Wiz.*;

public class EasyModalChoiceDemo extends AbstractEasyCard {
    public final static String ID = makeID("EasyModalChoiceDemo");
    // intellij stuff skill, self, uncommon, , , , , , 

    public EasyModalChoiceDemo() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        baseM2 = m2 = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> easyCardList = new ArrayList<>();
        easyCardList.add(new EasyModalChoiceCard("Draw", "Draw " + magicNumber + " cards.", () -> Wiz.addToTop(new DrawCardAction(magicNumber))));
        easyCardList.add(new EasyModalChoiceCard("Strength", "Gain " + m2 + " Strength.", () -> applyToSelfTop(new StrengthPower(p, m2))));
        Wiz.addToBot(new EasyModalChoiceAction(easyCardList));
    }

    public void upgradeVariables() {
        upgradeMagicNumber(1);
        upgradeM2(1);
    }
}