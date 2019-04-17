package ru.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dto.HorseDto;
import ru.itis.service.HorseService;
import ru.itis.validation.Validation;

import java.awt.*;
import java.util.Stack;

@Service
public class HorseServiceImpl implements HorseService {
    private final static int[] HORIZONTAL_DELTA = {2, 1, -1, -2, -2, -1, 1, 2};
    private final static int[] VERTICAL_DELTA = {-1, -2, -2, -1, 1, 2, 2, 1};

    private int min;

    @Autowired
    private Validation validation;

    public int findShortestPath(HorseDto horseDto) {
        validation.verifyHorseDto(horseDto);

        Point start = new Point(horseDto.getStart().charAt(0) - 97,
                Integer.valueOf(horseDto.getStart().split("\\D")[1]) - 1);
        Point end = new Point(horseDto.getEnd().charAt(0) - 97,
                Integer.valueOf(horseDto.getEnd().split("\\D")[1]) - 1);

        validation.verifyBounds(start.x, horseDto.getWidth());
        validation.verifyBounds(start.y, horseDto.getHeight());
        validation.verifyBounds(end.x, horseDto.getWidth());
        validation.verifyBounds(end.y, horseDto.getHeight());

        min = Integer.MAX_VALUE;

        int[][] board = new int[horseDto.getWidth()][horseDto.getHeight()];
        board[end.x][end.y] = 2;

        return horseRec(board, start, 0);
    }

    private int horseRec(int[][] board, Point currentPosition, int path) {
        if (path == min) {
            return path;
        }
        if (board[currentPosition.x][currentPosition.y] == 2) {
            if (path < min) {
                min = path;
            }
            return path;
        } else {
            board[currentPosition.x][currentPosition.y] = 1;
            Stack<Point> candidates = new Stack<>();
            for (int i = 0; i < HORIZONTAL_DELTA.length; i++) {
                Point candidate = new Point(currentPosition.x + HORIZONTAL_DELTA[i],
                        currentPosition.y + VERTICAL_DELTA[i]);
                if (checkPosition(candidate, board)) {
                    candidates.push(candidate);
                }
            }
            while (!candidates.isEmpty()) {
                horseRec(copyBoard(board), candidates.pop(), path + 1);

            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private boolean checkPosition(Point position, int[][] board) {
        return position.x >= 0 && position.x < board.length && position.y >= 0 && position.y < board[0].length
                && board[position.x][position.y] != 1;
    }

    private int[][] copyBoard(int[][] board) {
        int[][] copy = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            copy[i] = board[i].clone();
        }
        return copy;
    }

    public void setValidation(Validation validation) {
        this.validation = validation;
    }
}
