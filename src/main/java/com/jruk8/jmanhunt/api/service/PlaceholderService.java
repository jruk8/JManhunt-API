package com.jruk8.jmanhunt.api.service;

import java.util.Optional;
import java.util.Set;
import org.bukkit.OfflinePlayer;

/**
 * Programmatic `%jmanhunt_*%` resolution for companions that
 * embed JManhunt values without depending on PlaceholderAPI.
 * Keys and formats match the placeholder docs exactly,
 * including per-key enablement and paid gating; the
 * JMHScript tag engine stays closed and is not reachable
 * from here.
 */
public interface PlaceholderService {

    /**
     * Resolves one key without the `%jmanhunt_` prefix or
     * `%` markers (for example `career_kills`). The player
     * may be null for player-less keys; viewer keys with a
     * null player resolve empty. Empty when the key is
     * unknown, disabled, or unavailable.
     */
    Optional<String> resolve(OfflinePlayer player, String key);

    /** Every resolvable key, in config order. */
    Set<String> keys();
}
