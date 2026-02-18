package com.rra.arts.arts_backend.model.enums;


import lombok.Getter;

import java.util.Set;

@Getter
public enum UserRole {

    INTERNAL_AUDITOR(Set.of(
            Permission.DASHBOARD_VIEW,
            Permission.AUDIT_CREATE,
            Permission.AUDIT_READ,
            Permission.AUDIT_UPDATE,
            Permission.AUDIT_VALIDATE,
            Permission.AUDIT_ASSIGN_FOCAL_PERSON,
            Permission.AUDIT_ASSIGN_STAFF,
            Permission.AUDIT_RETURN_TO_FOCAL_PERSON,
            Permission.AUDIT_PROPOSE_DIRECTOR_REVIEW,
            Permission.ACTIVITY_CREATE,
            Permission.ACTIVITY_READ,
            Permission.ACTIVITY_UPDATE,
            Permission.ACTIVITY_COMPLETE,
            Permission.EVIDENCE_SUBMIT,
            Permission.EVIDENCE_COMMENT,
            Permission.WORKFLOW_UPDATE_STAGE,
            Permission.WORKFLOW_VIEW_HISTORY,
            Permission.REPORT_VIEW
    )),

    DIRECTOR_IAU(Set.of(
            Permission.DASHBOARD_VIEW,
            Permission.AUDIT_VALIDATE_DIRECTOR,
            Permission.AUDIT_READ,
            Permission.REPORT_VIEW,
            Permission.REPORT_EXPORT,
            Permission.REMINDER_SEND,
            Permission.REMINDER_MEETING_REQUEST,
            Permission.NOTIFICATION_SEND
    )),

    TEAM_LEADER_QMS(Set.of(
            Permission.DASHBOARD_VIEW,
            Permission.AUDIT_VALIDATE_DIRECTOR,
            Permission.AUDIT_READ,
            Permission.REPORT_VIEW,
            Permission.REPORT_EXPORT,
            Permission.REMINDER_SEND,
            Permission.REMINDER_MEETING_REQUEST,
            Permission.NOTIFICATION_SEND
    )),

    COMMISSIONER_IAID(Set.of(
            Permission.DASHBOARD_VIEW,
            Permission.AUDIT_READ,
            Permission.REPORT_VIEW,
            Permission.REPORT_EXPORT,
            Permission.NOTIFICATION_SEND
    )),

    ASSISTANT_COMMISSIONER(Set.of(
            Permission.DASHBOARD_VIEW,
            Permission.AUDIT_READ,
            Permission.REPORT_VIEW
    )),

    FOCAL_PERSON(Set.of(
            Permission.DASHBOARD_VIEW,
            Permission.EVIDENCE_SUBMIT,
            Permission.EVIDENCE_COMMENT,
            Permission.EVIDENCE_RETURN,
            Permission.ACTIVITY_READ,
            Permission.ACTIVITY_UPDATE
    )),

    DEPARTMENT_STAFF(Set.of(
            Permission.DASHBOARD_VIEW,
            Permission.ACTIVITY_READ,
            Permission.EVIDENCE_COMMENT
    )),

    SYSTEM_ADMIN(Set.of(
            Permission.DASHBOARD_VIEW,
            Permission.AUDIT_CREATE,
            Permission.AUDIT_READ,
            Permission.AUDIT_UPDATE,
            Permission.AUDIT_DELETE,
            Permission.AUDIT_VALIDATE,
            Permission.AUDIT_ASSIGN_FOCAL_PERSON,
            Permission.AUDIT_ASSIGN_STAFF,
            Permission.AUDIT_RETURN_TO_FOCAL_PERSON,
            Permission.AUDIT_PROPOSE_DIRECTOR_REVIEW,
            Permission.AUDIT_VALIDATE_DIRECTOR,
            Permission.ACTIVITY_CREATE,
            Permission.ACTIVITY_READ,
            Permission.ACTIVITY_UPDATE,
            Permission.ACTIVITY_COMPLETE,
            Permission.EVIDENCE_SUBMIT,
            Permission.EVIDENCE_COMMENT,
            Permission.EVIDENCE_RETURN,
            Permission.FILE_UPLOAD,
            Permission.FILE_DOWNLOAD,
            Permission.FILE_DELETE,
            Permission.WORKFLOW_UPDATE_STAGE,
            Permission.WORKFLOW_VIEW_HISTORY,
            Permission.NOTIFICATION_SEND,
            Permission.NOTIFICATION_VIEW,
            Permission.NOTIFICATION_MARK_READ,
            Permission.REMINDER_SEND,
            Permission.REMINDER_MEETING_REQUEST,
            Permission.USER_CREATE,
            Permission.USER_READ,
            Permission.USER_UPDATE,
            Permission.USER_DELETE,
            Permission.ADMIN_MANAGE,
            Permission.REPORT_VIEW,
            Permission.REPORT_EXPORT,
            Permission.AUDIT_LOG_VIEW,
            Permission.BACKUP_MANAGE,
            Permission.DISASTER_RECOVERY,
            Permission.HELP_ACCESS
    ));

    private final Set<Permission> permissions;

    UserRole(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
