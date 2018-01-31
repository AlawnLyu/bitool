/**
 * @author LYU
 * @create 2018年01月13日 9:16
 * @Copyright(C) 2010 - 2018 GBSZ
 * All rights reserved
 */

package com.wtown.util.common.fileutils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class FileUtil {
    public static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    private static FileUtil fu = new FileUtil();

    private FileUtil() {
    }

    public static FileUtil getInstance() {
        return fu;
    }

    public boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                if (logger.isDebugEnabled()) {
                    logger.debug("删除单个文件" + fileName + "成功！");
                }
                return true;
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug("删除单个文件" + fileName + "失败！");
                }
                return false;
            }
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("删除单个文件失败：" + fileName + "不存在！");
            }
            return false;
        }
    }

    public void renameFile(String path, String oldName, String newName) {
        //新的文件名和以前文件名不同时,才有必要进行重命名
        if (!oldName.equals(newName)) {
            File oldFile = new File(path + "/" + oldName);
            File newFile = new File(path + "/" + newName);
            if (!oldFile.exists()) {
                return;//重命名文件不存在
            }
            //若在该目录下已经有一个文件和新文件名相同，则不允许重命名
            if (newFile.exists()) {
                if (logger.isDebugEnabled()) {
                    logger.debug(newName + "已经存在！");
                }
            } else {
                oldFile.renameTo(newFile);
            }
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("新文件名和旧文件名相同...");
            }
        }
    }
}
