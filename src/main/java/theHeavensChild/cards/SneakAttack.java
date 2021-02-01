package theHeavensChild.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theHeavensChild.HeavensChildMod.makeID;

public class SneakAttack extends AbstractEasyCard {
    public final static String ID = makeID("Sneak Attack");

    private static final int COST = -2;
    private static final int UPG_COST = 0;
    private static final int DRAW_AMOUNT = 1;

    public SneakAttack() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        magicNumber = baseMagicNumber = DRAW_AMOUNT;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(magicNumber));
    }

    @Override
    public void triggerOnManualDiscard() {
        addToBot(new SelectCardsAction(
                AbstractDungeon.player.discardPile.group,
                1,
                cardStrings.EXTENDED_DESCRIPTION[0],
                false,
                c -> c.type == CardType.ATTACK && c.cost != -2,
                cardsPicked -> {
                    for (AbstractCard c : cardsPicked) {
                        AbstractMonster randomTarget = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(
                                (AbstractMonster) null,
                                true,
                                AbstractDungeon.cardRandomRng);

                        AbstractDungeon.player.discardPile.group.remove(c);
                        AbstractDungeon.getCurrRoom().souls.remove(c);
                        AbstractDungeon.player.limbo.group.add(c);
                        c.current_y = -200.0F * Settings.scale;
                        c.target_x = (float)Settings.WIDTH / 2.0F + 200.0F * Settings.xScale;
                        c.target_y = (float)Settings.HEIGHT / 2.0F;
                        c.targetAngle = 0.0F;
                        c.lighten(false);
                        c.drawScale = 0.12F;
                        c.targetDrawScale = 0.75F;
                        c.applyPowers();
                        addToBot(new NewQueueCardAction(c, randomTarget, false, true));
                        addToBot(new UnlimboAction(c));
                    }
                }
                ));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return upgraded;
    }

    public void upgradeVariables() {
        setUpgradeDescription();
        upgradeBaseCost(UPG_COST);
    }
}