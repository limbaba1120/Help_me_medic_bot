package com.capstonedesign.backend.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
public class ChatController {

    private static final String secretKey = "S2hFQnJtYVhQUXVZeUZQeGFXUXBUY0pMY1RwcWprV08=";
    private static final String apiUrl = "https://9klnwopt8e.apigw.ntruss.com/custom/v1/10307/f0032dbc440d074bb96f85880cabc76b1b76b1cb1beee6e5ac977104e4c73457";

    @GetMapping("chat")
    public String chat() {
        return "chat";
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/public")
    public String sendMessage(@Payload String chatMessage) throws IOException {

        // 언어 감지를 통해 얻은 Json 언어 코드 파싱
        String detectLangsString = detectLangs(chatMessage);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(detectLangsString);
        String langCode = jsonNode.get("langCode").asText();

        String translatedMessage1 = chatMessage;
        try {
            if (!Objects.equals(langCode, "ko")) {
                translatedMessage1 = extract(translate(langCode, "ko", chatMessage));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        URL url = new URL(apiUrl);
        String message =  getReqMessage(translatedMessage1);
        String encodeBase64String = makeSignature(message, secretKey);

        //api서버 접속 (서버 -> 서버 통신)
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json;UTF-8");
        con.setRequestProperty("X-NCP-CHATBOT_SIGNATURE", encodeBase64String);

        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());

        wr.write(message.getBytes(StandardCharsets.UTF_8));
        wr.flush();
        wr.close();
        int responseCode = con.getResponseCode();

        BufferedReader br;

        if(responseCode==200) { // 정상 호출

            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    con.getInputStream(), StandardCharsets.UTF_8));
            String decodedString;
            String jsonString = "";
            while ((decodedString = in.readLine()) != null) {
                jsonString = decodedString;
            }

            //받아온 값을 세팅하는 부분
            JSONParser jsonparser = new JSONParser();
            try {
                JSONObject json = (JSONObject) jsonparser.parse(jsonString);
                JSONArray bubblesArray = (JSONArray)json.get("bubbles");
                JSONObject bubbles = (JSONObject)bubblesArray.get(0);
                JSONObject data = (JSONObject)bubbles.get("data");
                String description = "";
                description = (String)data.get("description");
                chatMessage = description;
            } catch (Exception e) {
                System.out.println("error");
                e.printStackTrace();
            }

            in.close();
        } else {  // 에러 발생
            chatMessage = con.getResponseMessage();
        }

        try {
            if (!Objects.equals(langCode, "ko")) {
                chatMessage = extract(translate("ko", langCode, chatMessage));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return chatMessage;
    }

    //보낼 메세지를 네이버에서 제공해준 암호화로 변경해주는 메소드
    public static String makeSignature(String message, String secretKey) {

        String encodeBase64String = "";

        try {
            byte[] secrete_key_bytes = secretKey.getBytes(StandardCharsets.UTF_8);

            SecretKeySpec signingKey = new SecretKeySpec(secrete_key_bytes, "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);

            byte[] rawHmac = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
            encodeBase64String = Base64.encodeBase64String(rawHmac);

            return encodeBase64String;

        } catch (Exception e){
            System.out.println(e);
        }

        return encodeBase64String;

    }

    //보낼 메세지를 네이버 챗봇에 포맷으로 변경해주는 메소드
    public static String getReqMessage(String voiceMessage) {

        String requestBody = "";

        try {

            JSONObject obj = new JSONObject();

            long timestamp = new Date().getTime();

            System.out.println("##"+timestamp);

            obj.put("version", "v2");
            obj.put("userId", "U47b00b58c90f8e47428af8b7bddc1231heo2");
            obj.put("timestamp", timestamp);

            JSONObject bubbles_obj = new JSONObject();

            bubbles_obj.put("type", "text");

            JSONObject data_obj = new JSONObject();
            data_obj.put("description", voiceMessage);

            bubbles_obj.put("type", "text");
            bubbles_obj.put("data", data_obj);

            JSONArray bubbles_array = new JSONArray();
            bubbles_array.add(bubbles_obj);

            obj.put("bubbles", bubbles_array);
            obj.put("event", "send");

            requestBody = obj.toString();

        } catch (Exception e){
            System.out.println("## Exception : " + e);
        }

        return requestBody;

    }

    /**
     * 파파고 번역 메소드
     */
    public String translate(String source, String target, String text) {

        String clientId = "argmirrr8o"; // Client ID 및 Client Secret은 자신의 인증 정보를 발급 후 수정
        String clientSecret = "Rt2hZzVphe0TG2r9iK4ByuODsxbvBLmqfQJzaFiM";

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

    // Json 응답으로부터 번역된 텍스트를 추출
    public String extract(String message) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(message);
        JsonNode translatedTextNode = rootNode.path("message").path("result").path("translatedText");

        return translatedTextNode.asText();
    }

    /**
     * 언어 감지 메소드
     */
    public String detectLangs(String input) {
        String clientId = "argmirrr8o"; //애플리케이션 클라이언트 아이디값";
        String clientSecret = "Rt2hZzVphe0TG2r9iK4ByuODsxbvBLmqfQJzaFiM"; //애플리케이션 클라이언트 시크릿값";

        String query;
        query = URLEncoder.encode(input, StandardCharsets.UTF_8);
        String apiURL = "https://naveropenapi.apigw.ntruss.com/langs/v1/dect";

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-NCP-APIGW-API-KEY-ID", clientId);
        requestHeaders.put("X-NCP-APIGW-API-KEY", clientSecret);

        return post(apiURL, requestHeaders, query);
    }

    private static String post(String apiUrl, Map<String, String> requestHeaders, String text){
        HttpURLConnection con = connect(apiUrl);
        String postParams =  "query="  + text; //원본언어: 한국어 (ko) -> 목적언어: 영어 (en)
        try {
            con.setRequestMethod("POST");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postParams.getBytes());
                wr.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
                return readBody(con.getInputStream());
            } else {  // 에러 응답
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}