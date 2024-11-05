package dev.trabalhopicpay.entitys.user;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.math.BigDecimal;

// Entidade Usuário na modelagem do banco de dados
@Entity(name = "users")
@Table(name = "tb_users")
public class User extends PanacheEntityBase {

    // Id do Usuário (Chave primária)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    // Primeiro nome do Usuário
    public String firstName;

    // Sobrenome do Usuário
    public String lastName;

    // CPF do Usuário
    // O registro deve ser único
    @Column(unique = true)
    public String  document;

    // E-mail do Usuário
    // O registro deve ser único
    @Column(unique = true)
    public String email;

    // Senha do Usuário
    public String password;

    // Saldo do Usuário
    public BigDecimal balance;

    // Tipo de Usuário
    // Enum de String:
    // COMMON para usuário comum
    // MERCHANT para logista
    @Enumerated(EnumType.STRING)
    public UserType userType;

}
