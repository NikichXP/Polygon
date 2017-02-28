package chebanenko;

public class Main {

    public static void main(String[] args) {
        int ctr = 0, a, b;
        for (int i = 0; i < 100_000; i++) {
            a = (int) (Math.random() * 6);
            b = (int) (Math.random() * 6);
            a++;
            b++;
            if ((a+b) % 4 == 0) {
                ctr++;
            }
        }
        System.out.println(ctr);
//        Component a[] = {
//                new Component(3, 5),
//                new Component(5, 5),
//                new Component(1, 3),
//                new Component(3, 3),
//                new Component(1, 2),
//                new Component(4, 4)
//        };
//
//        Plate plate = new Plate (30, 20); // traditional x y axis
//        plate.add(a);
//        plate.print();

    }
}
