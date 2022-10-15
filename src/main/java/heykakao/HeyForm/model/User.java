package heykakao.HeyForm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import heykakao.HeyForm.model.dto.UserDto;
import lombok.*;

import javax.persistence.*;

@Entity @Getter @NoArgsConstructor @ToString
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_account")
    private String account;

    public User(String account){
        this.account = account;
    }

    public void setByDto(UserDto userDto) {
        this.account = userDto.getAccount();
    }
}

// 토큰, id, 닉네임...
