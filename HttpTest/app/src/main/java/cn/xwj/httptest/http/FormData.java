package cn.xwj.httptest.http;

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-05-09 2018/5/9
 * Description: FormData
 */
public enum FormData {
    FORM_DATA("application/form-data"),
    JSON_DATA("application/json;charset=utf-8"),
    MULTI_PART_DATA("application/multi-part");

    private String value;

    FormData(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
