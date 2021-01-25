package theHeavensChild.cards.democards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import theHeavensChild.cards.AbstractEasyCard;

import static theHeavensChild.HeavensChildMod.makeID;
import static theHeavensChild.util.Wiz.applyToSelfNextTurn;

public class NextTurnPowerDemo extends AbstractEasyCard {

    public final static String ID = makeID("NextTurnPowerDemo");
    // intellij stuff skill, self, uncommon

    private static final int MAGIC = 6;
    private static final int UPG_MAGIC = 3;

    public NextTurnPowerDemo() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelfNextTurn(new ThornsPower(p, magicNumber));
    }

    public void upgradeVariables() {
        upgradeMagicNumber(UPG_MAGIC);
    }
} 