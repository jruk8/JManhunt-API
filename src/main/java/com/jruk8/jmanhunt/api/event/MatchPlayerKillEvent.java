package com.jruk8.jmanhunt.api.event;

import java.util.UUID;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Fired when a match participant lands a killing hit on
 * another participant. Killer-side companion to
 * MatchPlayerDeathEvent.
 */
public class MatchPlayerKillEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final long matchId;
    private final UUID killerId;
    private final UUID victimId;
    private final boolean elimination;

    public MatchPlayerKillEvent(long matchId, UUID killerId, UUID victimId,
            boolean elimination) {
        this.matchId = matchId;
        this.killerId = killerId;
        this.victimId = victimId;
        this.elimination = elimination;
    }

    /** The match id. */
    public long getMatchId() {
        return matchId;
    }

    /** The killer's uuid. */
    public UUID getKillerId() {
        return killerId;
    }

    /** The victim's uuid. */
    public UUID getVictimId() {
        return victimId;
    }

    /** True when the kill eliminated the victim from the match. */
    public boolean isElimination() {
        return elimination;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
