package theHeavensChild.cards.democards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.Spiker;
import theHeavensChild.cardmods.LambdaMod;
import theHeavensChild.cards.AbstractEasyCard;

import static theHeavensChild.HeavensChildMod.makeID;
import static theHeavensChild.util.Wiz.makeInHand;
import static theHeavensChild.util.Wiz.returnTrulyRandomPrediCardInCombat;

public class InlineCardModDemo extends AbstractEasyCard {

    public final static String ID = makeID("InlineCardModDemo");
    // intellij stuff skill, self, uncommon

    public InlineCardModDemo() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard q = returnTrulyRandomPrediCardInCombat(c -> c.hasTag(CardTags.STRIKE) && c.rarity != CardRarity.BASIC, true);
        CardModifierManager.addModifier(q, new LambdaMod() {
            @Override
            public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
                if (target instanceof Spiker) {
                    addToBot(new InstantKillAction(target));
                }
            }

            @Override
            public String modifyDescription(String rawDescription, AbstractCard card) {
                return rawDescription + " NL Kills Spikers.";
            }
        });
        makeInHand(q);
    }

    public void upgradeVariables() {
        upgradeBaseCost(0);
    }
} 