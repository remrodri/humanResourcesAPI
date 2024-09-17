package com.humanResources.humanResourcesAPI.vo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class StandarResponse {
    private String message;
    private Integer statusCode;
    private Object data;
}
