package com.soniq.domain;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class RequestDTO {
    private Integer[] rooms = new Integer[100];
    @Min(1)
    private int senior;
    private int junior;
}
