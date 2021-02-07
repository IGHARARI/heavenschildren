package theHeavensChild.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class InfernoTempestAction extends AbstractGameAction {
    private AbstractCard card;
    public InfernoTempestAction(AbstractCard card) { this.card = card; }
    public void update() {
        for(AbstractCard c: AbstractDungeon.player.exhaustPile.group){
            target = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
            if(target != null) {
                card.calculateCardDamage((AbstractMonster) target);
                addToTop(new DamageAction(target, new DamageInfo(AbstractDungeon.player, card.damage, card.damageTypeForTurn), AttackEffect.FIRE));
            }
        }
        this.isDone = true;
    }
}