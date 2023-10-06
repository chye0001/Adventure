public enum ReturnMessage {
    EDIBLE,
    CANT_FIND,
    REALLY_EAT, // Udvidelse: Klogere håndtering af giftigt mad (i brugerfladen)
    NOT_EDIBLE,
    REALLY_DRINK, // Udvidelse: Klogere håndtering af giftigt mad (i brugerfladen) & Flere typer "consumables"
    NOT_DRINKABLE, // Udvidelse: Flere typer "consumables"
    WEAPON_EQUIPPED,
    WEAPON_NOT_EQUIPPED,
    NO_AMMO,
    IS_NOT_A_WEAPON,
    ATTACK,
    SHIELD,
    WEAPON_SLOT_UNAVAILABLE,
    WEAPON_ALREADY_EQUIPPED
}