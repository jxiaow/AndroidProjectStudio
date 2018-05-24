package cn.xwj.dbtest.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.util.ArrayMap;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-05-12 2018/5/12
 * Description: DaoSupportImpl
 */
public class DaoSupportImpl<T> implements IDaoSupport<T> {
    private SQLiteDatabase mSQLiteDatabase;
    private Class<T> mClazz;
    private ArrayMap<String, Method> mPutMethodMap = new ArrayMap<>();

    @Override
    public void init(SQLiteDatabase sqLiteDatabase, Class<T> clazz) {
        this.mSQLiteDatabase = sqLiteDatabase;
        this.mClazz = clazz;
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE IF NOT EXISTS ")
                .append(mClazz.getSimpleName())
                .append(" ( id integer  PRIMARY KEY AUTOINCREMENT ");
        Field[] fields = mClazz.getDeclaredFields();
        for (Field field : fields) {
            sb.append(", ").append(field.getName()).append(" ")
                    .append(field.getType().getSimpleName());
        }
        sb.append(" )");
        mSQLiteDatabase.execSQL(sb.toString());
    }


    @Override
    public long insert(T t) {
        ContentValues contentValues = new ContentValues();
        Field[] fields = mClazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object object = field.get(t);
                Method putMethod = mPutMethodMap.get(object.getClass().getSimpleName());
                if (putMethod == null) {
                    putMethod = ContentValues.class.getDeclaredMethod("put", String.class,
                            object.getClass());
                }
                putMethod.invoke(contentValues, field.getName(), object);
            }

            return mSQLiteDatabase.insert(mClazz.getSimpleName(),
                    null,
                    contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mPutMethodMap.clear();
        }
        return -1;
    }

    @Override
    public long insertAll(List<T> data) {
        if (data == null || data.isEmpty()) {
            return -1;
        }
        long count = 0;
        mSQLiteDatabase.beginTransaction();
        for (T t : data) {
            count++;
            if (insert(t) == -1) {
                break;
            }
        }
        if (count == data.size()) {
            mSQLiteDatabase.setTransactionSuccessful();
        }
        mSQLiteDatabase.endTransaction();
        return 0;
    }

    @Override
    public long delete(T t) {
        mSQLiteDatabase.delete(mClazz.getSimpleName(), "name=?", new String[]{"zhangsan"});
        return 0;
    }

    @Override
    public long delete(List<T> data) {
        return 0;
    }
}
