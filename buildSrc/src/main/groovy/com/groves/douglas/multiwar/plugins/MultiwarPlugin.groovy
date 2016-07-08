package com.groves.douglas.multiwar.plugins

import com.groves.douglas.multiwar.config.Container
import com.groves.douglas.multiwar.extensions.MultiwarExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.DuplicatesStrategy
import org.gradle.api.tasks.bundling.War

/**
 * Created by Douglas Groves on 07/07/2016.
 */
class MultiwarPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.extensions.create('multiwar', MultiwarExtension)
        def containers = project.multiwar.extensions.containers =
                project.container(Container)
        initialiseTasks(project, containers)
        disableWarTask(project)
    }

    def initialiseTasks(Project project, containers) {
        project.afterEvaluate {
            containers.all { Container container ->
                if(project.multiwar.verbose){
                    project.getLogger().quiet("Found container: {}", container.name)
                }
                def task = project.task([type: War], "assemble-${container.name}-war") { myTask ->
                    from("${project.rootDir}/config/${container.name}"){
                        into 'WEB-INF'
                    }
                    classpath project.configurations[container.name]
                    classifier = container.name
                    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
                    doLast {
                        if(project.multiwar.verbose){
                            project.getLogger().quiet("Assembling {} war file...", container.name)
                        }
                    }
                }
                project.tasks.assemble.dependsOn(task)
            }
        }
    }

    def disableWarTask(Project project) {
        project.gradle.taskGraph.whenReady { taskGraph ->
            def tasks = taskGraph.getAllTasks()
            if (tasks.find { it.name == 'assemble' }) {
                tasks.findAll { it.name == 'war' }.each { task ->
                    !project.multiwar.verbose ?: project.getLogger().quiet("Disabling default war plugin task...")
                    task.enabled = false
                }
            }
        }
    }
}
