package com.jruk8.jmanhunt.api;

import com.jruk8.jmanhunt.api.service.CompassService;
import com.jruk8.jmanhunt.api.service.LobbyService;
import com.jruk8.jmanhunt.api.service.MatchService;
import com.jruk8.jmanhunt.api.service.ModifierService;
import com.jruk8.jmanhunt.api.service.PlaceholderService;
import com.jruk8.jmanhunt.api.service.PlayerService;
import com.jruk8.jmanhunt.api.service.QueueService;
import com.jruk8.jmanhunt.api.service.ScoreService;
import java.util.Optional;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 * Public API for the JManhunt plugin, obtained from the
 * server's {@code ServicesManager} after JManhunt enables:
 *
 * <pre>{@code
 * RegisteredServiceProvider<JManhuntApi> provider = Bukkit.getServicesManager()
 *         .getRegistration(JManhuntApi.class);
 * JManhuntApi api = provider == null ? null : provider.getProvider();
 * }</pre>
 *
 * <p>Several matches can run concurrently, one per lobby.
 * Unknown ids read as empty: empty optionals, empty
 * collections, never null and never thrown.</p>
 *
 * <p>Thread rules: every call must run on the server main
 * thread unless its Javadoc says otherwise. Events fire
 * synchronously on the main thread.</p>
 *
 * <p>Stability: additive changes bump the minor version,
 * breaking changes bump the major. See {@link ApiVersion}
 * and {@link #registerAddon}.</p>
 */
public interface JManhuntApi {

    /**
     * Looks up the API without touching Bukkit statics twice.
     * Empty when JManhunt is absent or not yet enabled.
     */
    static Optional<JManhuntApi> lookup() {
        RegisteredServiceProvider<JManhuntApi> provider = Bukkit
                .getServicesManager().getRegistration(JManhuntApi.class);
        return provider == null
                ? Optional.empty()
                : Optional.of(provider.getProvider());
    }

    /** The running engine's API version. */
    ApiVersion version();

    /** Live matches plus lifecycle actions. */
    MatchService matches();

    /** Lobby membership reads. */
    LobbyService lobbies();

    /** Per-player state reads. */
    PlayerService players();

    /** Tracking-compass reads. */
    CompassService compass();

    /** Live scores plus adjustments. */
    ScoreService scores();

    /** Programmatic placeholder resolution. */
    PlaceholderService placeholders();

    /** Global modifier and preset toggles. */
    ModifierService modifiers();

    /** Quick Match queues: actionbar points plus penalty reads. */
    QueueService queues();

    /**
     * Registers one addon, checking API compatibility up
     * front. The engine releases the handle automatically
     * when the plugin disables.
     *
     * @param plugin the owning plugin, never null
     * @param minVersion the minimum API version the addon
     *     needs, never null
     * @return the registration handle, never null
     * @throws IllegalStateException when the running API
     *     version does not satisfy the minimum
     */
    AddonHandle registerAddon(Plugin plugin, ApiVersion minVersion);
}
