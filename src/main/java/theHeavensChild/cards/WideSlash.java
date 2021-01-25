package theHeavensChild.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeavensChild.actions.WideSlashAction;

import static theHeavensChild.HeavensChildMod.makeID;

public class WideSlash extends AbstractEasyCard {
    public final static String ID = makeID("WideSlash");

    private static final int BASE_COST = 1;
    private final int BASE_DMG = 8;
    private final int UPG_DMG = 3;


    public WideSlash() {
        super(ID, BASE_COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = BASE_DMG;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        damageAll(AttackEffect.SLASH_HEAVY);
        addToBot(new WideSlashAction(1));
    }


    public void upgradeVariables() {
        upgradeDamage(UPG_DMG);
    }
}