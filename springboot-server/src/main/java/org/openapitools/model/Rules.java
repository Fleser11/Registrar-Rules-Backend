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
 * Rules
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-09-03T14:43:55.413193228-04:00[America/New_York]", comments = "Generator version: 7.6.0")
public class Rules {

  private Integer maxSemCourses;

  private Integer minSemCourses;

  private Boolean minimization;

  public Rules maxSemCourses(Integer maxSemCourses) {
    this.maxSemCourses = maxSemCourses;
    return this;
  }

  /**
   * Get maxSemCourses
   * @return maxSemCourses
  */
  
  @Schema(name = "maxSemCourses", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("maxSemCourses")
  public Integer getMaxSemCourses() {
    return maxSemCourses;
  }

  public void setMaxSemCourses(Integer maxSemCourses) {
    this.maxSemCourses = maxSemCourses;
  }

  public Rules minSemCourses(Integer minSemCourses) {
    this.minSemCourses = minSemCourses;
    return this;
  }

  /**
   * Get minSemCourses
   * @return minSemCourses
  */
  
  @Schema(name = "minSemCourses", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("minSemCourses")
  public Integer getMinSemCourses() {
    return minSemCourses;
  }

  public void setMinSemCourses(Integer minSemCourses) {
    this.minSemCourses = minSemCourses;
  }

  public Rules minimization(Boolean minimization) {
    this.minimization = minimization;
    return this;
  }

  /**
   * Get minimization
   * @return minimization
  */
  
  @Schema(name = "minimization", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("minimization")
  public Boolean getMinimization() {
    return minimization;
  }

  public void setMinimization(Boolean minimization) {
    this.minimization = minimization;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Rules rules = (Rules) o;
    return Objects.equals(this.maxSemCourses, rules.maxSemCourses) &&
        Objects.equals(this.minSemCourses, rules.minSemCourses) &&
        Objects.equals(this.minimization, rules.minimization);
  }

  @Override
  public int hashCode() {
    return Objects.hash(maxSemCourses, minSemCourses, minimization);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Rules {\n");
    sb.append("    maxSemCourses: ").append(toIndentedString(maxSemCourses)).append("\n");
    sb.append("    minSemCourses: ").append(toIndentedString(minSemCourses)).append("\n");
    sb.append("    minimization: ").append(toIndentedString(minimization)).append("\n");
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

