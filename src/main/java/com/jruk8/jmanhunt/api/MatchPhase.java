package com.jruk8.jmanhunt.api;

/**
 * Where a match stands in its lifecycle. LOBBY only appears in
 * lobby context (no match running); live matches move PRESTART
 * to IN_PROGRESS to ENDING.
 */
public enum MatchPhase {
    /** No match running. */
    LOBBY,
    /** Created and announced, pre-start window running. */
    PRESTART,
    /** Begun, win conditions live. */
    IN_PROGRESS,
    /** Winner announced, end delay running. */
    ENDING
}
