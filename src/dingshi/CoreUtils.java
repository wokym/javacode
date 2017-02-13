package dingshi;

/**
* <b>简述：</b>
 *
 * @author : xieqiaojing  2015年12月29日 上午10:12:01
 */

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <b>简述：部分核心功能实现</b>
 *
 * @author : xieqiaojing  2015年12月29日 上午10:12:01
 */
public class CoreUtils {

    private static final String zhZero = "零";
    private static final String zhOne = "一";
    private static final String zhTen = "十";

    public static final Map<Character, Integer> numMap = new HashMap<>();

    static {
        numMap.put('零', 0);
        numMap.put('一', 1);
        numMap.put('壹', 2);
        numMap.put('二', 2);
        numMap.put('两', 2);
        numMap.put('三', 3);
        numMap.put('四', 4);
        numMap.put('五', 5);
        numMap.put('六', 6);
        numMap.put('七', 7);
        numMap.put('八', 8);
        numMap.put('九', 9);
    }

    /**
     * 可以省去最后一个
     * 如: 二百五十 -> 二百五, 一千五百 -> 一千五， 一万五千 -> 一万五
     */

    public static final Map<Character, Integer> numUnitMap = new LinkedHashMap<Character, Integer>();

    static {
        numUnitMap.put('亿', 100000000);
        numUnitMap.put('万', 10000);
        numUnitMap.put('千', 1000);
        numUnitMap.put('百', 100);
        numUnitMap.put('十', 10);
        numUnitMap.put('个', 1);
    }

    private static String numRegex = StringUtils.join(numUnitMap.keySet(), "|");
    static {
        numRegex = "[";
        for (char s : numUnitMap.keySet()) {
            numRegex += s;
        }
        numRegex += "]+";
    }

    /**
     * 一九九零 -> 1990
     *
     * @param txt
     * @return
     */
    public static String singleNumZHToALB(String txt) {
        StringBuilder sb = new StringBuilder();
        for (Character c : txt.toCharArray()) {
            Integer integer = numMap.get(c);
            if (integer != null) {
                sb.append(integer);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 是否是农历日期
     *
     * @param str
     * @return
     */
    public static boolean isLunarDate(String str) {
        if (str.contains("腊月") || str.contains("正月")) {
            return true;
        }
        return false;
    }
}
