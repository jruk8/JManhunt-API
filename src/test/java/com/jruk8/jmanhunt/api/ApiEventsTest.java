package com.jruk8.jmanhunt.api;

import com.jruk8.jmanhunt.api.event.CombatHitEvent;
import com.jruk8.jmanhunt.api.event.GameBeginEvent;
import com.jruk8.jmanhunt.api.event.MatchCancelEvent;
import com.jruk8.jmanhunt.api.event.MatchCreateEvent;
import com.jruk8.jmanhunt.api.event.MatchEndEvent;
import com.jruk8.jmanhunt.api.event.MatchPlayerDeathEvent;
import com.jruk8.jmanhunt.api.event.MatchPlayerKillEvent;
import com.jruk8.jmanhunt.api.event.MatchProgressionEvent;
import com.jruk8.jmanhunt.api.event.MatchRespawnEvent;
import com.jruk8.jmanhunt.api.event.ModifierToggleEvent;
import com.jruk8.jmanhunt.api.event.PlayerJoinMatchEvent;
import com.jruk8.jmanhunt.api.event.PlayerLeaveMatchEvent;
import com.jruk8.jmanhunt.api.event.PlayerMatchPenaltyEvent;
import com.jruk8.jmanhunt.api.event.PlayerRankedPenaltyEvent;
import com.jruk8.jmanhunt.api.event.PlayerRoleChangeEvent;
import com.jruk8.jmanhunt.api.event.TournamentRunEndEvent;
import com.jruk8.jmanhunt.api.model.TournamentRunResult;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/** Event goldens: every event carries its data and handlers. */
class ApiEventsTest {

    @Test
    void lifecycleEventsCarryIds() {
        assertEquals(7L, new GameBeginEvent(7L).getMatchId());
        assertEquals(7L, new MatchCancelEvent(7L).getMatchId());
        assertEquals(3, new MatchCreateEvent(7L, 3, OptionalLong.of(9L)).getLobbyId());
        assertEquals(OptionalLong.of(9L),
                new MatchCreateEvent(7L, 3, OptionalLong.of(9L)).getCellIndex());
        MatchEndEvent end = new MatchEndEvent(7L, Role.HUNTER);
        assertEquals(7L, end.getMatchId());
        assertEquals(Role.HUNTER, end.getWinner());
    }

    @Test
    void playerEventsCarryIdsAndRoles() {
        UUID id = UUID.randomUUID();
        PlayerJoinMatchEvent join = new PlayerJoinMatchEvent(7L, id, Role.RUNNER);
        assertEquals(id, join.getPlayerId());
        assertEquals(Role.RUNNER, join.getRole());
        assertEquals(id, new PlayerLeaveMatchEvent(7L, id).getPlayerId());
        PlayerRoleChangeEvent change =
                new PlayerRoleChangeEvent(7L, id, Role.HUNTER, Role.SPECTATOR);
        assertEquals(Role.HUNTER, change.getOldRole());
        assertEquals(Role.SPECTATOR, change.getNewRole());
    }

    @Test
    void penaltyEventsCarryIdsAndStrikes() {
        UUID id = UUID.randomUUID();
        PlayerMatchPenaltyEvent plain = new PlayerMatchPenaltyEvent(7L, id);
        assertEquals(7L, plain.getMatchId());
        assertEquals(id, plain.getPlayerId());
        PlayerRankedPenaltyEvent ranked =
                new PlayerRankedPenaltyEvent(7L, id, 3, 1_800_000L);
        assertEquals(7L, ranked.getMatchId());
        assertEquals(id, ranked.getPlayerId());
        assertEquals(3, ranked.getStrikes());
        assertEquals(1_800_000L, ranked.getCooldownUntilMillis());
    }

    @Test
    void combatEventsCarrySidesAndFlags() {
        UUID killer = UUID.randomUUID();
        UUID victim = UUID.randomUUID();
        MatchPlayerDeathEvent death =
                new MatchPlayerDeathEvent(7L, victim, Optional.of(killer), true);
        assertEquals(Optional.of(killer), death.getKillerId());
        assertTrue(death.isElimination());
        MatchPlayerKillEvent kill =
                new MatchPlayerKillEvent(7L, killer, victim, false);
        assertEquals(killer, kill.getKillerId());
        assertEquals(victim, kill.getVictimId());
        CombatHitEvent hit = new CombatHitEvent(7L, killer, victim, 4.5);
        assertEquals(4.5, hit.getDamage());
        assertEquals(2, new MatchRespawnEvent(7L, victim, 2).getLivesLeft());
    }

    @Test
    void progressionAndToggleEventsCarryKinds() {
        MatchProgressionEvent progression =
                new MatchProgressionEvent(7L, UUID.randomUUID(),
                        MatchProgressionEvent.Kind.DRAGON);
        assertEquals(MatchProgressionEvent.Kind.DRAGON, progression.getKind());
        ModifierToggleEvent toggle = new ModifierToggleEvent("vip", true);
        assertEquals("vip", toggle.getId());
        assertTrue(toggle.isEnabled());
    }

    @Test
    void handlerListsAreSingletons() {
        assertSame(GameBeginEvent.getHandlerList(),
                new GameBeginEvent(1L).getHandlers());
        assertSame(CombatHitEvent.getHandlerList(), new CombatHitEvent(
                1L, UUID.randomUUID(), UUID.randomUUID(), 1.0).getHandlers());
    }

    @Test
    void tournamentRunEndCarriesFinalNumbers() {
        UUID player = UUID.randomUUID();
        UUID party = UUID.randomUUID();
        TournamentRunResult result = new TournamentRunResult(3, "Tournament 3",
                true, 5, true, false, true, player, "Steve", party,
                TournamentRunResult.Result.LOSE, 3, 3, 2, 1, 0, 1, 0, 2, 3,
                35, -18, 17, false);
        TournamentRunEndEvent event = new TournamentRunEndEvent(result);
        assertSame(result, event.getResult());
        assertEquals(3, event.getResult().tournamentId());
        assertEquals(TournamentRunResult.Result.LOSE, event.getResult().result());
        assertEquals(35, event.getResult().pooledGains());
        assertEquals(-18, event.getResult().lossApplied());
        assertEquals(17, event.getResult().netChange());
        assertSame(TournamentRunEndEvent.getHandlerList(), event.getHandlers());
    }

    @Test
    void tournamentRunEndOmitsEloWhenStandard() {
        TournamentRunResult result = new TournamentRunResult(1, "Tournament 1",
                false, -1, true, false, false, UUID.randomUUID(), "Alex", null,
                TournamentRunResult.Result.WIN, 1, 2, 2, 0, 0, 0, 0, 2, 2,
                null, null, null, false);
        assertNull(new TournamentRunEndEvent(result).getResult().netChange());
    }

    @Test
    void roleSemantics() {
        assertTrue(Role.HUNTER.isParticipant());
        assertTrue(Role.RUNNER.isParticipant());
        assertTrue(!Role.SPECTATOR.isParticipant());
        assertTrue(!Role.NONE.isParticipant());
        assertTrue(!Role.AFK.isParticipant());
    }
}
