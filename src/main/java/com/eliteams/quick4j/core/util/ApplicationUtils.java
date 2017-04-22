package com.eliteams.quick4j.core.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.aliyun.oss.OSSClient;
import org.apache.commons.codec.digest.DigestUtils;

import static com.alibaba.fastjson.JSON.toJSONString;
import com.thoughtworks.xstream.XStream;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * ApplicationUtils : 程序工具类，提供大量的便捷方法
 *
 * @author StarZou
 * @since 2014-09-28 22:31
 */
public class ApplicationUtils {


    /**
     * 产生一个36个字符的UUID
     *
     * @return UUID
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * md5加密
     *
     * @param value 要加密的值
     * @return md5加密后的值
     */
    public static String md5Hex(String value) {
        return DigestUtils.md5Hex(value);
    }

    /**
     * sha1加密
     *
     * @param value 要加密的值
     * @return sha1加密后的值
     */
    public static String sha1Hex(String value) {
        return DigestUtils.sha1Hex(value);
    }

    /**
     * sha256加密
     *
     * @param value 要加密的值
     * @return sha256加密后的值
     */
    public static String sha256Hex(String value) {
        return DigestUtils.sha256Hex(value);
    }

    /**
     * 检测参数不为null 和“”
     * @param s
     * @return   true  为空，false 不为空
     */
    public static Boolean Checknotnull(String ... s){
         int i=0;
        Boolean result=false;
        for(i=0;i<s.length;i++){
            if(s[i]==null ||"".equals(s[i].trim())){

                result =true;
                return result;
            }
        }


        return result;

    }

    /**
     * 检测Interge参数不为null 和“”
     * @param s
     * @return   true  为空，false 不为空
     */
    public static Boolean Checknotnull_int(Integer ... s){
        int i=0;
        Boolean result=false;
        for(i=0;i<s.length;i++){
            if(s[i]==null ||"".equals(s[i]) || s[i]  ==0){

                result =true;
                return result;
            }
        }


        return result;

    }

    public static void Checknotnull_int_throws(Integer ... s) throws  Exception{
        int i=0;
        Boolean result=false;
        for(i=0;i<s.length;i++){
            if(s[i]==null ||"".equals(s[i]) || s[i]  ==0){

                result =true;
                JSONObject resJSONObj= new JSONObject();
                resJSONObj.put("status","NG");
                resJSONObj.put("error_msg","参数错误");
                throw new Exception( toJSONString(resJSONObj)) ;
            }
        }



    }

    /**
     *  返回错误工具类
     * @param status       网络请求状态
     * @param error_msg    网络错误信息
     * @return
     */
    public static String ReturnError(String status,String error_msg){
        String result=null;
        JSONObject resJSONObj= new JSONObject();
        resJSONObj.put("status",status);
        resJSONObj.put("error_msg",error_msg);
        result=toJSONString(resJSONObj);
        return result;
    }

    /**
     * 返回成功工具类
     * @param s  成对出现，0-键名，1-值；以此类推 双为键名，单为值
     * @return
     */
    public static String ReturnSuccess(Object ... s){

        String result=null;
        JSONObject resJSONObj= new JSONObject();
        if(s.length%2==1)
        {
            return null;
        }
        for (int i=0;i<s.length;) {
            resJSONObj.put((String) s[i],s[i+1]);
            i+=2;
        }
        result=toJSONString(resJSONObj, SerializerFeature.DisableCircularReferenceDetect);
        return result;
    }


    /** * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss * * @param dateDate * @return */
    public static   String dateToStrLong(java.util.Date dateDate)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }


    /**
     * InputStream流转换成String字符串
     * @param inStream InputStream流
     * @param encoding 编码格式
     * @return String字符串
     */
    public static String InputStreamToString(InputStream inStream, String encoding){
        String result = null;
        int _buffer_size=1024;
        try {
            if(inStream != null){
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] tempBytes = new byte[_buffer_size];
                int count = -1;
                while((count = inStream.read(tempBytes, 0, _buffer_size)) != -1){
                    outStream.write(tempBytes, 0, count);
                }
                tempBytes = null;
                outStream.flush();
                result = new String(outStream.toByteArray(), encoding);
            }
        } catch (Exception e) {
            result = null;
        }
        return result;
    }


    /**
     * 将xml 字符串数据映射到JAVA 对象
     * @param xml
     * @param tClass
     * @return
     */
    public static Object GetObjectFromXML(String xml,Class tClass){

        XStream xStreamForResponseData =new XStream();
        xStreamForResponseData.alias("xml",tClass);
        xStreamForResponseData.ignoreUnknownElements();
        return xStreamForResponseData.fromXML(xml);
    }


    public static List<String> upImageToOss(HttpServletRequest request, String user_id, String Flag) throws Exception{

         List<String>  imageURLList=new ArrayList<String>();
        int index=0;

        //解析器解析request的上下文
        CommonsMultipartResolver multipartResolver =
                new CommonsMultipartResolver(request.getSession().getServletContext());
        //先判断request中是否包涵multipart类型的数据，
        if(multipartResolver.isMultipart(request)) {
            //再将request中的数据转化成multipart类型的数据
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                MultipartFile file = multiRequest.getFile((String) iter.next());

                if (file != null) {
                    String fileName = file.getOriginalFilename();

                    if (fileName != null && (!"".equals(fileName.trim()))) {
                        //文件名，加后缀名wowo
                        String prefixion = user_id + Flag + System.currentTimeMillis() + RandomUtil.generateStringnumber(5);
                        //   String prefixion=10000+"_a"+System.currentTimeMillis() +RandomUtil.generateStringnumber(5);
                        String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
                        fileName = prefixion + fileSuffix;
                        String path = "E:/testimage/" + fileName;
                        File localFile = new File(path);

                        //写文件到本地
                        file.transferTo(localFile);

                        // 创建OSSClient实例
                        OSSClient ossClient = new OSSClient(CUSTOM_UTIL.endpoint, CUSTOM_UTIL.accessKeyId, CUSTOM_UTIL.accessKeySecret);
                        // 上传文件
                        ossClient.putObject(CUSTOM_UTIL.BucketName, fileName, localFile);
                        String Imageurl = CUSTOM_UTIL.ImageBaseURL + fileName;

                         if(Imageurl !=null){
                             imageURLList.add(index,Imageurl);
                           //  imageURLList.set(index,Imageurl);
                             index++;
                         }


                        // 删除
                        //ossClient.deleteObject(CUSTOM_UTIL.BucketName, fileName);
                        // 关闭client
                        ossClient.shutdown();
                    }
                }
            }
        }

        return imageURLList;

    }






}
