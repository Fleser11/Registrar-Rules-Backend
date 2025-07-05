package org.formalmethods.registrarproject.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.openapitools.model.Audit;
import org.openapitools.api.AuditsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.openapitools.model.RunConfig;
import org.openapitools.model.SemConfig;
import org.openapitools.model.Audit;


import org.formalmethods.registrarproject.exception.InvalidInputException;
import org.formalmethods.registrarproject.exception.MissingItemException;

import org.formalmethods.registrarproject.api.impl.AuditManager;
import org.formalmethods.registrarproject.api.impl.AuditRunner;


@RestController
public class AuditApiController implements AuditsApi {
    /**
     * Basic class that implements the API interface and calls implmenetation code.
     * As well as handling exceptions and returning appropriate HTTP responses.
     */


    private final NativeWebRequest request;

    private final AuditManager auditManager;

    @Autowired
    public AuditApiController(NativeWebRequest request, AuditManager auditManager) {
        this.request = request;
        this.auditManager = auditManager;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    /**
     * Retrieves a list of all stored audits.
     * @return A list of Audit objects.
     */
    @Override
    public ResponseEntity<Audit> auditsGet(String name) {
        try{
            Audit audit = auditManager.getAudit(name);
            return ResponseEntity.ok(audit);
        } catch (MissingItemException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            System.err.println("Error in modelsGet: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Stores the provided audit in the system.
     * @param audit The audit to store.
     * @return The stored audit.
     */
    @Override
    public ResponseEntity<Audit> auditsPost(Audit audit) {
        try {
            return ResponseEntity.ok(auditManager.storeAudit(audit));
        }
        catch (InvalidInputException e) {
            System.err.println("Invalid input error in auditsPost: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(audit);
        } catch (Exception e) {
            System.err.println("Error in modelsPost: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Runs the specified audit with the provided run configuration.
     * @param audit The ID of the audit to run.
     * @param runConfig The configuration for running the audit.
     * @return A list of SemConfig objects representing the results of the audit run.
     */
    @Override
    public ResponseEntity<List<SemConfig>> auditsAuditRunPost(String audit, RunConfig runConfig) {
        try {
            Audit storedModel = auditManager.getAudit(audit);
            AuditRunner auditRunner = new AuditRunner();
            DBService.
            auditRunner.runAudit(storedModel, runConfig);
            return ResponseEntity.ok(auditRunner.getResult());
        } catch (MissingItemException e) {
            System.err.println("Missing audit error in modelsModelRunPost: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            System.err.println("Error in modelsModelRunPost: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}