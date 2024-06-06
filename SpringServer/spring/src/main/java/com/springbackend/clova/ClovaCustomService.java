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

        Map<String, Object> stringObjectMap = buildRequestData(userQuery);
        System.out.println("stringObjectMap = " + stringObjectMap);
        return webClient.post()
                .uri("https://clovastudio.stream.ntruss.com/testapp/v1/tasks/iqskrs23/chat-completions")
                .header("X-NCP-CLOVASTUDIO-API-KEY", "NTA0MjU2MWZlZTcxNDJiY9gDVtcezKwC/+76yZeh1/v3/cpNkGD+PSxqVRvkJj2B")
                .header("X-NCP-APIGW-API-KEY", "69l7F5sx9BJEUa36wKr2oZ2VfYy4UC1AEXHyEzYp")
                .header("X-NCP-CLOVASTUDIO-REQUEST-ID", "31026603-06b8-4d18-8a77-fcd48badc951")
                .header("Content-Type","application/json")
                .header("Accept","")
                .bodyValue(stringObjectMap)
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(System.out::println)
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
                        Map.of("role","system","content","-사용자의 요청에 맞는 적절한 운동 루틴을 추천합니다. 운동은 4~7개 정도 추천합니다.\n" +
                                "-사용자는 다음 형식으로 질문합니다\n" +
                                "###\n" +
                                "1. 사용자 인바디 유형 2. 선호하는 운동 부위 3. 1RM 4. 운동 목표  5. 운동 환경 및 장소\n" +
                                "###\n" +
                                "-사용자 인바디 유형은 비만형, 일반형, 강인형 3가지로 나뉩니다.\n" +
                                "-비만형은 운동 루틴에 점핑 잭, 버피테스트, 마운틴 클라이머, 암 워킹 중 2개가 포함해 출력합니다.\n" +
                                "-일반형은 운동 루틴에 점핑 잭, 버피테스트, 마운틴 클라이머, 암 워킹 중 1개가 포함해 추천합니다.\n" +
                                "-중량은 1RM에서 제공되는 무게의 65~80%로 추천합니다. \n" +
                                "예시) 상체 벤치프레스가 50kg고 하체 스쿼트가 100kg라면, 상체는 32.5~40kg, 하체는 65~80kg로 추천합니다 \n \n" +
                                "-중량은 무조건 정수입니다. 소수점은 존재하지 않습니다 \n" +
                                "-목표는 체중 감소, 근비대, 근지구력, 근파워로 나뉩니다.  \n" +
                                "-일반적으로는 세트는 4~5, 1세트당 반복횟수는 15~20으로 추천합니다. \n" +
                                "-목표가 근비대인 경우 세트는 3~6, 1세트당 반복횟수는 6~12로 추천합니다. \n \n" +
                                "-환경은 헬스장과 집으로 나뉩니다. 각 환경에 맞는 운동을 추천해줍니다.\n\n" +
                                "-운동은 반드시 아래 운동목록 안에 있는 것 중에서만 선택하여 추천합니다.\n" +
                                "-운동목록 : \n" +
                                "<등 부위> \n" +
                                "랫 풀 다운, 시티드 로우, 데드리프트, 원 암 덤벨 로우, 풀업 \n" +
                                "<가슴 부위> \n" +
                                "벤치프레스, 덤벨 플라이, 케이블 크로스 오버, 체스트프레스, 팔굽혀펴기 \n" +
                                "<팔 부위> \n" +
                                "덤벨 컬, 트라이셉스 푸시 다운, 오버헤드 케이블 컬, 딥스, 벤치프레스 \n" +
                                "<어깨 부위> \n" +
                                "오버헤드 프레스, 스미스 머신 슈러그, 케틀벨 스트릭 프레스, 밴드 오버헤드 프레스, 파이크 푸쉬업 \n" +
                                "<하체 부위> \n" +
                                "스쿼트, 런지, 힙 어브덕션, 레그 익스텐션, 레그 프레스 \n" +
                                "<복근 부위> \n" +
                                "디클라인 크런치, 행잉 레그 레이즈, 시티드 니 업, 바이시클 크런치 \n\n" +
                                "-위의 운동목록의 분류에 따라 사용자가 선호하는 부위에 해당하는 운동으로만 추천합니다. \\n \\n\" +\n" +
                                "-json 파일 형식에 대해 잘 알고있습니다. \n" +
                                "-출력 데이터는 아래 형식으로 출력합니다.\n" +
                                        "{\n" +
                                        "  \"userId\": \"\",\n" +
                                        "  \"routineName\": \"\",\n" +
                                        "  \"routineDetails\": [\n" +
                                        "    {\n" +
                                        "      \"exerciseName\": \"값1-1\",\n" +
                                        "      \"weight\": 값1-2,\n" +
                                        "      \"sets\": 값1-3,\n" +
                                        "      \"reps\": 값1-4\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"exerciseName\": \"값n-1\",\n" +
                                        "      \"weight\": 값n-2,\n" +
                                        "      \"sets\": 값n-3,\n" +
                                        "      \"reps\": 값n-4\n" +
                                        "    }\n" +
                                        "  ]\n" +
                                        "}\n" +
                                "-출력 데이터에 json 파일 형식 외에는 아무 말도 추가하지 않습니다" +
                                "-만약 맨몸운동이라면, weight는 0으로 출력합니다."
                        ),
                        Map.of("role", "user", "content", "1. 강인형  2. 등, 하체 부위를 선호해 \n" +
                                "3. 1RM은 상체 벤치프레스 50kg, 하체 스쿼트 80kg이야 4. 근지구력 강화가 목적이야 5. 헬스장에서 할 수 있었으면 좋겠어"),
                        Map.of("role", "assistant", "content",
                                "{\n" +
                                        "  \"userId\": \"\",\n"+
                                        "  \"routineName\": \"\",\n"+
                                        "  \"routineDetails\": [\n" +
                                        "    {\n" +
                                        "      \"exerciseName\": \"데드리프트\",\n" +
                                        "      \"weight\": 40,\n" +
                                        "      \"sets\": 4,\n" +
                                        "      \"reps\": 15\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"exerciseName\": \"원 암 덤벨 로우\",\n" +
                                        "      \"weight\": 15,\n" +
                                        "      \"sets\": 5,\n" +
                                        "      \"reps\": 20\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"exerciseName\": \"레그 프레스\",\n" +
                                        "      \"weight\": 50,\n" +
                                        "      \"sets\": 5,\n" +
                                        "      \"reps\": 20\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"exerciseName\": \"힙 어브덕션\",\n" +
                                        "      \"weight\": 40,\n" +
                                        "      \"sets\": 5,\n" +
                                        "      \"reps\": 15\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"exerciseName\": \"스쿼트\",\n" +
                                        "      \"weight\": 50,\n" +
                                        "      \"sets\": 4,\n" +
                                        "      \"reps\": 20\n" +
                                        "    }\n" +
                                        "  ]\n" +
                                        "}\n"),
                        Map.of("role", "user", "content", "1. 일반형  2. 팔, 어깨 부위를 선호해 \n" +
                                "3. 1RM은 상체 벤치프레스 40kg, 하체 스쿼트 70kg이야 4. 근비대가 목적이야 5. 헬스장에서 할 수 있었으면 좋겠어"),
                        Map.of("role", "assistant", "content",
                                "{\n" +
                                        "  \"userId\": \"\",\n"+
                                        "  \"routineName\": \"\",\n"+
                                        "  \"routineDetails\": [\n" +
                                        "    {\n" +
                                        "      \"exerciseName\": \"점핑 잭\",\n" +
                                        "      \"weight\": 0,\n" +
                                        "      \"sets\": 4,\n" +
                                        "      \"reps\": 20\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"exerciseName\": \"덤벨 컬\",\n" +
                                        "      \"weight\": 5,\n" +
                                        "      \"sets\": 5,\n" +
                                        "      \"reps\": 10\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"exerciseName\": \"트라이셉스 푸시 다운\",\n" +
                                        "      \"weight\": 25,\n" +
                                        "      \"sets\": 4,\n" +
                                        "      \"reps\": 10\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"exerciseName\": \"오버헤드 프레스\",\n" +
                                        "      \"weight\": 25,\n" +
                                        "      \"sets\": 5,\n" +
                                        "      \"reps\": 12\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"exerciseName\": \"파이크 푸쉬업\",\n" +
                                        "      \"weight\": 0,\n" +
                                        "      \"sets\": 6,\n" +
                                        "      \"reps\": 12\n" +
                                        "    }\n" +
                                        "  ]\n" +
                                        "}\n"),
                        Map.of("role", "user", "content", "1. 비만형  2. 하체 부위를 선호해 \n" +
                                "3. 1RM은 상체 벤치프레스 20kg, 하체 스쿼트 30kg이야 4. 체지방 감소가 목적이야 5. 집에서 할 수 있었으면 좋겠어"),
                        Map.of("role", "assistant", "content",
                                "{\n" +
                                        "  \"userId\": \"\",\n"+
                                        "  \"routineName\": \"\",\n"+
                                        "  \"routineDetails\": [\n" +
                                        "    {\n" +
                                        "      \"exerciseName\": \"버피테스트\",\n" +
                                        "      \"weight\": 0,\n" +
                                        "      \"sets\": 4,\n" +
                                        "      \"reps\": 15\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"exerciseName\": \"점핑 잭\",\n" +
                                        "      \"weight\": 0,\n" +
                                        "      \"sets\": 4,\n" +
                                        "      \"reps\": 20\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"exerciseName\": \"스쿼트\",\n" +
                                        "      \"weight\": 0,\n" +
                                        "      \"sets\": 5,\n" +
                                        "      \"reps\": 20\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"exerciseName\": \"런지\",\n" +
                                        "      \"weight\": 50,\n" +
                                        "      \"sets\": 4,\n" +
                                        "      \"reps\": 20\n" +
                                        "    }\n" +
                                        "  ]\n" +
                                        "}\n"),
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