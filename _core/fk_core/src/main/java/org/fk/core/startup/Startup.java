package org.fk.core.startup;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.fk.core.dto.DTOValidityScanner;
import org.jboss.logging.Logger;

/**
 * Startup
 *
 * Central place for core startup-handling,
 * which is common for all our services/modules.
 */
@Singleton
public class Startup {

    public static final Logger LOGGER = Logger.getLogger(Startup.class);

    @Inject
    DTOValidityScanner dtoValidityScanner;

    public void handleCoreStartup(@Observes StartupEvent startupEvent) {
        // ---------------------------
        // System-Properties Overrides
        // ---------------------------
        // all our services, should always use UTC timezone.
        // We never want to use the timezone of the operating system / the JVM defaults.
        LOGGER.debug("setting user.timezone to UTC");
        System.setProperty("user.timezone", "UTC");

        // ---------------------------
        // DTO-Validation (Fail-Fast)
        // ---------------------------
        // our DTOs must be pre-validated, because of the non-typesafe bookKeeper touch() method we use.
        // that way we can make sure there is no typo, and that all fields can be found!
        boolean dtosAreValid = dtoValidityScanner.scanValidity();

        if (!dtosAreValid) {
            throw new RuntimeException("startup failed!");
        }
    }
}
