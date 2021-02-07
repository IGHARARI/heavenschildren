package theHeavensChild.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;

import static theHeavensChild.HeavensChildMod.makeID;

public class ForceOfNaturePower extends AbstractPower {
    public static final String POWER_ID = makeID(ForceOfNaturePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractCard copy;

    public ForceOfNaturePower()
    {
        name = NAME;
        ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        type = PowerType.BUFF;
        isTurnBased = false;
        this.amount = -1;
        loadRegion("flameBarrier");
        updateDescription();
    }
    public void atEndOfTurn(boolean isPlayer) {
        flash();
        AbstractMonster m = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        addToBot(new DamageAction(m, new DamageInfo(this.owner, this.owner.currentBlock / 2, DamageInfo.DamageType.THORNS)));
    }
    @Override
    public void updateDescription() { this.description = DESCRIPTIONS[0]; }
}
