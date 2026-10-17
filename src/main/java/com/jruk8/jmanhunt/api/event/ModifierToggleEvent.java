package com.jruk8.jmanhunt.api.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Fired after a modifier or preset toggles at global scope,
 * whatever route flipped it (API, CLI, GUI, or reload).
 */
public class ModifierToggleEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final String id;
    private final boolean enabled;

    public ModifierToggleEvent(String id, boolean enabled) {
        this.id = id;
        this.enabled = enabled;
    }

    /** The toggled modifier or preset id. */
    public String getId() {
        return id;
    }

    /** The state after the toggle. */
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
