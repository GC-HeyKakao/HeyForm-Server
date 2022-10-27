package heykakao.HeyForm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import heykakao.HeyForm.model.dto.UserDto;
import lombok.*;

import javax.persistence.*;

@Entity @Getter @NoArgsConstructor @ToString @Setter
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_account")
    private String account;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_email")
    private String email;

    @Column(name = "age_range")
    private Long age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "token")
    private String token;

    public User(String account, String user_name, String user_email, Long age_range, String gender){
        this.account = account;
        this.name = user_name;
        this.email = user_email;
        this.age =age_range;
        this.gender = gender;
    }
//    public void setByUserAccount(String userAccount) {this.account = userAccount;}
//    public void setByDto(UserDto userDto) {
//        this.account = userDto.getAccount();
//    }
}

// 토큰, id, 닉네임...
