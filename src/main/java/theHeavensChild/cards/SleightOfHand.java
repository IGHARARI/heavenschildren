package theHeavensChild.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theHeavensChild.HeavensChildMod.makeID;

public class SleightOfHand extends AbstractEasyCard {
    public final static String ID = makeID("SleightOfHand");

    private final int BASE_DISCARD_DAMAGE = 10;
    private final int UPG_DISCARD_DAMAGE = 4;

    private static final int COST = -2;


    public SleightOfHand() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        magicNumber = baseMagicNumber = BASE_DISCARD_DAMAGE;
    }

    @Override
    public void triggerOnManualDiscard() {
        AbstractMonster m = AbstractDungeon.getRandomMonster();
        damageEnemy(m, magicNumber, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    @Override
    public void triggerWhenDrawn() {
        addToBot(new DiscardSpecificCardAction(this));
    }

    public void use(AbstractPlayer p, AbstractMonster m) { }

    public void upgradeVariables() {
        upgradeMagicNumber(UPG_DISCARD_DAMAGE);
    }
}