package com.hengxunda;

import com.hengxunda.dfs.utils.MD5Utils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class FastdfsCoreApplicationTests {

    public static final String HEADER_APP_KEY = "dfs-request-app-key"; // 应用appKey
    public static final String HEADER_TIMESTAMP = "dfs-request-timestamp"; // 时间戳
    public static final String HEADER_SIGN = "dfs-request-sign"; // 签名

    static String appKey = "dfs";
    static String appSecret = "dfs_secret";
    static String fileAbsPath = "E:\\test.png";

    static String newLine = "\r\n";

    public static void testUpload() {

        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        BufferedReader reader = null;
        HttpURLConnection connection;
        try {
            // 换行符
            final String boundaryPrefix = "--";
            // 定义数据分隔线
            String BOUNDARY = "========7d4a6d158c9";
            // 服务器的域名
            URL url = new URL("http://localhost:8808/dfs/auth/v1/upload/self");
            connection = (HttpURLConnection) url.openConnection();
            // 设置为POST情
            connection.setRequestMethod("POST");
            // 发送POST请求必须设置如下两行
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setChunkedStreamingMode(0);

            File file = new File(fileAbsPath);
            FileInputStream fis = new FileInputStream(file);
            // 设置请求头参数
            connection.setRequestProperty("connectionection", "Keep-Alive");
            connection.setRequestProperty("Content-Length", String.valueOf(fis.available()));
            connection.setRequestProperty("Charsert", "UTF-8");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

            // -------------开始设置鉴权参数-------------
            String timestamp = String.valueOf(new Date().getTime());
            connection.setRequestProperty(HEADER_APP_KEY, appKey);
            connection.setRequestProperty(HEADER_TIMESTAMP, timestamp);
            connection.setRequestProperty(HEADER_SIGN, MD5Utils.md5(appendSeq(appKey, appSecret, timestamp)));
            // -------------结束设置鉴权参数---------------

            // 上传文件
            out = new BufferedOutputStream(connection.getOutputStream());
            StringBuilder sb = new StringBuilder();
            sb.append(boundaryPrefix);
            sb.append(BOUNDARY);
            sb.append(newLine);
            // 文件参数,photo参数名可以随意修改
            sb.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"" + newLine);
            // 参数头设置完以后需要两个换行，然后才是参数内容
            sb.append(newLine);

            // 将参数头的数据写入到输出流中
            out.write(sb.toString().getBytes("UTF-8"));
            out.flush();

            // 数据输入流,用于读取文件数据
            in = new BufferedInputStream(fis);
            byte[] bufferOut = new byte[1 * 1024 * 1024];
            int bytes = 0;
            // 每次读1MB数据,并且将文件数据写入到输出流中
            while ((bytes = in.read(bufferOut)) > 0) {
                out.write(bufferOut, 0, bytes);
                out.flush();
            }
            // 最后添加换行
            out.write(newLine.getBytes("UTF-8"));
            // 定义最后数据分隔线，即--加上BOUNDARY再加上--。
            byte[] end_data = (boundaryPrefix + BOUNDARY + boundaryPrefix + newLine).getBytes("UTF-8");
            // 写上结尾标识
            out.write(end_data);
            out.flush();
            int status = connection.getResponseCode();
            if (status == 200) { // 不等于 200个表示异常
                // 定义BufferedReader输入流来读取URL的响应
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } else {

            }
        } catch (Exception e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(reader);
        }
    }

    private static String appendSeq(String appKey, String appSecret, String timestamp) {
        StringBuilder seq = new StringBuilder(256);
        seq.append(appKey);
        seq.append("$");
        seq.append(appSecret);
        seq.append("$");
        seq.append(timestamp);

        return seq.toString();
    }

    public static void main(String[] args) throws Exception {
        testUpload();
    }
}
