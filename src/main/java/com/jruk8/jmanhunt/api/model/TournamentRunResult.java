package com.jruk8.jmanhunt.api.model;

import java.util.UUID;

/**
 * One player's complete tournament run, built after elo settlement so
 * the numbers are final. The engine renders its end-of-tournament
 * summary from this same object, so companions and the summary can
 * never disagree.
 *
 * @param tournamentId the stable tournament id (display: Tournament N)
 * @param tournamentName display name, for example Tournament 3
 * @param bestOfEnabled true when a best-of series decides slots
 * @param bestOf the series length, for example 5; -1 when disabled
 * @param brackets true for single-elimination brackets
 * @param roundRobin true for the points-table format
 * @param ranked true for ranked ladders (elo fields present)
 * @param playerId the participant's uuid
 * @param playerName last known display name
 * @param partyId the queue party id, or null when unknown
 * @param result WIN for the champion, LOSE for every other run end
 * @param placement final placement, 1 for the champion
 * @param matchesPlayed maps played across the run, byes excluded
 * @param matchesWon maps won across the run
 * @param matchesLost maps lost across the run, byes included
 * @param byes byes received (each also counted in matchesLost)
 * @param seriesWon series won (best-of formats, else 0)
 * @param seriesLost series lost (best-of formats, else 0)
 * @param score tournament score at run end (series wins, points, wins)
 * @param maxScore theoretical maximum for the format
 * @param pooledGains cached win deltas banked, null when non-ranked
 * @param lossApplied loss delta applied at settlement, null when non-ranked
 * @param netChange net elo applied, null when non-ranked
 * @param leftEarly true when the run ended by leave or disconnect
 */
public record TournamentRunResult(int tournamentId, String tournamentName,
        boolean bestOfEnabled, int bestOf, boolean brackets, boolean roundRobin,
        boolean ranked, UUID playerId, String playerName, UUID partyId,
        Result result, int placement, int matchesPlayed, int matchesWon,
        int matchesLost, int byes, int seriesWon, int seriesLost, int score,
        int maxScore, Integer pooledGains, Integer lossApplied, Integer netChange,
        boolean leftEarly) {

    /** Run outcome: champion or any other end. */
    public enum Result {
        WIN,
        LOSE
    }

    public TournamentRunResult {
        if (tournamentName == null || tournamentName.isBlank()) {
            throw new IllegalArgumentException("tournamentName must not be blank");
        }
        if (playerId == null) {
            throw new IllegalArgumentException("playerId must not be null");
        }
        if (playerName == null || playerName.isBlank()) {
            throw new IllegalArgumentException("playerName must not be blank");
        }
        if (result == null) {
            throw new IllegalArgumentException("result must not be null");
        }
        if (placement < 1) {
            throw new IllegalArgumentException("placement must be at least 1");
        }
        if (matchesPlayed < 0 || matchesWon < 0 || matchesLost < 0 || byes < 0
                || seriesWon < 0 || seriesLost < 0 || score < 0 || maxScore < 0) {
            throw new IllegalArgumentException("run counters must not be negative");
        }
    }
}
