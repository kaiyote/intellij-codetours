package org.thuddle.codetours.gui

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.layout.panel
import org.thuddle.codetours.SeenToursStateComponent
import javax.swing.JComboBox
import javax.swing.JComponent

class CodeTourDialogWrapper(private val project: Project): DialogWrapper(false) {

    var tourName: String = ""

    init {
        init()
        title = "Choose Tour"
    }

    override fun createCenterPanel(): JComponent? {
        val tours = SeenToursStateComponent.instance.getTours(project).sortedBy { it.title }.sortedBy { !it.isPrimary }
        tourName = tours[0].title

        return panel {
            val box = JComboBox(tours.map { it.title }.toTypedArray())
            box.addActionListener {
                tourName = (it.source as JComboBox<*>).selectedItem as String
            }
            box.selectedItem = tours.elementAt(0)
            return box
        }
    }
}