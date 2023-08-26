package com.example.table.common.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {

  private List<T> data;
  private int pageNumber;
  private int pageSize;
  private int totalPages;
  private long totalElements;
  private boolean isFirst;
  private boolean isLast;

}
