package com.springbootPOC.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.springbootPOC.constants.UserSortingColumns;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;


import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * @author mamta.t
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DefaultResponseWithPage<T> extends DefaultResponse<T> {

    private Long page;
    private Long pageSize;
    private Long totalRecords;


    public DefaultResponseWithPage(int code, HttpStatus httpStatus, T data, PageDTO<UserSortingColumns> page) {
        super(code, data, httpStatus);
        this.page = page.getPageNo();
        this.pageSize = page.getPageSize();
        this.totalRecords = page.getTotalRecords();
    }
}
