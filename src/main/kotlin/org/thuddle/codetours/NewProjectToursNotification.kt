package org.thuddle.codetours

import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project

const val RunTour = "run-tour"
const val Ignore = "dismiss"

object NewProjectToursNotification {
    private val group = NotificationGroupManager.getInstance().getNotificationGroup("CodeTours Notification")

    fun notifyUser(project: Project) {
        val title = "View code tours?"

        val content = escapeString("<a href='$RunTour'>Sure, why not?</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='$Ignore'>Nah, I know what I'm doing</a>")

        val notification = group.createNotification(title, content, NotificationType.INFORMATION) { notification, event ->
            notification.expire()

            if (event.description == RunTour) {
                SeenToursStateComponent.instance.getTours(project)
            }
        }

        notification.whenExpired {
            SeenToursStateComponent.instance.state.seenProjects.add(project.name)
        }

        notification.notify(project)
    }

    private fun escapeString(string: String): String {
        return string.replace("\n".toRegex(), "\n<br />")
    }
}