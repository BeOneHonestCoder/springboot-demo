package com.net.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityLogDTO {

    private String activityType;
    private Integer operationStatus;
    private String requestUrl;
    private Map<String, String> requestHeader;
    private String requestParameter;
    private String requestBody;
    private Map<String, String> responseHeader;
    private String responseBody;
    private String timestamp;
}
