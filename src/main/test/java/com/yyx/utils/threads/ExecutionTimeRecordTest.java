package com.yyx.utils.threads;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExecutionTimeRecordTest {

    @Mock
    private ExecutionTimeRecord executionTimeRecord;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getExectionTime() {
        List mockList = mock(List.class);
       // when(mockList.contains(argThat(isValid()))).thenReturn("element")
    }

    @Test
    public void setExecutionTime() {
    }

    @Test
    public void removeCurThreadExecutionTimeRecord() {
    }
}
