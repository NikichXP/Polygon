import java.util.Arrays;
import java.util.Random;

public class Bench {

    static int max2 = Integer.MIN_VALUE;

    public static void main(String[] args) {
        int [] array = new int [500_000_000];
        Random r = new Random();

//        System.out.println("Gen arr");
        for (int i = 0; i < array.length; i++) {
//            array[i] = r.nextInt();
            array[i] = i;
        }
//        System.out.println("Gen done");
        int max1 = Integer.MIN_VALUE,

                max3 = Integer.MIN_VALUE;

        long t1 = System.currentTimeMillis();

        for (int i : array) {
            if (max1 < i) {
                max1 = i;
            }
        }

        long t2 = System.currentTimeMillis();

//        Arrays.asList(array).forEach(i -> {if (i > max2){max2 = i;}});

        long t3 = System.currentTimeMillis();


        System.out.println(t2-t1);
        System.out.println(t3-t2);


//        System.out.println(max1);


    }

}
