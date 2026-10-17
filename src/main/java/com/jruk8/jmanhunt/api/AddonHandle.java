package com.jruk8.jmanhunt.api;

import org.bukkit.plugin.Plugin;

/**
 * One addon's registration with JManhunt, created by
 * {@link JManhuntApi#registerAddon}. The engine releases the
 * handle automatically when the owning plugin disables, so
 * addons only call {@link #unregister} when dropping the
 * integration early. Unregistering twice is a no-op.
 */
public interface AddonHandle extends AutoCloseable {

    /** The owning plugin. */
    Plugin plugin();

    /** The minimum API version the addon declared. */
    ApiVersion requiredVersion();

    /** Releases the registration; idempotent. */
    void unregister();

    @Override
    default void close() {
        unregister();
    }
}
