package com.jruk8.jmanhunt.api.event;

import java.util.UUID;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Fired when a player leaves a live match: voluntary leave,
 * disconnect, or removal. Teardown after the end delay does
 * not fire this per player.
 */
public class PlayerLeaveMatchEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final long matchId;
    private final UUID playerId;

    public PlayerLeaveMatchEvent(long matchId, UUID playerId) {
        this.matchId = matchId;
        this.playerId = playerId;
    }

    /** The left match id. */
    public long getMatchId() {
        return matchId;
    }

    /** The leaving player's uuid. */
    public UUID getPlayerId() {
        return playerId;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
