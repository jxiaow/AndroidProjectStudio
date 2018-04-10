package cn.xwj.dbproject.db;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-04-10 2018/4/10
 * Description: SupportDaoFactory
 */
public class SupportDaoFactory {
    private static SupportDaoFactory mFactory;

    //持有数据库的引用
    private SQLiteDatabase mSQLiteDatabases;

    private SupportDaoFactory() {
        createDB();
    }

    private void createDB() {
        //将数据库放到内存卡中
        String dbRootPath = Environment.getExternalStorageDirectory() + File.separator + "test"
                + File.separator + "database";

        File dbRoot = new File(dbRootPath);
        if (!dbRoot.exists()) {
            dbRoot.mkdirs();
        }

        File dbFile = new File(dbRoot, "test.db");
        //打开或者创建一个数据库
        mSQLiteDatabases = SQLiteDatabase.openOrCreateDatabase(dbFile.getPath(), null);
    }

    public static SupportDaoFactory getFactory() {
        if (mFactory == null) {
            synchronized (SupportDaoFactory.class) {
                if (mFactory == null) {
                    mFactory = new SupportDaoFactory();
                }
            }
        }
        return mFactory;
    }

    public <T> ISupportDao<T> getDao(Class<T> tClass) {
        ISupportDao<T> supportDao = new SupportDaoImpl<>(mSQLiteDatabases, tClass);
        return supportDao;
    }
}
