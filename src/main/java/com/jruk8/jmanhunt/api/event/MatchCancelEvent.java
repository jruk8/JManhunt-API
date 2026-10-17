package com.jruk8.jmanhunt.api.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Fired when a match is cancelled with no winner: there is
 * no winning role, so this never carries one.
 */
public class MatchCancelEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final long matchId;

    public MatchCancelEvent(long matchId) {
        this.matchId = matchId;
    }

    /** The cancelled match id. */
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
