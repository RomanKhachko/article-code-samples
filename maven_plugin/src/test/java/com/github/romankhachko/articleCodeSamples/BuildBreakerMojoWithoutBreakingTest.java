package com.github.romankhachko.articleCodeSamples;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.junit.Test;

/**
 * Created by Roman Khachko on 10/10/2018.
 */
public class BuildBreakerMojoWithoutBreakingTest extends BuildBreakerMojoTestPreparer {

    @Test
    public void pluginExecutionShouldNotThrowAnyExceptionWhenBreakingIsNotRequired()
            throws MojoFailureException, MojoExecutionException {
        buildBreakerMojo.execute();
    }

}
