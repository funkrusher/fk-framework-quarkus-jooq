package org.fk.core.actor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.fk.core.exception.MappingException;
import org.quartz.*;

public class ActorDispatchBuilder<T> {

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
     * - note: the @DisallowConcurrentExecution on Job level can be used because we persist the job to quartz
     *   with data given in the trigger, so the job itself has no data and can be given a cluster-wide key
     *   that makes the use of the @DisallowConcurrentExecution annotation possible.
     * - the @DisallowConcurrentExecution annotation is only recognized if a trigger on the same jobDetail
     *   is executed (triggered) at the same time / multiple times.
     */
    public void dispatchNow() {
        // TODO: should we register all jobs at startup once instead of the dynamic check/register here?
        final String jobData;
        try {
            jobData = objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new MappingException(e);
        }
        final JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("data", jobData);

        // this code could help getting DisallowConcurrent to work.

        JobKey jobKey = new JobKey(actorClazz.getSimpleName(), "actorGroup");

        final boolean jobExists;
        try {
            jobExists = quartzScheduler.checkExists(jobKey);
        } catch (SchedulerException e) {
            throw new MappingException(e);
        }
        if (!jobExists) {
            JobDetail jobDetail2 = JobBuilder.newJob(actorClazz)
                .withIdentity(jobKey)
                .storeDurably()  // Store the job so it can be triggered later
                .build();
            try {
                quartzScheduler.addJob(jobDetail2, true);  // Add job to the scheduler, replacing if it exists
            } catch (SchedulerException e) {
                throw new MappingException(e);
            }
        }

        // Create a trigger to fire immediately, passing tenant-specific data
        Trigger trigger2 = TriggerBuilder.newTrigger()
            .forJob(jobKey)
            .startNow()
            .usingJobData("data", jobData)
            .build();

        // Trigger the job with the tenant-specific data
        try {
            quartzScheduler.triggerJob(jobKey, trigger2.getJobDataMap());
        } catch (SchedulerException e) {
            throw new MappingException(e);
        }
    }
}
