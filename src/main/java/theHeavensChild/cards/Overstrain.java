package theHeavensChild.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeavensChild.powers.OverstrainPower;
import theHeavensChild.util.Wiz;

import static theHeavensChild.HeavensChildMod.makeID;

public class Overstrain extends AbstractEasyCard {
    public final static String ID = makeID("Overstrain");

    private static final int BASE_COST = 1;
    private final int BASE_DMG = 15;
    private final int UPG_DMG = 5;


    public Overstrain() {
        super(ID, BASE_COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = BASE_DMG;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        damageEnemy(m, AttackEffect.BLUNT_LIGHT);
        Wiz.applyToSelf(new OverstrainPower(p, 1));
    }

    public void upgradeVariables() {
        upgradeDamage(UPG_DMG);
    }
}