package com.springbootPOC.utils;

import com.springbootPOC.constants.UserSortingColumns;
import com.springbootPOC.dto.PageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;

import static java.util.Objects.nonNull;

/**
 * @author mamta.t
 */
public final class PageUtils {

    public static PageRequest getPageableFromPageDTO(PageDTO<UserSortingColumns> page) {
        String direction = page.getSortDirection().getDirection();
        return PageRequest.of(Math.toIntExact(page.getPageNo()), Math.toIntExact(page.getPageSize()), Sort.Direction.valueOf(direction), page.getSortColumn());
    }

    public static <T> List<T> getContentFromPage(Page<T> page) {
        if (nonNull(page) && !(page.getContent().isEmpty())) {
            return page.getContent();
        }
        return Collections.emptyList();
    }
}
