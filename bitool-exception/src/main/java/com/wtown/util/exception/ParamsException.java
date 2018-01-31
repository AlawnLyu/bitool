/**
 * @author LYU
 * @create 2018年01月12日 15:36
 * @Copyright(C) 2010 - 2018 GBSZ
 * All rights reserved
 */

package com.wtown.util.exception;

import com.wtown.util.entity.enums.MessageCode;

public class ParamsException extends AbstractBaseException {
    private MessageCode messageCode;
    private String additionalMessage;

    public ParamsException(MessageCode messageCode, String additionalMessage) {
        super();
        this.messageCode = messageCode;
        this.additionalMessage = additionalMessage;
    }


    @Override
    public String getErrorCode() {
        return messageCode.getCode();
    }

    @Override
    public String getErrorMessage() {
        if (additionalMessage != null && !"".equals(additionalMessage)) {
            return messageCode.getMessageDesc() + "(" + additionalMessage + ")";
        }
        return messageCode.getMessageDesc();
    }
}
