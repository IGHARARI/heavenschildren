package theHeavensChild;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import theHeavensChild.cards.AbstractEasyCard;
import theHeavensChild.cards.cardvars.SecondDamage;
import theHeavensChild.cards.cardvars.M2Variable;
import theHeavensChild.relics.AbstractEasyRelic;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class HeavensChildMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        OnPlayerDamagedSubscriber,
        OnStartBattleSubscriber,
        PreMonsterTurnSubscriber,
        PostEnergyRechargeSubscriber,
        EditCharactersSubscriber,
        OnCardUseSubscriber,
        OnPlayerLoseBlockSubscriber
{

    private static final String modID = "HeavensChild";
    public static String getModID() {
        return modID;
    }
    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }

    public static Color characterColor = new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1);
    public static final String SHOULDER1 = getModID() + "Resources/images/char/mainChar/shoulder.png";
    public static final String SHOULDER2 = getModID() + "Resources/images/char/mainChar/shoulder2.png";
    public static final String CORPSE = getModID() + "Resources/images/char/mainChar/corpse.png";
    private static final String ATTACK_S_ART = getModID() + "Resources/images/512/attack.png";
    private static final String SKILL_S_ART = getModID() + "Resources/images/512/skill.png";
    private static final String POWER_S_ART = getModID() + "Resources/images/512/power.png";
    private static final String CARD_ENERGY_S = getModID() + "Resources/images/512/energy.png";
    private static final String TEXT_ENERGY = getModID() + "Resources/images/512/text_energy.png";
    private static final String ATTACK_L_ART = getModID() + "Resources/images/1024/attack.png";
    private static final String SKILL_L_ART = getModID() + "Resources/images/1024/skill.png";
    private static final String POWER_L_ART = getModID() + "Resources/images/1024/power.png";
    private static final String CARD_ENERGY_L = getModID() + "Resources/images/1024/energy.png";
    private static final String CHARSELECT_BUTTON = getModID() + "Resources/images/charSelect/charButton.png";
    private static final String CHARSELECT_PORTRAIT = getModID() + "Resources/images/charSelect/charBG.png";

    public HeavensChildMod() {
        BaseMod.subscribe(this);

        BaseMod.addColor(TheHeavensChild.Enums.HEAVENSCHILD_COLOR, characterColor, characterColor, characterColor,
                characterColor, characterColor, characterColor, characterColor,
                ATTACK_S_ART, SKILL_S_ART, POWER_S_ART, CARD_ENERGY_S,
                ATTACK_L_ART, SKILL_L_ART, POWER_L_ART,
                CARD_ENERGY_L, TEXT_ENERGY);
    }

    public static boolean receivedAttackDamageLastTurn = false;
    public static boolean resettedAttackDamage = true;
    public static int energySpentThisCombat = 0;

    public static String makePath(String resourcePath) {
        return modID + "Resources/" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return modID + "Resources/images/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return modID + "Resources/images/relics/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return modID + "Resources/images/powers/" + resourcePath;
    }

    public static String makeCardPath(String resourcePath) {
        return modID + "Resources/images/cards/" + resourcePath;
    }

    public static void initialize() {
        HeavensChildMod thismod = new HeavensChildMod();
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new TheHeavensChild(TheHeavensChild.characterStrings.NAMES[1], TheHeavensChild.Enums.THE_HEAVENSCHILD),
                CHARSELECT_BUTTON, CHARSELECT_PORTRAIT, TheHeavensChild.Enums.THE_HEAVENSCHILD);
    }

    @Override
    public void receiveEditRelics() {
        new AutoAdd(modID)
                .packageFilter(AbstractEasyRelic.class)
                .any(AbstractEasyRelic.class, (info, relic) -> {
                    if (relic.color == null) {
                        BaseMod.addRelic(relic, RelicType.SHARED);
                    } else {
                        BaseMod.addRelicToCustomPool(relic, relic.color);
                    }
                    if (!info.seen) {
                        UnlockTracker.markRelicAsSeen(relic.relicId);
                    }
                });
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addDynamicVariable(new M2Variable());
        BaseMod.addDynamicVariable(new SecondDamage());
        new AutoAdd(modID)
                .packageFilter(AbstractEasyCard.class)
                .setDefaultSeen(true)
                .cards();
    }


    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, getModID() + "Resources/localization/eng/Cardstrings.json");

        BaseMod.loadCustomStringsFile(RelicStrings.class, getModID() + "Resources/localization/eng/Relicstrings.json");

        BaseMod.loadCustomStringsFile(CharacterStrings.class, getModID() + "Resources/localization/eng/Charstrings.json");

        BaseMod.loadCustomStringsFile(PowerStrings.class, getModID() + "Resources/localization/eng/Powerstrings.json");

    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(getModID() + "Resources/localization/eng/Keywordstrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        if (keywords != null) { for (Keyword keyword : keywords) { BaseMod.addKeyword(modID.toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION); } }
    }

    @Override
    public int receiveOnPlayerDamaged(int i, DamageInfo damageInfo) {
        if (damageInfo.type == DamageInfo.DamageType.NORMAL && i > 0) {
            if (i > AbstractDungeon.player.currentBlock) {
                receivedAttackDamageLastTurn = true;
            }
        }
        return i;
    }

    @Override
    public void receiveCardUsed(AbstractCard c){
        if(c.cost >= 0){ energySpentThisCombat += c.cost; }
        else if(c.cost == -1){ energySpentThisCombat += EnergyPanel.totalCount; }
    }

    @Override public int receiveOnPlayerLoseBlock(int var){
        // logic for Vigilance.
        System.out.println("debug logging take 1.");
        return var;
    }


    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        resettedAttackDamage = true;
        receivedAttackDamageLastTurn = false;
        energySpentThisCombat = 0;
    }

    @Override
    public boolean receivePreMonsterTurn(AbstractMonster abstractMonster) {
        if (!resettedAttackDamage) {
            resettedAttackDamage = true;
            receivedAttackDamageLastTurn = false;
        }
        return true;
    }

    @Override
    public void receivePostEnergyRecharge() {
        resettedAttackDamage = false;
    }
}
