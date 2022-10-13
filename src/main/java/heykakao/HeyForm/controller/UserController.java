package heykakao.HeyForm.controller;

import heykakao.HeyForm.model.User;
import heykakao.HeyForm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ResponseBody
@RequiredArgsConstructor
public class UserController {

    UserRepository userRepository;

    @GetMapping("/user")
    public List<User> getAllUser(){
        return userRepository.findAll();
    }
}

