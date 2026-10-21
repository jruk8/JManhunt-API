package com.jruk8.jmanhunt.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/** Priority pins: engine order plus the companion bounds. */
class QueueActionbarPrioritiesTest {

    @Test
    void enginePointsOrderRootFirst() {
        assertEquals(QueueActionbarPriorities.MIN_PRIORITY,
                QueueActionbarPriorities.ROOT);
        assertTrue(QueueActionbarPriorities.ROOT
                < QueueActionbarPriorities.MATCH);
        assertTrue(QueueActionbarPriorities.MATCH
                < QueueActionbarPriorities.EXIT_HINT);
        assertTrue(QueueActionbarPriorities.EXIT_HINT
                <= QueueActionbarPriorities.MAX_PRIORITY);
    }

    @Test
    void boundsSpanTenThousand() {
        assertEquals(0, QueueActionbarPriorities.MIN_PRIORITY);
        assertEquals(10_000, QueueActionbarPriorities.MAX_PRIORITY);
    }
}
