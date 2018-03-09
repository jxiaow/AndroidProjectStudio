package cn.xwj.http;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Author: xw
 * Date: 2018-03-09 14:06:24
 * Description: OkHttpEngine <this is description>.
 */

public class OkHttpEngine implements IHttpEngine {

    private static OkHttpClient sHttpClient = new OkHttpClient();

    @Override
    public void get(Context context, String url, ArrayMap<String, Object> params, final HttpCallBack callBack) {

        String finalUrl = HttpUtils.joinParams(url, params);
        Request request = new Request.Builder().url(finalUrl).tag(context).build();
        sHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onSuccess(response.body().string());
            }
        });
    }

    @Override
    public void post(Context context, String url, ArrayMap<String, Object> params, final HttpCallBack callBack) {
        String finalUrl = HttpUtils.joinParams(url, params);
        RequestBody requestBody = appendRequestBody(params);
        final Request request = new Request.Builder().url(finalUrl).tag(context).post(requestBody).build();
        sHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onSuccess(response.body().string());
            }
        });
    }

    private RequestBody appendRequestBody(ArrayMap<String, Object> params) {
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        addParams(builder, params);
        return builder.build();
    }

    private void addParams(MultipartBody.Builder builder, ArrayMap<String, Object> params) {
        if (params == null || params.isEmpty()) {
            return;
        }

        for (String key : params.keySet()) {
            builder.addFormDataPart(key, params.get(key) + "");
            Object value = params.get(key);
            if (value instanceof File) {
                File file = (File) value;
                builder.addFormDataPart(key, file.getName(),
                        RequestBody.create(MediaType.parse(guessMimeType(file.getAbsolutePath())), file));
            } else if (value instanceof List) {
                List<File> fileList = (List<File>) value;
                for (int i = 0; i < fileList.size(); i++) {
                    File file = fileList.get(i);
                    builder.addFormDataPart(key + i, file.getName(),
                            RequestBody.create(MediaType.parse(guessMimeType(file.getAbsolutePath())), file));
                }
            } else {
                builder.addFormDataPart(key, value + "");
            }
        }
    }

    @NonNull
    private String guessMimeType(String filePath) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(filePath);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }
}
