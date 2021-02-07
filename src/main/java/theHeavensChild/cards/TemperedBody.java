package theHeavensChild.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theHeavensChild.powers.NextTurnDiscardPower;

import static theHeavensChild.HeavensChildMod.makeID;
import static theHeavensChild.util.Wiz.applyToSelf;

public class TemperedBody extends AbstractEasyCard {
    public final static String ID = makeID(PurgeBody.class.getSimpleName());

    private final int BLOCK = 9;
    private final int UPG_PLUS_BLOCK = 14;

    private static final int COST = 2;

    public TemperedBody() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        block = baseBlock = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) { gainBlock(); }
    public void upgradeVariables() {
        upgradeBlock(UPG_PLUS_BLOCK);
    }
}