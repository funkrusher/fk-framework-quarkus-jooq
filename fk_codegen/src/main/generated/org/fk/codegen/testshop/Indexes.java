/*
 * This file is generated by jOOQ.
 */
package org.fk.codegen.testshop;


import org.fk.codegen.testshop.tables.QrtzBlobTriggers;
import org.fk.codegen.testshop.tables.QrtzFiredTriggers;
import org.fk.codegen.testshop.tables.QrtzJobDetails;
import org.fk.codegen.testshop.tables.QrtzTriggers;
import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables in testshop.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index QRTZ_FIRED_TRIGGERS_IDX_QRTZ_FT_INST_JOB_REQ_RCVRY = Internal.createIndex(DSL.name("IDX_QRTZ_FT_INST_JOB_REQ_RCVRY"), QrtzFiredTriggers.QRTZ_FIRED_TRIGGERS, new OrderField[] { QrtzFiredTriggers.QRTZ_FIRED_TRIGGERS.SCHED_NAME, QrtzFiredTriggers.QRTZ_FIRED_TRIGGERS.INSTANCE_NAME, QrtzFiredTriggers.QRTZ_FIRED_TRIGGERS.REQUESTS_RECOVERY }, false);
    public static final Index QRTZ_FIRED_TRIGGERS_IDX_QRTZ_FT_J_G = Internal.createIndex(DSL.name("IDX_QRTZ_FT_J_G"), QrtzFiredTriggers.QRTZ_FIRED_TRIGGERS, new OrderField[] { QrtzFiredTriggers.QRTZ_FIRED_TRIGGERS.SCHED_NAME, QrtzFiredTriggers.QRTZ_FIRED_TRIGGERS.JOB_NAME, QrtzFiredTriggers.QRTZ_FIRED_TRIGGERS.JOB_GROUP }, false);
    public static final Index QRTZ_FIRED_TRIGGERS_IDX_QRTZ_FT_JG = Internal.createIndex(DSL.name("IDX_QRTZ_FT_JG"), QrtzFiredTriggers.QRTZ_FIRED_TRIGGERS, new OrderField[] { QrtzFiredTriggers.QRTZ_FIRED_TRIGGERS.SCHED_NAME, QrtzFiredTriggers.QRTZ_FIRED_TRIGGERS.JOB_GROUP }, false);
    public static final Index QRTZ_FIRED_TRIGGERS_IDX_QRTZ_FT_T_G = Internal.createIndex(DSL.name("IDX_QRTZ_FT_T_G"), QrtzFiredTriggers.QRTZ_FIRED_TRIGGERS, new OrderField[] { QrtzFiredTriggers.QRTZ_FIRED_TRIGGERS.SCHED_NAME, QrtzFiredTriggers.QRTZ_FIRED_TRIGGERS.TRIGGER_NAME, QrtzFiredTriggers.QRTZ_FIRED_TRIGGERS.TRIGGER_GROUP }, false);
    public static final Index QRTZ_FIRED_TRIGGERS_IDX_QRTZ_FT_TG = Internal.createIndex(DSL.name("IDX_QRTZ_FT_TG"), QrtzFiredTriggers.QRTZ_FIRED_TRIGGERS, new OrderField[] { QrtzFiredTriggers.QRTZ_FIRED_TRIGGERS.SCHED_NAME, QrtzFiredTriggers.QRTZ_FIRED_TRIGGERS.TRIGGER_GROUP }, false);
    public static final Index QRTZ_FIRED_TRIGGERS_IDX_QRTZ_FT_TRIG_INST_NAME = Internal.createIndex(DSL.name("IDX_QRTZ_FT_TRIG_INST_NAME"), QrtzFiredTriggers.QRTZ_FIRED_TRIGGERS, new OrderField[] { QrtzFiredTriggers.QRTZ_FIRED_TRIGGERS.SCHED_NAME, QrtzFiredTriggers.QRTZ_FIRED_TRIGGERS.INSTANCE_NAME }, false);
    public static final Index QRTZ_JOB_DETAILS_IDX_QRTZ_J_GRP = Internal.createIndex(DSL.name("IDX_QRTZ_J_GRP"), QrtzJobDetails.QRTZ_JOB_DETAILS, new OrderField[] { QrtzJobDetails.QRTZ_JOB_DETAILS.SCHED_NAME, QrtzJobDetails.QRTZ_JOB_DETAILS.JOB_GROUP }, false);
    public static final Index QRTZ_JOB_DETAILS_IDX_QRTZ_J_REQ_RECOVERY = Internal.createIndex(DSL.name("IDX_QRTZ_J_REQ_RECOVERY"), QrtzJobDetails.QRTZ_JOB_DETAILS, new OrderField[] { QrtzJobDetails.QRTZ_JOB_DETAILS.SCHED_NAME, QrtzJobDetails.QRTZ_JOB_DETAILS.REQUESTS_RECOVERY }, false);
    public static final Index QRTZ_TRIGGERS_IDX_QRTZ_T_C = Internal.createIndex(DSL.name("IDX_QRTZ_T_C"), QrtzTriggers.QRTZ_TRIGGERS, new OrderField[] { QrtzTriggers.QRTZ_TRIGGERS.SCHED_NAME, QrtzTriggers.QRTZ_TRIGGERS.CALENDAR_NAME }, false);
    public static final Index QRTZ_TRIGGERS_IDX_QRTZ_T_G = Internal.createIndex(DSL.name("IDX_QRTZ_T_G"), QrtzTriggers.QRTZ_TRIGGERS, new OrderField[] { QrtzTriggers.QRTZ_TRIGGERS.SCHED_NAME, QrtzTriggers.QRTZ_TRIGGERS.TRIGGER_GROUP }, false);
    public static final Index QRTZ_TRIGGERS_IDX_QRTZ_T_J = Internal.createIndex(DSL.name("IDX_QRTZ_T_J"), QrtzTriggers.QRTZ_TRIGGERS, new OrderField[] { QrtzTriggers.QRTZ_TRIGGERS.SCHED_NAME, QrtzTriggers.QRTZ_TRIGGERS.JOB_NAME, QrtzTriggers.QRTZ_TRIGGERS.JOB_GROUP }, false);
    public static final Index QRTZ_TRIGGERS_IDX_QRTZ_T_JG = Internal.createIndex(DSL.name("IDX_QRTZ_T_JG"), QrtzTriggers.QRTZ_TRIGGERS, new OrderField[] { QrtzTriggers.QRTZ_TRIGGERS.SCHED_NAME, QrtzTriggers.QRTZ_TRIGGERS.JOB_GROUP }, false);
    public static final Index QRTZ_TRIGGERS_IDX_QRTZ_T_N_G_STATE = Internal.createIndex(DSL.name("IDX_QRTZ_T_N_G_STATE"), QrtzTriggers.QRTZ_TRIGGERS, new OrderField[] { QrtzTriggers.QRTZ_TRIGGERS.SCHED_NAME, QrtzTriggers.QRTZ_TRIGGERS.TRIGGER_GROUP, QrtzTriggers.QRTZ_TRIGGERS.TRIGGER_STATE }, false);
    public static final Index QRTZ_TRIGGERS_IDX_QRTZ_T_N_STATE = Internal.createIndex(DSL.name("IDX_QRTZ_T_N_STATE"), QrtzTriggers.QRTZ_TRIGGERS, new OrderField[] { QrtzTriggers.QRTZ_TRIGGERS.SCHED_NAME, QrtzTriggers.QRTZ_TRIGGERS.TRIGGER_NAME, QrtzTriggers.QRTZ_TRIGGERS.TRIGGER_GROUP, QrtzTriggers.QRTZ_TRIGGERS.TRIGGER_STATE }, false);
    public static final Index QRTZ_TRIGGERS_IDX_QRTZ_T_NEXT_FIRE_TIME = Internal.createIndex(DSL.name("IDX_QRTZ_T_NEXT_FIRE_TIME"), QrtzTriggers.QRTZ_TRIGGERS, new OrderField[] { QrtzTriggers.QRTZ_TRIGGERS.SCHED_NAME, QrtzTriggers.QRTZ_TRIGGERS.NEXT_FIRE_TIME }, false);
    public static final Index QRTZ_TRIGGERS_IDX_QRTZ_T_NFT_MISFIRE = Internal.createIndex(DSL.name("IDX_QRTZ_T_NFT_MISFIRE"), QrtzTriggers.QRTZ_TRIGGERS, new OrderField[] { QrtzTriggers.QRTZ_TRIGGERS.SCHED_NAME, QrtzTriggers.QRTZ_TRIGGERS.MISFIRE_INSTR, QrtzTriggers.QRTZ_TRIGGERS.NEXT_FIRE_TIME }, false);
    public static final Index QRTZ_TRIGGERS_IDX_QRTZ_T_NFT_ST = Internal.createIndex(DSL.name("IDX_QRTZ_T_NFT_ST"), QrtzTriggers.QRTZ_TRIGGERS, new OrderField[] { QrtzTriggers.QRTZ_TRIGGERS.SCHED_NAME, QrtzTriggers.QRTZ_TRIGGERS.TRIGGER_STATE, QrtzTriggers.QRTZ_TRIGGERS.NEXT_FIRE_TIME }, false);
    public static final Index QRTZ_TRIGGERS_IDX_QRTZ_T_NFT_ST_MISFIRE = Internal.createIndex(DSL.name("IDX_QRTZ_T_NFT_ST_MISFIRE"), QrtzTriggers.QRTZ_TRIGGERS, new OrderField[] { QrtzTriggers.QRTZ_TRIGGERS.SCHED_NAME, QrtzTriggers.QRTZ_TRIGGERS.MISFIRE_INSTR, QrtzTriggers.QRTZ_TRIGGERS.NEXT_FIRE_TIME, QrtzTriggers.QRTZ_TRIGGERS.TRIGGER_STATE }, false);
    public static final Index QRTZ_TRIGGERS_IDX_QRTZ_T_NFT_ST_MISFIRE_GRP = Internal.createIndex(DSL.name("IDX_QRTZ_T_NFT_ST_MISFIRE_GRP"), QrtzTriggers.QRTZ_TRIGGERS, new OrderField[] { QrtzTriggers.QRTZ_TRIGGERS.SCHED_NAME, QrtzTriggers.QRTZ_TRIGGERS.MISFIRE_INSTR, QrtzTriggers.QRTZ_TRIGGERS.NEXT_FIRE_TIME, QrtzTriggers.QRTZ_TRIGGERS.TRIGGER_GROUP, QrtzTriggers.QRTZ_TRIGGERS.TRIGGER_STATE }, false);
    public static final Index QRTZ_TRIGGERS_IDX_QRTZ_T_STATE = Internal.createIndex(DSL.name("IDX_QRTZ_T_STATE"), QrtzTriggers.QRTZ_TRIGGERS, new OrderField[] { QrtzTriggers.QRTZ_TRIGGERS.SCHED_NAME, QrtzTriggers.QRTZ_TRIGGERS.TRIGGER_STATE }, false);
    public static final Index QRTZ_BLOB_TRIGGERS_SCHED_NAME = Internal.createIndex(DSL.name("SCHED_NAME"), QrtzBlobTriggers.QRTZ_BLOB_TRIGGERS, new OrderField[] { QrtzBlobTriggers.QRTZ_BLOB_TRIGGERS.SCHED_NAME, QrtzBlobTriggers.QRTZ_BLOB_TRIGGERS.TRIGGER_NAME, QrtzBlobTriggers.QRTZ_BLOB_TRIGGERS.TRIGGER_GROUP }, false);
}
