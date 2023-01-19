package com.example.coursework3.controllers;

import com.example.coursework3.dto.SockRequest;
import com.example.coursework3.exception.NotEnoughSockQuantityException;
import com.example.coursework3.exception.NotWrongSockRequestException;
import com.example.coursework3.model.Color;
import com.example.coursework3.model.Size;
import com.example.coursework3.service.SockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sock")
public class SockController {
    private final SockService sockService;

    public SockController(SockService sockService) {
        this.sockService = sockService;
    }

    @ExceptionHandler(NotWrongSockRequestException.class)
    public ResponseEntity<String> handleInvalidException(NotWrongSockRequestException notWrongSockRequestException) {
        return ResponseEntity.badRequest().body(notWrongSockRequestException.getMessage());
    }

    @ExceptionHandler(NotEnoughSockQuantityException.class)
    public ResponseEntity<String> handleInvalidException(NotEnoughSockQuantityException notEnoughSockQuantityException) {
        return ResponseEntity.badRequest().body(notEnoughSockQuantityException.getMessage());
    }

    @PostMapping
    public void addSock(@RequestBody SockRequest sockRequest) {
        sockService.addSock(sockRequest);
    }

    @PutMapping
    public void issueSock(@RequestBody SockRequest sockRequest) {
        sockService.issueSock(sockRequest);
    }

    @GetMapping
    public int getSockCount(@RequestParam(required = false, name = "цвет") Color color,
                            @RequestParam(required = false, name = "размер") Size size,
                            @RequestParam(required = false, name = "хлопок мин") Integer cottonMin,
                            @RequestParam(required = false, name = "хлопок макс") Integer cottonMax) {
        return sockService.getSockQuantity(color, size, cottonMin, cottonMax);
    }

    @DeleteMapping
    public void deleteSock(@RequestBody SockRequest sockRequest) {
        sockService.deleteSock(sockRequest);
    }

}
