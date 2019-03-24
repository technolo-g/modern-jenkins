import hudson.model.Cause.UserIdCause
import hudson.model.FreeStyleProject
import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.plugin.JenkinsJobManagement
import jenkins.model.Jenkins

// Make the Job DSL integration optional. Fairly easy to come up with different ways to trigger it. Env vars are simple.
if (! System.getenv("GITOPS_DSL_REPO")) {
    println "No DSL repo defined, skipping DSL prep"
    return
}

// Create the utility seed job, so it can run and create various DSL-managed utility jobs from a target DSL repo
def jobDslScript = new File("/var/jenkins_home/init.groovy.d/SeedJobDSL.groovy.DSL")
def workspace = new File("/tmp/dsl")
def jobManagement = new JenkinsJobManagement(System.out, [:], workspace)
new DslScriptLoader(jobManagement).runScript(jobDslScript.text)

// Schedule the job to run so the jobs are created when Jenkins starts - or skip this for more control
Jenkins.instance.getItemByFullName('SeedJobUtilityDSL', FreeStyleProject.class).scheduleBuild(new UserIdCause())

// Secondary seed job used to demonstrate loading a file directly instead of a repository
def jobDslScript2 = new File("/var/jenkins_home/init.groovy.d/SeedJobDSL2.groovy.DSL")
def workspace2 = new File("/tmp/dsl")
def jobManagement2 = new JenkinsJobManagement(System.out, [:], workspace2)
new DslScriptLoader(jobManagement2).runScript(jobDslScript2.text)

// Schedule the job to run so the jobs are created when Jenkins starts.
Jenkins.instance.getItemByFullName('SeedJobInternal', FreeStyleProject.class).scheduleBuild(new UserIdCause())
