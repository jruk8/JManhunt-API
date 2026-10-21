package com.jruk8.jmanhunt.api.service;

import com.jruk8.jmanhunt.api.model.QueueActionbarContext;
import java.util.OptionalLong;
import java.util.UUID;
import java.util.function.Function;
import net.kyori.adventure.text.Component;

/**
 * Quick Match queues for companions: custom actionbar points
 * plus ranked penalty reads. The surface stays open in both
 * editions; without the paid queue engine every read degrades
 * (no points render, strikes read 0, expiry reads empty).
 */
public interface QueueService {

    /**
     * Adds one actionbar point at the priority, replacing any
     * item under the same id. Ids should carry the plugin name
     * (for example {@code myplugin:line}) so two addons never
     * share one. Priorities clamp into
     * {@link QueueActionbarPriorities#MIN_PRIORITY} to
     * {@link QueueActionbarPriorities#MAX_PRIORITY}; see
     * {@link QueueActionbarPriorities} for the engine points
     * and the collision rule. A null render hides the point
     * for that tick; a throwing renderer logs and hides.
     *
     * @param id the item id, never null
     * @param priority the render slot, lower renders first
     * @param renderer the per-viewer point, never null
     */
    void addQueueActionbarItem(String id, int priority,
            Function<QueueActionbarContext, Component> renderer);

    /**
     * Removes one actionbar point. True when one was registered
     * under the id.
     */
    boolean removeQueueActionbarItem(String id);

    /**
     * Ranked penalty strikes inside the remember window, or 0
     * when the player holds none. Expired windows read 0.
     */
    int rankedPenaltyStrikes(UUID playerId);

    /**
     * Ranked queue cooldown expiry in epoch millis, or empty
     * when no cooldown runs. Expired cooldowns read empty.
     */
    OptionalLong rankedPenaltyExpiryMillis(UUID playerId);
}
