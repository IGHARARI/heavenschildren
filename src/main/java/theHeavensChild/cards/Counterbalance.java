package theHeavensChild.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theHeavensChild.util.Wiz;

import static theHeavensChild.HeavensChildMod.makeID;

public class Counterbalance extends AbstractEasyCard {
    public final static String ID = makeID("Counterbalance");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,
    private final int BASE_BLOCK = 2;
    private final int UPG_BLOCK = 3;
    private final int BASE_WEAK = 1;
    private final int UPG_WEAK = 1;


    public Counterbalance() {
        super(ID, 0, CardType.SKILL, CardRarity.BASIC, CardTarget.ENEMY);
        block = baseBlock = BASE_BLOCK;
        magicNumber = baseMagicNumber = BASE_WEAK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        gainBlock();
        Wiz.applyToEnemy(m, new WeakPower(m, magicNumber, false));
    }

    public void upgradeVariables() {
        upgradeMagicNumber(UPG_WEAK);
        upgradeBlock(UPG_BLOCK);
    }
}