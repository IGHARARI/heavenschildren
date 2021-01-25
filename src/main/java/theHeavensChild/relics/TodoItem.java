package theHeavensChild.relics;

import theHeavensChild.TheHeavensChild;

import static theHeavensChild.HeavensChildMod.makeID;

public class TodoItem extends AbstractEasyRelic {
    public static final String ID = makeID("TodoItem");

    public TodoItem() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, TheHeavensChild.Enums.HEAVENSCHILD_COLOR);
    }
}
