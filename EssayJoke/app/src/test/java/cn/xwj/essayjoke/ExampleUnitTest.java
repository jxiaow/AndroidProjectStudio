package cn.xwj.essayjoke;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void reflectGetFieldType() throws Exception {
        Person person = new Person();
        person.setAge(10);
        person.setName("jiang");

        Field[] fields = Person.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            System.out.println("fieldName: " + field.getName() + " filedType: " + field.getType());
            Object o = field.get(person);
            System.out.println(o.getClass());
        }
    }
}