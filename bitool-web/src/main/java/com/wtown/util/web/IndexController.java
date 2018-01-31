/**
 * @author LYU
 * @create 2018年01月11日 16:01
 * @Copyright(C) 2010 - 2018 GBSZ
 * All rights reserved
 */

package com.wtown.util.web;

import com.wtown.util.config.RestaurauntProperties;
import com.wtown.util.entity.dto.RestaurauntRequestDTO;
import com.wtown.util.entity.enums.MessageCodeEnum;
import com.wtown.util.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/index")
public class IndexController extends BaseController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurauntProperties properties;

    @RequestMapping("/getdetail")
    public String getDetail(@RequestBody RestaurauntRequestDTO requestDTO) {
        String resultStr = "";
        String details = restaurantService.getDetailData(requestDTO.getrCode(), requestDTO.getStartTime(), requestDTO.getEndTime());
        resultStr = returnSuccessMessage(details);
        return resultStr;
    }

    @RequestMapping("/getsummary")
    public String getSumary(@RequestBody RestaurauntRequestDTO requestDTO) {
        String resultStr = "";
        String details = restaurantService.getSummaryData(requestDTO.getrCode(), requestDTO.getStartTime(), requestDTO.getEndTime());
        resultStr = returnSuccessMessage(details);
        return resultStr;
    }

    @RequestMapping("/download")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
        String resultStr = "";
        String fileName = request.getParameter("filename");
        String path = properties.getPath();
        File file = new File(path, fileName);
        if (file.exists()) {
            response.setHeader("content-type", "application/octet-stream;charset=UTF-8");
            response.setContentType("application/octet-stream");
            try {
                response.setHeader("Content-Disposition", "attachment;filename=\"" + new String(fileName.getBytes("gbk"), "iso-8859-1") + "\"");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            byte[] buffer = new byte[1024];
            BufferedInputStream bi = null;
            OutputStream os = null;
            try {
                os = response.getOutputStream();
                bi = new BufferedInputStream(new FileInputStream(file));
                int i = bi.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, buffer.length);
                    os.flush();
                    i = bi.read(buffer);
                }
                resultStr = returnSuccessMessage("下载成功");
            } catch (Exception e) {
                resultStr = returnErrorMessage(MessageCodeEnum.DOWNLOAD_ERROR);
            } finally {
                if (bi != null) {
                    try {
                        bi.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            resultStr = returnErrorMessage(MessageCodeEnum.FILE_NOT_EXISTS);
        }
        return resultStr;
    }

    @RequestMapping(value = "/download2", produces = MediaType.TEXT_PLAIN_VALUE)
    public Resource downloadTxt2() {
        return new FileSystemResource(properties.getPath() + File.separator + "餐饮汇总20171201-20171231.xlsx");
    }
}
