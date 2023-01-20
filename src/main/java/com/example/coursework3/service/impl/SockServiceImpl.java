package com.example.coursework3.service.impl;

import com.example.coursework3.dto.SockRequest;
import com.example.coursework3.exception.NotEnoughSockQuantityException;
import com.example.coursework3.exception.NotWrongSockRequestException;
import com.example.coursework3.model.Color;
import com.example.coursework3.model.Size;
import com.example.coursework3.model.Sock;
import com.example.coursework3.service.SockService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SockServiceImpl implements SockService {

    private final Map<Sock, Long> socks = new HashMap<>();

    @Override
    public void  addSock(SockRequest sockRequest) {
        validateRequest(sockRequest);
        Sock sock = mapToSock(sockRequest);
        if (this.socks.containsKey(sock)) {
            this.socks.put(sock, this.socks.get(sock) + sockRequest.getQuantity());
        } else {
            this.socks.put(sock, sockRequest.getQuantity());
        }
    }

    @Override
    public void issueSock(SockRequest sockRequest) {
        decreaseSockQuantity(sockRequest);
    }

    @Override
    public void deleteSock(SockRequest sockRequest) {
        decreaseSockQuantity(sockRequest);
    }

    private void decreaseSockQuantity(SockRequest sockRequest) {
        validateRequest(sockRequest);
        Sock sock = mapToSock(sockRequest);
        long sockQuantity = this.socks.getOrDefault(sock, 0L);
        if (sockQuantity >= sockRequest.getQuantity()) {
            this.socks.put(sock, sockQuantity - sockRequest.getQuantity());
        } else {
            throw new NotEnoughSockQuantityException("носков больше нет");
        }
    }

    @Override
    public int getSockQuantity(Color color, Size size, Integer cottonMin, Integer cottonMax) {
        int total = 0;
        for (Map.Entry<Sock, Long> entry : socks.entrySet()) {
            if (color != null && !entry.getKey().getColor().equals(color)) {
                continue;
            }
            if (size != null && !entry.getKey().getSize().equals(size)) {
                continue;
            }
            if (cottonMin != null && entry.getKey().getCottonPart() < cottonMin) {
                continue;
            }
            if (cottonMax != null && entry.getKey().getCottonPart() > cottonMax) {
                continue;
            }
            total += entry.getValue();
        }
        return total;
    }

    private void validateRequest(SockRequest sockRequest) {
        if (sockRequest.getColor() == null || sockRequest.getSize() == null) {
            throw new NotWrongSockRequestException("все поля должны быть заполнены");
        }
        if (sockRequest.getCottonPart() < 0 || sockRequest.getCottonPart() > 100) {
            throw new NotWrongSockRequestException("содержание хлопка должно быть от 0 до 100");
        }
        if (sockRequest.getQuantity() <= 0) {
            throw new NotWrongSockRequestException("число должно быть больше 0");
        }
    }

    private Sock mapToSock(SockRequest sockRequest) {
        return new Sock(sockRequest.getColor(), sockRequest.getSize(), sockRequest.getCottonPart());
    }

}
