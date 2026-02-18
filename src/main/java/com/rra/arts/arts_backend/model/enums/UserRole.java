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
            Permission.AUDIT_SEND_REMINDER,
            Permission.ACTIVITY_CREATE,
            Permission.ACTIVITY_READ,
            Permission.ACTIVITY_UPDATE,
            Permission.ACTIVITY_COMPLETE,
            Permission.ACTIVITY_ASSIGN_TO_STAFF,
            Permission.EVIDENCE_SUBMIT,
            Permission.EVIDENCE_COMMENT,
            Permission.EVIDENCE_APPROVE,
            Permission.WORKFLOW_UPDATE_STAGE,
            Permission.WORKFLOW_VIEW_HISTORY,
            Permission.REPORT_VIEW,
            Permission.NOTIFICATION_SEND,
            Permission.NOTIFICATION_VIEW,
            Permission.NOTIFICATION_MARK_READ
    )),

    DIRECTOR_IAU(Set.of(
            Permission.DASHBOARD_VIEW,
            Permission.AUDIT_VALIDATE_DIRECTOR,
            Permission.AUDIT_READ,
            Permission.AUDIT_MODIFY_DEADLINE,
            Permission.AUDIT_SEND_REMINDER,
            Permission.REPORT_VIEW,
            Permission.REPORT_EXPORT,
            Permission.REMINDER_SEND,
            Permission.REMINDER_MEETING_REQUEST_PHYSICAL,
            Permission.REMINDER_MEETING_REQUEST_VIRTUAL,
            Permission.NOTIFICATION_SEND,
            Permission.WORKFLOW_VIEW_HISTORY
    )),

    TEAM_LEADER_QMS(Set.of(
            Permission.DASHBOARD_VIEW,
            Permission.AUDIT_VALIDATE_DIRECTOR,
            Permission.AUDIT_READ,
            Permission.AUDIT_MODIFY_DEADLINE,
            Permission.AUDIT_SEND_REMINDER,
            Permission.REPORT_VIEW,
            Permission.REPORT_EXPORT,
            Permission.REMINDER_SEND,
            Permission.REMINDER_MEETING_REQUEST_PHYSICAL,
            Permission.REMINDER_MEETING_REQUEST_VIRTUAL,
            Permission.NOTIFICATION_SEND,
            Permission.WORKFLOW_VIEW_HISTORY
    )),

    COMMISSIONER_IAID(Set.of(
            Permission.DASHBOARD_VIEW,
            Permission.AUDIT_READ,
            Permission.AUDIT_UPDATE,
            Permission.REPORT_VIEW,
            Permission.REPORT_EXPORT,
            Permission.NOTIFICATION_VIEW,
            Permission.NOTIFICATION_SEND,
            Permission.WORKFLOW_VIEW_HISTORY,
            Permission.USER_READ,
            Permission.ADMIN_MANAGE
    )),

    ASSISTANT_COMMISSIONER(Set.of(
            Permission.DASHBOARD_VIEW,
            Permission.AUDIT_READ,
            Permission.REPORT_VIEW,
            Permission.NOTIFICATION_VIEW,
            Permission.REMINDER_MEETING_REQUEST_PHYSICAL,
            Permission.REMINDER_MEETING_REQUEST_VIRTUAL
    )),

    FOCAL_PERSON(Set.of(
            Permission.DASHBOARD_VIEW,
            Permission.AUDIT_READ,
            Permission.ACTIVITY_READ,
            Permission.ACTIVITY_UPDATE,
            Permission.ACTIVITY_ASSIGN_TO_STAFF,
            Permission.EVIDENCE_SUBMIT,
            Permission.EVIDENCE_COMMENT,
            Permission.EVIDENCE_RETURN,
            Permission.FILE_UPLOAD,
            Permission.FILE_DOWNLOAD,
            Permission.WORKFLOW_UPDATE_STAGE,
            Permission.NOTIFICATION_MARK_READ,
            Permission.NOTIFICATION_VIEW
    )),

    DEPARTMENT_STAFF(Set.of(
            Permission.DASHBOARD_VIEW,
            Permission.ACTIVITY_READ,
            Permission.ACTIVITY_COMPLETE,
            Permission.EVIDENCE_SUBMIT,
            Permission.EVIDENCE_COMMENT,
            Permission.FILE_UPLOAD,
            Permission.FILE_DOWNLOAD,
            Permission.NOTIFICATION_MARK_READ,
            Permission.NOTIFICATION_VIEW
    )),
    SYSTEM_ADMIN(Set.of(
            Permission.ALL
    ));
    private final Set<Permission> permissions;

    UserRole(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
