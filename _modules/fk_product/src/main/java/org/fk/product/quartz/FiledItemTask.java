package org.fk.product.quartz;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.fk.core.task.AbstractTask;
import org.fk.database1.Database1;
import org.jboss.logging.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@ApplicationScoped
public class FiledItemTask extends AbstractTask<FiledItemTaskDTO> {

    private static final Logger LOGGER = Logger.getLogger(FiledItemTask.class);


    @Inject
    Database1 database1;

    public FiledItemTask() {
        super(FiledItemTaskDTO.class);
    }

    @Override
    protected void executeTask(FiledItemTaskDTO task, JobExecutionContext context) throws JobExecutionException {
        LOGGER.info("FUNNY STUFF!! " + task.getClientId() + " " + task.getName());
    }
}
