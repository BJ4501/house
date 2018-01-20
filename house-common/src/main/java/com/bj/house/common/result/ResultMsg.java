package com.bj.house.common.result;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import org.eclipse.jetty.util.UrlEncoded;

import javax.xml.transform.Result;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by BJ on 2018/1/20.
 */
public class ResultMsg {
    public static final String errorMsgKey = "errorMsg";

    public static final String successMsgKey = "successMsg";

    private String errorMsg;

    private String successMsg;

    public boolean isSuccess(){
        return errorMsg == null;
    }

    public static ResultMsg errorMsg(String msg){
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setErrorMsg(msg);
        return resultMsg;
    }

    public static ResultMsg successMsg(String msg){
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setSuccessMsg(msg);
        return resultMsg;
    }

    public Map<String,String> asMap(){
        //使用Guava工具构建Map
        Map<String,String> map = Maps.newHashMap();
        map.put(successMsgKey,successMsg);
        map.put(errorMsgKey,errorMsg);
        return map;
    }

    //实现将参数返回为url格式
    public String asUrlParams(){
        Map<String,String> map = asMap();
        Map<String,String> newMap = Maps.newHashMap();
        map.forEach((k,v) -> {if(v!=null) try {
            newMap.put(k, URLEncoder.encode(v,"utf-8"));
        } catch (UnsupportedEncodingException e) {
            //e.printStackTrace();
        } });

        //将map拼接为&符号和=号
        return Joiner.on("&").useForNull("").withKeyValueSeparator("=").join(newMap);
    }

/*    public String asUrlParams(){
        Map<String, String> map = asMap();
        Map<String, String> newMap = Maps.newHashMap();
        map.forEach((k,v) -> {if(v!=null)
            try {
                newMap.put(k, URLEncoder.encode(v,"utf-8"));
            } catch (UnsupportedEncodingException e) {

            }});
        return Joiner.on("&").useForNull("").withKeyValueSeparator("=").join(newMap);
    }*/

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getSuccessMsg() {
        return successMsg;
    }

    public void setSuccessMsg(String successMsg) {
        this.successMsg = successMsg;
    }
}
