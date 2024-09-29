package org.fk.core.actor;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.fk.core.dto.AbstractDTO;
import org.quartz.*;

@ApplicationScoped
public class ActorDispatcher {

    @Inject
    Scheduler quartzScheduler;

    @Inject
    ObjectMapper objectMapper;


    public <T extends AbstractDTO> ActorDispatchBuilder<T> withActor(Class<? extends AbstractActor<T>> actorClazz) {
        return new ActorDispatchBuilder<>(actorClazz, quartzScheduler, objectMapper);
    }
}
