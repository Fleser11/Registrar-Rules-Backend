package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * AuditInfo
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-04T18:38:39.247600968-05:00[America/Chicago]", comments = "Generator version: 7.6.0")
public class AuditInfo {

  private Integer gid;

  private String code;

  private String program;

  private Boolean isGenEd = false;

  public AuditInfo gid(Integer gid) {
    this.gid = gid;
    return this;
  }

  /**
   * Get gid
   * @return gid
  */
  
  @Schema(name = "gid", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("gid")
  public Integer getGid() {
    return gid;
  }

  public void setGid(Integer gid) {
    this.gid = gid;
  }

  public AuditInfo code(String code) {
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

  public AuditInfo program(String program) {
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

  public AuditInfo isGenEd(Boolean isGenEd) {
    this.isGenEd = isGenEd;
    return this;
  }

  /**
   * Get isGenEd
   * @return isGenEd
  */
  
  @Schema(name = "isGenEd", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isGenEd")
  public Boolean getIsGenEd() {
    return isGenEd;
  }

  public void setIsGenEd(Boolean isGenEd) {
    this.isGenEd = isGenEd;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuditInfo auditInfo = (AuditInfo) o;
    return Objects.equals(this.gid, auditInfo.gid) &&
        Objects.equals(this.code, auditInfo.code) &&
        Objects.equals(this.program, auditInfo.program) &&
        Objects.equals(this.isGenEd, auditInfo.isGenEd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(gid, code, program, isGenEd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AuditInfo {\n");
    sb.append("    gid: ").append(toIndentedString(gid)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    program: ").append(toIndentedString(program)).append("\n");
    sb.append("    isGenEd: ").append(toIndentedString(isGenEd)).append("\n");
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

