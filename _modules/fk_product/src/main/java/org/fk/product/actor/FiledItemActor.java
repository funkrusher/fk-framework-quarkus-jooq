package org.fk.product.actor;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.fk.core.actor.AbstractActor;
import org.fk.core.exception.MappingException;
import org.fk.database1.Database1;
import org.fk.product.dto.FiledItemActorDTO;
import org.jboss.logging.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@DisallowConcurrentExecution
@ApplicationScoped
public class FiledItemActor extends AbstractActor<FiledItemActorDTO> {

    private static final Logger LOGGER = Logger.getLogger(FiledItemActor.class);

    @Inject
    Database1 database1;

    public FiledItemActor() {
        super(FiledItemActorDTO.class);
    }

    @Override
    protected void execute(FiledItemActorDTO data, JobExecutionContext context) throws JobExecutionException {
        LOGGER.info("filedItemActor executing now! " + data.clientId() + " " + data.name());
        try {
            Thread.sleep(2500L);
        } catch (InterruptedException e) {
            throw new MappingException(e);
        }
    }
}
