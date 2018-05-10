package cn.xwj.httptest.http;

import android.text.TextUtils;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-05-09 2018/5/9
 * Description: OkHttpEngine
 */
public class OkHttpEngine implements IHttpEngine {
    private static OkHttpClient sHttpClient;

    static {
        sHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30000, TimeUnit.MILLISECONDS)
                .readTimeout(30000, TimeUnit.MILLISECONDS)
                .writeTimeout(30000, TimeUnit.MILLISECONDS)
                .build();
    }

    @Override
    public void get(final EngineOption option, final IEngineCallback callback) {

        //拼接url
        String url = option.getUrl();
        Map<String, Object> params = option.getParams();
        url = EngineUtils.gengerateUrl(url, params);
        //设置url
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        //设置header
        if (!option.getHeaders().isEmpty()) {
            for (Map.Entry<String, String> entry : option.getHeaders().entrySet()) {
                builder.header(entry.getKey(), entry.getValue());
            }
        }
        //设置tag
        Object tag = option.getTag();
        if (tag != null) {
            builder.tag(tag);
        }
        //开始请求
        sHttpClient.newCall(builder.build())
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        if (call.isCanceled()) {
                            return;
                        }
                        handleError(e, option, callback);
                    }

                    @Override
                    public void onResponse(Call call, Response response) {
                        handleSuccess(response, option, callback);
                    }
                });

    }

    /**
     * 处理成功返回结果
     *
     * @param response 成功请求返回的response
     * @param option   {@link EngineOption}
     * @param callback engineCallBack 请求结果返回
     */
    private void handleSuccess(Response response, EngineOption option, final IEngineCallback callback) {
        if (response.isSuccessful()) {
            try {
                final String result = response.body().string();
                switch (option.getThreadType()) {
                    case EngineOption.WORK_THREAD:
                        callback.onSuccess(result);
                        break;
                    case EngineOption.UI_THREAD:
                        HttpUtils.sHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.onSuccess(result);
                            }
                        });
                        break;
                }

            } catch (IOException e) {
                handleError(e, option, callback);
            }
        } else {
            handleError(new Exception(response.message()), option, callback);
        }
    }

    /**
     * 处理请求返回失败的情况
     *
     * @param e        错误exception
     * @param option   {@link EngineOption}
     * @param callback 请求回调
     */
    private void handleError(final Exception e, EngineOption option,
                             final IEngineCallback callback) {
        switch (option.getThreadType()) {
            case EngineOption.WORK_THREAD:
                callback.onError(e);
                break;
            case EngineOption.UI_THREAD:
                HttpUtils.sHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onError(e);
                    }
                });
                break;
        }
    }

    @Override
    public void post(final EngineOption option, final IEngineCallback callback) {

        //拼接url
        String url = option.getUrl();
        //获取Content-Type 的值
        String contentType = option.getHeaders().get("Content-Type");
        RequestBody requestBody = getRequestBody(contentType, option.getParams());
        //设置url
        Request.Builder builder = new Request.Builder().url(url).post(requestBody);
        //设置header
        if (!option.getHeaders().isEmpty()) {
            for (Map.Entry<String, String> entry : option.getHeaders().entrySet()) {
                builder.header(entry.getKey(), entry.getValue());
            }
        }
        //设置tag
        Object tag = option.getTag();
        if (tag != null) {
            builder.tag(tag);
        }
        //开始请求
        sHttpClient.newCall(builder.build())
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        if (call.isCanceled()) {
                            return;
                        }
                        handleError(e, option, callback);
                    }

                    @Override
                    public void onResponse(Call call, Response response) {
                        if(call.isCanceled()){
                            return;
                        }
                        handleSuccess(response, option, callback);
                    }
                });

    }

    private RequestBody getRequestBody(String contentType, Map<String, Object> params) {
        //body 是json 格式
        if (FormData.JSON_DATA.getValue().equals(contentType)) {
            Object json = params.get("json");
            if (json == null) {
                throw new IllegalArgumentException("请将json 加入到key 为 json的params中");
            }
            return RequestBody.create(MediaType.parse(contentType),
                    String.valueOf(json));
        }
        // body是复杂的类型，如文件
        if (FormData.MULTI_PART_DATA.getValue().equals(contentType)) {
            MultipartBody.Builder builder = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM);
            addParams(builder, params);
            return builder.build();
        }
        // body 为form形式
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            builder.add(entry.getKey(), String.valueOf(entry.getValue()));
        }
        return builder.build();
    }

    /**
     * 拼接参数
     *
     * @param builder 复杂的builder
     * @param params  请求参数
     */
    private void addParams(MultipartBody.Builder builder, Map<String, Object> params) {

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof File) {

                File file = (File) value;
                builder.addFormDataPart(key, file.getName(), MultipartBody.create(
                        MediaType.parse(guessType(file.getAbsolutePath())), file));
            } else if (value instanceof List) {
                List<File> fileList = (List<File>) value;
                for (int i = 0; i < fileList.size(); i++) {
                    File file = fileList.get(i);
                    builder.addFormDataPart(key + i, file.getName(),
                            MultipartBody.create(MediaType.parse(guessType(file.getAbsolutePath())), file));
                }
            } else {
                builder.addFormDataPart(key, String.valueOf(value));
            }
        }


    }

    private String guessType(String filePath) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(filePath);
        if (TextUtils.isEmpty(contentTypeFor)) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }

    @Override
    public void cancel(Object tag) {

        if (sHttpClient == null) {
            return;
        }

        //查找当前需要取消的tag是否在未执行的请求中
        for (Call call : sHttpClient.dispatcher().queuedCalls()) {
            if (call.request().tag().equals(tag)) {
                call.cancel();
            }
        }

        //查找当前需要请求的tag是否在正在执行的请求中
        for (Call call : sHttpClient.dispatcher().runningCalls()) {
            if (call.request().tag().equals(tag)) {
                call.cancel();
            }
        }
    }
}
