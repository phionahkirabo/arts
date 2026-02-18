package com.rra.arts.arts_backend.model.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    // ================= Dashboard =================
    DASHBOARD_VIEW("dashboard:view"),

    // ================= Audit Recommendation =================
    AUDIT_CREATE("audit:create"),
    AUDIT_READ("audit:read"),
    AUDIT_UPDATE("audit:update"),
    AUDIT_DELETE("audit:delete"),
    AUDIT_VALIDATE("audit:validate"),
    AUDIT_ASSIGN_FOCAL_PERSON("audit:assign_focal_person"),
    AUDIT_ASSIGN_STAFF("audit:assign_staff"),
    AUDIT_RETURN_TO_FOCAL_PERSON("audit:return_to_focal_person"),
    AUDIT_PROPOSE_DIRECTOR_REVIEW("audit:propose_director_review"),
    AUDIT_VALIDATE_DIRECTOR("audit:validate_director"),

    // ================= Implementation Activity =================
    ACTIVITY_CREATE("activity:create"),
    ACTIVITY_READ("activity:read"),
    ACTIVITY_UPDATE("activity:update"),
    ACTIVITY_COMPLETE("activity:complete"),

    // ================= Evidence Submission =================
    EVIDENCE_SUBMIT("evidence:submit"),
    EVIDENCE_COMMENT("evidence:comment"),
    EVIDENCE_RETURN("evidence:return"),

    // ================= File Attachments =================
    FILE_UPLOAD("file:upload"),
    FILE_DOWNLOAD("file:download"),
    FILE_DELETE("file:delete"),

    // ================= Workflow Tracking =================
    WORKFLOW_UPDATE_STAGE("workflow:update_stage"),
    WORKFLOW_VIEW_HISTORY("workflow:view_history"),

    // ================= Notifications =================
    NOTIFICATION_SEND("notification:send"),
    NOTIFICATION_VIEW("notification:view"),
    NOTIFICATION_MARK_READ("notification:mark_read"),

    // ================= Reminders =================
    REMINDER_SEND("reminder:send"),
    REMINDER_MEETING_REQUEST("reminder:meeting_request"),

    // ================= Users & Access Control =================
    USER_CREATE("user:create"),
    USER_READ("user:read"),
    USER_UPDATE("user:update"),
    USER_DELETE("user:delete"),
    ADMIN_MANAGE("admin:manage"),

    // ================= Reports =================
    REPORT_VIEW("report:view"),
    REPORT_EXPORT("report:export"),

    // ================= Audit Change Log =================
    AUDIT_LOG_VIEW("audit_log:view"),

    // ================= Backup / Recovery =================
    BACKUP_MANAGE("backup:manage"),
    DISASTER_RECOVERY("disaster:recovery"),

    // ================= Help / Support =================
    HELP_ACCESS("help:access");

    @Getter
    private final String permission;
}

