/**
 * @author LYU
 * @create 2018年01月12日 9:47
 * @Copyright(C) 2010 - 2018 GBSZ
 * All rights reserved
 */

package com.wtown.util.common.excelutils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class TemplateFileUtil {
    public static InputStream getTemplates(String tempName) throws IOException {
        Resource resource = new ClassPathResource("excel-templates/" + tempName);
        return resource.getInputStream();
    }
}
