package Domain;

import java.io.Serial;
import java.io.Serializable;

public class GameInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 925178964257697580L;
    private int[][] data = new int[4][4];
    //二维数组中“0”的位置
    int x = 0;
    int y = 0;
    //定义走的总步数
    int count = 0;
    String path;


    public GameInfo() {
    }

    public GameInfo(int[][] data, int x, int y, int count, String path) {
        this.data = data;
        this.x = x;
        this.y = y;
        this.count = count;
        this.path = path;
    }

    /**
     * 获取
     * @return data
     */
    public int[][] getData() {
        return data;
    }

    /**
     * 设置
     * @param data
     */
    public void setData(int[][] data) {
        this.data = data;
    }

    /**
     * 获取
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * 设置
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * 获取
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * 设置
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * 获取
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * 设置
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * 获取
     * @return path
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    public String toString() {
        return "GameInfo{data = " + data + ", x = " + x + ", y = " + y + ", count = " + count + ", path = " + path + "}";
    }
}
