package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.HorseDto;
import ru.itis.service.impl.HorseServiceImpl;

@RestController
@RequestMapping("/hourse/rest")
public class HorseController {

    @Autowired
    private HorseServiceImpl horseService;

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public int findShortestPath(@RequestParam("width") int width,
                                @RequestParam("height") int height,
                                @RequestParam("start") String start,
                                @RequestParam("end") String end) {
        HorseDto horseDto = HorseDto.builder()
                .width(width)
                .height(height)
                .start(start.toLowerCase())
                .end(end.toLowerCase())
                .build();
        return horseService.findShortestPath(horseDto);
    }
}
