package com.springbackend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbackend.parsing.ApiResponseDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class ClovaService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public ClovaService(WebClient webClient) {
        this.webClient = webClient;
        this.objectMapper = new ObjectMapper();
    }

    //api 부분
    public Mono<String> getCompletion(String userQuery) {
        return webClient.post()
                .uri("https://clovastudio.stream.ntruss.com/testapp/v1/chat-completions/HCX-003")
                .header("X-NCP-CLOVASTUDIO-API-KEY", "NTA0MjU2MWZlZTcxNDJiY/bEfwpnZM0oOSzn0mYVKIkfhLLCWaDuoNPeCXVrFfK/")
                .header("X-NCP-APIGW-API-KEY", "MBqGVTV8LYwW20beQ3DiCUijR7OCFWJ2oMREEygI")
                .header("X-NCP-CLOVASTUDIO-REQUEST-ID", "f4c0d72e-7e43-4cd1-bfc1-86eb085a1769")
                .bodyValue(buildRequestData(userQuery))
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(this::formatResponse);
    }

    //clova response 데이터(json)를 파싱 후 content만 추출
    private Mono<String> formatResponse(String jsonResponse) {
        try {
            ApiResponseDTO response = objectMapper.readValue(jsonResponse, ApiResponseDTO.class);
            ApiResponseDTO.Message message = response.getResult().getMessage();
            return Mono.just(message.getContent());
        } catch (IOException e) {
            return Mono.error(new RuntimeException("Error parsing JSON response", e));
        }
    }

    //clova request 데이터
    private Map<String, Object> buildRequestData(String userQuery) {
        return Map.of(
                "topK", 0,
                "topP", 0.8,
                "maxTokens", 256,
                "temperature", 0.5,
                "repeatPenalty", 5.0,
                "stopBefore", List.of(),
                "messages", List.of(
                        Map.of("role","system","content","-사용자의 요청에 맞는 적절한 운동 루틴을 추천합니다\n-운동 루틴은 4~6개 사이의 운동으로 구성됩니다.\n" +
                                "-운동 루틴은 운동이름 ,해당 기구의 세팅할 중량 , 세트수 , 1세트당 반복횟수로 이루어져 있습니다.\n" +
                                "-다른 설명 없이 운동 루틴만 출력합니다.\n -운동 루틴 출력시 해당 값의 의미를 설명하지 말고 값만 출력합니다. \n"),
                        Map.of("role", "user", "content", userQuery)
                )
        );
    }

}

