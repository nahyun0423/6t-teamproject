package com.springbackend.clova;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
                                "-운동세트의 각 값 사이에는 쉼표를 붙입니다." +
                                "-운동 이름을 제외하곤 띄어쓰기를 사용하지 않고 출력합니다." +
                                "-운동세트 사이에는 띄어쓰기 없이 / 를 붙여줍니다 \n" +
                                "-만일 맨몸운동이여서 기구의 세팅할 중량이 없으면 0kg으로 출력합니다." +
                                "-운동 루틴을 출력할 때는 다른 설명 없이 운동 세트만 출력합니다.\n"
                        ),
                        Map.of("role", "user", "content", userQuery)
                )
        );
    }

    //clova response 데이터(json)를 파싱 후 content만 추출하고 그것만 다시 json으로 변환
    private Mono<String> formatResponse(String jsonResponse) {
        try {
            ApiResponseDTO response = objectMapper.readValue(jsonResponse, ApiResponseDTO.class);
            ApiResponseDTO.Message message = response.getResult().getMessage();
            //return Mono.just(message.getContent());
            return Mono.just(convertToJSON(message.getContent()));
        } catch (IOException e) {
            return Mono.error(new RuntimeException("Error parsing JSON response", e));
        }
    }

    private String convertToJSON(String data) {
        String[] routines = data.split("/");
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();

        for (String routine : routines) {
            String[] details = routine.split(",");
            if (details.length == 4) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("exerciseName", details[0].trim());
                objectNode.put("weight", details[1].trim());
                objectNode.put("sets", details[2].trim().replaceAll("[^0-9]", "")); // 숫자만 추출
                objectNode.put("reps", details[3].trim().replaceAll("[^0-9]", "")); // 숫자만 추출
                arrayNode.add(objectNode);
            }
        }
        return arrayNode.toString();

        //clova request 데이터
    }


}