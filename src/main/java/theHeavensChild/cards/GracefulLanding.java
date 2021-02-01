package theHeavensChild.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theHeavensChild.HeavensChildMod.makeID;
import static theHeavensChild.HeavensChildMod.receivedAttackDamageLastTurn;

public class GracefulLanding extends AbstractMasterCard {
    public final static String ID = makeID("GracefulLanding");

    private final int BASE_BLOCK = 7;
    private final int UPG_BLOCK = 2;

    private static final int COST = 1;

    public GracefulLanding() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        block = baseBlock = BASE_BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        gainBlock();
        refundEnergyIfMasterAchieved();
    }

    public void upgradeVariables() {
        upgradeBlock(UPG_BLOCK);
    }

    @Override
    public boolean satisfiesMasterCheck() {
        return !receivedAttackDamageLastTurn;
    }
}