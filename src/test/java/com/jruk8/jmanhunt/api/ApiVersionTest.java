package com.jruk8.jmanhunt.api;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/** Version pins: parsing, ordering, and the compat rule. */
class ApiVersionTest {

    @Test
    void currentIsOneOneZero() {
        assertEquals(new ApiVersion(1, 1, 0), ApiVersion.CURRENT);
        assertEquals("1.1.0", ApiVersion.CURRENT.toString());
    }

    @Test
    void parseRoundTrips() {
        assertEquals(Optional.of(new ApiVersion(2, 11, 3)),
                ApiVersion.parse("2.11.3"));
    }

    @Test
    void parseRejectsMalformed() {
        assertTrue(ApiVersion.parse(null).isEmpty());
        assertTrue(ApiVersion.parse("").isEmpty());
        assertTrue(ApiVersion.parse("1.2").isEmpty());
        assertTrue(ApiVersion.parse("1.2.3.4").isEmpty());
        assertTrue(ApiVersion.parse("v1.2.3").isEmpty());
        assertTrue(ApiVersion.parse("1.x.3").isEmpty());
        assertTrue(ApiVersion.parse("-1.2.3").isEmpty());
        assertTrue(ApiVersion.parse("1.2.3-SNAPSHOT").isEmpty());
    }

    @Test
    void ordersByMajorMinorPatch() {
        assertTrue(new ApiVersion(1, 0, 0).compareTo(new ApiVersion(1, 0, 1)) < 0);
        assertTrue(new ApiVersion(1, 2, 0).compareTo(new ApiVersion(1, 10, 0)) < 0);
        assertTrue(new ApiVersion(2, 0, 0).compareTo(new ApiVersion(1, 99, 99)) > 0);
        assertEquals(0, new ApiVersion(1, 2, 3).compareTo(new ApiVersion(1, 2, 3)));
    }

    @Test
    void compatibleNeedsSameMajorAndEnoughMinor() {
        assertTrue(ApiVersion.compatible(
                new ApiVersion(1, 0, 0), new ApiVersion(1, 0, 0)));
        assertTrue(ApiVersion.compatible(
                new ApiVersion(1, 2, 0), new ApiVersion(1, 5, 9)));
        assertFalse(ApiVersion.compatible(
                new ApiVersion(1, 5, 0), new ApiVersion(1, 2, 9)));
        assertFalse(ApiVersion.compatible(
                new ApiVersion(1, 0, 0), new ApiVersion(2, 0, 0)));
        assertFalse(ApiVersion.compatible(
                new ApiVersion(2, 0, 0), new ApiVersion(1, 9, 9)));
    }

    @Test
    void compatibleRejectsNull() {
        assertFalse(ApiVersion.compatible(null, ApiVersion.CURRENT));
        assertFalse(ApiVersion.compatible(ApiVersion.CURRENT, null));
    }
}
