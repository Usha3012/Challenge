package com.soniq.controller;

import com.soniq.domain.RequestDTO;
import com.soniq.domain.ResponseDTO;
import com.soniq.service.OptimizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OptimizeController {

    private final OptimizeService optimizeService;

    @RequestMapping(path = "/api/optimize/cleaners", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ResponseDTO>> optimizer(@RequestBody @Valid RequestDTO optimizeRequest) {
        List<ResponseDTO> optimizedResult = optimizeService.optimize(optimizeRequest);
        return new ResponseEntity<>(optimizedResult, HttpStatus.CREATED);
    }
}
