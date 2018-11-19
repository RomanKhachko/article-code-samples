package com.github.romankhachko.articleCodeSamples;

import org.apache.maven.project.MavenProject;
import org.junit.Before;

import java.lang.reflect.Field;
import java.util.Properties;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Roman Khachko
 */
public class BuildBreakerMojoTestPreparer {

    protected BuildBreakerMojo buildBreakerMojo;

    @Before
    public void setUp() {
        MavenProject project = mock(MavenProject.class);
        Properties props = mock(Properties.class);
        when(props.getProperty(anyString())).thenReturn(EMPTY);
        when(project.getProperties()).thenReturn(mock(Properties.class));
        when(project.getArtifactId()).thenReturn(EMPTY);
        when(project.getGroupId()).thenReturn(EMPTY);

        buildBreakerMojo = new BuildBreakerMojo();
        setInternalState(buildBreakerMojo, "project", project);
    }

    protected static void setInternalState(Object target, String field, Object value) {
        Class<?> c = target.getClass();
        try {
            Field f = c.getDeclaredField(field);
            f.setAccessible(true);
            f.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Unable to set internal state on a private field. ", e);
        }
    }

}
