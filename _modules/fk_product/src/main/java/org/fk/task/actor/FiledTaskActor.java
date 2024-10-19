package org.fk.task.actor;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.fk.framework.actor.AbstractActor;
import org.fk.framework.exception.MappingException;
import org.fk.database1.Database1;
import org.fk.task.dto.FiledTaskActorDTO;
import org.jboss.logging.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@DisallowConcurrentExecution
@ApplicationScoped
public class FiledTaskActor extends AbstractActor<FiledTaskActorDTO> {

    private static final Logger LOGGER = Logger.getLogger(FiledTaskActor.class);

    @Inject
    Database1 database1;

    public FiledTaskActor() {
        super(FiledTaskActorDTO.class);
    }

    @Override
    protected void execute(FiledTaskActorDTO data, JobExecutionContext context) throws JobExecutionException {
        LOGGER.info("filedItemActor executing now! " + data.clientId() + " " + data.name());
        try {
            Thread.sleep(2500L);
        } catch (InterruptedException e) {
            throw new MappingException(e);
        }
    }
}
