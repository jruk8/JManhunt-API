package com.jruk8.jmanhunt.api.event;

import java.util.UUID;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Fired when a match disconnect counts as a penalty: the
 * participant left a live match by disconnecting. Rudimentary
 * on purpose (no strikes are stored for standard matches) and
 * fired in both editions. Ranked disconnects fire
 * {@link PlayerRankedPenaltyEvent} instead, with the strike
 * details.
 */
public class PlayerMatchPenaltyEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final long matchId;
    private final UUID playerId;

    public PlayerMatchPenaltyEvent(long matchId, UUID playerId) {
        this.matchId = matchId;
        this.playerId = playerId;
    }

    /** The penalized match id. */
    public long getMatchId() {
        return matchId;
    }

    /** The penalized player's uuid. */
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
