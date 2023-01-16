package com.example.coursework3.service;

import com.example.coursework3.dto.SockRequest;
import com.example.coursework3.model.Color;
import com.example.coursework3.model.Size;

public interface SockService {

    void addSock(SockRequest sockRequest);

    void issueSock(SockRequest sockRequest);

    int getSockQuantity(Color color, Size size, Integer cottonMin, Integer cottonMax);

    void deleteSock(SockRequest sockRequest);
}

