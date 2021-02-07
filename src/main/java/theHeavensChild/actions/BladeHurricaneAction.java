package theHeavensChild.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BladeHurricaneAction extends AbstractGameAction {

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction");
    public static final String[] TEXT = uiStrings.TEXT;
    private static final float DURATION = Settings.ACTION_DUR_XFAST;
    private AbstractPlayer p;
    private AbstractCard paired;

    public BladeHurricaneAction(AbstractCard pair) {
        p = AbstractDungeon.player;
        this.actionType = AbstractGameAction.ActionType.DISCARD;
        this.duration = DURATION;
        paired = pair;
    }

    public void update() {

        if (this.duration == DURATION) {
            if (this.p.hand.size() == 0) {
                this.isDone = true;
                return;
            }
            if (p.hand.size() > 0) {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], p.hand.size(), true, true);
                tickDuration();
                return;
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                this.p.hand.moveToDiscardPile(c);
                c.triggerOnManualDiscard();
                GameActionManager.incrementDiscard(false);
                addToTop(new AbstractGameAction() {
                    @Override
                    public void update() {
                        target = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
                        if(target != null) {
                            paired.calculateCardDamage((AbstractMonster) target);
                            addToTop(new DamageAction(target, new DamageInfo(AbstractDungeon.player, paired.damage, paired.damageTypeForTurn), AttackEffect.FIRE));
                        }
                        this.isDone = true;
                    }
                });
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }
        tickDuration();
    }
}