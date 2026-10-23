package com.jruk8.jmanhunt.api.event;

import com.jruk8.jmanhunt.api.model.TournamentRunResult;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Fired once per player when that player's tournament run ends, after
 * elo settlement, so the carried numbers are final. Champions fire
 * with result WIN; eliminations, leaves, and disconnects fire LOSE.
 */
public class TournamentRunEndEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final TournamentRunResult result;

    public TournamentRunEndEvent(TournamentRunResult result) {
        if (result == null) {
            throw new IllegalArgumentException("result must not be null");
        }
        this.result = result;
    }

    /** The finished run with final cumulative numbers. */
    public TournamentRunResult getResult() {
        return result;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
