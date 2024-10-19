package org.fk.framework.actor;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.quartz.*;

@ApplicationScoped
public class ActorDispatcher {

    @Inject
    Scheduler quartzScheduler;

    @Inject
    ObjectMapper objectMapper;


    public <T> ActorDispatchBuilder<T> withActor(Class<? extends AbstractActor<T>> actorClazz) {
        return new ActorDispatchBuilder<>(actorClazz, quartzScheduler, objectMapper);
    }
}
