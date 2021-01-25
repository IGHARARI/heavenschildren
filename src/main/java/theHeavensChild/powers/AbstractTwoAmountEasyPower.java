package theHeavensChild.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theHeavensChild.util.TexLoader;

import static theHeavensChild.HeavensChildMod.makeID;

public abstract class AbstractTwoAmountEasyPower extends TwoAmountPower {
    public AbstractTwoAmountEasyPower(String NAME, PowerType powerType, boolean isTurnBased, AbstractCreature owner, int amount, int amount2) {
        this.ID = makeID(NAME.replaceAll("([ ])", "") );
        this.isTurnBased = isTurnBased;

        this.name = NAME;

        this.owner = owner;
        this.amount = amount;
        this.amount2 = amount2;
        this.type = powerType;

        Texture normalTexture = TexLoader.getTexture("bronzeResources/images/powers/" + NAME.replaceAll("([ ])", "")  + "32.png");
        Texture hiDefImage = TexLoader.getTexture("bronzeResources/images/powers/" + NAME.replaceAll("([ ])", "")  + "84.png");
        if (hiDefImage != null) {
            region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
            if (normalTexture != null)
                region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        } else if (normalTexture != null) {
            this.img = normalTexture;
            region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        }

        this.updateDescription();
    }
}