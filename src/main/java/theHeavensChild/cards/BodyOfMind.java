package theHeavensChild.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theHeavensChild.HeavensChildMod.makeID;

public class BodyOfMind extends AbstractEasyCard {
    public final static String ID = makeID(BodyOfMind.class.getSimpleName());
    private final int BASE_BLOCK = 7;
    private final int BASE_ADDITIONAL_BLOCK = 1;
    private final int UPG_ADDITIONAL_BLOCK = 1;

    private static final int COST = 1;

    public BodyOfMind() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        block = baseBlock = BASE_BLOCK;
        magicNumber = baseMagicNumber = BASE_ADDITIONAL_BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        gainBlock();
    }

    public void applyPowers() {
        int realBaseBlock = this.baseBlock;
        this.baseBlock += this.magicNumber * countCards();
        super.applyPowers();
        this.baseBlock = realBaseBlock;
        this.isBlockModified = this.block != this.baseBlock;
    }

    public static int countCards() {
        int count = 0;
        AbstractPlayer p = AbstractDungeon.player;

        for (AbstractCard c : p.hand.group) { if (c.type == CardType.SKILL) count++; }
        return count;
    }

    public void upgradeVariables() {
        upgradeMagicNumber(UPG_ADDITIONAL_BLOCK);
    }
}