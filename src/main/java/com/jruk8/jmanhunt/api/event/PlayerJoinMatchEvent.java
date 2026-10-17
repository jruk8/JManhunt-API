package com.jruk8.jmanhunt.api.event;

import com.jruk8.jmanhunt.api.Role;
import java.util.UUID;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Fired when a player joins a match: at start for the
 * initial roster, and on every mid-match join after that.
 */
public class PlayerJoinMatchEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final long matchId;
    private final UUID playerId;
    private final Role role;

    public PlayerJoinMatchEvent(long matchId, UUID playerId, Role role) {
        this.matchId = matchId;
        this.playerId = playerId;
        this.role = role;
    }

    /** The joined match id. */
    public long getMatchId() {
        return matchId;
    }

    /** The joining player's uuid. */
    public UUID getPlayerId() {
        return playerId;
    }

    /** The role they joined with. */
    public Role getRole() {
        return role;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
