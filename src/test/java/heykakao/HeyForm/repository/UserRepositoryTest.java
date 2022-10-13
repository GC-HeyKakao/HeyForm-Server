package heykakao.HeyForm.repository;

import heykakao.HeyForm.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    void userRepsTest() {
        User user = new User();
        user.setId("abcd");
        userRepository.save(user);

        User user2 = new User();
        user2.setId("efgabcd");
        userRepository.save(user2);

        List<User> um_list = userRepository.findAll();
        Iterator<User> um_iter = um_list.iterator();

        int i = 0;
        while(um_iter.hasNext()){
            System.out.println(um_iter.next());
            i++;
        }

        assertThat(i).isEqualTo(2);
    }
}