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

    // Valor da transferência
    public BigDecimal amount;

    // Notificação do usuário que envia:
    public String notifySender;

    // Notificação do usuário que recebe:
    public String notifyReceiver;

    // Usuário envia transferência
    @ManyToOne
    @JoinColumn(name = "sender_id")
    public User sender;

    // Usuário que recebe a transferência
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    public User receiver;

    // Timestamp da transferência
    @CreationTimestamp
    public LocalDateTime timestamp;
}
