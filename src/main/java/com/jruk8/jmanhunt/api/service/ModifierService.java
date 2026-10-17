package com.jruk8.jmanhunt.api.service;

import com.jruk8.jmanhunt.api.model.ModifierInfo;
import java.util.List;
import java.util.Optional;

/**
 * Modifier and preset toggles at global scope. This is the
 * secondary route on purpose: authoring stays in JMHScript
 * and YAML, per-match and per-lobby overrides stay in the
 * CLI and GUI. Toggling here behaves like the operations
 * panel: edition caps apply.
 */
public interface ModifierService {

    /** Every known modifier and preset. */
    List<ModifierInfo> modifiers();

    /** One modifier or preset, empty for unknown ids. */
    Optional<ModifierInfo> modifier(String id);

    /**
     * Enables or disables one modifier or preset globally
     * (a preset flips every transitive member). False for
     * unknown ids; edition caps apply at match sync as in
     * game. Known ids always return true, even when the
     * flag already held the value.
     */
    boolean setEnabled(String id, boolean enabled);
}
