package com.learn.javareflect.equals;

import java.util.Arrays;

/**
 * @Author: xiazewei
 * @Date: 2020-05-19 17:04
 */
public class StringNew {

    private final char value[];

    private int hash; // Default to 0


    public StringNew(StringNew original) {
        this.value = original.value;
        this.hash = this.hashCode();
    }

    public StringNew(char value[]) {
        this.value = Arrays.copyOf(value, value.length);
    }

//    public int hashCode() {
//        int h = hash;
//        if (h == 0 && value.length > 0) {
//            char val[] = value;
//
//            for (int i = 0; i < value.length; i++) {
//                h = 31 * h + val[i];
//            }
//            hash = h;
//        }
//        return h;
//    }

    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof StringNew) {
            StringNew anotherString = (StringNew)anObject;
            int n = value.length;
            if (n == anotherString.value.length) {
                char v1[] = value;
                char v2[] = anotherString.value;
                int i = 0;
                while (n-- != 0) {
                    if (v1[i] != v2[i])
                        return false;
                    i++;
                }
                return true;
            }
        }
        return false;
    }
}
