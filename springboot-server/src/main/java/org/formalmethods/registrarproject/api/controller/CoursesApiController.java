package org.formalmethods.registrarproject.api.controller;

import org.openapitools.api.CoursesApi;
import org.formalmethods.registrarproject.api.impl.CourseManager;
import org.openapitools.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import java.util.Optional;

import org.formalmethods.registrarproject.exception.*;

@Component
@RestController
class CourseApiController implements CoursesApi {
    /**
     * Basic class that implements the API interface and calls implementation code.
     * As well as handling exceptions and returning appropriate HTTP responses.
     */

    private final NativeWebRequest request;
    private final CourseManager courseManager;

    @Autowired
    public CourseApiController(NativeWebRequest request, CourseManager courseManager) {
        this.request = request;
        this.courseManager = courseManager;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }


    public ResponseEntity<Course> coursesCodeGet(String code) {
        try {
            Course course = courseManager.getCourse(code);
            return ResponseEntity.ok(course);
        } catch (MissingItemException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.err.println("Error in coursesCodeGet: " + e.getMessage());
            return ResponseEntity.status(500).build();
        }
        
    }
}