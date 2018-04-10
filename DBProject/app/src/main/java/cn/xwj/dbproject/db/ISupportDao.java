package cn.xwj.dbproject.db;

import java.util.Collection;

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-04-10 2018/4/10
 * Description: ISupportDao
 */
public interface ISupportDao<T> {

    /**
     * 插入数据
     */
    long insert(T t);

    /**
     * 插入全部数据
     */
    void insertAll(Collection<T> tCollection);

}
