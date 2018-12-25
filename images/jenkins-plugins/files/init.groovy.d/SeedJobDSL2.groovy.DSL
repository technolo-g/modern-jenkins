job('seed-job2') {
    description('Seed Job to create other DSL-based jobs')
    customWorkspace('/var/jenkins_home/dslScripts')
    concurrentBuild(false)
    steps {
        dsl {
            external "GentleRestartJenkins.groovy"
            removeAction('DELETE')
            removeViewAction('DELETE')
            ignoreExisting(false)
        }
    }
}
