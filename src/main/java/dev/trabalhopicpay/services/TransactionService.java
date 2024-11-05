package dev.trabalhopicpay.services;

import dev.trabalhopicpay.dtos.requests.RequestSendNotification;
import dev.trabalhopicpay.dtos.requests.RequestTransaction;
import dev.trabalhopicpay.dtos.responses.ResponseSendNotification;
import dev.trabalhopicpay.entitys.transaction.Transaction;
import dev.trabalhopicpay.entitys.user.User;
import dev.trabalhopicpay.mocks.SendNotification.SendNotification;
import org.jboss.resteasy.reactive.ClientWebApplicationException;
import dev.trabalhopicpay.dtos.responses.ResponseAuthorizationTransaction;
import dev.trabalhopicpay.mocks.AuthorizationTransaction.AuthorizationTransaction;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.math.BigDecimal;

@ApplicationScoped
public class TransactionService {

    @Inject
    UserService userService;

    @Transactional
    public Transaction createTransaction(RequestTransaction requestTransaction) throws  Exception{

        // Validações da regra
        BigDecimal amount = requestTransaction.amount;
        User sender = userService.findUserSenderById(requestTransaction.senderId);
        User receiver = userService.findUserReceiverById(requestTransaction.receiverId);
        userService.validadeTransaction(sender, requestTransaction.amount);

        // Validação de autorização externa
        authorizeTransaction();

        // Vendo todas às autorizações
        sender.balance = sender.balance.subtract(requestTransaction.amount);
        receiver.balance = receiver.balance.add(requestTransaction.amount);

        // Continua a transferência:
        Transaction transaction = new Transaction();

        transaction.amount = amount;
        transaction.sender = sender;
        transaction.receiver = receiver;
        transaction.notifySender = sendNotificationEmail();
        transaction.notifyReceiver = sendNotificationEmail();

        userService.saveUser(sender);
        userService.saveUser(receiver);
        transaction.persist();

        return transaction;
    }

    @Inject
    @RestClient
    AuthorizationTransaction authorizationTransaction;

    public void authorizeTransaction() throws Exception {
        try {
            ResponseAuthorizationTransaction response = authorizationTransaction.authorize();
        } catch (ClientWebApplicationException err) {
            throw new Exception("A transferência foi recusada pelo sistema de autorização");
        }
    }

    @Inject
    @RestClient
    SendNotification sendNotification;

    public String sendNotificationEmail() {
        String notifyStatus;
        RequestSendNotification requestSendNotification = new RequestSendNotification();

        try {
            ResponseSendNotification response = sendNotification.sendNotification(requestSendNotification);
            return "Notificação enviada!";
        } catch (ClientWebApplicationException err) {
            return "Erro, falha ao enviar notificação";
        }
    }
}
