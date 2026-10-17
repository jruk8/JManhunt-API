package com.jruk8.jmanhunt.api.service;

import com.jruk8.jmanhunt.api.model.MatchScore;
import com.jruk8.jmanhunt.api.model.MvpInfo;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;

/**
 * Live match scores: reads plus bonus and penalty
 * adjustments. Adjustments fold into the same ledger the
 * engine scores into, so MVP picks and end reports see
 * them; career still folds at match end as usual.
 */
public interface ScoreService {

    /** One participant's live score, empty for unknown rows. */
    OptionalInt scoreOf(long matchId, UUID playerId);

    /** Every scored row of one match, empty for unknown ids. */
    List<MatchScore> scores(long matchId);

    /** The live score leader, empty when nobody leads. */
    Optional<MvpInfo> liveMvp(long matchId);

    /**
     * Adds a bonus (positive) or penalty (negative) to one
     * participant's live score. False when the match has no
     * live unfinalized ledger for the player.
     */
    boolean adjustScore(long matchId, UUID playerId, int delta);
}
