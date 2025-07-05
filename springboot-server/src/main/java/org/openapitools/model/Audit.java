package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.model.AuditInfo;
import org.openapitools.model.SubAudit;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Audit
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-04T22:02:41.348659615-05:00[America/Chicago]", comments = "Generator version: 7.6.0")
public class Audit {

  private AuditInfo info;

  @Valid
  private List<@Valid SubAudit> subAudit = new ArrayList<>();

  public Audit info(AuditInfo info) {
    this.info = info;
    return this;
  }

  /**
   * Get info
   * @return info
  */
  @Valid 
  @Schema(name = "info", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("info")
  public AuditInfo getInfo() {
    return info;
  }

  public void setInfo(AuditInfo info) {
    this.info = info;
  }

  public Audit subAudit(List<@Valid SubAudit> subAudit) {
    this.subAudit = subAudit;
    return this;
  }

  public Audit addSubAuditItem(SubAudit subAuditItem) {
    if (this.subAudit == null) {
      this.subAudit = new ArrayList<>();
    }
    this.subAudit.add(subAuditItem);
    return this;
  }

  /**
   * Get subAudit
   * @return subAudit
  */
  @Valid 
  @Schema(name = "subAudit", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("subAudit")
  public List<@Valid SubAudit> getSubAudit() {
    return subAudit;
  }

  public void setSubAudit(List<@Valid SubAudit> subAudit) {
    this.subAudit = subAudit;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Audit audit = (Audit) o;
    return Objects.equals(this.info, audit.info) &&
        Objects.equals(this.subAudit, audit.subAudit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(info, subAudit);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Audit {\n");
    sb.append("    info: ").append(toIndentedString(info)).append("\n");
    sb.append("    subAudit: ").append(toIndentedString(subAudit)).append("\n");
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

