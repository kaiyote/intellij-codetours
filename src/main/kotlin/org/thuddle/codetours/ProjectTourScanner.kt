package org.thuddle.codetours

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity

class ProjectTourScanner: StartupActivity.Background {
    override fun runActivity(project: Project) {
        if (SeenToursStateComponent.instance.shouldNotify(project)) {
            NewProjectToursNotification.notifyUser(project)
        }
    }
}