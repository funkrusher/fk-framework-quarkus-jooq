package org.fk.core.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.quartz.*;

import java.util.UUID;

@ApplicationScoped
public class TaskDispatcher {

    @Inject
    Scheduler quartzScheduler;

    @Inject
    ObjectMapper objectMapper;


    public void dispatch(AbstractTaskDTO task) {

        String jobData;
        try {
            jobData = objectMapper.writeValueAsString(task);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("data", jobData);

        JobDetail jobDetail = JobBuilder.newJob(task.getExecutorClass())
            .withIdentity(task.getClass().getSimpleName()+ "-" + UUID.randomUUID())
            .setJobData(jobDataMap)
            .build();
        Trigger trigger = TriggerBuilder.newTrigger()
            .withIdentity(task.getClass().getSimpleName() + "-Trigger-" + UUID.randomUUID())
            .startNow()
            .build();
        try {
            quartzScheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
