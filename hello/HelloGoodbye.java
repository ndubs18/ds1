/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class HelloGoodbye {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Provide 2 names please.");
        }
        else {
            String name1 = args[0];
            String name2 = args[1];
            System.out.printf("Hello %s and %s.", name1, name2);
            System.out.printf("\nGoodbye %s and %s.", name2, name1);
        }
    }
}
