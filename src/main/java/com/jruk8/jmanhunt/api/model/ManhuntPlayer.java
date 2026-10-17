package com.jruk8.jmanhunt.api.model;

import com.jruk8.jmanhunt.api.Role;
import java.util.OptionalLong;
import java.util.UUID;

/**
 * One player's JManhunt state. Snapshots are immutable and
 * re-read per call. Score, kills, and deaths cover the
 * player's live match and read 0 outside one.
 */
public interface ManhuntPlayer {

    /** The player's uuid. */
    UUID uuid();

    /** Last known name, never null. */
    String name();

    /** Current role, NONE when match-less; never null. */
    Role role();

    /** Live match id, empty when in no live match. */
    OptionalLong matchId();

    /** False once eliminated while the match runs. */
    boolean alive();

    /** Lives remaining in the live match, -1 for unlimited, 0 outside one. */
    int livesLeft();

    /** Live match score, at minimum 0, 0 outside a match. */
    int score();

    /** Kills this match, 0 outside a match. */
    int kills();

    /** Deaths this match, 0 outside a match. */
    int deaths();
}
