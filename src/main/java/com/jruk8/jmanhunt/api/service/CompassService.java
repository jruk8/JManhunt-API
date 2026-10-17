package com.jruk8.jmanhunt.api.service;

import com.jruk8.jmanhunt.api.model.CompassTarget;
import java.util.Optional;
import java.util.UUID;

/**
 * Tracking-compass reads: what each hunter currently tracks.
 * Accuracy rules apply exactly as in game, so fuzzy reads
 * stay fuzzy here too.
 */
public interface CompassService {

    /** What one hunter tracks, empty when tracking nothing. */
    Optional<CompassTarget> targetOf(UUID hunterId);
}
