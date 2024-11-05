package dev.trabalhopicpay.services;

import dev.trabalhopicpay.entitys.user.User;
import dev.trabalhopicpay.entitys.user.UserType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
public class UserService {

    // Lista de usuários
    public List<User> listUsers() {
        return User.listAll();
    }

    @Transactional
    public User saveUser(User user) {
        user.persist();
        return user;
    }

    public void validadeTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.userType == UserType.MERCHANT) {
            throw new Exception("Lojistas não podem realizar transferências.");
        }
        if (sender.balance.compareTo(amount) < 0) {
            throw new Exception("Não possui saldo suficiente.");
        }
    }

    public User findUserSenderById(Long id) throws Exception {
        if (User.findById(id) == null) {
            throw new Exception("Usuário remetente não encontrado");
        }
        return User.findById(id);
    }

    public User findUserReceiverById(Long id) throws Exception {
        if (User.findById(id) == null) {
            throw new Exception("Usuário destinatário não encontrado");
        }
        return User.findById(id);
    }
}
