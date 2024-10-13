package org.fk.core.startup;

import io.quarkus.runtime.Startup;
import io.smallrye.config.Priorities;
import jakarta.annotation.Priority;
import jakarta.inject.Singleton;
import org.jboss.logging.Logger;

/**
 * CoreStartup
 * <p>
 * Central place for core startup-handling,
 * which is common for all our services/modules.
 */
@Singleton
public class CoreStartup {

    public static final Logger LOGGER = Logger.getLogger(CoreStartup.class);

    @Startup
    @Priority(Priorities.PLATFORM)
    public void startup() {
        // ---------------------------
        // System-Properties Overrides
        // ---------------------------
        // all our services, should always use UTC timezone.
        // We never want to use the timezone of the operating system / the JVM defaults.
        LOGGER.debug("setting user.timezone to UTC");
        System.setProperty("user.timezone", "UTC");
    }
}
