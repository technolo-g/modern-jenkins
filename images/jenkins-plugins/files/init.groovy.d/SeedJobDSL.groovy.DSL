// Important note: Thanks to the init scripts this folder should end up being created first, then used elsewhere.
// If not then file name order may cause trouble, despite the Job DSL docs suggesting folders will go first
folder('GitOpsUtility') {
  description("This folder contains automatically generated seed & utility jobs")
}

job('GitOpsUtility/SeedJobUtilityDSL') {
    description('Seed Job to create DSL repo-provided utility jobs')
    label("master")
    scm {
        git {
            remote {
                url(System.getenv("GITOPS_DSL_REPO"))
            }
            branch("master")
        }
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
