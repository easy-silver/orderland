package backpac.homework.orderland.util;

public class StringUtil {

    public static void main(String[] args) {
        System.out.println(randomAlphabet(3));
        System.out.println(randomAlphabet(4));
    }

    /**
     * 랜덤 알파벳 생성
     */
    public static String randomAlphabet(int size) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < size; i++) {
            sb.append((char)((Math.random() * 26) + 65));
        }

        return sb.toString();
    }
}
