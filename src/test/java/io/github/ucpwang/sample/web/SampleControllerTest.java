package io.github.ucpwang.sample.web;

import io.github.ucpwang.sample.config.ApplicationTestConfig;
import io.github.ucpwang.sample.service.SampleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ApplicationTestConfig.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleControllerTest {

    @Mock
    private SampleService sampleService;

    @InjectMocks
    private SampleController sampleController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(sampleController).build();
    }

    @Test
    public void indexTest() throws Exception {

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(String.format("/sample")));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void listTest() throws Exception {

        when(sampleService.getList()).then((invocation) -> Collections.emptyList());

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(String.format("/sample/list")));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andDo(MockMvcResultHandlers.print());

        verify(sampleService).getList();
        verifyNoMoreInteractions(sampleService);

    }

}