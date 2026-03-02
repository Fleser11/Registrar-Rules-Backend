package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.model.Pathway;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-04T20:20:31.572986831-05:00[America/New_York]", comments = "Generator version: 7.6.0")
public class RunConfig {

  private String genEdProgram = null;

  private Pathway pathway;

  @Valid
  private List<String> transferCourses = new ArrayList<>();

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

  public RunConfig pathway(Pathway pathway) {
    this.pathway = pathway;
    return this;
  }

  /**
   * Get pathway
   * @return pathway
  */
  @Valid 
  @Schema(name = "pathway", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("pathway")
  public Pathway getPathway() {
    return pathway;
  }

  public void setPathway(Pathway pathway) {
    this.pathway = pathway;
  }

  public RunConfig transferCourses(List<String> transferCourses) {
    this.transferCourses = transferCourses;
    return this;
  }

  public RunConfig addTransferCoursesItem(String transferCoursesItem) {
    if (this.transferCourses == null) {
      this.transferCourses = new ArrayList<>();
    }
    this.transferCourses.add(transferCoursesItem);
    return this;
  }

  /**
   * Get transferCourses
   * @return transferCourses
  */
  
  @Schema(name = "transferCourses", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("transferCourses")
  public List<String> getTransferCourses() {
    return transferCourses;
  }

  public void setTransferCourses(List<String> transferCourses) {
    this.transferCourses = transferCourses;
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
        Objects.equals(this.pathway, runConfig.pathway) &&
        Objects.equals(this.transferCourses, runConfig.transferCourses);
  }

  @Override
  public int hashCode() {
    return Objects.hash(genEdProgram, pathway, transferCourses);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RunConfig {\n");
    sb.append("    genEdProgram: ").append(toIndentedString(genEdProgram)).append("\n");
    sb.append("    pathway: ").append(toIndentedString(pathway)).append("\n");
    sb.append("    transferCourses: ").append(toIndentedString(transferCourses)).append("\n");
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

