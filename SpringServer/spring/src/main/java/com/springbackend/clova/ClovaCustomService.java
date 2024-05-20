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
                                "-사용자는 다음 형식으로 질문합니다:\n" +
                                "1. 자신의 신체 정보 2. 운동에서 제외했으면 하는 부위 3. 운동 목적 4. 주로 운동했으면 하는 부위 5. 운동 환경 및 장소 6. 운동 개수\n" +
                                "-사용자가 운동에서 제외했으면 하는 부위에 해당하는 운동은 추천하지 않습니다.\n" +
                                "-사용자가 주로 운동했으면 하는 부위에 해당하는 운동은 1개 이상 추천합니다.\n" +
                                "-환경 및 장소가 헬스장이면 바벨이나 기구를 사용하는 운동 위주로 추천하고 집이면 기구나 도구 없이 운동할 수 있는 운동을 추천합니다.\n" +
                                "-운동 루틴은 운동이름, 해당 기구의 세팅할 중량, 세트수, 1세트당 반복횟수로 이루어져 있습니다.\n" +
                                "-운동 루틴은 3개~5개의 운동이 포함되어 있습니다.\n" +
                                "-운동 루틴은 다음 목록 안에 있는 것 중에서만 선택합니다.\n" +
                                "- 등 부위: 풀업, 랫 풀 다운, 시티드 로우, 데드리프트, 원 암 덤벨 로우\n" +
                                "- 가슴 부위: 벤치프레스, 덤벨 플라이, 케이블 크로스 오버, 체스트프레스, 팔굽혀펴기\n" +
                                "- 팔 부위: 덤벨 컬, 트라이셉스 푸시 다운, 오버헤드 케이블 컬, 딥스, 팔굽혀펴기, 벤치프레스\n" +
                                "- 어깨: 오버헤드 프레스, 스미스 머신 슈러그, 케틀벨 스트릭 프레스, 밴드 오버헤드 프레스, 파이크 푸쉬업\n" +
                                "- 하체: 스쿼트, 런지, 힙 어브덕션, 레그 익스텐션, 레그 프레스\n" +
                                "- 복근: 플랭크, 디클라인 크런치, 행잉 레그 레이즈, 시티드 니 업, 바이시클 크런치\n" +
                                "- json 파일 형식에 대해 잘 알고있습니다.\n" +
                                "- 출력 데이터는 아래 형식으로 출력합니다:\n" +
                                "{\n" +
                                "  \"userId\": \"dbtls\""+
                                "  \"routineName\": \"상체운동2\""+
                                "  \"routineDetails\": [\n" +
                                "    {\n" +
                                "      \"exerciseName\": \"값1-1\",\n" +
                                "      \"weight\": 값1-2,\n" +
                                "      \"sets\": 값1-3,\n" +
                                "      \"reps\": 값1-4\n" +
                                "    },\n" +
                                "    {\n" +
                                "      \"exerciseName\": \"값2-1\",\n" +
                                "      \"weight\": 값2-2,\n" +
                                "      \"sets\": 값2-3,\n" +
                                "      \"reps\": 값2-4\n" +
                                "    }\n" +
                                "  ]\n" +
                                "}\n" +
                                "만약 맨몸운동이라면, weight는 0으로 출력합니다."
                        ),
                        Map.of("role", "user", "content", userQuery)
                )
        );
    }

    //clova response 데이터(json)를 파싱 후 content만 추출
    private Mono<String> formatResponse(String jsonResponse) {
        try {
            ApiResponseDTO response = objectMapper.readValue(jsonResponse, ApiResponseDTO.class);
            ApiResponseDTO.Message message = response.getResult().getMessage();
            //return Mono.just(message.getContent());
            return Mono.just(message.getContent());
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