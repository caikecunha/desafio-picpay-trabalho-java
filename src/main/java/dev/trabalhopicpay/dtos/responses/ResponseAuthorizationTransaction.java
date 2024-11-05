package dev.trabalhopicpay.dtos.responses;

// Objeto response da requisição que solicita autorização de transferência
// mock - AuthorizationTransaction
public class ResponseAuthorizationTransaction {

    public String status;
    public Data data;

    public static class Data {
        public boolean authorization;
    }
}
