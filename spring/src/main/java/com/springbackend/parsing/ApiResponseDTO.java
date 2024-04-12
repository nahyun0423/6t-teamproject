package com.springbackend.parsing;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApiResponseDTO {
    private Status status;
    private Result result;

    @Getter
    @Setter
    public static class Status {
        private String code;
        private String message;
    }

    @Getter
    @Setter
    public static class Result {
        private Message message;
        private int inputLength;
        private int outputLength;
        private String stopReason;
        private long seed;
        private List<Object> aiFilter;
    }

    @Getter
    @Setter
    public static class Message {
        private String role;
        private String content;
    }
}