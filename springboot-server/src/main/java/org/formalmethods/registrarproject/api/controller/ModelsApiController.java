
package org.formalmethods.registrarproject.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.openapitools.model.Model;
import org.openapitools.api.ModelsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.NativeWebRequest;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.openapitools.model.RunConfig;


import org.formalmethods.registrarproject.exception.InvalidInputException;
import org.formalmethods.registrarproject.exception.MissingModelException;

import org.formalmethods.registrarproject.api.impl.ModelManager;
import org.formalmethods.registrarproject.api.impl.ModelRunner;


@RestController
public class ModelsApiController implements ModelsApi {

    private final NativeWebRequest request;

    @Autowired
    public ModelsApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Model> modelsGet(String name) {
        try{
            Model model = ModelManager.getModel(name);
            return ResponseEntity.ok(model);
        } catch (MissingModelException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            System.err.println("Error in modelsGet: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Model> modelsPost(Model model) {
        try {
            return ModelManager.storeModel(model);
        }
        catch (InvalidInputError e) {
            System.err.println("Invalid input error in modelsPost: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(model);
        } catch (Exception e) {
            System.err.println("Error in modelsPost: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<RunConfig> modelsModelRunPost(String model, RunConfig runConfig) {
        try {
            Model storedModel = ModelManager.getModel(model);
            ModelRunner.runModel(runConfig);
            return ResponseEntity.ok(runConfig);
        } catch (MissingModelException e) {
            System.err.println("Missing model error in modelsModelRunPost: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            System.err.println("Error in modelsModelRunPost: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}