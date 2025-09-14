package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Course
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-09-07T23:20:24.360136977-04:00[America/New_York]", comments = "Generator version: 7.6.0")
public class Course {

  private String code;

  private String dept;

  private Integer number;

  private String name;

  private Float credits = null;

  private String semester;

  private String prereqs;

  private String description;

  public Course code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Get code
   * @return code
  */
  
  @Schema(name = "code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("code")
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Course dept(String dept) {
    this.dept = dept;
    return this;
  }

  /**
   * Get dept
   * @return dept
  */
  
  @Schema(name = "dept", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dept")
  public String getDept() {
    return dept;
  }

  public void setDept(String dept) {
    this.dept = dept;
  }

  public Course number(Integer number) {
    this.number = number;
    return this;
  }

  /**
   * Get number
   * @return number
  */
  
  @Schema(name = "number", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("number")
  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public Course name(String name) {
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

  public Course credits(Float credits) {
    this.credits = credits;
    return this;
  }

  /**
   * Get credits
   * @return credits
  */
  
  @Schema(name = "credits", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("credits")
  public Float getCredits() {
    return credits;
  }

  public void setCredits(Float credits) {
    this.credits = credits;
  }

  public Course semester(String semester) {
    this.semester = semester;
    return this;
  }

  /**
   * Get semester
   * @return semester
  */
  
  @Schema(name = "semester", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("semester")
  public String getSemester() {
    return semester;
  }

  public void setSemester(String semester) {
    this.semester = semester;
  }

  public Course prereqs(String prereqs) {
    this.prereqs = prereqs;
    return this;
  }

  /**
   * Get prereqs
   * @return prereqs
  */
  
  @Schema(name = "prereqs", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("prereqs")
  public String getPrereqs() {
    return prereqs;
  }

  public void setPrereqs(String prereqs) {
    this.prereqs = prereqs;
  }

  public Course description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  */
  
  @Schema(name = "description", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Course course = (Course) o;
    return Objects.equals(this.code, course.code) &&
        Objects.equals(this.dept, course.dept) &&
        Objects.equals(this.number, course.number) &&
        Objects.equals(this.name, course.name) &&
        Objects.equals(this.credits, course.credits) &&
        Objects.equals(this.semester, course.semester) &&
        Objects.equals(this.prereqs, course.prereqs) &&
        Objects.equals(this.description, course.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, dept, number, name, credits, semester, prereqs, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Course {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    dept: ").append(toIndentedString(dept)).append("\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    credits: ").append(toIndentedString(credits)).append("\n");
    sb.append("    semester: ").append(toIndentedString(semester)).append("\n");
    sb.append("    prereqs: ").append(toIndentedString(prereqs)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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

