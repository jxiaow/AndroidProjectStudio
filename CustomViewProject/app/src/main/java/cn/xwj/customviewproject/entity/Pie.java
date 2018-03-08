package cn.xwj.customviewproject.entity;

/**
 * Author: xw
 * Date: 2018-02-13 09:43:33
 * Description: Pie <this is description>.
 */

public class Pie {
    private String name;
    private float value;
    private float percent;

    private int color;
    private float angle;

    public Pie(String name, float value) {
        this.name = name;
        this.value = value;
    }


    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }
}
