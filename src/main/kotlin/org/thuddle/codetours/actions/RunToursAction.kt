package org.thuddle.codetours.actions

import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import org.thuddle.codetours.RunTourByNameAction
import org.thuddle.codetours.SeenToursStateComponent
import org.thuddle.codetours.gui.CodeTourDialogWrapper
import org.thuddle.codetours.model.Tour

class RunToursAction: AnAction() {
    lateinit var tours: List<Tour>

    override fun update(e: AnActionEvent) {
        val project = e.project

        if (project == null) {
            e.presentation.isEnabledAndVisible = false
            return
        }

        if (!this::tours.isInitialized) {
            tours = SeenToursStateComponent.instance.getTours(project)
        }

        e.presentation.isEnabledAndVisible = tours.isNotEmpty()
    }

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project!!
        val dialog = CodeTourDialogWrapper(project)

        if (dialog.showAndGet()) {
            ActionManager.getInstance().tryToExecute(RunTourByNameAction(), e.inputEvent, null, null, false)
        }
    }
}