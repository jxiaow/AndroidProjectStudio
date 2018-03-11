package cn.xwj.frame.db;

import java.util.Collection;
import java.util.List;

/**
 * Created by xw on 2018/3/10.
 */

public interface IDaoSupport<T> {
    //插入
    long insert(T t);
    //批量插入
    long insert(Collection<T> collection);
    //删除
    int delete(String whereClause, String[] whereArgs);

    //查询
    List<T> query();
}
