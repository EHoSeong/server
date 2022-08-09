package com.example.server.controller;

import com.example.server.dto.User;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequestMapping("/api/server")
public class ServerApiController {

    @GetMapping("/naver")
    public String naver(){
        String query = "갈비집";
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/local.json")
                .queryParam("query","중국집")
                .queryParam("display",10)
                .queryParam("start",1)
                .queryParam("sort","random")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id","6tM_zrcTiX3VDr53khFI")
                .header("X-Naver-Client-Secret","6lircOB2te")
                .build();
        ResponseEntity<String> result = restTemplate.exchange(req,String.class);

        return result.getBody();
    }
    @GetMapping("/hello")
    public User hello(){
        User user = new User();
        user.setName("steve");
        user.setAge(10);
        return user;
    }
}
