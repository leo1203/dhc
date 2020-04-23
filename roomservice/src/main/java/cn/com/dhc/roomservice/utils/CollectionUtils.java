package cn.com.dhc.roomservice.utils;

import java.util.Collection;

public class CollectionUtils {

    public static boolean isEmpty(Collection col) {
        return col == null || col.isEmpty();
    }
}
