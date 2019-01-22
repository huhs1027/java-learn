package utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

/**
 * http请求工具类
 *
 * @author huhongsen
 * @date 2018/9/19
 */
public final class HttpUtils {
    private HttpUtils() {
    }

    private static CloseableHttpClient httpClient = HttpClients.createDefault();

    /**
     * post json请求
     */
    public static String doPostJson(String url, String json) throws IOException {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json; charset=utf-8");
        if (StringUtils.isNotBlank(json)) {
            StringEntity stringEntity = new StringEntity(json, "utf-8");
            httpPost.setEntity(stringEntity);
        }
        CloseableHttpResponse response = httpClient.execute(httpPost);
        return EntityUtils.toString(response.getEntity(), "utf-8");
    }

    /**
     * post请求
     */
    public static String doPost(String url, Map<String, Object> pramMap) throws IOException {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        String pram = makeQuery(pramMap);
        StringEntity stringEntity = new StringEntity(pram, "utf-8");
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        return EntityUtils.toString(response.getEntity(), "utf-8");
    }

    /**
     * get请求
     */
    public static String doGet(String url, Map<String, Object> pramMap) throws IOException {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        if (MapUtils.isNotEmpty(pramMap)) {
            StringBuilder sb;
            if (url.indexOf("?") > 0) {
                sb = new StringBuilder(url);
            } else {
                sb = new StringBuilder(url).append("?");
            }
            sb.append(makeQuery(pramMap));
            url = sb.toString();
        }
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        return EntityUtils.toString(response.getEntity(), "utf-8");
    }

    /**
     * get请求
     */
    public static String doGet(String url, String pram) throws IOException {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        if (StringUtils.isNotBlank(pram)) {
            StringBuilder sb;
            if (url.indexOf("?") > 0) {
                sb = new StringBuilder(url).append("&").append(pram);
            } else {
                sb = new StringBuilder(url).append("?").append(pram);
            }
            url = sb.toString();
        }
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        return EntityUtils.toString(response.getEntity(), "utf-8");
    }

    private static String makeQuery(Map<String, Object> pramMap) {
        if (MapUtils.isEmpty(pramMap)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean hasPram = false;
        for (Map.Entry<String, Object> stringObjectEntry : pramMap.entrySet()) {
            if (hasPram) {
                sb.append("&");
            } else {
                hasPram = true;
            }
            sb.append(stringObjectEntry.getKey()).append("=").append(stringObjectEntry.getValue().toString());
        }
        return sb.toString();
    }
}
