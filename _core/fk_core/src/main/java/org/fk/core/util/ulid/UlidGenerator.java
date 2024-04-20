package org.fk.core.util.ulid;

import com.github.f4b6a3.ulid.Ulid;
import com.github.f4b6a3.ulid.UlidCreator;

import java.util.UUID;

public class UlidGenerator {

    public static UUID createMariadbUuid() {
        // mariadb uses RFC4122 UUID, we should not use another UUID variant!
        Ulid ulid = UlidCreator.getMonotonicUlid();
        return ulid.toRfc4122().toUuid();
    }
}
