package cn.xwj.http;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xw on 2018/3/10.
 */

public class OkHttpEngine implements IHttpEngine {

    private OkHttpClient mOkHttpClient = new OkHttpClient();

    @Override
    public void get(Context context, String url, ArrayMap<String, Object> params, final IEngineCallBack callBack) {

        String finalUrl = HttpUtils.generateUrl(url, params);
        final Request request = new Request.Builder()
                .url(finalUrl)
                .tag(context)
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
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
    public void post(Context context, String url, ArrayMap<String, Object> params, final IEngineCallBack callBack) {
        String finalUrl = HttpUtils.generateUrl(url, params);
        MultipartBody multipartBody = appendBody(params);
        Request request = new Request.Builder()
                .url(finalUrl)
                .tag(context)
                .post(multipartBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
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

    private MultipartBody appendBody(ArrayMap<String, Object> params) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        addMultipart(builder, params);
        return builder.build();
    }

    private void addMultipart(MultipartBody.Builder builder, ArrayMap<String, Object> params) {
        if (params == null || params.isEmpty()) {
            return;
        }

        for (String key : params.keySet()) {
            Object value = params.get(key);
            if (value == null) {
                builder.addFormDataPart(key, "");
                continue;
            }
            if (value instanceof File) {
                File file = (File) value;
                builder.addFormDataPart(key, file.getName(),
                        MultipartBody.create(MediaType.parse(guessMimeType(file.getAbsolutePath())), file));
            } else if (value instanceof List) {
                List<File> fileList = (List<File>) value;
                for (int i = 0; i < fileList.size(); i++) {
                    File file = fileList.get(i);
                    builder.addFormDataPart(key + i, file.getName(), MultipartBody.create(MediaType.parse(guessMimeType(file.getAbsolutePath())), file));
                }
            } else {
                builder.addFormDataPart(key, value.toString());
            }
        }
    }

    @NonNull
    private String guessMimeType(String absolutePath) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(absolutePath);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }
}
