package com.springbackend.clova;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbackend.parsing.ApiResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ClovaCustomService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public ClovaCustomService(WebClient webClient) {
        this.webClient = webClient;
        this.objectMapper = new ObjectMapper();
    }

    //api 부분
    public Mono<String> getCompletion(String userQuery) {
        return webClient.post()
                .uri("https://clovastudio.stream.ntruss.com/testapp/v1/tasks/ef9swe3v/chat-completions")
                .header("X-NCP-CLOVASTUDIO-API-KEY", "NTA0MjU2MWZlZTcxNDJiY/"
                        +"bEfwpnZM0oOSzn0mYVKIkfhLLCWaDuoNPeCXVrFfK/")
                .header("X-NCP-APIGW-API-KEY", "fcV1oaDqNuVmEwluCqce5nROIUj0O1gK8vOWWqRA")
                .header("X-NCP-CLOVASTUDIO-REQUEST-ID", "1ebeea9d-94d6-4486-ada4-f2b3e274e75d")
                .header("Content-Type","application/json")
                .header("Accept","")
                .bodyValue(buildRequestData(userQuery))
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(this::formatResponse);
    }
    private Map<String, Object> buildRequestData(String userQuery) {
        return Map.of(
                "topK", 0,
                "topP", 0.8,
                "maxTokens", 800,
                "temperature", 0.5,
                "repeatPenalty", 5.0,
                "stopBefore", List.of(),
                "messages", List.of(
                        Map.of("role","system","content","-사용자의 요청에 맞는 적절한 운동 루틴을 추천합니다\n" +
                                "-운동 루틴은 운동이름,해당 기구의 세팅할 중량,세트수,1세트당 반복횟수로 이루어져 있는 운동세트의 5개의 집합입니다. " +
                                "출력 형식도 이와 동일하게만 출력합니다.\n" +
                                "운동세트 출력시 전후에 다른 내용을 붙히지 않고 운동세트만을 출력하고, 루틴의 각 값 사이에는 쉼표를 붙힙니다.\n" +
                                "벤치프레스,70kg,3세트,10회  ->출력 예시입니다." +
                                "한개의 운동세트가 모두 출력 된 후에는 줄바꿈을 한 후 출력합니다.\n" +
                                "만일 맨몸운동이여서 기구의 세팅할 중량이 없으면 0kg으로 출력합니다." +
                                "-운동 목록을 출력할 때는 다른 설명 없이 운동 루틴만 출력합니다.\n" +
                                "-운동 루틴은 다음 목록 안에 있는 것 중에서만 선택합니다. "),
                        Map.of("role", "user", "content", userQuery)
                )
        );
    }

    //clova response 데이터(json)를 파싱 후 content만 추출
    private Mono<String> formatResponse(String jsonResponse) {
        try {
            ApiResponseDTO response = objectMapper.readValue(jsonResponse, ApiResponseDTO.class);
            ApiResponseDTO.Message message = response.getResult().getMessage();
            //String formattedContent = message.getContent().replace("\n", "<br>"); // 줄바꿈 문자를 <br>로 변환
            return Mono.just(message.getContent());
            //return Mono.just(formattedContent);
        } catch (IOException e) {
            return Mono.error(new RuntimeException("Error parsing JSON response", e));
        }
    }

    //clova request 데이터


}