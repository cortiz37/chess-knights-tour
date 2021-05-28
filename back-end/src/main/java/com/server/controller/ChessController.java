package com.server.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ChessController.PATH)
@Tag(name = "Chess Controller")
public class ChessController {

    public static final String PATH = "/v1/chess";

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok().build();
    }
}
