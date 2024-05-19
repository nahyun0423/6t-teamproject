package com.springbackend.clova;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbackend.parsing.ApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

@Service
public class ClovaBasicService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    @Autowired
    public ClovaBasicService(WebClient webClient) {
        this.webClient = webClient;
        this.objectMapper = new ObjectMapper();
    }

    //api 부분
    public Mono<String> getCompletion(String data) {
        return webClient.post()
                .uri("https://clovastudio.stream.ntruss.com/testapp/v1/chat-completions/HCX-003")
                .header("X-NCP-CLOVASTUDIO-API-KEY", "NTA0MjU2MWZlZTcxNDJiY/bEfwpnZM0oOSzn0mYVKIkfhLLCWaDuoNPeCXVrFfK/")
                .header("X-NCP-APIGW-API-KEY", "fcV1oaDqNuVmEwluCqce5nROIUj0O1gK8vOWWqRA")
                .header("X-NCP-CLOVASTUDIO-REQUEST-ID", "905ee286-20db-43a2-8371-9a0dce56bdd9")
                .header("Content-Type","application/json")
                .header("Accept","")
                .bodyValue(buildRequestData(data))
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(30))
                .flatMap(this::formatResponse);
    }

    //clova response 데이터(json)를 파싱 후 content만 추출
    private Mono<String> formatResponse(String jsonResponse) {
        try {
            ApiResponseDTO response = objectMapper.readValue(jsonResponse, ApiResponseDTO.class);
            ApiResponseDTO.Message message = response.getResult().getMessage();
            //String formattedContent = message.getContent().replace("\n", "<br>"); // 줄바꿈 문자를 <br>로 변환
            return Mono.just(message.getContent());
           // return Mono.just(formattedContent);
        } catch (IOException e) {
            return Mono.error(new RuntimeException("Error parsing JSON response", e));
        }
    }

    //clova request 데이터
    private Map<String, Object> buildRequestData(String userQuery) {
        return Map.of(
                "topK", 0,
                "topP", 0.8,
                "maxTokens", 300,
                "temperature", 0.5,
                "repeatPenalty", 5.0,
                "stopBefore", List.of(),
                "messages", List.of(
                        Map.of("role","system","content","-사용자의 요청에 맞는 적절한 운동 루틴을 추천합니다 -운동 루틴은 4~6개 사이의 운동으로 구성됩니다. -운동 루틴은 운동이름 ,해당 기구의 세팅할 중량 , 세트수 , 1세트당 반복횟수만으로 이루어져 있습니다. " +"사용자가 원하는 부위의 운동만을 추천합니다 -다른 설명 없이 운동 루틴만 출력합니다. -운동 루틴 출력시 해당 값의 의미를 설명하지 말고 값만 출력합니다."),
                        Map.of("role", "user", "content", userQuery)
                )
        );
    }

}

