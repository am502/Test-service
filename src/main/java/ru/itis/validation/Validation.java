package ru.itis.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ru.itis.dto.HorseDto;
import ru.itis.web.utils.IncorrectDataException;

@Component
public class Validation {

    public void verifyHorseDto(HorseDto horseDto) {
        if (horseDto.getWidth() < 1) {
            throw new IncorrectDataException("width", "Incorrect width");
        }
        if (horseDto.getHeight() < 1) {
            throw new IncorrectDataException("height", "Incorrect height");
        }
        if (StringUtils.isEmpty(horseDto.getStart())) {
            throw new IncorrectDataException("start", "Incorrect start position");
        }
        if (StringUtils.isEmpty(horseDto.getEnd())) {
            throw new IncorrectDataException("end", "Incorrect end position");
        }
    }

    public void verifyBounds(int position, int bound) {
        if (position < 0 || position >= bound) {
            throw new IncorrectDataException("position", "Position out of bounds");
        }
    }
}
