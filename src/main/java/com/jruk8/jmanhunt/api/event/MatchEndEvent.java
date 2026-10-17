package com.jruk8.jmanhunt.api.event;

import com.jruk8.jmanhunt.api.Role;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Fired when a winner is announced. The end delay still runs,
 * so the match reads live with phase ENDING.
 */
public class MatchEndEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final long matchId;
    private final Role winner;

    public MatchEndEvent(long matchId, Role winner) {
        this.matchId = matchId;
        this.winner = winner;
    }

    /** The ended match id. */
    public long getMatchId() {
        return matchId;
    }

    /** The winning role, never NONE. */
    public Role getWinner() {
        return winner;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
