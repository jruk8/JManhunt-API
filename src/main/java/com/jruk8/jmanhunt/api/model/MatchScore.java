package com.jruk8.jmanhunt.api.model;

import java.util.UUID;

/**
 * One participant's live score line, for scoreboard-style reads.
 *
 * @param playerId the participant's uuid
 * @param score the live total, at minimum 0
 * @param kills kills this match
 */
public record MatchScore(UUID playerId, int score, int kills) {
}
