package theHeavensChild.cards;

import basemod.devcommands.draw.Draw;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theHeavensChild.util.Wiz;

import static theHeavensChild.HeavensChildMod.makeID;

public class TacticalToxin extends AbstractEasyCard {
    public final static String ID = makeID("TacticalToxin");

    private final int BASE_POISON = 4;
    private final int UPG_POISON = 3;
    private final int BASE_DRAW = 1;

    private static final int COST = 1;


    public TacticalToxin() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        magicNumber = baseMagicNumber = BASE_POISON;
        m2 = baseM2 = BASE_DRAW;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToEnemy(m, new PoisonPower(m, p, magicNumber), AbstractGameAction.AttackEffect.POISON);
        addToBot(new DrawCardAction(m2));
    }

    public void upgradeVariables() {
        upgradeMagicNumber(UPG_POISON);
    }
}