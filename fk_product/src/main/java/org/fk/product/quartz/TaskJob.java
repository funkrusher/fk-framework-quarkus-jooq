package org.fk.product.quartz;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.fk.core.jooq.DSLFactory;
import org.fk.core.jooq.RequestContext;
import org.fk.product.dao.TaskDAO;
import org.fk.product.dto.TaskDTO;
import org.jooq.DSLContext;

@ApplicationScoped
public class TaskJob {

    @Inject
    DSLFactory dslFactory;

    @Scheduled(every = "300s", identity = "task-job")
    @Transactional(rollbackOn = Exception.class)
    void schedule() {
        // note: the Transactional annotation is important here,
        // because in failback case when server restarts and missed times will be fired during startup
        // it would otherwise fail.
        DSLContext dsl = dslFactory.create(new RequestContext(1, 1));
        TaskDTO task = new TaskDTO();
        TaskDAO taskDAO = new TaskDAO(dsl);
        taskDAO.insertDTO(task);
    }
}
