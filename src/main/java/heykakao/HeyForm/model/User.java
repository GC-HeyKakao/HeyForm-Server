package heykakao.HeyForm.model;

import lombok.Data;

import javax.persistence.*;

@Entity @Data
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private Long id;

    @Column(name = "userAccount")
    private String account;
}

// 토큰, id, 닉네임...
