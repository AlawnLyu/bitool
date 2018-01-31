/**
 * @author LYU
 * @create 2018-01-12-9:35
 * @Copyright(C) 2010 - 2018 GBSZ
 * All rights reserved
 */

package com.wtown.util.convert;

import java.io.IOException;

public interface BaseDataOutput {
    <T> String writeObject(T t) throws IOException;
}
