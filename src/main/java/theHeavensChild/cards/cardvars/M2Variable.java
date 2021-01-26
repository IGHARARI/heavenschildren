package theHeavensChild.cards.cardvars;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theHeavensChild.cards.AbstractEasyCard;

import static theHeavensChild.HeavensChildMod.makeID;

public class M2Variable extends DynamicVariable {

    @Override
    public String key() {
        return makeID("M2");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        if (card instanceof AbstractEasyCard) {
            return ((AbstractEasyCard) card).isM2Modified;
        }
        return false;
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof AbstractEasyCard) {
            return ((AbstractEasyCard) card).m2;
        }
        return -1;
    }

    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof AbstractEasyCard) {
            ((AbstractEasyCard) card).isM2Modified = v;
        }
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof AbstractEasyCard) {
            return ((AbstractEasyCard) card).baseM2;
        }
        return -1;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        if (card instanceof AbstractEasyCard) {
            return ((AbstractEasyCard) card).upgradedM2;
        }
        return false;
    }
}