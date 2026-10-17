package com.jruk8.jmanhunt.api.model;

import com.jruk8.jmanhunt.api.MatchPhase;
import com.jruk8.jmanhunt.api.Role;
import java.util.List;
import java.util.Optional;

/**
 * One live match. Snapshots are immutable: each call re-reads
 * the live match, so holding a match never sees later state.
 * Unknown match ids read as empty optionals from the service,
 * never as matches.
 */
public interface ManhuntMatch {

    /** The incrementing persistent match id. */
    long matchId();

    /** The lobby id the match was started from. */
    int lobbyId();

    /** Where the match stands; never LOBBY for a live match. */
    MatchPhase phase();

    /** True once the pre-start window has passed. */
    boolean begun();

    /** True while the end delay runs after a winner is decided. */
    boolean ending();

    /** Epoch millis when the match was created. */
    long startedAtMillis();

    /** Millis since creation; reads the clock on every call. */
    long elapsedMillis();

    /** Active participants (hunters and runners), never null. */
    List<ManhuntPlayer> players();

    /** Active hunters, never null. */
    List<ManhuntPlayer> hunters();

    /** Active runners, never null. */
    List<ManhuntPlayer> runners();

    /** Assigned spectators, never null. */
    List<ManhuntPlayer> spectators();

    /** The winner once decided (ENDING phase), empty before that. */
    Optional<Role> winner();
}
