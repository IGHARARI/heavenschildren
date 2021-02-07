package theHeavensChild.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theHeavensChild.actions.EasyXCostAction;
import theHeavensChild.actions.QuickStepAction;
import theHeavensChild.powers.FortifyPower;

import static theHeavensChild.HeavensChildMod.makeID;
import static theHeavensChild.HeavensChildMod.receivedAttackDamageLastTurn;

public class Fortify extends AbstractMasterCard {
    public final static String ID = makeID(Fortify.class.getSimpleName());
    private final int BLOCK = 7;
    private final int UPG_PLUS_BLOCK = 3;
    private static final int COST = -1;

    public Fortify() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        block = baseBlock = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        gainBlock();
        addToBot(new EasyXCostAction(this, (effect, params) -> {
            addToBot(new ApplyPowerAction(p, p, new FortifyPower(params[0], effect)));
            return true;
        }, block));
        refundEnergyIfMasterAchieved();
    }
    public void upgradeVariables() {
        upgradeBlock(UPG_PLUS_BLOCK);
    }
    @Override
    public boolean satisfiesMasterCheck() {
        for(AbstractPower p: AbstractDungeon.player.powers){
            if(p.type == AbstractPower.PowerType.DEBUFF){ return true; }
        }
        return false;
    }
}