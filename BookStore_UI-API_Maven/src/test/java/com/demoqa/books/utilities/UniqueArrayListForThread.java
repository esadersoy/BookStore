package com.demoqa.books.utilities;

import java.util.ArrayList;
import java.util.List;

public class UniqueArrayListForThread {


    private static InheritableThreadLocal<List> sessionPool = new InheritableThreadLocal<>();

    // InheritableThreadLocal  --> this is like a container, bag, pool.
    // in this pool we can have separate objects for each thread
    // for each thread, in InheritableThreadLocal we can have separate object for that thread
    // UniqueArrayListForThread class will provide separate arrayList() object per thread

    private UniqueArrayListForThread() {
    }

    public static List getArray() {
        if (sessionPool.get() == null) {
            List<Object> listOfKeys=new ArrayList<>();
            sessionPool.set(listOfKeys);
        }
        return sessionPool.get();
    }

    public static void removeThreadArray() {
//        System.out.println("SessionStateArray-BEFORE remove() = " + ThreadStateArray.getArray());
//        System.out.println("-----------------------------------------");
        for (Object key : UniqueArrayListForThread.getArray()) {
//            System.out.println("SessionStateArrayElements BEFORE removeKey-> "+key+": " + SessionStateHandler.getValue((String) key));
            ThreadStateHandler.removeKey((String) key);
        }
//        System.out.println("-----------------------------------------");
//        for (Object key : ThreadStateArray.getArray()) {
//            System.out.println("SessionStateArrayElements AFTER removeKey--> "+key+": " + SessionStateHandler.getValue((String) key));
//        }
        sessionPool.remove();
//        System.out.println("****************");
//        System.out.println("SessionStateArray-AFTER remove() = " + ThreadStateArray.getArray());
    }
}

