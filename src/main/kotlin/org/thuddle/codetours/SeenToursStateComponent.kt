package org.thuddle.codetours

import com.intellij.openapi.application.ReadAction
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.search.FilenameIndex
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.thuddle.codetours.model.Tour

@State(name = "SeenToursStateComponent", storages = [Storage("seen-tours.xml")])
class SeenToursStateComponent : PersistentStateComponent<SeenToursStateComponent.SeenToursState> {
    companion object {
        val instance: SeenToursStateComponent = SeenToursStateComponent()
    }

    private var seenToursState: SeenToursState = SeenToursState()
    private var tourFiles: List<Tour> = listOf()

    class SeenToursState {
        var seenProjects: ArrayList<String> = ArrayList()
    }

    override fun getState(): SeenToursState {
        return seenToursState
    }

    override fun loadState(state: SeenToursState) {
        seenToursState = state
    }

    fun getTours(project: Project): List<Tour> {
        if (tourFiles.isEmpty()) {
            tourFiles = ReadAction.compute<List<Tour>, Throwable> {
                FilenameIndex.getAllFilesByExt(project, "tour")
                    .map<VirtualFile, Tour> {
                        Json { ignoreUnknownKeys = true }.decodeFromString(String(it.inputStream.readAllBytes()))
                    }
            }
        }

        return tourFiles
    }

    fun shouldNotify(project: Project): Boolean {
        return !state.seenProjects.contains(project.name) && getTours(project).isNotEmpty()
    }
}