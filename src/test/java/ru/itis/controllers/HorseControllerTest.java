package ru.itis.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.itis.dto.HorseDto;
import ru.itis.service.HorseService;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HorseControllerTest {

    private MockMvc mvc;

    @InjectMocks
    private HorseController horseController = new HorseController();

    @Mock
    private HorseService horseService;

    private HorseDto correctHorseDto;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(horseController).build();

        correctHorseDto = HorseDto.builder()
                .width(10)
                .height(14)
                .start("b1")
                .end("a3")
                .build();

        doReturn(1).when(horseService).findShortestPath(correctHorseDto);
    }

    @Test
    public void findShortestPathCorrect() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hourse/rest/count?width=10&height=14&start=B1&end=A3")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }


    @Test
    public void findShortestPathIncorrect() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hourse/rest/count?width=&height=14&start=B1&end=A99"))
                .andExpect(status().isBadRequest());
    }
}
