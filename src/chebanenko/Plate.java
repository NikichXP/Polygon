package chebanenko;

import java.util.ArrayList;

public class Plate {

    private int [][] plate;
    private Component[][] plateObj;
    private ArrayList<Component> added;
    private int sizeX, sizeY;


    public Plate(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        plate = new int[sizeX][sizeY];
        plateObj = new Component[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                plate[i][j] = 0;
                plateObj[i][j] = null;
            }
        }
        added = new ArrayList<>();
    }

    public void add(Component[] a) {
        boolean flag = true;
        Component temp;
        while (flag) {
            flag = false;
            for (int i = 0; i < a.length - 1; i++) {
                if (a[i].getPriority() < a[i+1].getPriority()) {
                    temp = a[i];
                    a[i] = a[i+1];
                    a[i+1] = temp;
                    flag = true;
                }
            }
        }
    }

    public void realPrint() {
        for (int [] arr : plate) {
            for (int i : arr) {
                if (i == -1) {
                    System.out.print(" * ");
                } else {
                    System.out.print(" " + i + " ");
                }
            }
            System.out.println();
        }
    }
    public void print() {
        for (int i = 0; i < plate[0].length; i++) {
            for (int j = 0; j < plate.length; j++) {
                if (i == -1) {
                    System.out.print(" * ");
                } else {
                    System.out.print(" " + plate[j][i] + " ");
                }
            }
            System.out.println();
        }
    }
}
