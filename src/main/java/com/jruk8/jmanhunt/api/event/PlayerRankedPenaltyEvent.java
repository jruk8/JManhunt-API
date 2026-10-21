package com.jruk8.jmanhunt.api.event;

import java.util.UUID;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Fired when a ranked disconnect counts as a penalty: the
 * participant left a live ranked match by disconnecting, inside
 * or restarting the remember window. Standard disconnects fire
 * {@link PlayerMatchPenaltyEvent} instead. The event only fires
 * where ranked matches run, so the data is paid-gated while the
 * class itself stays open like every API type.
 */
public class PlayerRankedPenaltyEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final long matchId;
    private final UUID playerId;
    private final int strikes;
    private final long cooldownUntilMillis;

    /**
     * @param matchId the penalized match id
     * @param playerId the penalized player's uuid
     * @param strikes the strike count after this penalty, from 1
     * @param cooldownUntilMillis ranked queue cooldown expiry in
     *     epoch millis
     */
    public PlayerRankedPenaltyEvent(long matchId, UUID playerId, int strikes,
            long cooldownUntilMillis) {
        this.matchId = matchId;
        this.playerId = playerId;
        this.strikes = strikes;
        this.cooldownUntilMillis = cooldownUntilMillis;
    }

    /** The penalized match id. */
    public long getMatchId() {
        return matchId;
    }

    /** The penalized player's uuid. */
    public UUID getPlayerId() {
        return playerId;
    }

    /** The strike count after this penalty, from 1. */
    public int getStrikes() {
        return strikes;
    }

    /** Ranked queue cooldown expiry in epoch millis. */
    public long getCooldownUntilMillis() {
        return cooldownUntilMillis;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
