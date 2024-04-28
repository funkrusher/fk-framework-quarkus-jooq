/*
 * This file is generated by jOOQ.
 */
package org.fk.database1.testshop;


import java.util.Arrays;
import java.util.List;

import org.fk.database1.DefaultCatalog;
import org.fk.database1.testshop.tables.Client;
import org.fk.database1.testshop.tables.Databasechangelog;
import org.fk.database1.testshop.tables.Databasechangeloglock;
import org.fk.database1.testshop.tables.Init;
import org.fk.database1.testshop.tables.Lang;
import org.fk.database1.testshop.tables.Post;
import org.fk.database1.testshop.tables.QrtzBlobTriggers;
import org.fk.database1.testshop.tables.QrtzCalendars;
import org.fk.database1.testshop.tables.QrtzCronTriggers;
import org.fk.database1.testshop.tables.QrtzFiredTriggers;
import org.fk.database1.testshop.tables.QrtzJobDetails;
import org.fk.database1.testshop.tables.QrtzLocks;
import org.fk.database1.testshop.tables.QrtzPausedTriggerGrps;
import org.fk.database1.testshop.tables.QrtzSchedulerState;
import org.fk.database1.testshop.tables.QrtzSimpleTriggers;
import org.fk.database1.testshop.tables.QrtzSimpropTriggers;
import org.fk.database1.testshop.tables.QrtzTriggers;
import org.fk.database1.testshop.tables.Role;
import org.fk.database1.testshop.tables.Task;
import org.fk.database1.testshop.tables.User;
import org.fk.database1.testshop.tables.UserRole;
import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Testshop extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>testshop</code>
     */
    public static final Testshop TESTSHOP = new Testshop();

    /**
     * The table <code>testshop.client</code>.
     */
    public final Client CLIENT = Client.CLIENT;

    /**
     * The table <code>testshop.DATABASECHANGELOG</code>.
     */
    public final Databasechangelog DATABASECHANGELOG = Databasechangelog.DATABASECHANGELOG;

    /**
     * The table <code>testshop.DATABASECHANGELOGLOCK</code>.
     */
    public final Databasechangeloglock DATABASECHANGELOGLOCK = Databasechangeloglock.DATABASECHANGELOGLOCK;

    /**
     * The table <code>testshop.init</code>.
     */
    public final Init INIT = Init.INIT;

    /**
     * Lang contains available languages of the app
     */
    public final Lang LANG = Lang.LANG;

    /**
     * The table <code>testshop.post</code>.
     */
    public final Post POST = Post.POST;

    /**
     * The table <code>testshop.QRTZ_BLOB_TRIGGERS</code>.
     */
    public final QrtzBlobTriggers QRTZ_BLOB_TRIGGERS = QrtzBlobTriggers.QRTZ_BLOB_TRIGGERS;

    /**
     * The table <code>testshop.QRTZ_CALENDARS</code>.
     */
    public final QrtzCalendars QRTZ_CALENDARS = QrtzCalendars.QRTZ_CALENDARS;

    /**
     * The table <code>testshop.QRTZ_CRON_TRIGGERS</code>.
     */
    public final QrtzCronTriggers QRTZ_CRON_TRIGGERS = QrtzCronTriggers.QRTZ_CRON_TRIGGERS;

    /**
     * The table <code>testshop.QRTZ_FIRED_TRIGGERS</code>.
     */
    public final QrtzFiredTriggers QRTZ_FIRED_TRIGGERS = QrtzFiredTriggers.QRTZ_FIRED_TRIGGERS;

    /**
     * The table <code>testshop.QRTZ_JOB_DETAILS</code>.
     */
    public final QrtzJobDetails QRTZ_JOB_DETAILS = QrtzJobDetails.QRTZ_JOB_DETAILS;

    /**
     * The table <code>testshop.QRTZ_LOCKS</code>.
     */
    public final QrtzLocks QRTZ_LOCKS = QrtzLocks.QRTZ_LOCKS;

    /**
     * The table <code>testshop.QRTZ_PAUSED_TRIGGER_GRPS</code>.
     */
    public final QrtzPausedTriggerGrps QRTZ_PAUSED_TRIGGER_GRPS = QrtzPausedTriggerGrps.QRTZ_PAUSED_TRIGGER_GRPS;

    /**
     * The table <code>testshop.QRTZ_SCHEDULER_STATE</code>.
     */
    public final QrtzSchedulerState QRTZ_SCHEDULER_STATE = QrtzSchedulerState.QRTZ_SCHEDULER_STATE;

    /**
     * The table <code>testshop.QRTZ_SIMPLE_TRIGGERS</code>.
     */
    public final QrtzSimpleTriggers QRTZ_SIMPLE_TRIGGERS = QrtzSimpleTriggers.QRTZ_SIMPLE_TRIGGERS;

    /**
     * The table <code>testshop.QRTZ_SIMPROP_TRIGGERS</code>.
     */
    public final QrtzSimpropTriggers QRTZ_SIMPROP_TRIGGERS = QrtzSimpropTriggers.QRTZ_SIMPROP_TRIGGERS;

    /**
     * The table <code>testshop.QRTZ_TRIGGERS</code>.
     */
    public final QrtzTriggers QRTZ_TRIGGERS = QrtzTriggers.QRTZ_TRIGGERS;

    /**
     * The table <code>testshop.role</code>.
     */
    public final Role ROLE = Role.ROLE;

    /**
     * The table <code>testshop.task</code>.
     */
    public final Task TASK = Task.TASK;

    /**
     * The table <code>testshop.user</code>.
     */
    public final User USER = User.USER;

    /**
     * The table <code>testshop.user_role</code>.
     */
    public final UserRole USER_ROLE = UserRole.USER_ROLE;

    /**
     * No further instances allowed
     */
    private Testshop() {
        super("testshop", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            Client.CLIENT,
            Databasechangelog.DATABASECHANGELOG,
            Databasechangeloglock.DATABASECHANGELOGLOCK,
            Init.INIT,
            Lang.LANG,
            Post.POST,
            QrtzBlobTriggers.QRTZ_BLOB_TRIGGERS,
            QrtzCalendars.QRTZ_CALENDARS,
            QrtzCronTriggers.QRTZ_CRON_TRIGGERS,
            QrtzFiredTriggers.QRTZ_FIRED_TRIGGERS,
            QrtzJobDetails.QRTZ_JOB_DETAILS,
            QrtzLocks.QRTZ_LOCKS,
            QrtzPausedTriggerGrps.QRTZ_PAUSED_TRIGGER_GRPS,
            QrtzSchedulerState.QRTZ_SCHEDULER_STATE,
            QrtzSimpleTriggers.QRTZ_SIMPLE_TRIGGERS,
            QrtzSimpropTriggers.QRTZ_SIMPROP_TRIGGERS,
            QrtzTriggers.QRTZ_TRIGGERS,
            Role.ROLE,
            Task.TASK,
            User.USER,
            UserRole.USER_ROLE
        );
    }
}
