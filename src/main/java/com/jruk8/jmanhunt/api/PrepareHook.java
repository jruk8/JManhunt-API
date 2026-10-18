package com.jruk8.jmanhunt.api;

import com.jruk8.jmanhunt.api.model.PrepareContext;
import java.util.concurrent.CompletionStage;

/**
 * One prepare hook: extra work a companion runs while a match
 * sits in {@link MatchPhase#PREPARING}. Invoked on the main
 * thread; the returned stage may complete on any thread, and the
 * match proceeds once every hook's stage completes. A null
 * stage, a thrown exception, or a failure completes that hook
 * immediately with a console warning, and the preparing
 * watchdog still bounds the whole phase, so hooks must finish
 * promptly.
 */
@FunctionalInterface
public interface PrepareHook {

    /**
     * Runs the hook for one preparing match.
     *
     * @param context the preparing match and lobby
     * @return a stage completing when the hook is done
     */
    CompletionStage<Void> prepare(PrepareContext context);
}
