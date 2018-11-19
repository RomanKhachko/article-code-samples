package com.github.romankhachko.articleCodeSamples;

import com.github.romankhachko.articleCodeSamples.sonar.ProjectStatus;
import com.github.romankhachko.articleCodeSamples.sonar.SonarClient;
import com.github.romankhachko.articleCodeSamples.sonar.SonarClientException;
import com.github.romankhachko.articleCodeSamples.sonar.SonarProject;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * Created by Roman Khachko.
 */
@Mojo(name = "buildBreaker", defaultPhase = LifecyclePhase.VERIFY)
public class BuildBreakerMojo extends AbstractMojo {

    @Parameter(property = "breakBuild")
    private boolean isBreakingRequired;

    @Parameter(defaultValue = "${project}", readonly = true)
    private MavenProject project;

    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Break the build: " + isBreakingRequired);

        if (isBreakingRequired) {
            ProjectStatus projectStatus;
            try {
                projectStatus = createSonarClient().getQualityGateResult(createSonarProject());
            } catch (SonarClientException e) {
                throw new MojoExecutionException(e.getMessage());
            }

            if (!"OK".equals(projectStatus.getStatus())) {
                throw new MojoFailureException(
                        "Sonar quality gate has not been passed. Please check sonar project for details");
            }
        }
    }

    protected SonarClient createSonarClient() {
        return new SonarClient();
    }

    private SonarProject createSonarProject() {
        return new SonarProject(project.getArtifactId(), project.getGroupId());
    }
}
