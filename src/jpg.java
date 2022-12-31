import java.util.*;

public class jpg {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // 文字列 => 算術符号の処理の出力
            System.out.println("=================");
            System.out.print("文字列を入力: ");
            String query = scanner.nextLine();
            double value = str2num(query, 0.5, 0.3, 0.15, 0.05);
            System.out.print("出力: ");
            System.out.println(value);
            System.out.println("=================");

            // 算術符号 => 文字列の処理の出力
            System.out.print("算術符号を入力: ");
            double number = scanner.nextDouble();
            String str = num2str(number, 0.5, 0.3, 0.15, 0.05);
            System.out.print("出力: ");
            System.out.println(str);
            System.out.println("=================");
        }
    }

    public static Double str2num(String query, double a, double b, double c, double d) {
        // 各数値の開始点を辞書で格納
        Map<String, Double> front = new HashMap<String, Double>();
        front.put("a", 0.);
        front.put("b", a);
        front.put("c", a + b);
        front.put("d", a + b + c);

        // 各数値の終了点を辞書で格納
        Map<String, Double> back = new HashMap<String, Double>();
        back.put("a", a);
        back.put("b", a + b);
        back.put("c", a + b + c);
        back.put("d", a + b + c + d);

        // 各数値の範囲幅を辞書で格納
        Map<String, Double> lineWidth = new HashMap<String, Double>();
        lineWidth.put("a", a);
        lineWidth.put("b", b);
        lineWidth.put("c", c);
        lineWidth.put("d", d);

        String[] queryArray = query.split("");

        // 1文字目の算術符号を初期値として格納。
        double value = front.get(queryArray[0]);
        double width = back.get(queryArray[0]);

        // 2文字目からループを回して算術符号を求める。
        for (int i = 1; i < queryArray.length; i++) {
            value = value + (width * front.get(queryArray[i]));
            width = width * lineWidth.get(queryArray[i]);
        }

        return value;
    }


    public static String num2str(double number, double a, double b, double c, double d) {
        // 各数値の開始点を辞書で格納
        Map<String, Double> front = new HashMap<String, Double>();
        front.put("a", 0.);
        front.put("b", a);
        front.put("c", a + b);
        front.put("d", a + b + c);

        // 各数値の終了点を辞書で格納
        Map<String, Double> back = new HashMap<String, Double>();
        back.put("a", a);
        back.put("b", a + b);
        back.put("c", a + b + c);
        back.put("d", a + b + c + d);

        // 各数値の範囲幅を辞書で格納
        Map<String, Double> lineWidth = new HashMap<String, Double>();
        lineWidth.put("a", a);
        lineWidth.put("b", b);
        lineWidth.put("c", c);
        lineWidth.put("d", d);

        // 一文字づつデコードする
        String ans = "";
        String[] letters = {"a", "b", "c", "d"};
        while (number > 0.) {
            for (String str : letters) {
                if (front.get(str) <= number && number < back.get(str)) {
                    ans += str;
                    number = (number - front.get(str)) / lineWidth.get(str);
                }
            }
        }
        return ans;
    }
}
