package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ModelInfo
 */

@JsonTypeName("Model_info")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-01T20:07:02.025195025-05:00[America/Chicago]", comments = "Generator version: 7.6.0")
public class ModelInfo {

  private String code;

  private String program;

  public ModelInfo code(String code) {
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

  public ModelInfo program(String program) {
    this.program = program;
    return this;
  }

  /**
   * Get program
   * @return program
  */
  
  @Schema(name = "program", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("program")
  public String getProgram() {
    return program;
  }

  public void setProgram(String program) {
    this.program = program;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelInfo modelInfo = (ModelInfo) o;
    return Objects.equals(this.code, modelInfo.code) &&
        Objects.equals(this.program, modelInfo.program);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, program);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelInfo {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    program: ").append(toIndentedString(program)).append("\n");
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

