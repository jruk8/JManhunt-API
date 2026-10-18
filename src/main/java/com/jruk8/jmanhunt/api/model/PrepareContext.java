package com.jruk8.jmanhunt.api.model;

/**
 * Where a preparing match comes from: the allocated match id
 * and its origin lobby. Handed to prepare hooks; the instance
 * is registered but players are not teleported yet.
 *
 * @param matchId the allocated match id
 * @param lobbyId the origin lobby id
 */
public record PrepareContext(long matchId, int lobbyId) {
}
