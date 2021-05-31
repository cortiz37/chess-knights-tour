package com.server.controller;

import com.server.model.CaptureRequest;
import com.server.model.Response;
import com.server.model.TourRequest;
import com.server.model.structure.Node;
import com.server.service.ChessService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
