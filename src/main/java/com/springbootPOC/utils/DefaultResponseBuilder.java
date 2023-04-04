package com.springbootPOC.utils;

import com.springbootPOC.constants.ResponseMessageCodes;
import com.springbootPOC.dto.DefaultResponse;
import com.springbootPOC.dto.DefaultResponseWithPage;
import com.springbootPOC.dto.PageDTO;
import org.springframework.http.HttpStatus;


/**
 * @author mamta.t
 */
public final class DefaultResponseBuilder {

    public static <T> DefaultResponse<T> success(T data, PageDTO page) {
        return new DefaultResponseWithPage<>(ResponseMessageCodes.SUCCESS.getCode(), HttpStatus.OK, data, page);
    }
        public static <T> DefaultResponse<T> noContent(T data, PageDTO page) {
            return new DefaultResponseWithPage<>(ResponseMessageCodes.NO_CONTENT.getCode(), HttpStatus.OK, data, page);
        }

}
