package com.jruk8.jmanhunt.api.event;

import java.util.UUID;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Fired for player-on-player damage inside a live match, after
 * the engine records it. Informational only: it never
 * cancels or changes the hit. The engine skips the dispatch
 * entirely while nothing listens, so idle servers pay
 * nothing per hit.
 */
public class CombatHitEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final long matchId;
    private final UUID damagerId;
    private final UUID victimId;
    private final double damage;

    public CombatHitEvent(long matchId, UUID damagerId, UUID victimId,
            double damage) {
        this.matchId = matchId;
        this.damagerId = damagerId;
        this.victimId = victimId;
        this.damage = damage;
    }

    /** The match id. */
    public long getMatchId() {
        return matchId;
    }

    /** The damager's uuid. */
    public UUID getDamagerId() {
        return damagerId;
    }

    /** The victim's uuid. */
    public UUID getVictimId() {
        return victimId;
    }

    /** Final damage in half-hearts, as recorded. */
    public double getDamage() {
        return damage;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
