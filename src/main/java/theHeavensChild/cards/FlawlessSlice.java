package theHeavensChild.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theHeavensChild.actions.InfernoTempestAction;

import static theHeavensChild.HeavensChildMod.makeID;

public class FlawlessSlice extends AbstractEasyCard {
    public final static String ID = makeID(FlawlessSlice.class.getSimpleName());
    private final int DAMAGE = 15;
    private final int UPG_PLUS_DAMAGE = 5;
    private static final int COST = 1;
    public FlawlessSlice() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        damage = baseDamage = DAMAGE;
    }
    public void use(AbstractPlayer p, AbstractMonster m) { addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn))); }
    public void upgradeVariables() { upgradeDamage(UPG_PLUS_DAMAGE); }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if(!canUse){ return false; }
        for(AbstractPower pow: AbstractDungeon.player.powers){
            if(pow.type == AbstractPower.PowerType.DEBUFF){ return false; }
        }
        return true;
    }

}