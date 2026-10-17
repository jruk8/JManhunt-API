package com.jruk8.jmanhunt.api;

/**
 * A player's role within JManhunt, as exposed to companion
 * plugins. Mirrors the engine role model without leaking
 * implementation classes.
 */
public enum Role {
    /** No JManhunt role assigned. */
    NONE,
    /** An active hunter. */
    HUNTER,
    /** An active runner. */
    RUNNER,
    /** Marked as away. */
    AFK,
    /** Watching instead of playing. */
    SPECTATOR;

    /** True for HUNTER or RUNNER (active match participants). */
    public boolean isParticipant() {
        return this == HUNTER || this == RUNNER;
    }
}
