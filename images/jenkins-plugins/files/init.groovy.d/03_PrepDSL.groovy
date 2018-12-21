import hudson.model.Cause.UserIdCause
import hudson.model.FreeStyleProject
import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.plugin.JenkinsJobManagement
import jenkins.model.Jenkins

def jobDslScript = new File("/var/jenkins_home/dslScripts/SeedJobDSL.groovy")
def workspace = new File("/tmp/dsl")
def jobManagement = new JenkinsJobManagement(System.out, [:], workspace)
new DslScriptLoader(jobManagement).runScript(jobDslScript.text)

// Schedule the job to run so the jobs are created when Jenkins starts.
Jenkins.instance.getItemByFullName('seed-job', FreeStyleProject.class).scheduleBuild(new UserIdCause())
