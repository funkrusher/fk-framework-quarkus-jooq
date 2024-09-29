package org.fk.core.actor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.fk.core.dto.AbstractDTO;
import org.fk.core.exception.MappingException;
import org.quartz.*;

import java.util.UUID;

public class ActorDispatchBuilder<T extends AbstractDTO> {

    private final Class<? extends AbstractActor<T>> actorClazz;

    private final Scheduler quartzScheduler;

    private final ObjectMapper objectMapper;

    private T data;

    public ActorDispatchBuilder(Class<? extends AbstractActor<T>> actorClazz, Scheduler quartzScheduler, ObjectMapper objectMapper) {
        this.actorClazz = actorClazz;
        this.quartzScheduler = quartzScheduler;
        this.objectMapper = objectMapper;
    }

    public ActorDispatchBuilder<T> withData(T data) {
        this.data = data;
        return this;
    }

    /**
     * dispatches the actor now.
     * - note: this is a forced concurrent dispatching
     * - note: the @DisallowConcurrentExecution on Job level is not recognized for such actors,
     * because each of the gets a unique jobKey on jobDetail level.
     *   - the @DisallowConcurrentExecution annotation is only recognized if a trigger on the same jobDetail
     *     is executed (triggered) at the same time / multiple times.
     */
    public void dispatchNowConcurrently() {
        final String jobData;
        try {
            jobData = objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new MappingException(e);
        }
        final JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("data", jobData);

        final JobDetail jobDetail = JobBuilder.newJob(actorClazz)
            .withIdentity(actorClazz.getSimpleName()+ "-" + UUID.randomUUID())
            .setJobData(jobDataMap)
            .build();
        final Trigger trigger = TriggerBuilder.newTrigger()
            .withIdentity(actorClazz.getSimpleName() + "-Trigger-" + UUID.randomUUID())
            .startNow()
            .build();
        try {
            quartzScheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            throw new MappingException(e);
        }
    }
}
