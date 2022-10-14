package heykakao.HeyForm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity @Getter @Setter @NoArgsConstructor @ToString()
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long id;

    @Column(name = "userAccount")
    private String account;
}

// 토큰, id, 닉네임...
