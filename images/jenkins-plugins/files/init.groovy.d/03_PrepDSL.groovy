import hudson.model.Cause.UserIdCause
import hudson.model.FreeStyleProject
import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.plugin.JenkinsJobManagement
import jenkins.model.Jenkins

// Make the Job DSL integration optional. Fairly easy to come up with different ways to trigger it. Env vars are simple.
if (! System.getenv("DSL_REPO")) {
    println "No DSL repo defined, skipping DSL prep"
    return
}

// Create the seed job itself, so it can run and create the "real" DSL-managed jobs
def jobDslScript = new File("/var/jenkins_home/init.groovy.d/SeedJobDSL.groovy.DSL")
def workspace = new File("/tmp/dsl")
def jobManagement = new JenkinsJobManagement(System.out, [:], workspace)
new DslScriptLoader(jobManagement).runScript(jobDslScript.text)

// Schedule the job to run so the jobs are created when Jenkins starts - or skip this for more control
Jenkins.instance.getItemByFullName('seed-job', FreeStyleProject.class).scheduleBuild(new UserIdCause())
