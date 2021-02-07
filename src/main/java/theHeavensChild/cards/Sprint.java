package theHeavensChild.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeavensChild.actions.BladeHurricaneAction;

import static theHeavensChild.HeavensChildMod.makeID;

public class Sprint extends AbstractEasyCard {
    public final static String ID = makeID(Sprint.class.getSimpleName());

    private final int ENERGY = 1;
    private static final int DRAW = 1;
    private final int UPG_DRAW = 1;

    private static final int COST = 0;
    public Sprint() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = DRAW;
        this.exhaust = true;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(magicNumber));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                for(AbstractCard c: p.hand.group){ addToTop(new GainEnergyAction(ENERGY)); }
                this.isDone = true;
            }
        });
    }
    public void upgradeVariables() {
        upgradeMagicNumber(UPG_DRAW);
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        this.initializeDescription();
    }
}