//package com.algorithm.demo.thread;
//
//import javax.xml.crypto.Data;
//import java.io.IOException;
//import java.util.concurrent.locks.StampedLock;
//
//
//public class StampedTest {
//    private final StampedLock sl = new StampedLock();
//
//    void mutate() throws IOException {
//        long stamp = sl.writeLock();
//        try {
//            write();
//        } finally {
//            sl.unlockWrite(stamp);
//        }
//    }
//
//    Data access() {
//        long stamp = sl.tryOptimisticRead();
//        Data data = read();
//        if (!sl.validate(stamp)) {
//            stamp = sl.readLock();
//            try {
//                data = read();
//            } finally {
//                sl.unlockRead(stamp);
//            }
//        }
//        return data;
//    }
//}
