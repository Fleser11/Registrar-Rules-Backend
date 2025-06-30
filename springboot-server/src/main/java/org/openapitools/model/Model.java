package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.model.ModelInfo;
import org.openapitools.model.SubAudit;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Model
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-06-29T20:46:51.265913484-05:00[America/Chicago]", comments = "Generator version: 7.6.0")
public class Model {

  private ModelInfo info;

  @Valid
  private List<@Valid SubAudit> subAudit = new ArrayList<>();

  public Model info(ModelInfo info) {
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
  public ModelInfo getInfo() {
    return info;
  }

  public void setInfo(ModelInfo info) {
    this.info = info;
  }

  public Model subAudit(List<@Valid SubAudit> subAudit) {
    this.subAudit = subAudit;
    return this;
  }

  public Model addSubAuditItem(SubAudit subAuditItem) {
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
    Model model = (Model) o;
    return Objects.equals(this.info, model.info) &&
        Objects.equals(this.subAudit, model.subAudit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(info, subAudit);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Model {\n");
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

