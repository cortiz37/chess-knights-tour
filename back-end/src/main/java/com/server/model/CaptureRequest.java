package com.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CaptureRequest {

    private PieceType pieceType;

    private int sourceColumn;
    private int sourceRow;

    private int targetColumn;
    private int targetRow;

    private int boardSize = 8;

    @JsonIgnore
    public boolean isSamePosition() {
        return sourceColumn == targetColumn && sourceRow == targetRow;
    }

    @JsonIgnore
    public String getSourceAsString() {
        return sourceColumn + "," + sourceRow;
    }

    @JsonIgnore
    public String getTargetAsString() {
        return targetColumn + "," + targetRow;
    }
}
