package org.fk.core.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fk.core.jackson.ObjectMapperProducer;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public abstract class AbstractTask<T extends AbstractTaskDTO> implements Job {

    private final Class<T> taskType;

    private final ObjectMapper jsonMapper;

    public AbstractTask(Class<T> taskType) {
        this.taskType = taskType;
        this.jsonMapper = ObjectMapperProducer.create();
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        T task = deserializeTask(context); // Deserialize task data
        executeTask(task, context); // Execute task-specific logic
    }

    // Abstract method for subclasses to implement task-specific logic
    protected abstract void executeTask(T task, JobExecutionContext context) throws JobExecutionException;

    protected T deserializeTask(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        String jobData = jobDataMap.getString("data");
        try {
            return jsonMapper.readValue(jobData, taskType);
        } catch (Exception e) {
            throw new JobExecutionException("Failed to deserialize task data", e);
        }
    }
}
