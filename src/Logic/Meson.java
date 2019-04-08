package Logic;

import java.util.concurrent.Semaphore;

public class Meson {

    private int[] meson;
    private int size;
    private String name;

    public Meson(int size, String name) {
        this.size = size;
        this.meson = new int[size];
        this.name = name;
    }

    public int getSize() {
        return this.size;
    }

    public String getName() {
        return this.name;
    }

    public void cook(int i, int data) {
        meson[i] = data;/*
        System.out.println("______________________");
        switch (data) {
            case 0:
                System.out.println("Consumed " + data);
                break;
            case 1:
                System.out.println("Cooked entry " + data);
                break;
            case 2:
                System.out.println("Cooked main " + data);
                break;
            case 3:
                System.out.println("Cooked dessert " + data);
                break;
        }
        System.out.println("----------------------");*/
    }

    public int getSpaceLeft() {
        int temp = this.meson.length;
        for (int i = 0; i < this.meson.length; i++) {
            if (this.meson[i] != 0) {
                temp--;
            } else {
                return temp;
            }
        }
        
        return 0;
    }

}
