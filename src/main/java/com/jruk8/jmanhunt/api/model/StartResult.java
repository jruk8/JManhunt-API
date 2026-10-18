package com.jruk8.jmanhunt.api.model;

/**
 * Why a match start request succeeded or failed. Checks run
 * before anything is allocated, so any failure leaves no
 * trace.
 */
public enum StartResult {
    /** The match started, or entered preparing with teleports pending. */
    OK,
    /** No lobby answers that id. */
    UNKNOWN_LOBBY,
    /** The lobby already runs a match. */
    ALREADY_RUNNING,
    /** The edition cap blocks another concurrent match. */
    CONCURRENCY_CAPPED,
    /** The start refused for any other reason. */
    REFUSED
}
