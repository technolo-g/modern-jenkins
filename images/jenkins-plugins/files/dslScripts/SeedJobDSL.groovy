job('seed-job') {
    description('Seed Job to create other DSL-based jobs')
    scm {
        git("https://github.com/sheehan/job-dsl-gradle-example.git")
    }
    triggers {
        scm('H/15 * * * *')
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
