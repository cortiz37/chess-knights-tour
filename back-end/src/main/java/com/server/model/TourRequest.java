package com.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class TourRequest {

    private int sourceColumn;
    private int sourceRow;

    private int boardSize = 8;

    @JsonIgnore
    public String getSourceAsString() {
        return sourceColumn + "," + sourceRow;
    }
}
