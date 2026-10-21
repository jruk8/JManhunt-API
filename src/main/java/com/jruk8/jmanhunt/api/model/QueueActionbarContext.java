package com.jruk8.jmanhunt.api.model;

import java.util.UUID;

/**
 * One queue actionbar render for one viewer: what the engine
 * knows about their attempt when custom points render. The
 * playlist id is null while still searching; the seconds left
 * read -1 unless the match is starting. Renderers run on the
 * main thread every actionbar tick, so keep them cheap.
 *
 * @param playerId the viewing queued player, never null
 * @param elapsedSeconds seconds since the attempt started
 * @param playlistId the attached playlist id, null while searching
 * @param memberCount queued members of the attached instance, else 0
 * @param total members filling the attached instance, else 0
 * @param starting true while the attached instance counts down
 * @param secondsLeft countdown seconds left, -1 unless starting
 */
public record QueueActionbarContext(UUID playerId, long elapsedSeconds, String playlistId,
        int memberCount, int total, boolean starting, long secondsLeft) {
}
