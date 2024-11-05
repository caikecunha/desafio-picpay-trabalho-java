package dev.trabalhopicpay.entitys.transaction;

import dev.trabalhopicpay.entitys.user.User;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transactions")
@Table(name = "tb_transactions")
public class Transaction extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    // Valor a ser transferido
    public BigDecimal amount;

    // Notificação do usuário que está a enviar
    public String notifySender;

    // Notificação do usuário que está a receber
    public String notifyReceiver;

    // Usuário que envia
    @ManyToOne
    @JoinColumn(name = "sender_id")
    public User sender;

    // Usuário que recebe
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    public User receiver;

    // Timestamp da transferência
    @CreationTimestamp
    public LocalDateTime timestamp;
}
