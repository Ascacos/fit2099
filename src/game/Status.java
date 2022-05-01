package game;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY,   // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL,               // use this status to tell that current instance has "grown".
    SUPER_MUSHROOM,     // used when an Actor is under the effect of a Super Mushroom consumable
    POWER_STAR,         // used when an Actor is under the effect of a Power Star consumable
    FERTILE,            // used to distinguish between fertile and infertile land
    RESETTING,          // used this to indicate a resetting ITEM/ACTOR/GROUND on next tick
}
