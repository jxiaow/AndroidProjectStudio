package cn.xwj.dbproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.xwj.dbproject.db.ISupportDao;
import cn.xwj.dbproject.db.SupportDaoFactory;
import cn.xwj.dbproject.entity.Person;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ISupportDao<Person> personDao = SupportDaoFactory.getFactory().getDao(Person.class);
        personDao.insert(new Person(23, "张三"));


    }
}
