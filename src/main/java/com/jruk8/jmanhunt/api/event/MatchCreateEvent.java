package com.jruk8.jmanhunt.api.event;

import java.util.OptionalLong;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Fired when a match is created and announced: the pre-start
 * window begins. Informational: the match is already
 * allocated and cannot be vetoed.
 */
public class MatchCreateEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final long matchId;
    private final int lobbyId;
    private final OptionalLong cellIndex;

    public MatchCreateEvent(long matchId, int lobbyId, OptionalLong cellIndex) {
        this.matchId = matchId;
        this.lobbyId = lobbyId;
        this.cellIndex = cellIndex;
    }

    /** The created match id. */
    public long getMatchId() {
        return matchId;
    }

    /** The lobby id the match started from. */
    public int getLobbyId() {
        return lobbyId;
    }

    /** The world-engine cell index, when cell allocation ran. */
    public OptionalLong getCellIndex() {
        return cellIndex;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
