package dev.trabalhopicpay.entitys.user;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.math.BigDecimal;

// Entidade de usuário
@Entity(name = "users")
@Table(name = "tb_users")
public class User extends PanacheEntityBase {

    // Chave primária usuário
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    // Primeiro nome
    public String firstName;

    // Sobrenome
    public String lastName;

    // CPF do usuário (único)
    @Column(unique = true)
    public String  document;

    // E-mail do usuário (único)
    @Column(unique = true)
    public String email;

    // Senha
    public String password;

    // Saldo disponível
    public BigDecimal balance;

    // Tipo de Usuário (COMMON para comum ou MERCHANT para lojista)
    @Enumerated(EnumType.STRING)
    public UserType userType;

}
