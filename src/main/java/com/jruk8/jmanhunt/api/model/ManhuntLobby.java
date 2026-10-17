package com.jruk8.jmanhunt.api.model;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * One lobby: its members plus the matches running from it.
 * Unknown lobby ids read as empty optionals from the
 * service, never as lobbies.
 */
public interface ManhuntLobby {

    /** The lobby id. */
    int id();

    /** Assigned member uuids, never null. */
    Set<UUID> members();

    /** Live match ids started from this lobby, oldest first. */
    List<Long> runningMatchIds();
}
