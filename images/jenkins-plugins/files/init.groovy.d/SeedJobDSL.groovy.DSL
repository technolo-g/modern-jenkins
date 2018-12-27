job('seed-job') {
    description('Seed Job to create other DSL-based jobs')
    label("master")
    scm {
        git("${System.getenv("DSL_REPO")}")
    }
    triggers {
        // Every 15 minutes poll to see if there are new changes
        scm('H/15 * * * *')
        // Once a day run anyway to catch and highlight manual changes
        cron('H/60 H/24 * * *')
    }
    concurrentBuild(false)
    steps {
        dsl {
            external "src/jobs/**/*.groovy"
            removeAction('DELETE')
            removeViewAction('DELETE')
            ignoreExisting(false)
        }
    }
}
