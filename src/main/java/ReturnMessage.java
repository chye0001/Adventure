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
    ATTACK1,
    ATTACK2,
    SHIELD,
    WEAPON_SLOT_UNAVAILABLE,
    WEAPON_ALREADY_EQUIPPED,
    DUAL_WIELDING_ATTACK,
    WEAPON1_NO_AMMO_USE_WEAPON_2,
    WEAPON2_NO_AMMO_USE_WEAPON_1
}