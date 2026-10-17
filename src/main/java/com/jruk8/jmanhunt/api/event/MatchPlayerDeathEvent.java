package com.jruk8.jmanhunt.api.event;

import java.util.Optional;
import java.util.UUID;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Fired when a match participant dies, whatever the cause.
 * Fires alongside MatchPlayerKillEvent when a killer took
 * the final hit: listen to kills for killer credit, to
 * deaths for victim handling.
 */
public class MatchPlayerDeathEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final long matchId;
    private final UUID victimId;
    private final Optional<UUID> killerId;
    private final boolean elimination;

    public MatchPlayerDeathEvent(long matchId, UUID victimId,
            Optional<UUID> killerId, boolean elimination) {
        this.matchId = matchId;
        this.victimId = victimId;
        this.killerId = killerId;
        this.elimination = elimination;
    }

    /** The match id. */
    public long getMatchId() {
        return matchId;
    }

    /** The victim's uuid. */
    public UUID getVictimId() {
        return victimId;
    }

    /** The killer's uuid, empty for deaths with no killer. */
    public Optional<UUID> getKillerId() {
        return killerId;
    }

    /** True when the death eliminated the victim from the match. */
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
