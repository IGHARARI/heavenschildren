package theHeavensChild.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theHeavensChild.util.Wiz;

import static theHeavensChild.HeavensChildMod.makeID;

public class LostSoul extends AbstractEasyCard {
    public final static String ID = makeID("LostSoul");

    private static final int BASE_COST = 1;
    private final int BASE_DMG = 6;
    private final int UPG_DMG = 2;
    private final int BASE_EXH_DMG = 10;
    private final int UPG_EXH_DMG = 5;


    public LostSoul() {
        super(ID, BASE_COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = BASE_DMG;
        magicNumber = baseMagicNumber = BASE_EXH_DMG;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        damageEnemy(m, AttackEffect.BLUNT_LIGHT);
    }

    @Override
    public void triggerOnExhaust() {
        addToBot(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, magicNumber, DamageInfo.DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
    }

    public void upgradeVariables() {
        upgradeMagicNumber(UPG_EXH_DMG);
        upgradeDamage(UPG_DMG);
    }
}