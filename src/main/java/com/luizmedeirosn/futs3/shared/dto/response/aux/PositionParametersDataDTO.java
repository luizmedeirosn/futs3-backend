package com.luizmedeirosn.futs3.shared.dto.response.aux;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serial;
import java.io.Serializable;

@JsonPropertyOrder({"id", "name", "weight"})
public record PositionParametersDataDTO(Long id, String name, Integer weight) implements Serializable {
  @Serial private static final long serialVersionUID = 1L;
}
