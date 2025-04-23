package com.astrapay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponsesDto<T> {

    private int code = 200;
    private String message = "OK";
    private T response;

}
