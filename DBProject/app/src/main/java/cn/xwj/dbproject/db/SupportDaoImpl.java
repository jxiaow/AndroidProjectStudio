package cn.xwj.dbproject.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-04-10 2018/4/10
 * Description: SupportDaoImpl
 */
public class SupportDaoImpl<T> implements ISupportDao<T> {
    private SQLiteDatabase mSQLiteDatabases;
    private Class<T> mTClasss;
    private Map<String, Method> mMethodMaps = new HashMap<>();

    public SupportDaoImpl(SQLiteDatabase sqLiteDatabase, Class<T> tClass) {
        this.mSQLiteDatabases = sqLiteDatabase;
        this.mTClasss = tClass;
        createTableIfNotExist();
    }

    private void createTableIfNotExist() {

        Field[] declaredFields = mTClasss.getDeclaredFields();
        if (declaredFields == null || declaredFields.length <= 0) {
            throw new IllegalArgumentException(mTClasss.getSimpleName() + "has not field, " +
                    "so can't create table");
        }
        StringBuilder createTableBuilder = new StringBuilder();
        createTableBuilder.append("create table if not exists ")
                .append(mTClasss.getSimpleName()).append(" ( id integer primary key autoincrement, ");
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            createTableBuilder.append(declaredField.getName())
                    .append(" ")
                    .append(declaredField.getType().getSimpleName())
                    .append(" , ");

        }
        createTableBuilder.append(" ); ");
        mSQLiteDatabases.execSQL(createTableBuilder.toString());
    }

    @Override
    public long insert(T t) {
        try {
            ContentValues contentValues = contentValueByObj(t);
            return mSQLiteDatabases.insert(mTClasss.getSimpleName(), null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void insertAll(Collection<T> tCollection) {
        if (tCollection == null || tCollection.isEmpty()) {
            return;
        }
        mSQLiteDatabases.beginTransaction();
        try {
            for (T t : tCollection) {
                mSQLiteDatabases.insert(mTClasss.getSimpleName(), null, contentValueByObj(t));
            }
            mSQLiteDatabases.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mSQLiteDatabases.endTransaction();
    }

    private ContentValues contentValueByObj(T t) throws Exception {
        ContentValues contentValues = new ContentValues();
        Field[] fields = mTClasss.getDeclaredFields();

        for (Field field : fields) {
            String name = field.getName();
            Object object = field.get(t);
            Method method = mMethodMaps.get(field.getType().getName());
            if (method == null) {
                method = ContentValues.class.getMethod("put", String.class,
                        object.getClass());
                mMethodMaps.put(field.getType().getName(), method);
            }

            method.invoke(contentValues, name, object);
        }
        return contentValues;
    }
}
