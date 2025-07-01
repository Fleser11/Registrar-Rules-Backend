package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.model.SemConfig;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * RunConfig
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-06-30T20:36:08.461319642-05:00[America/Chicago]", comments = "Generator version: 7.6.0")
public class RunConfig {

  private String degreeProgram;

  private String genEdProgram;

  @Valid
  private List<@Valid SemConfig> courseConfig = new ArrayList<>();

  public RunConfig degreeProgram(String degreeProgram) {
    this.degreeProgram = degreeProgram;
    return this;
  }

  /**
   * Get degreeProgram
   * @return degreeProgram
  */
  
  @Schema(name = "degreeProgram", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("degreeProgram")
  public String getDegreeProgram() {
    return degreeProgram;
  }

  public void setDegreeProgram(String degreeProgram) {
    this.degreeProgram = degreeProgram;
  }

  public RunConfig genEdProgram(String genEdProgram) {
    this.genEdProgram = genEdProgram;
    return this;
  }

  /**
   * Get genEdProgram
   * @return genEdProgram
  */
  
  @Schema(name = "genEdProgram", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("genEdProgram")
  public String getGenEdProgram() {
    return genEdProgram;
  }

  public void setGenEdProgram(String genEdProgram) {
    this.genEdProgram = genEdProgram;
  }

  public RunConfig courseConfig(List<@Valid SemConfig> courseConfig) {
    this.courseConfig = courseConfig;
    return this;
  }

  public RunConfig addCourseConfigItem(SemConfig courseConfigItem) {
    if (this.courseConfig == null) {
      this.courseConfig = new ArrayList<>();
    }
    this.courseConfig.add(courseConfigItem);
    return this;
  }

  /**
   * Get courseConfig
   * @return courseConfig
  */
  @Valid 
  @Schema(name = "courseConfig", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("courseConfig")
  public List<@Valid SemConfig> getCourseConfig() {
    return courseConfig;
  }

  public void setCourseConfig(List<@Valid SemConfig> courseConfig) {
    this.courseConfig = courseConfig;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RunConfig runConfig = (RunConfig) o;
    return Objects.equals(this.degreeProgram, runConfig.degreeProgram) &&
        Objects.equals(this.genEdProgram, runConfig.genEdProgram) &&
        Objects.equals(this.courseConfig, runConfig.courseConfig);
  }

  @Override
  public int hashCode() {
    return Objects.hash(degreeProgram, genEdProgram, courseConfig);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RunConfig {\n");
    sb.append("    degreeProgram: ").append(toIndentedString(degreeProgram)).append("\n");
    sb.append("    genEdProgram: ").append(toIndentedString(genEdProgram)).append("\n");
    sb.append("    courseConfig: ").append(toIndentedString(courseConfig)).append("\n");
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

