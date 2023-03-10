import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetherRealms {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, List<Double>> demonsInfo = new LinkedHashMap<>();
        String[] input = scanner.nextLine().split(",\\s*");
        double damage = 0;
        double health = 0;

        String regexDamage = "[+-]?\\d+\\.?\\d*";
        String regexHealth = "[^0-9*\\/+\\-.]";           //'+', '-', '*', '/'

        for (int i = 0; i < input.length; i++) {  //each demon
            String demon = input[i].replaceAll(" ", "");
            demon = demon.replaceAll(",", "");

            Pattern pattern = Pattern.compile(regexHealth);
            Matcher matcher = pattern.matcher(demon);
            int countMultiply = 0;
            int countDivide = 0;

            for (int i1 = 0; i1 < demon.length(); i1++) {
                if (demon.charAt(i1) == '*') {
                    countMultiply++;
                } else if (demon.charAt(i1) == '/') {
                    countDivide++;
                }
            }

            while (matcher.find()) {   // Making Health sum
                health += matcher.group().charAt(0);
            }
            Pattern pattern1 = Pattern.compile(regexDamage);
            Matcher matcher1 = pattern1.matcher(demon);

            while (matcher1.find()) {
                damage += Double.parseDouble(matcher1.group());
            }
            for (int j = 0; j < countDivide; j++) {
                damage /= 2;
            }
            for (int j = 0; j < countMultiply; j++) {
                damage *= 2;
            }

            List<Double> healthDamage = new ArrayList<>();
            healthDamage.add(health);
            healthDamage.add(damage);
            demonsInfo.put(demon, healthDamage);

            damage = 0;
            health = 0;
        }
        for (Map.Entry<String, List<Double>> entry : demonsInfo.entrySet()) {
            System.out.println(String.format("%s - %.0f health, %.2f damage", entry.getKey(), entry.getValue().get(0), entry.getValue().get(1)));
     }
    }
}
