package com.jruk8.jmanhunt.api.model;

import java.util.UUID;

/**
 * The live score leader of a match. Mid-match this is the
 * current leader; once finalized it is the official MVP.
 *
 * @param playerId the leader's uuid
 * @param name the leader's last known name
 * @param score the leading score
 */
public record MvpInfo(UUID playerId, String name, int score) {
}
