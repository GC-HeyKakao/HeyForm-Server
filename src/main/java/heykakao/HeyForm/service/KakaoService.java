package heykakao.HeyForm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import heykakao.HeyForm.model.Survey;
import heykakao.HeyForm.model.User;
import heykakao.HeyForm.model.dto.UserDto;
import heykakao.HeyForm.repository.UserRepository;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Service
public class KakaoService {

    @Autowired
    UserRepository userRepository;


    public String getInfoByKakaoToken(String token){
        JWTService jwtService = new JWTService();

        String myTocken = "Bearer " + token;

        //헤더 객체 생성
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", myTocken);

        //요청 url
        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl("https://kapi.kakao.com/v2/user/me");

        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = null;
        RestTemplate restTemplate = new RestTemplate();
        //요청
        try {

            response = restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    entity,
                    String.class);


        } catch (HttpStatusCodeException e) {

            System.out.println("error :" + e);

        }
        System.out.println(response.getBody());
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(response.getBody());
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonElement kakao_account = jsonObject.get("kakao_account");

        System.out.println(kakao_account);
        jsonElement = jsonParser.parse(String.valueOf(kakao_account));
        jsonObject = jsonElement.getAsJsonObject();

        JsonElement nameElement = jsonParser.parse(String.valueOf(jsonObject.get("profile")));
        JsonObject nameObject = nameElement.getAsJsonObject();


        String name = "",email = "",age = "",gender = "";

        name = nameObject.get("nickname").getAsString();
        System.out.println(name);

        if (jsonObject.get("is_email_valid").getAsString() == "true"){
            email = jsonObject.get("email").getAsString();
            System.out.println(email);
        }
        if (jsonObject.get("has_age_range").getAsString() == "true"){
            String age_range = jsonObject.get("age_range").getAsString();
            if (age_range.equals("0~9") || age_range.equals("10~19")) {
                age = "10대 이하";
            }
            else if (age_range.equals("20~29")) {
                age = "20대";
            }
            else if (age_range.equals("30~39")) {
                age = "30대";
            }
            else if (age_range.equals("40~49")) {
                age = "40대";
            }
            else if (age_range.equals("50~59")) {
                age = "50대";
            }
            else {
                age = "60대 이상";
            }
            System.out.println(age);
        }
        if (jsonObject.get("has_gender").getAsString() == "true"){
            gender = jsonObject.get("gender").getAsString();
            System.out.println(gender);
        }

        if (userRepository.findByEmail(email).isPresent()){
            if (!jwtService.validateToken(userRepository.findByEmail(email).get().getToken())){
                return "expired";
            }
            else{
                return "ok";
            }
        }
        else{

            User user = new User();
            user.setAge(age);
            user.setName(name);
            user.setEmail(email);
            user.setGender(gender);
            user.setAccount(token);

            String jwtToken = jwtService.createToken(JWTService.SECRET_KEY, token);
            user.setToken(jwtToken);

            System.out.println(user);


            userRepository.save(user);
        }

        return response.getBody();
    }


}
