package com.server.config;

import com.server.model.CaptureRequest;
import com.server.model.PieceType;
import com.server.model.Response;
import com.server.model.TourRequest;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        OpenAPI openAPI = new OpenAPI()
            .info(apiInfo());
        return openAPI;
    }

    @Bean
    public OpenApiCustomiser customize(Collection<Map.Entry<String, Example>> examples) {
        return openApi -> {
            examples.forEach(example -> {
                openApi.getComponents().addExamples(example.getKey(), example.getValue());
            });
        };
    }

    private Info apiInfo() {
        return new Info()
            .title("Chess - Knight's tour")
            .version("1.0");
    }

    @Bean
    public Map.Entry<String, Example> entityCaptureRequest() {
        CaptureRequest request = new CaptureRequest();
        request.setBoardSize(8);
        request.setPieceType(PieceType.ROOK);
        request.setTargetColumn(5);
        request.setTargetRow(6);
        return buildExample(
            "CaptureRequestExample",
            "Capture Request",
            "Piece from source position to the target position",
            request
        );
    }

    @Bean
    public Map.Entry<String, Example> entityCaptureResponse() {
        Response response = new Response();
        response.setPossible(true);
        response.setSteps(Arrays.asList("0,0", "0,6", "0,6", "5,6"));
        return buildExample(
            "CaptureResponseExample",
            "Capture Response",
            "Wrapper indicating whether it is possible to capture the target position and the list of steps",
            response
        );
    }

    @Bean
    public Map.Entry<String, Example> entityTourRequest() {
        TourRequest request = new TourRequest();
        request.setBoardSize(8);
        request.setSourceColumn(2);
        request.setSourceRow(3);
        return buildExample(
            "TourRequestExample",
            "Tour Request",
            "Knight's tour from the source position",
            request
        );
    }

    @Bean
    public Map.Entry<String, Example> entityTourResponse() {
        Response response = new Response();
        response.setPossible(true);
        response.setSteps(Arrays.asList("2,3", "0,2", "1,0", "3,1", "5,0", "7,1", "6,3", "7,5", "6,7", "4,6", "2,7", "0,6", "1,4", "2,6", "0,7", "1,5", "0,3", "1,1", "3,0", "2,2", "0,1", "2,0", "1,2", "0,0", "2,1", "4,0", "6,1", "4,2", "3,4", "1,3", "0,5", "1,7", "2,5", "0,4", "1,6", "3,7", "5,6", "7,7", "6,5", "7,3", "5,2", "4,4", "3,2", "2,4", "3,6", "5,7", "7,6", "5,5", "4,7", "3,5", "4,3", "5,1", "7,0", "6,2", "5,4", "3,3", "4,1", "6,0", "7,2", "6,4", "4,5", "5,3", "7,4", "6,6"));
        return buildExample(
            "TourResponseExample",
            "Tour Response",
            "Wrapper indicating whether it is possible to complete the tour and the list of steps",
            response
        );
    }

    private Map.Entry<String, Example> buildExample(String id, String summary, String description, Object data) {
        Example example = new Example();
        Map.Entry<String, Example> entry = new AbstractMap.SimpleEntry<>(id, example);
        example.setSummary(summary);
        example.setDescription(description);
        example.setValue(data);
        return entry;
    }
}
