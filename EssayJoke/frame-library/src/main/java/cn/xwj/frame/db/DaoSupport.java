package cn.xwj.frame.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by xw on 2018/3/10.
 */

public class DaoSupport<T> implements IDaoSupport<T> {
    private SQLiteDatabase mSQLiteDatabase;
    private Class<T> mClass;

    public DaoSupport(SQLiteDatabase sqLiteDatabase, Class<T> tClass) {
        this.mSQLiteDatabase = sqLiteDatabase;
        this.mClass = tClass;
        createTable();
    }

    private void createTable() {
        // 创建表
        /*"create table if not exists Person ("
                + "id integer primary key autoincrement, "
                + "name text, "
                + "age integer, "
                + "flag boolean)";*/

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("create table if not exists ")
                .append(DaoUtils.getTableName(mClass))
                .append(" ( id integer primary key autoincrement, ");
        Field[] fields = mClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            stringBuffer.append(field.getName())
                    .append(" ")
                    .append(DaoUtils.getColumnType(field.getType().getSimpleName()))
                    .append(", ");
        }
        stringBuffer.replace(stringBuffer.length() - 2, stringBuffer.length(), ")");
        mSQLiteDatabase.execSQL(stringBuffer.toString());
    }

    @Override
    public long insert(T t) {
        ContentValues values = createContentValues(t);
        return mSQLiteDatabase.insert(DaoUtils.getTableName(mClass), null, values);
    }

    @Override
    public long insert(Collection<T> collections) {
        if (collections.isEmpty()) {
            return -1;
        }
        mSQLiteDatabase.beginTransaction();
        for (T t : collections) {
            insert(t);
        }
        mSQLiteDatabase.setTransactionSuccessful();
        mSQLiteDatabase.endTransaction();
        return 1;
    }

    @Override
    public int delete(String whereClause, String[] whereArgs) {
        return mSQLiteDatabase.delete(DaoUtils.getTableName(mClass), whereClause, whereArgs);
    }

    @Override
    public List<T> query() {
        Cursor cursor = mSQLiteDatabase.query(DaoUtils.getTableName(mClass), null, null, null, null, null, null);
        return cursorToList(cursor);
    }

    private List<T> cursorToList(Cursor cursor) {
        List<T> list = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            // 不断的从游标里面获取数据
            do {
                try {
                    // 通过反射new对象
                    T instance = mClass.newInstance();
                    Field[] fields = mClass.getDeclaredFields();


                    for (Field field : fields) {
                        // 遍历属性
                        field.setAccessible(true);
                        String name = field.getName();
                        // 获取角标  获取在第几列
                        int index = cursor.getColumnIndex(name);
                        if (index == -1) {
                            continue;
                        }

                        // 通过反射获取 游标的方法  field.getType() -> 获取的类型
                        Method cursorMethod = cursorMethod(field.getType());
                        if (cursorMethod != null) {
                            // 通过反射获取了 value
                            Object value = cursorMethod.invoke(cursor, index);
                            if (value == null) {
                                continue;
                            }

                            // 处理一些特殊的部分
                            if (field.getType() == boolean.class || field.getType() == Boolean.class) {
                                if ("0".equals(String.valueOf(value))) {
                                    value = false;
                                } else if ("1".equals(String.valueOf(value))) {
                                    value = true;
                                }
                            } else if (field.getType() == char.class || field.getType() == Character.class) {
                                value = ((String) value).charAt(0);
                            } else if (field.getType() == Date.class) {
                                long date = (Long) value;
                                if (date <= 0) {
                                    value = null;
                                } else {
                                    value = new Date(date);
                                }
                            }

                            // 通过反射注入
                            field.set(instance, value);
                        }
                    }
                    // 加入集合
                    list.add(instance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    private Method cursorMethod(Class<?> type) throws NoSuchMethodException {
        String methodName = getColumnMethodName(type);
        return Cursor.class.getMethod(methodName, int.class);
    }

    private String getColumnMethodName(Class<?> fieldType) {
        String typeName;
        if (fieldType.isPrimitive()) {
            typeName = DaoUtils.capitalize(fieldType.getName());
        } else {
            typeName = fieldType.getSimpleName();
        }
        String methodName = "get" + typeName;
        if ("getBoolean".equals(methodName)) {
            methodName = "getInt";
        } else if ("getChar".equals(methodName) || "getCharacter".equals(methodName)) {
            methodName = "getString";
        } else if ("getDate".equals(methodName)) {
            methodName = "getLong";
        } else if ("getInteger".equals(methodName)) {
            methodName = "getInt";
        }
        return methodName;

    }

    private ContentValues createContentValues(T t) {
        ContentValues contentValues = new ContentValues();
        try {
            Field[] fields = t.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String key = field.getName();
                Object value = field.get(t);
                Method putMethod = ContentValues.class.getDeclaredMethod("put",
                        String.class, value.getClass());
                putMethod.invoke(contentValues, key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentValues;
    }
}
