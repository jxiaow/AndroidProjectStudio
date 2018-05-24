package cn.xwj.dbtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.xwj.dbtest.db.DaoSupportFactory;
import cn.xwj.dbtest.db.IDaoSupport;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IDaoSupport<Person> daoSupport = DaoSupportFactory.getFactory().getSupportDao(Person.class);
    }
}
