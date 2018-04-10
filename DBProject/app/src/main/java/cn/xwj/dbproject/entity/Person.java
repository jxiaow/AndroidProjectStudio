package cn.xwj.dbproject.entity;

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-04-10 2018/4/10
 * Description: Person
 */
public class Person {
    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
