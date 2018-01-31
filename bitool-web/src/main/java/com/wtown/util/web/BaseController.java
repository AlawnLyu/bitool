/**
 * @author LYU
 * @create 2018年01月12日 15:11
 * @Copyright(C) 2010 - 2018 GBSZ
 * All rights reserved
 */

package com.wtown.util.web;

import com.wtown.util.convert.json.JsonDataUtil;
import com.wtown.util.convert.xml.XmlDataUtil;
import com.wtown.util.entity.dto.ResultDTO;
import com.wtown.util.entity.enums.MessageCodeEnum;
import com.wtown.util.exception.ParamsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class BaseController {
    protected static Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private JsonDataUtil jsonDataUtil;

    @Autowired
    private XmlDataUtil xmlDataUtil;

    protected String returnErrorMessage(MessageCodeEnum codeEnum) {
        return returnErrorMessage(codeEnum.getCode(), codeEnum.getMessageDesc());
    }

    protected String returnErrorMessage(String errorCode, String errorMessage) {
        String result = "";
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(errorCode);
        resultDTO.setMessage(errorMessage);
        logger.debug("返回错误内容：{}", resultDTO.toString());
        try {
            result = jsonDataUtil.writeObject(resultDTO);
        } catch (IOException e) {
            e.printStackTrace();
            result = "内部错误";
        }
        return result;
    }

    protected String returnSuccessMessage(Object data){
        String result = "";
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(MessageCodeEnum.SUCCESS.getCode());
        resultDTO.setMessage(MessageCodeEnum.SUCCESS.getMessageDesc());
        resultDTO.setData(data);
        logger.debug("返回成功内容：{}", resultDTO.toString());
        try {
            result = jsonDataUtil.writeObject(resultDTO);
        } catch (IOException e) {
            e.printStackTrace();
            result = "内部错误";
        }
        return result;
    }

    @ExceptionHandler(ParamsException.class)
    public String handlerException(ParamsException exception) {
        return returnErrorMessage(exception.getErrorCode(), exception.getErrorMessage());
    }

    protected <T> T getObjectByJson(String data, Class<T> clazz) throws IOException {
        return jsonDataUtil.readObject(data, clazz);
    }

    protected <T> T getObjectByXml(String data, Class<T> clazz) throws IOException {
        return xmlDataUtil.readObject(data, clazz);
    }

    protected String getXmlStr(Object data) throws IOException {
        return xmlDataUtil.writeObject(data);
    }

    protected String getJsonStr(Object data) throws IOException {
        return jsonDataUtil.writeObject(data);
    }
}
