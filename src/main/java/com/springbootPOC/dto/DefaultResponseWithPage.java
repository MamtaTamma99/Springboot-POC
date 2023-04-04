package com.springbootPOC.dto;

import com.springbootPOC.constants.UserSortingColumns;
import com.springbootPOC.dbo.User;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * @author mamta.t
 */
@Data
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
