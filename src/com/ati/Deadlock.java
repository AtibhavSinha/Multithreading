package com.ati;

public class Deadlock {
    static class Friend {
        private final String name;
        public Friend(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        public synchronized void bow(Friend bower) {
            System.out.format("%s: %s"
                + "  has bowed to me!%n", 
                this.name, bower.getName());
            bower.bowBack(this);
        }
        public synchronized void bowBack(Friend bower) {
            System.out.format("%s: %s"
                + " has bowed back to me!%n",
                this.name, bower.getName());
        }
    }

    public static void main(String[] args) {
        final Friend alphonse =
            new Friend("Alphonse");
        final Friend gaston =
            new Friend("Gaston");
        //Thread 1 is having lock on alphonse and need to get lock on Gaston which is locked by Thread1
        new Thread(new Runnable() {
            public void run() { alphonse.bow(gaston); }
        }).start();
      //Thread 2 is having lock on gaston and need to get lock on alphonse which is locked by Thread1
      // So both thread are waiting infinitely to execute  bower.bowBack(this);
        new Thread(new Runnable() {
            public void run() { gaston.bow(alphonse); }
        }).start();
    }
}