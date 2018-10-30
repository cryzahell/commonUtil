package com.ox.common.num;

public class Minteger {

    /**
     * @return a.intValue == b.intValue
     */
    public static boolean equals(Integer a, Integer b) {
        if (a == null && b == null) {
            return false;
        } else if (a != null) {
            return a.equals(b);
        } else {
            return false;
        }
    }

    /**
     * @return val=1,count=2 ==> 01
     */
    public static String getIndexString(Integer val, int count) {
        String rlt = "";
        if (val != null && count > 0) {
            rlt = String.format("%0" + count + "d", val);
        }
        return rlt;
    }

}
