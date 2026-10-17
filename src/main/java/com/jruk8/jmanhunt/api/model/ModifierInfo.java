package com.jruk8.jmanhunt.api.model;

/**
 * One modifier or preset, as listed to companions.
 *
 * @param id the stable modifier or preset id
 * @param displayName the display name
 * @param author the author string
 * @param enabled whether it is currently enabled
 * @param preset true for presets, false for modifiers
 */
public record ModifierInfo(String id, String displayName, String author,
        boolean enabled, boolean preset) {
}
