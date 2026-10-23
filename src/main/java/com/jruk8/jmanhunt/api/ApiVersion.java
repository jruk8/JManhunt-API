package com.jruk8.jmanhunt.api;

import java.util.Optional;

/**
 * The running API version, compared semantically: the major must
 * match and the engine minor must be at least the required minor.
 * Addons declare the minimum they need in
 * {@link JManhuntApi#registerAddon}.
 *
 * @param major breaking changes; must match exactly
 * @param minor additive changes; the engine must meet or exceed it
 * @param patch fixes only; never affects compatibility
 */
public record ApiVersion(int major, int minor, int patch)
        implements Comparable<ApiVersion> {

    /** The version this artifact was released with. */
    public static final ApiVersion CURRENT = new ApiVersion(1, 3, 0);

    /**
     * Parses {@code "major.minor.patch"}; malformed input yields empty.
     */
    public static Optional<ApiVersion> parse(String raw) {
        if (raw == null) {
            return Optional.empty();
        }
        String[] parts = raw.split("\\.", -1);
        if (parts.length != 3) {
            return Optional.empty();
        }
        try {
            int parsedMajor = Integer.parseInt(parts[0]);
            int parsedMinor = Integer.parseInt(parts[1]);
            int parsedPatch = Integer.parseInt(parts[2]);
            if (parsedMajor < 0 || parsedMinor < 0 || parsedPatch < 0) {
                return Optional.empty();
            }
            return Optional.of(
                    new ApiVersion(parsedMajor, parsedMinor, parsedPatch));
        } catch (NumberFormatException malformed) {
            return java.util.Optional.empty();
        }
    }

    /**
     * True when an engine reporting {@code actual} satisfies an
     * addon requiring {@code required}: same major, minor at
     * least the required one. Null on either side is never
     * compatible.
     */
    public static boolean compatible(ApiVersion required, ApiVersion actual) {
        if (required == null || actual == null) {
            return false;
        }
        return actual.major == required.major && actual.compareTo(required) >= 0;
    }

    @Override
    public int compareTo(ApiVersion other) {
        int byMajor = Integer.compare(major, other.major);
        if (byMajor != 0) {
            return byMajor;
        }
        int byMinor = Integer.compare(minor, other.minor);
        if (byMinor != 0) {
            return byMinor;
        }
        return Integer.compare(patch, other.patch);
    }

    @Override
    public String toString() {
        return major + "." + minor + "." + patch;
    }
}
