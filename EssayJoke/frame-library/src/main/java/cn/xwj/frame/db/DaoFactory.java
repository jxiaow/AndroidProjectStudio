package cn.xwj.frame.db;

import android.Manifest;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.annotation.RequiresPermission;

import java.io.File;

/**
 * Created by xw on 2018/3/10.
 */

public class DaoFactory {
    private static DaoFactory sInstance;
    private SQLiteDatabase mSQLiteDatabase;

    private DaoFactory() {
        String dirPath = Environment.getExternalStorageDirectory() + File.separator
                + "essayJoke" + File.separator + "db";
        File rootDir = new File(dirPath);
        if (!rootDir.exists()) {
            rootDir.mkdirs();
        }
        File file = new File(rootDir, "db");
        mSQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(file, null);
    }

    @RequiresPermission(value = Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public static DaoFactory getFactory() {
        if (sInstance == null) {
            synchronized (DaoFactory.class) {
                if (sInstance == null) {
                    sInstance = new DaoFactory();
                }
            }
        }
        return sInstance;
    }

    public <T> IDaoSupport<T> getDao(Class<T> tClass) {
        return new DaoSupport<>(mSQLiteDatabase, tClass);
    }
}
