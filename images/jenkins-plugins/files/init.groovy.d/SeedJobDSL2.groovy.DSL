job('SeedJobInternal') {
    description('Seed Job to showcase using DSL from scripts stored locally')
    label("master")
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
