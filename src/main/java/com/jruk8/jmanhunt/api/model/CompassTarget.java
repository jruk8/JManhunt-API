package com.jruk8.jmanhunt.api.model;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.UUID;
import org.bukkit.Location;

/**
 * What one hunter's compass currently points at. Accuracy
 * rules apply: fuzzy or locked compasses may report a
 * location without a player, or nothing at all.
 *
 * @param hunter the tracking hunter's uuid
 * @param kind what the compass points at
 * @param targetId the tracked player's uuid, if known
 * @param targetName the tracked player's name, if known
 * @param location where the compass points, if known
 * @param distanceMeters straight-line distance, if known
 */
public record CompassTarget(UUID hunter, TargetKind kind,
        Optional<UUID> targetId, Optional<String> targetName,
        Optional<Location> location, OptionalDouble distanceMeters) {

    /** What a compass points at. */
    public enum TargetKind {
        /** A tracked player. */
        PLAYER,
        /** A location with no tracked player. */
        LOCATION,
        /** Nothing: idle, fuzzy, or untracked. */
        NONE
    }
}
