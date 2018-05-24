package cn.xwj.dbtest.db;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-05-12 2018/5/12
 * Description: IDaoSupport
 */
public interface IDaoSupport<T> {

    void init(SQLiteDatabase sqLiteDatabase, Class<T> clazz);

    /**
     * 插入数据
     *
     * @param t 泛型实例
     * @return 插入成功，返回插入条数，失败 -1
     */
    long insert(T t);

    /**
     * 插入数据list集合
     *
     * @param data 泛型实例集合
     * @return 插入成功，返回插入条数，失败 -1
     */
    long insertAll(List<T> data);

    /**
     * 删除数据
     *
     * @param t 根据泛型删除数据
     * @return 成功 1，否则 -1
     */
    long delete(T t);

    /**
     * 删除集合中的数据
     *
     * @param data
     * @return
     */
    long delete(List<T> data);


}
