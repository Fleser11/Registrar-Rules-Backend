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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-09-14T18:46:29.410457831-04:00[America/New_York]", comments = "Generator version: 7.6.0")
public class RunConfig {

  private String genEdProgram = null;

  @Valid
  private List<@Valid SemConfig> pathway = new ArrayList<>();

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

  public RunConfig pathway(List<@Valid SemConfig> pathway) {
    this.pathway = pathway;
    return this;
  }

  public RunConfig addPathwayItem(SemConfig pathwayItem) {
    if (this.pathway == null) {
      this.pathway = new ArrayList<>();
    }
    this.pathway.add(pathwayItem);
    return this;
  }

  /**
   * Get pathway
   * @return pathway
  */
  @Valid @Size(min = 8, max = 8) 
  @Schema(name = "pathway", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("pathway")
  public List<@Valid SemConfig> getPathway() {
    return pathway;
  }

  public void setPathway(List<@Valid SemConfig> pathway) {
    this.pathway = pathway;
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
    return Objects.equals(this.genEdProgram, runConfig.genEdProgram) &&
        Objects.equals(this.pathway, runConfig.pathway);
  }

  @Override
  public int hashCode() {
    return Objects.hash(genEdProgram, pathway);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RunConfig {\n");
    sb.append("    genEdProgram: ").append(toIndentedString(genEdProgram)).append("\n");
    sb.append("    pathway: ").append(toIndentedString(pathway)).append("\n");
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

