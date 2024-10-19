package org.fk.framework.actor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fk.provider.jackson.ObjectMapperProducer;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public abstract class AbstractActor<T> implements Job {

    private final Class<T> dataClazz;

    private final ObjectMapper jsonMapper;

    protected AbstractActor(Class<T> dataClazz) {
        this.dataClazz = dataClazz;
        this.jsonMapper = ObjectMapperProducer.create();
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        execute(deserializeData(context), context);
    }

    // Abstract method for subclasses to implement actor-specific logic
    protected abstract void execute(T data, JobExecutionContext context) throws JobExecutionException;

    protected T deserializeData(JobExecutionContext context) throws JobExecutionException {
        // TODO: do we also need basic job data from getJobDetail in addition to the one from getTrigger() ?
        JobDataMap jobDataMap = context.getTrigger().getJobDataMap();
        String jobData = jobDataMap.getString("data");
        try {
            return jsonMapper.readValue(jobData, dataClazz);
        } catch (Exception e) {
            throw new JobExecutionException("Failed to deserialize actor data", e);
        }
    }
}
