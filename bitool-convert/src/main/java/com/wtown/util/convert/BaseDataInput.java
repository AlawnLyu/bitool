/**
 * @author LYU
 * @create 2018-01-12-9:34
 * @Copyright(C) 2010 - 2018 GBSZ
 * All rights reserved
 */

package com.wtown.util.convert;

import java.io.IOException;

public interface BaseDataInput {
    <T> T readObject(String value, Class<T> tClass) throws IOException;
}
