package com.springbootPOC.validator;

import com.springbootPOC.constants.UserSortingColumns;
import com.springbootPOC.dto.PageDTO;
import com.springbootPOC.exception.InvalidArgumentException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.springbootPOC.constants.UserSortingColumns.USER_NAME;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @author mamta.t
 */
@Component
@Slf4j
public class UserValidator {
    public void validateUserSortColumn(PageDTO<UserSortingColumns> page) throws InvalidArgumentException {

        if(isBlank(page.getSortColumn())){
                log.debug("No sort column received, setting default sort column to : {}", USER_NAME.getSortColumn());
                page.setSortColumn(USER_NAME.getSortColumn());
            }
            else {
                UserSortingColumns userSortingColumns = UserSortingColumns.fromValue(page.getSortColumn());
                if(isNull(userSortingColumns))
                {
                    throw new InvalidArgumentException("Invalid Sort Column value"+" : " + page.getSortColumn());
                }
                page.setSortColumn(page.getSortColumn());
            }
    }
}
