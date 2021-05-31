package com.server.controller;

import com.server.model.CaptureRequest;
import com.server.model.Response;
import com.server.model.TourRequest;
import com.server.model.structure.Node;
import com.server.service.ChessService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ChessController.PATH)
@Tag(name = "Chess Controller")
public class ChessController {

    public static final String PATH = "/v1/chess";

    private final ChessService chessService;

    public ChessController(ChessService chessService) {
        this.chessService = chessService;
    }

    @Operation(
        summary = "Capture request",
        description = "Finds out if the piece can capture the target position",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "JSON document with capture request data",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                examples = @ExampleObject(name = "captureRequest", ref = "CaptureRequestExample")
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "" + HttpServletResponse.SC_OK,
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                        @ExampleObject(name = "captureResponse", ref = "CaptureResponseExample")
                    },
                    schema = @Schema(oneOf = Response.class))
            ),
            @ApiResponse(
                responseCode = "" + HttpServletResponse.SC_BAD_REQUEST,
                description = "Cannot process the capture request",
                content = @Content()
            )
        })
    @PostMapping("/capture")
    public ResponseEntity canCapture(@RequestBody CaptureRequest captureRequest) {
        if (captureRequest.getPieceType() == null) {
            return ResponseEntity.badRequest().body("PieceType is required");
        }
        if (captureRequest.isSamePosition()) {
            return ResponseEntity.badRequest().body("Source and Target positions cannot be the same");
        }
        if (captureRequest.getBoardSize() < 4) {
            return ResponseEntity.badRequest().body("Minimum board size is 4");
        }

        final List<Node> steps = chessService.capturePiece(captureRequest.getPieceType(), captureRequest.getSourceAsString(), captureRequest.getTargetAsString(), captureRequest.getBoardSize());
        if (steps == null || steps.isEmpty()) {
            return ResponseEntity.ok(Response.of(false, null));
        }

        return ResponseEntity.ok(
            Response.of(
                true,
                steps.stream()
                    .map(Node::getValue)
                    .collect(Collectors.toList())
            )
        );
    }

    @Operation(
        summary = "Knight's tour request",
        description = "Finds out if it is possible to complete the tour",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "JSON document with knight's tour request data",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                examples = @ExampleObject(name = "tourRequest", ref = "TourRequestExample")
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "" + HttpServletResponse.SC_OK,
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                        @ExampleObject(name = "tourResponse", ref = "TourResponseExample")
                    },
                    schema = @Schema(oneOf = Response.class))
            ),
            @ApiResponse(
                responseCode = "" + HttpServletResponse.SC_BAD_REQUEST,
                description = "Cannot process the capture request",
                content = @Content()
            )
        })
    @PostMapping("/tour")
    public ResponseEntity tour(@RequestBody TourRequest tourRequest) {
        if (tourRequest.getBoardSize() < 4) {
            return ResponseEntity.badRequest().body("Minimum board size is 4");
        }

        List<Node> steps = chessService.knightTour(tourRequest.getSourceAsString(), tourRequest.getBoardSize());

        return ResponseEntity.ok(
            Response.of(
                true,
                steps.stream()
                    .map(Node::getValue)
                    .collect(Collectors.toList())
            )
        );
    }
}
