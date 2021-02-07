package theHeavensChild.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import theHeavensChild.powers.NextTurnDiscardPower;

import java.util.Iterator;

import static theHeavensChild.HeavensChildMod.makeID;
import static theHeavensChild.util.Wiz.applyToSelf;

public class PerfectGuard extends AbstractEasyCard {
    public final static String ID = makeID("PerfectGuard");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,
    private final int BASE_BLOCK = 5;
    private final int BASE_ADDITIONAL_BLOCK = 1;

    private static final int COST = 2;
    private static final int UPG_COST = 1;

    public PerfectGuard() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
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

        for (AbstractCard c : p.drawPile.group) {
            if (c.type == CardType.SKILL) count++;
        }

        for (AbstractCard c : p.discardPile.group) {
            if (c.type == CardType.SKILL) count++;
        }

        for (AbstractCard c : p.hand.group) {
            if (c.type == CardType.SKILL) count++;
        }
        return count;
    }

    public void upgradeVariables() {
        upgradeBaseCost(UPG_COST);
    }
}