package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.util.NoSuchElementException;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * SemConfig
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-05T15:30:40.976992738-05:00[America/Chicago]", comments = "Generator version: 7.6.0")
public class SemConfig {

  private JsonNullable<Integer> numCourses = JsonNullable.<Integer>undefined();

  @Valid
  private List<String> courses = new ArrayList<>();

  public SemConfig numCourses(Integer numCourses) {
    this.numCourses = JsonNullable.of(numCourses);
    return this;
  }

  /**
   * Get numCourses
   * @return numCourses
  */
  
  @Schema(name = "numCourses", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("numCourses")
  public JsonNullable<Integer> getNumCourses() {
    return numCourses;
  }

  public void setNumCourses(JsonNullable<Integer> numCourses) {
    this.numCourses = numCourses;
  }

  public SemConfig courses(List<String> courses) {
    this.courses = courses;
    return this;
  }

  public SemConfig addCoursesItem(String coursesItem) {
    if (this.courses == null) {
      this.courses = new ArrayList<>();
    }
    this.courses.add(coursesItem);
    return this;
  }

  /**
   * Get courses
   * @return courses
  */
  @Size(min = 0, max = 6) 
  @Schema(name = "courses", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("courses")
  public List<String> getCourses() {
    return courses;
  }

  public void setCourses(List<String> courses) {
    this.courses = courses;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SemConfig semConfig = (SemConfig) o;
    return equalsNullable(this.numCourses, semConfig.numCourses) &&
        Objects.equals(this.courses, semConfig.courses);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(hashCodeNullable(numCourses), courses);
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent() ? Arrays.deepHashCode(new Object[]{a.get()}) : 31;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SemConfig {\n");
    sb.append("    numCourses: ").append(toIndentedString(numCourses)).append("\n");
    sb.append("    courses: ").append(toIndentedString(courses)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

