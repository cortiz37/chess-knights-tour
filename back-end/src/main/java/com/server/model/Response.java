package com.server.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Response {

    private boolean possible;

    private List<String> steps;

    public static Response of(boolean possible, List<String> steps) {
        Response response = new Response();
        response.setPossible(possible);
        response.setSteps(steps);
        return response;
    }
}
