package com.jruk8.jmanhunt.api.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Fired when the game actually begins: the pre-start window
 * is over and win conditions go live.
 */
public class GameBeginEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final long matchId;

    public GameBeginEvent(long matchId) {
        this.matchId = matchId;
    }

    /** The begun match id. */
    public long getMatchId() {
        return matchId;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
