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
    WEAPON_SLOT_UNAVAILABLE, //Udvidelse: dual wielding
    WEAPON_ALREADY_EQUIPPED, //Udvidelse: dual wielding
    DUAL_WIELDING_ATTACK, //Udvidelse: dual wielding
    WEAPON1_NO_AMMO_USE_WEAPON_2, //Udvidelse: dual wielding
    WEAPON2_NO_AMMO_USE_WEAPON_1, //Udvidelse: dual wielding
    PLAYER_DEAD,
    ENEMY_KILLED,
    PLAYER_DEAD_AND_ENEMY_KILLED,
    BATTLE_ONGOING_DUAL_WIELDING_ATTACK,
    ENEMY_DEAD,
    ENEMY_ALIVE,
    BATTLE_ONGOING_USE_WEAPON1_WEAPON2_NO_AMMO,
    BATTLE_ONGOING_USE_WEAPON2_WEAPON1_NO_AMMO,
    BATTLE_ONGOING_USE_WEAPON1_WEAPON2_NOT_EQUIPPED,
    BATTLE_ONGOING_USE_WEAPON2_WEAPON1_NOT_EQUIPPED,

}