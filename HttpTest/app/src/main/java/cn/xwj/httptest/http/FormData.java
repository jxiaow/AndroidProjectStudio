package cn.xwj.httptest.http;

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-05-09 2018/5/9
 * Description: FormData 表示post常用的三种提交方式
 */
public enum FormData {
    FORM_DATA("application/x-www-form-urlencoded;charset=utf-8"),
    JSON_DATA("application/json;charset=utf-8"),
    MULTI_PART_DATA("multipart/form-data;charset=utf-8");

    private String value;

    FormData(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
