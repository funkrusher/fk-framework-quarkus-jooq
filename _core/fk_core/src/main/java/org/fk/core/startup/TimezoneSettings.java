package org.fk.core.startup;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Singleton;
import org.jboss.logging.Logger;

/**
 * all our services, should always use UTC timezone.
 * We never want to use the timezone of the operating system / the JVM defaults.
 */
@Singleton
public class TimezoneSettings {

    public static final Logger LOGGER = Logger.getLogger(TimezoneSettings.class);

    public void setTimezone(@Observes StartupEvent startupEvent) {
        LOGGER.debug("setting user.timezone to UTC");
        System.setProperty("user.timezone", "UTC");
    }
}
