package com.capstonedesign.backend.controller;

import com.capstonedesign.backend.domain.translate.dto.request.TranslateRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@Api(tags = "번역 관련 API")
public class TranslateController {

  @PostMapping("/translate")
  @ApiOperation(value = "번역 API", notes = "출발 언어, 도착 언어 및  번역할 텍스트를 요청 파라미터로 하여 번역")
  public String translate(@RequestBody TranslateRequest request) {

    String clientId = "Client ID"; // Client ID 및 Client Secret은 자신의 인증 정보를 발급 후 수정
    String clientSecret = "Client Secret";
    String text = request.getText();
    String source = request.getSource();
    String target = request.getTarget();

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.add("X-NCP-APIGW-API-KEY-ID", clientId);
    headers.add("X-NCP-APIGW-API-KEY", clientSecret);

    String encodedText = URLEncoder.encode(text, StandardCharsets.UTF_8);
    String body = "source=" + source + "&target=" + target + "&text=" + encodedText;

    HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
    ResponseEntity<String> responseEntity = restTemplate.exchange(
        "https://naveropenapi.apigw.ntruss.com/nmt/v1/translation",
        HttpMethod.POST,
        requestEntity,
        String.class
    );

    if (responseEntity.getStatusCode() == HttpStatus.OK) {
      return responseEntity.getBody();
    } else {
      return "Error occurred!";
    }
  }
}
