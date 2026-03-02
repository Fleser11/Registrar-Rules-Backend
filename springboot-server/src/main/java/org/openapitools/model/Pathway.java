package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.model.SemConfig;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Pathway
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-04T20:20:31.572986831-05:00[America/New_York]", comments = "Generator version: 7.6.0")
public class Pathway implements AuditsAuditRunPost200Response {

  @Valid
  private List<@Valid SemConfig> semesters = new ArrayList<>();

  public Pathway semesters(List<@Valid SemConfig> semesters) {
    this.semesters = semesters;
    return this;
  }

  public Pathway addSemestersItem(SemConfig semestersItem) {
    if (this.semesters == null) {
      this.semesters = new ArrayList<>();
    }
    this.semesters.add(semestersItem);
    return this;
  }

  /**
   * Get semesters
   * @return semesters
  */
  @Valid @Size(min = 6, max = 9) 
  @Schema(name = "semesters", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("semesters")
  public List<@Valid SemConfig> getSemesters() {
    return semesters;
  }

  public void setSemesters(List<@Valid SemConfig> semesters) {
    this.semesters = semesters;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Pathway pathway = (Pathway) o;
    return Objects.equals(this.semesters, pathway.semesters);
  }

  @Override
  public int hashCode() {
    return Objects.hash(semesters);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Pathway {\n");
    sb.append("    semesters: ").append(toIndentedString(semesters)).append("\n");
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

