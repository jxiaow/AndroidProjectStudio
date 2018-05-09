package cn.xwj.httptest.http;

import android.support.annotation.NonNull;

import java.util.Map;

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-05-09 2018/5/9
 * Description: EngineUtils
 */
public class EngineUtils {

    /**
     * 拼接参数和url
     *
     * @param url    url
     * @param params 请求参数
     * @return 拼接后的url
     */
    public static String gengerateUrl(@NonNull String url, @NonNull Map<String, Object> params) {

        StringBuilder builder = new StringBuilder(url);
        if (!url.contains("?")) {
            builder.append("?");
        } else if (!url.endsWith("?")) {
            builder.append("&");
        }

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            builder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}
