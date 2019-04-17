package ru.itis.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.itis.dto.HorseDto;
import ru.itis.service.impl.HorseServiceImpl;
import ru.itis.validation.Validation;
import ru.itis.web.utils.IncorrectDataException;

import static org.mockito.Mockito.doThrow;

public class HorseServiceTest {

    @InjectMocks
    private HorseService horseService = new HorseServiceImpl();

    @Mock
    private Validation validation;

    private HorseDto incorrectHorseDto;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        incorrectHorseDto = HorseDto.builder()
                .width(10)
                .height(10)
                .start("A20")
                .end("B3")
                .build();

        doThrow(IncorrectDataException.class).when(validation).verifyHorseDto(incorrectHorseDto);
    }

    @Test(expected = IncorrectDataException.class)
    public void findShortestPathIncorrect() throws Exception {
        horseService.findShortestPath(incorrectHorseDto);
    }
}
