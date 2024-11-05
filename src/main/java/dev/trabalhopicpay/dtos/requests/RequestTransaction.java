package dev.trabalhopicpay.dtos.requests;

import java.math.BigDecimal;

// Objeto request da requisição que solicita a transferência
public class RequestTransaction {

    public Long senderId;
    public Long receiverId;
    public BigDecimal amount;

}
