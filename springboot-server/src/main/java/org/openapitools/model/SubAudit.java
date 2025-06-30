package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * SubAudit
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-06-29T20:46:51.265913484-05:00[America/Chicago]", comments = "Generator version: 7.6.0")
public class SubAudit {

  private String name;

  private String cardinality;

  @Valid
  private List<String> courses = new ArrayList<>();

  public SubAudit name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  
  @Schema(name = "name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public SubAudit cardinality(String cardinality) {
    this.cardinality = cardinality;
    return this;
  }

  /**
   * Get cardinality
   * @return cardinality
  */
  
  @Schema(name = "cardinality", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cardinality")
  public String getCardinality() {
    return cardinality;
  }

  public void setCardinality(String cardinality) {
    this.cardinality = cardinality;
  }

  public SubAudit courses(List<String> courses) {
    this.courses = courses;
    return this;
  }

  public SubAudit addCoursesItem(String coursesItem) {
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
    SubAudit subAudit = (SubAudit) o;
    return Objects.equals(this.name, subAudit.name) &&
        Objects.equals(this.cardinality, subAudit.cardinality) &&
        Objects.equals(this.courses, subAudit.courses);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, cardinality, courses);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubAudit {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    cardinality: ").append(toIndentedString(cardinality)).append("\n");
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

