package com.jruk8.jmanhunt.api.service;

import com.jruk8.jmanhunt.api.model.ManhuntMatch;
import com.jruk8.jmanhunt.api.model.StartResult;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Live matches: reads plus lifecycle actions. Actions run the
 * same paths as the built-in commands and GUI, so edition
 * caps and preconditions apply exactly as in game.
 */
public interface MatchService {

    /** One live match, empty for unknown or ended ids. */
    Optional<ManhuntMatch> match(long matchId);

    /** All live matches, oldest first. */
    List<ManhuntMatch> liveMatches();

    /** The player's live match, empty when in none. */
    Optional<ManhuntMatch> matchOf(UUID playerId);

    /** Live matches started from one lobby, oldest first. */
    List<ManhuntMatch> lobbyMatches(int lobbyId);

    /**
     * Starts a match in one lobby, running the same checks as
     * the start command. Nothing is allocated unless the
     * result is OK.
     */
    StartResult start(int lobbyId);

    /**
     * Ends the pre-start window early and begins the game.
     * False for unknown matches and matches already begun.
     */
    boolean begin(long matchId);

    /**
     * Cancels a live match with no winner (the same path as
     * the end command). False for unknown matches.
     */
    boolean cancel(long matchId);
}
