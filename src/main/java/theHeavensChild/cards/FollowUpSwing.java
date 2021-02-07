package theHeavensChild.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theHeavensChild.HeavensChildMod.makeID;
import static theHeavensChild.HeavensChildMod.receivedAttackDamageLastTurn;

public class FollowUpSwing extends AbstractMasterCard {
    public final static String ID = makeID(FollowUpSwing.class.getSimpleName());
    private static final int COST = 1;
    private int DAMAGE = 10;
    private int UPG_DAMAGE = 4;

    public FollowUpSwing() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        damage = baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        damageEnemy(m, AbstractGameAction.AttackEffect.NONE);
        refundEnergyIfMasterAchieved();
    }

    public void upgradeVariables() {
        upgradeDamage(UPG_DAMAGE);
    }

    @Override
    public boolean satisfiesMasterCheck() {
        for(AbstractCard c: AbstractDungeon.actionManager.cardsPlayedThisTurn){
            if(c.type == CardType.SKILL){ return true; }
        }
        return false;
    }
}