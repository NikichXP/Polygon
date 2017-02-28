import java.util.Scanner;

public class Task3 {

    public static void main(String[] agrs) {


        String tar = "Hello world!";
        String got = "hello, world";

        StringBuilder sb = new StringBuilder();

        int tarPtr = 0;
        int gotPtr = 0;
        int opCtr = 0;

        while (tarPtr < tar.length()) {

            if (tarPtr == tar.length() | gotPtr == got.length()) {
                break;
            }

            if (got.charAt(gotPtr) == tar.charAt(tarPtr)) {
                sb.append(got.charAt(gotPtr));
                gotPtr++;
                tarPtr++;
                continue;
            }

            if (tar.charAt(tarPtr) == got.charAt(gotPtr + 1)) { //extra char
//                System.out.println("extra char " + got.charAt(gotPtr));
                sb.append(tar.charAt(tarPtr));
                gotPtr += 2;
                tarPtr++;
                opCtr++;
                continue;
            }

            if (tar.charAt(tarPtr + 1) == got.charAt(gotPtr)) { //missed char
//                System.out.println("Miss char at " + gotPtr);
                sb.append(tar.charAt(tarPtr));
                tarPtr++;
                sb.append(tar.charAt(tarPtr));
                tarPtr++;
                opCtr++;
                continue;
            }

            if (got.charAt(gotPtr + 1) == tar.charAt(tarPtr + 1)) {
//                System.out.println("Wrong char at " + got.charAt(gotPtr));
                sb.append(tar.charAt(gotPtr));
                gotPtr++;
                tarPtr++;
                opCtr++;
                continue;
            }

        }

        opCtr += tar.length() - tarPtr;
        System.out.println(opCtr);

    }
}
