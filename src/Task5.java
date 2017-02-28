import java.util.Scanner;

public class Task5 {

//    static int N = 4;
//    static int NN = 2 * N + 1;
//    static char[] table = new char[NN];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int N = Integer.parseInt(str);

        StringBuilder sb = new StringBuilder();

        char[] c = {'w', 'b'};

        int ptr = 0;
        int num = 0;

        for (int i = 0; i < N; i++) { //1 2 3 4
            for (int j = 0; j <= i; j++) {
//                System.out.println(Character.toString(c[ptr])+ (j+1));
                sb.append(Character.toString(c[ptr])+ (j+1));
                num++;
            }
            ptr++;
            ptr %= 2;
        }

        for (int j = 0; j < N; j++) {
//            System.out.println(Character.toString(c[ptr])+ (j+1));
            sb.append(Character.toString(c[ptr])+ (j+1));
            num++;
        }
        ptr++;
        ptr %= 2;

        for (int i = 0; i < N; i++) { //1 2 3 4
            for (int j = i; j < N; j++) {
//                System.out.println(Character.toString(c[ptr])+ (j+1));
                sb.append(Character.toString(c[ptr])+ (j+1));
                num++;
            }
            ptr++;
            ptr %= 2;
        }

        System.out.println(num);
        System.out.println(sb.toString());
    }

    private static void turn(char turn) {
//        if (turn == 'w') {
//            for (int i = 0; i < NN; i++) {
//                if (table[i] == 'w') {
//                    if (i > 0 & table[i-1] == ' ') {
//                        table[i] ^= table[i-1];
//                        table[i-1] ^= table[i];
//                        table[i] ^= table[i-1];
//                        return;
//                    }
//                }
//            }
//        }
//        if (turn == 'b') {
//            for (int i = 2*N; i >=0; i--) {
//                if (table[i] == 'b') {
//                    if (i > 0 & table[i-1] == ' ') {
//                        table[i] ^= table[i-1];
//                        table[i-1] ^= table[i];
//                        table[i] ^= table[i-1];
//                        return;
//                    }
//                }
//            }
//        }
    }

    private static boolean cond(char[] table) {
//        for (int i = 0; i < N; i++) {
//            if (table[i] != 'w') {
//                return true;
//            }
//        }
//        if (table[N] != ' ') {
//            return true;
//        }
//        for (int i = 2 * N; i > N; i--) {
//            if (table[i] != 'b') {
//                return true;
//            }
//        }
        return false;
    }
//        for (int i = 0; i < N; i++) {
//            table[i] = 'b';
//        }
//        table[N] = ' ';
//        for (int i = 2 * N; i > N; i--) {
//            table[i] = 'w';
//        }
//
//        char turn = 'w';
//        int size = 1;
//
//        // TEST
//
//        // table: bbbb wwww -> bbbbw www
//
//        while (cond(table)) {
//
//            for (int i = 0; i < size; i++) {
//                turn(turn);
//            }
//
//        }
//
//
//        Arrays.asList(table).forEach(System.out::print);
}
