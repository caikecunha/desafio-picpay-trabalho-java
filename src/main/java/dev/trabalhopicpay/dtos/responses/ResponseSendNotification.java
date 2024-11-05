package dev.trabalhopicpay.dtos.responses;

// Objeto response da requisição que envia notificação
// (mock - SendNotification)
public class ResponseSendNotification {

    public String status;
    public Data data;

    public static class Data {
        public boolean authorization;
    }
}
