package com.jruk8.jmanhunt.api;

/**
 * Where a match stands in its lifecycle. LOBBY only appears in
 * lobby context (no match running); live matches move PREPARING
 * to PRESTART to IN_PROGRESS to ENDING. Matches that need no
 * preparation skip PREPARING and appear as PRESTART at once.
 */
public enum MatchPhase {
    /** No match running. */
    LOBBY,
    /** Allocated and preparing: teleports pending. */
    PREPARING,
    /** Created and announced, pre-start window running. */
    PRESTART,
    /** Begun, win conditions live. */
    IN_PROGRESS,
    /** Winner announced, end delay running. */
    ENDING
}
