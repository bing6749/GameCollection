package top.bing6749;
/*
数字块类
 */

public class Tile {
    private boolean merged; //是否合并
    private int value;      //值

    Tile(int val) {
        value = val;
    }

    int getValue() {
        return value;
    }

    void setMerged(boolean m) {
        merged = m;
    }

    //两个块接触能否合并
    boolean canMergeWith(Tile other) {
        return !merged && other != null && !other.merged && value == other.getValue();
    }

    //合并的操作
    int mergeWith(Tile other) {
        if (canMergeWith(other)) {
            value *= 2;
            merged = true;
            return value;
        }
        return -1;
    }
}