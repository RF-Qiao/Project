package com.feng.util;
//import javax.servlet.http.HttpServletRequest;
//import java.io.BufferedReader;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class GetDataUtils{
//    public static String getPostData(HttpServletRequest request) {
//        StringBuffer data = new StringBuffer();
//        String line = null;
//        BufferedReader reader = null;
//        try {
//            reader = request.getReader();
//            while (null != (line = reader.readLine()))
//                data.append(line);
//        } catch (IOException e) {
//        } finally{
//        }
//        return data.toString();
//    }

    public static String getPostData(HttpServletRequest request) throws IOException {
        StringBuffer data = new StringBuffer();
        String line=null;
        BufferedReader reader=null;
        reader= request.getReader();
        while ((line=reader.readLine())!=null)
            data.append(line);
        return data.toString();
    }
}
