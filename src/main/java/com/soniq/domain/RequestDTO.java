package com.soniq.domain;

import lombok.Data;

@Data
public class RequestDTO {
    private Integer[] rooms=new Integer[100];
    private int senior;
    private int junior;
}
