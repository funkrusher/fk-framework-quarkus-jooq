package org.fk.product.quartz;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.fk.core.request.RequestContext;
import org.fk.core.actor.ActorDispatcher;
import org.fk.database1.Database1;
import org.fk.database1.testshop.tables.records.TaskRecord;
import org.fk.product.dao.TaskDAO;
import org.jooq.DSLContext;

@ApplicationScoped
public class TaskJob {

    @Inject
    Database1 database1;

    @Inject
    ActorDispatcher actorDispatcher;

    @Scheduled(every = "120s", identity = "task-job")
    void schedule() {
        // either use Transactional annotation of quarkus, or DSLContext.transaction, to make sure we commit.
        DSLContext dsl = database1.dsl(new RequestContext(1, 1));
        dsl.transaction(trx -> {
            TaskRecord task = new TaskRecord();
            TaskDAO taskDAO = new TaskDAO(trx.dsl());
            taskDAO.insert(task);
        });

        // example of the actor dispatcher, that can be used everywhere in the codebase,
        // to dispatch async tasks, that will be processed with quartz.
//        actorDispatcher
//            .withActor(FiledItemActor.class)
//            .withData(new FiledItemActorDTO().setClientId(1).setName("test1"))
//            .dispatchNow();
//        actorDispatcher
//            .withActor(FiledItemActor.class)
//            .withData(new FiledItemActorDTO().setClientId(2).setName("test2"))
//            .dispatchNow();
//        actorDispatcher
//            .withActor(FiledItemActor.class)
//            .withData(new FiledItemActorDTO().setClientId(3).setName("test3"))
//            .dispatchNow();
    }
}
