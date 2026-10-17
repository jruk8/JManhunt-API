package com.jruk8.jmanhunt.api.event;

import com.jruk8.jmanhunt.api.Role;
import java.util.UUID;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Fired after a player's match role changes mid-match. The
 * initial assignment fires PlayerJoinMatchEvent instead.
 */
public class PlayerRoleChangeEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final long matchId;
    private final UUID playerId;
    private final Role oldRole;
    private final Role newRole;

    public PlayerRoleChangeEvent(long matchId, UUID playerId, Role oldRole,
            Role newRole) {
        this.matchId = matchId;
        this.playerId = playerId;
        this.oldRole = oldRole;
        this.newRole = newRole;
    }

    /** The match id. */
    public long getMatchId() {
        return matchId;
    }

    /** The player's uuid. */
    public UUID getPlayerId() {
        return playerId;
    }

    /** The role before the change. */
    public Role getOldRole() {
        return oldRole;
    }

    /** The role after the change. */
    public Role getNewRole() {
        return newRole;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
