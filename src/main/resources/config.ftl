<?xml version='1.0' encoding='UTF-8'?>
<maven2-moduleset plugin="maven-plugin@2.13">
    <actions/>
    <description></description>
    <keepDependencies>false</keepDependencies>
    <properties/>
    <scm class="hudson.scm.SubversionSCM" plugin="subversion@2.6">
        <locations>
            <hudson.scm.SubversionSCM_-ModuleLocation>
                <remote>${svnUrl}</remote>
                <credentialsId>afe709d5-6a97-421e-867d-a0b439364482</credentialsId>
                <local>${svnUrlend}</local>
                <depthOption>infinity</depthOption>
                <ignoreExternalsOption>true</ignoreExternalsOption>
            </hudson.scm.SubversionSCM_-ModuleLocation>
        </locations>
        <excludedRegions></excludedRegions>
        <includedRegions></includedRegions>
        <excludedUsers></excludedUsers>
        <excludedRevprop></excludedRevprop>
        <excludedCommitMessages></excludedCommitMessages>
        <workspaceUpdater class="hudson.scm.subversion.UpdateUpdater"/>
        <ignoreDirPropChanges>false</ignoreDirPropChanges>
        <filterChangelog>false</filterChangelog>
    </scm>
    <canRoam>true</canRoam>
    <disabled>false</disabled>
    <blockBuildWhenDownstreamBuilding>false</blockBuildWhenDownstreamBuilding>
    <blockBuildWhenUpstreamBuilding>false</blockBuildWhenUpstreamBuilding>
    <triggers/>
    <concurrentBuild>false</concurrentBuild>
    <rootModule>
        <groupId>${groupId}</groupId>
        <artifactId>${artifactId}</artifactId>
    </rootModule>
    <goals>clean package -U -DskipTests=true</goals>
    <aggregatorStyleBuild>true</aggregatorStyleBuild>
    <incrementalBuild>false</incrementalBuild>
    <ignoreUpstremChanges>true</ignoreUpstremChanges>
    <ignoreUnsuccessfulUpstreams>false</ignoreUnsuccessfulUpstreams>
    <archivingDisabled>false</archivingDisabled>
    <siteArchivingDisabled>false</siteArchivingDisabled>
    <fingerprintingDisabled>false</fingerprintingDisabled>
    <resolveDependencies>false</resolveDependencies>
    <processPlugins>false</processPlugins>
    <mavenValidationLevel>-1</mavenValidationLevel>
    <runHeadless>false</runHeadless>
    <disableTriggerDownstreamProjects>false</disableTriggerDownstreamProjects>
    <blockTriggerWhenBuilding>true</blockTriggerWhenBuilding>
    <settings class="jenkins.mvn.DefaultSettingsProvider"/>
    <globalSettings class="jenkins.mvn.DefaultGlobalSettingsProvider"/>
    <reporters/>
    <publishers>
        <hudson.plugins.deploy.DeployPublisher plugin="deploy@1.10">
            <adapters>
                <hudson.plugins.deploy.tomcat.Tomcat7xAdapter>
                    <userName>admin</userName>
                    <passwordScrambled>YWRtaW4=</passwordScrambled>
                    <url>http://127.0.0.1:${port?c}/</url>
                </hudson.plugins.deploy.tomcat.Tomcat7xAdapter>
            </adapters>
            <contextPath>/</contextPath>
            <war>**/ROOT.war</war>
            <onFailure>false</onFailure>
        </hudson.plugins.deploy.DeployPublisher>
    </publishers>
    <buildWrappers/>
    <prebuilders/>
    <postbuilders/>
    <runPostStepsIfResult>
        <name>FAILURE</name>
        <ordinal>2</ordinal>
        <color>RED</color>
        <completeBuild>true</completeBuild>
    </runPostStepsIfResult>
</maven2-moduleset>