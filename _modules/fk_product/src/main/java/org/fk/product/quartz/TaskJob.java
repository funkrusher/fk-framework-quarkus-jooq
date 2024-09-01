package org.fk.product.quartz;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.fk.core.request.RequestContext;
import org.fk.database1.Database1;
import org.fk.product.dao.TaskDAO;
import org.fk.product.api.TaskAPI;
import org.jooq.DSLContext;

@ApplicationScoped
public class TaskJob {

    @Inject
    Database1 database1;

    @Scheduled(every = "300s", identity = "task-job")
    void schedule() {
        // either use Transactional annotation of quarkus, or DSLContext.transaction, to make sure we commit.
        DSLContext dsl = database1.dsl(new RequestContext(1, 1));
        dsl.transaction(trx -> {
            TaskAPI task = new TaskAPI();
            TaskDAO taskDAO = new TaskDAO(trx.dsl());
            taskDAO.insert(task);
        });
    }
}
