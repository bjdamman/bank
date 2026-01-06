package com.bank.bank.exception;

import java.util.Map;

/**
 * @author shuang.kou
 */
public class AccountAlreadyExistException extends BaseException {

    public AccountAlreadyExistException(Map<String, Object> data) {
        super(ErrorCode.ACCOUNT_NAME_ALREADY_EXIST, data);
    }
}
