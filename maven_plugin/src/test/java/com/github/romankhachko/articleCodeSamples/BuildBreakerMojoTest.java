package com.github.romankhachko.articleCodeSamples;

import com.github.romankhachko.articleCodeSamples.sonar.ProjectStatus;
import com.github.romankhachko.articleCodeSamples.sonar.SonarClient;
import com.github.romankhachko.articleCodeSamples.sonar.SonarClientException;
import com.github.romankhachko.articleCodeSamples.sonar.SonarProject;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Roman Khachko.
 */
public class BuildBreakerMojoTest extends BuildBreakerMojoTestPreparer {

    private BuildBreakerMojo buildBreakerMojoSpy;
    private SonarClient sonarClient;

    @Override
    public void setUp() {
        super.setUp();
        setInternalState(buildBreakerMojo, "isBreakingRequired", true);
        buildBreakerMojoSpy = spy(buildBreakerMojo);

        sonarClient = mock(SonarClient.class);
        doReturn(sonarClient).when(buildBreakerMojoSpy).createSonarClient();
    }

    @Test
    public void pluginExecutionShouldNotThrowAnyExceptionOnPassedQualityGate() throws Exception {
        executeWithReturnedStatus("OK");
    }

    @Test(expected = MojoFailureException.class)
    public void buildShouldBeBrokenOnFailedQualityGate()
            throws MojoFailureException, MojoExecutionException, SonarClientException {
        executeWithReturnedStatus("FAILED");
    }

    @Test(expected = MojoExecutionException.class)
    public void buildShouldBeBrokenOnClientException()
            throws SonarClientException, MojoFailureException, MojoExecutionException {
        when(sonarClient.getQualityGateResult(any(SonarProject.class)))
                .thenThrow(SonarClientException.class);
        buildBreakerMojoSpy.execute();
    }

    private void executeWithReturnedStatus(String status)
            throws SonarClientException, MojoExecutionException, MojoFailureException {
        ProjectStatus projectStatus = mock(ProjectStatus.class);
        when(projectStatus.getStatus()).thenReturn(status);
        when(sonarClient.getQualityGateResult(any(SonarProject.class))).thenReturn(projectStatus);
        buildBreakerMojoSpy.execute();
    }
}
