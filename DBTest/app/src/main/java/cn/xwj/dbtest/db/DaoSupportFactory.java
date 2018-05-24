package cn.xwj.dbtest.db;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v4.util.ArrayMap;

import java.io.File;

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-05-12 2018/5/12
 * Description: DaoFactory
 */
public class DaoSupportFactory {

    private static DaoSupportFactory sFactory;
    private SQLiteDatabase mSQLiteDatabase;
    private ArrayMap<String, IDaoSupport> sDaoMap = new ArrayMap<>();


    private DaoSupportFactory() {
        String path = Environment.getExternalStorageDirectory() + File.separator + "test" + File.separator + "db";
        File dbRoot = new File(path);
        if (!dbRoot.exists()) {
            dbRoot.mkdirs();
        }

        File dbFile = new File(dbRoot, "test.db");
        mSQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(dbFile, null);
    }


    public static DaoSupportFactory getFactory() {
        if (sFactory == null) {
            synchronized (DaoSupportFactory.class) {
                if (sFactory == null) {
                    sFactory = new DaoSupportFactory();
                }
            }
        }
        return sFactory;
    }

    public <T> IDaoSupport<T> getSupportDao(Class<T> clazz) {
        IDaoSupport daoSupport = sDaoMap.get(clazz.getSimpleName());
        if (daoSupport == null) {
            daoSupport = new DaoSupportImpl<>();
            daoSupport.init(mSQLiteDatabase, clazz);
            sDaoMap.put(clazz.getSimpleName(), daoSupport);
        }
        return daoSupport;

    }

    public void clear() {
        sDaoMap.clear();
    }
}
