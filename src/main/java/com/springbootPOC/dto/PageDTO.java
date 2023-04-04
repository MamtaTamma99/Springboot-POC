package com.springbootPOC.dto;

import lombok.Data;

import static com.springbootPOC.constants.PageConstants.DEFAULT_PAGE_NUMBER;
import static com.springbootPOC.constants.PageConstants.DEFAULT_PAGE_SIZE;

/**
 * @author mamta.t
 */
@Data
public class PageDTO<E extends Enum<E>> {

    private Long pageNo = DEFAULT_PAGE_NUMBER;

    private Long pageSize = DEFAULT_PAGE_SIZE;

    private String sortColumn;

    private SortDirection sortDirection = SortDirection.ASC;

    private Long totalRecords = 0L;

    public PageDTO() {
        super();
    }

    public PageDTO(Long pageNo, Long pageSize, String sortColumn, SortDirection sortDirection) {
        super();
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.sortColumn = sortColumn;
        this.sortDirection = sortDirection;
    }

}
