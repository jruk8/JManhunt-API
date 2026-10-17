package com.jruk8.jmanhunt.api.event;

import java.util.UUID;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Fired when an eliminated-or-dead participant respawns back
 * into the match with lives left.
 */
public class MatchRespawnEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final long matchId;
    private final UUID playerId;
    private final int livesLeft;

    public MatchRespawnEvent(long matchId, UUID playerId, int livesLeft) {
        this.matchId = matchId;
        this.playerId = playerId;
        this.livesLeft = livesLeft;
    }

    /** The match id. */
    public long getMatchId() {
        return matchId;
    }

    /** The respawned player's uuid. */
    public UUID getPlayerId() {
        return playerId;
    }

    /** Lives remaining after the respawn. */
    public int getLivesLeft() {
        return livesLeft;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
