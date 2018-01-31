/**
 * @author LYU
 * @create 2018年01月12日 15:35
 * @Copyright(C) 2010 - 2018 GBSZ
 * All rights reserved
 */

package com.wtown.util.exception;

public abstract class AbstractBaseException extends Exception {
    public AbstractBaseException() {
        super();
    }

    public abstract String getErrorCode();

    public abstract String getErrorMessage();
}
