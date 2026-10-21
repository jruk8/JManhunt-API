package com.jruk8.jmanhunt.api.service;

/**
 * Queue actionbar point priorities: the engine's own points plus
 * the bounds companions place their items inside. Lower renders
 * first; {@link #ROOT} is the elapsed line, {@link #MATCH} the
 * attached playlist headcount, {@link #EXIT_HINT} the trailing
 * double-shift hint. Companions pick any int between
 * {@link #MIN_PRIORITY} and {@link #MAX_PRIORITY} (clamped);
 * collisions halve toward the requested slot and log severe,
 * and ten failed halves overwrite the slot, also severe. The
 * engine consumes these same constants, so this class is the
 * single ordering reference for docs and addons.
 */
public final class QueueActionbarPriorities {

    /** Lowest placeable priority; also the root point. */
    public static final int MIN_PRIORITY = 0;
    /** Highest placeable priority; renders last. */
    public static final int MAX_PRIORITY = 10000;
    /** The elapsed line: the first point, never a bullet. */
    public static final int ROOT = 0;
    /** The attached playlist headcount bullet. */
    public static final int MATCH = 1000;
    /** The trailing double-shift exit hint. */
    public static final int EXIT_HINT = 9000;

    private QueueActionbarPriorities() {
    }
}
