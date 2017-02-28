import java.util.Arrays;
public class Core {
    static int [][] plate = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
    public static void main (String [] args) {
        Comp a[] = { new Comp (5, 5),
                new Comp(3, 1),
                new Comp(2,2),
                new Comp(1,1),
                new Comp(3, 1),
                new Comp(3, 1),
                new Comp(2,2),
        };
        for (Comp sel : a) {
            addComp(sel);
        }
        for (int [] arr : plate) {
            for (int i : arr) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
    private static void addComp(Comp a) {
        for (int i = 0; i < plate.length; i++) {
            for (int j = 0; j < plate.length; j++) {
                if (checkAv (i, j, a)) {
                    for (int xi = 0; xi < a.x; xi++) {
                        for (int yi = 0; yi < a.y; yi++) {
                            plate[xi + i][yi+j] = a.id;
                        }
                    }
                    return;
                }
            }
        }
        extendPlate();
        addComp(a);
    }
    private static void extendPlate() {
        int t[][] = new int[plate.length+4][plate[0].length+3];
        for (int i = 0; i < t.length-1; i++) {
            t[i] = Arrays.copyOf(plate[i], t.length);
        }
        t[t.length - 1] = new int [t.length];
        plate = t;
    }
    private static boolean checkAv(int x, int y, Comp a) {
        try {
            for (int i = 0; i < a.x; i++) {
                for (int j = 0; j < a.x; j++) {
                    if (plate[i+x][j+y] != 0) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    static class Comp {
        int x, y, id;
        static int genId = 0;
        public Comp (int x, int y) {
            this.x = x;
            this.y = y;
            this.id = ++genId;
        }
    }
}