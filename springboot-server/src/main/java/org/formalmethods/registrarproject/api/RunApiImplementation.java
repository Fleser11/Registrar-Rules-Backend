package org.formalmethods.registrarproject.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.openapitools.model.RunConfig;
import org.openapitools.api.RunApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.NativeWebRequest;
import java.util.Optional;
import org.springframework.http.HttpStatus;

@RestController
public class RunApiImplementation implements RunApi {

    private final NativeWebRequest request;

    @Autowired
    public RunApiImplementation(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<RunConfig> runPost(RunConfig config) {
        // Implementation logic to run the model with the provided configuration
        // For now, returning a placeholder response

        try {}
        RunConfig runConfig = new RunConfig();
        System.out.println("Running model with configuration: " + config);
        return ResponseEntity.ok(runConfig);
    }

}