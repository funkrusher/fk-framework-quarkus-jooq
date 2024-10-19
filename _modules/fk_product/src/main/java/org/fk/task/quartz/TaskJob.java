package org.fk.task.quartz;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.fk.database1.Database1;
import org.fk.database1.testshop.tables.records.TaskRecord;
import org.fk.framework.actor.ActorDispatcher;
import org.fk.framework.request.RequestContext;
import org.fk.task.actor.FiledTaskActor;
import org.fk.task.dao.TaskDAO;
import org.fk.task.dto.FiledTaskActorDTO;
import org.jooq.DSLContext;

@ApplicationScoped
public class TaskJob {

    @Inject
    Database1 database1;

    @Inject
    ActorDispatcher actorDispatcher;

    @Scheduled(every = "300s", identity = "task-job")
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
        actorDispatcher
            .withActor(FiledTaskActor.class)
            .withData(FiledTaskActorDTO.builder().clientId(1).name("test1").build())
            .dispatchNow();
        actorDispatcher
            .withActor(FiledTaskActor.class)
            .withData(FiledTaskActorDTO.builder().clientId(2).name("test2").build())
            .dispatchNow();
        actorDispatcher
            .withActor(FiledTaskActor.class)
            .withData(FiledTaskActorDTO.builder().clientId(3).name("test3").build())
            .dispatchNow();
    }
}
