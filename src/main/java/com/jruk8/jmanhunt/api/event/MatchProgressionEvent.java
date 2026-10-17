package com.jruk8.jmanhunt.api.event;

import java.util.UUID;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Fired when a participant reaches a tracked progression
 * milestone: first Nether entry, first End entry, or the
 * final hit on the Ender Dragon.
 */
public class MatchProgressionEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    /** Tracked progression milestones. */
    public enum Kind {
        /** First entry into the Nether. */
        NETHER,
        /** First entry into the End. */
        END,
        /** Final hit on the Ender Dragon. */
        DRAGON
    }

    private final long matchId;
    private final UUID playerId;
    private final Kind kind;

    public MatchProgressionEvent(long matchId, UUID playerId, Kind kind) {
        this.matchId = matchId;
        this.playerId = playerId;
        this.kind = kind;
    }

    /** The match id. */
    public long getMatchId() {
        return matchId;
    }

    /** The progressing player's uuid. */
    public UUID getPlayerId() {
        return playerId;
    }

    /** The reached milestone. */
    public Kind getKind() {
        return kind;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
