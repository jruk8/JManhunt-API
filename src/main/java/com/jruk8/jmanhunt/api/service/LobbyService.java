package com.jruk8.jmanhunt.api.service;

import com.jruk8.jmanhunt.api.model.ManhuntLobby;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Lobby membership reads. Lobby assignment stays with the
 * engine: companions observe, they never move players.
 */
public interface LobbyService {

    /** Ids of all known lobbies. */
    Set<Integer> lobbyIds();

    /** One lobby, empty for unknown ids. */
    Optional<ManhuntLobby> lobby(int lobbyId);

    /** Assigned members of one lobby, empty for unknown ids. */
    Set<UUID> members(int lobbyId);

    /** The player's lobby, empty when lobby-less. */
    Optional<ManhuntLobby> lobbyOf(UUID playerId);
}
