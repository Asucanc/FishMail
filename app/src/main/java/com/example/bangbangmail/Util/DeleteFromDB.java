package com.example.bangbangmail.Util;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Kenshin on 2017/6/4.
 */

public class DeleteFromDB {
    private static final String TAG = "DeleteFromDB";
    /*
    *   发送http请求删除文件
    *   @return boolean
    */
    public static boolean deleteMail(String mailid,String isSender,String isdelete){
        boolean signal = false;
        try{
            Log.d("DeleteMail", "try to delete");
            Log.d(TAG, "DeleteMail: " + mailid + "-" + isSender);
            URL url = new URL("http://120.77.168.57:9090/FishMail/UDeleteMailServlet");
//            URL url = new URL("http://101.201.69.120:8080/HealthGuardian/Validate.do");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(5000);
            //传递数据
            String data = "mailID=" + URLEncoder.encode(mailid,"UTF-8")
                    + "&isSender=" + URLEncoder.encode(isSender,"UTF-8")
                    + "&delete=" + URLEncoder.encode(isdelete,"UTF-8");
            Log.d(TAG, "loginByPost: date:" + data);
            urlConnection.setRequestProperty("Connection","keep-alive");
            urlConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            urlConnection.setRequestProperty("Content-Length",String.valueOf(data.getBytes().length));
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            //获取输出流
            OutputStream os =urlConnection.getOutputStream();
            os.write(data.getBytes());
            os.flush();
            //接收报文
            if(urlConnection.getResponseCode()==200){
                InputStream is = urlConnection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int len = 0;
                byte buffer[] = new byte[1024];
                while((len=is.read(buffer)) != -1){
                    baos.write(buffer,0,len);
                }
                is.close();
                baos.close();
                final String res = new String(baos.toByteArray());
                if(res.equals("true")){
                    signal = true;
                }
                else {
                    signal = false;
                }
            } else {
                Log.d(TAG, "loginByPost: 状态码：" + urlConnection.getResponseCode());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.d(TAG, "DeleteMail: signal:" + signal);
        return signal;
    }
}
