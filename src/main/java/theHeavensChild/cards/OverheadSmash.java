package theHeavensChild.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theHeavensChild.util.Wiz;

import static theHeavensChild.HeavensChildMod.makeID;

public class OverheadSmash extends AbstractEasyCard {
    public final static String ID = makeID("OverheadSmash");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,
    private final int BASE_DMG = 7;
    private final int UPG_DMG = 3;
    private final int BASE_VULN = 1;
    private final int UPG_VULN = 1;


    public OverheadSmash() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = BASE_DMG;
        magicNumber = baseMagicNumber = BASE_VULN;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        damageEnemy(m, AbstractGameAction.AttackEffect.NONE);
        Wiz.applyToEnemyNextTurn(new VulnerablePower(m, magicNumber, false), m);

    }

    public void upgradeVariables() {
        upgradeMagicNumber(UPG_VULN);
        upgradeDamage(UPG_DMG);
    }
}