package com.jruk8.jmanhunt.api.service;

import com.jruk8.jmanhunt.api.Role;
import com.jruk8.jmanhunt.api.model.ManhuntPlayer;
import java.util.Optional;
import java.util.UUID;

/**
 * Per-player state reads. Roles and participation follow the
 * engine's live assignment; nothing here mutates it.
 */
public interface PlayerService {

    /** One player's state, empty when JManhunt never saw them. */
    Optional<ManhuntPlayer> player(UUID playerId);

    /** Current role, NONE when match-less; never null. */
    Role roleOf(UUID playerId);

    /** True while an active hunter or runner. */
    boolean isParticipant(UUID playerId);
}
