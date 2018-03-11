package cn.xwj.essayjoke;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import cn.xwj.frame.db.DaoFactory;
import cn.xwj.frame.db.IDaoSupport;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("cn.xwj.essayjoke", appContext.getPackageName());
    }

    @Test
    public void testInsert() throws Exception {
        IDaoSupport<Person> dao = DaoFactory.getFactory().getDao(Person.class);
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            Person person = new Person();
            person.setName("jiang " + i);
            person.setAge(10 + i);
            persons.add(person);
        }
        long start = System.currentTimeMillis();
        long insert = dao.insert(persons);
        long end = System.currentTimeMillis();
        Log.e("TAG", "insert---> " + (end - start));
    }
}
