package f_app.dmx.com.securelocate;

import cn.bmob.v3.BmobObject;

public class LocationData extends BmobObject {
    String NODE;
    float X;
    float Y;

    public void setNODE(String nodeName) {
        NODE = nodeName;
    }

    public void setX(float x) {
        X = x;
    }

    public void setY(float y) {
        Y = y;
    }

    public String getNODE() {

        return NODE;
    }

    public float getX() {
        return X;
    }

    public float getY() {
        return Y;
    }
}
