package dingshi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;


public class NLPDatetime {

    public static Map<String, Integer> Year = new HashMap<>();

    static {
        Year.put("前年", -2);
        Year.put("上上1年", -2);
        Year.put("去年", -1);
        Year.put("昨年", -1);
        Year.put("前1年", -1);
        Year.put("上1年", -1);
        Year.put("昨年", -1);
        Year.put("今年", 0);
        Year.put("这1年", 0);
        Year.put("明年", 1);
        Year.put("下1年", 1);
        Year.put("后年", 2);
        Year.put("下下1年", 2);
    }

    public static Map<String, Integer> Month = new HashMap<>();

    static {
        Month.put("上上月", -2);
        Month.put("上上个月", -2);
        Month.put("上月", -1);
        Month.put("上个月", -1);
        Month.put("本月", 0);
        Month.put("这月", 0);
        Month.put("这个月", 0);
        Month.put("下月", 1);
        Month.put("下个月", 1);
        Month.put("下下月", 2);
        Month.put("下下个月", 2);
    }

    public static Map<String, Integer> Day = new HashMap<>();

    static {
        Day.put("前天", -2);
        Day.put("前日", -2);
        Day.put("昨天", -1);
        Day.put("昨儿", -1);
        Day.put("昨日", -1);
        Day.put("前1天", -1);
        Day.put("今天", 0);
        Day.put("今儿", 0);
        Day.put("本日", 0);
        Day.put("今日", 0);
        Day.put("明天", 1);
        Day.put("明儿", 1);
        Day.put("明日", 1);
        Day.put("后天", 2);
        Day.put("后日", 2);
        Day.put("大后天", 3);
        Day.put("后后天", 3);
    }

    public static Map<String, Integer> Week = new HashMap<>();

    static {
        Week.put("上上周", -2);
        Week.put("上上个周", -2);
        Week.put("上周", -1);
        Week.put("上个周", -1);
        Week.put("周", 0);
        Week.put("本周", 0);
        Week.put("这周", 0);
        Week.put("这个周", 0);
        Week.put("下周", 1);
        Week.put("下个周", 1);
        Week.put("下下周", 2);
        Week.put("下下个周", 2);

        Week.put("上上星期", -2);
        Week.put("上上个星期", -2);
        Week.put("上星期", -1);
        Week.put("上个星期", -1);
        Week.put("星期", 0);
        Week.put("本星期", 0);
        Week.put("这星期", 0);
        Week.put("这个星期", 0);
        Week.put("下星期", 1);
        Week.put("下个星期", 1);
        Week.put("下下星期", 2);
        Week.put("下下个星期", 2);

        Week.put("上上礼拜", -2);
        Week.put("上上个礼拜", -2);
        Week.put("上礼拜", -1);
        Week.put("上个礼拜", -1);
        Week.put("礼拜", 0);
        Week.put("本礼拜", 0);
        Week.put("这礼拜", 0);
        Week.put("这个礼拜", 0);
        Week.put("下礼拜", 1);
        Week.put("下个礼拜", 1);
        Week.put("下下礼拜", 2);
        Week.put("下下个礼拜", 2);
    }

    public static Map<String, Integer> TimePeriod = new HashMap<>();

    static {
        TimePeriod.put("凌晨", 0);
        TimePeriod.put("早晨", 6);
        TimePeriod.put("清晨", 6);
        TimePeriod.put("早上", 6);
        TimePeriod.put("上午", 9);
        TimePeriod.put("中午", 12);
        TimePeriod.put("下午", 15);
        TimePeriod.put("下午", 15);
        TimePeriod.put("傍晚", 18);
        TimePeriod.put("晚上", 21);
        TimePeriod.put("午夜", 21);
        TimePeriod.put("夜里", 21);

//        TimePeriod.put("昨早", -6 - 24);
//        TimePeriod.put("昨早上", -6 - 24);
//        TimePeriod.put("昨上午", -6 - 24);
//        TimePeriod.put("昨中午", -6 - 24);
//        TimePeriod.put("昨下午", -6 - 24);
//        TimePeriod.put("昨傍晚", -6 - 24);
//        TimePeriod.put("昨晚", -21 - 24);
//        TimePeriod.put("昨晚上", -21 - 24);
//        TimePeriod.put("昨夜", -21 - 24);
//        TimePeriod.put("昨午夜", -21 - 24);
//
//        TimePeriod.put("今早", 6);
//        TimePeriod.put("今早上", 6);
//        TimePeriod.put("今上午", 6);
//        TimePeriod.put("今中午", 6);
//        TimePeriod.put("今下午", 6);
//        TimePeriod.put("今傍晚", 6);
//        TimePeriod.put("今晚", 21);
//        TimePeriod.put("今晚上", 21);
//        TimePeriod.put("今夜", 21);
//        TimePeriod.put("今午夜", 21);
//
//        TimePeriod.put("明早", 6 + 24);
//        TimePeriod.put("明早上", 6 + 24);
//        TimePeriod.put("明上午", 6 + 24);
//        TimePeriod.put("明中午", 6 + 24);
//        TimePeriod.put("明下午", 6 + 24);
//        TimePeriod.put("明傍晚", 6 + 24);
//        TimePeriod.put("明晚", 21 + 24);
//        TimePeriod.put("明晚上", 21 + 24);
//        TimePeriod.put("明夜", 21 + 24);
//        TimePeriod.put("明午夜", 21 + 24);
    }

    public static Map<String, Integer> DateTimePeriod = new HashMap<>();

    static {
        DateTimePeriod.put("昨凌晨", 0);
        DateTimePeriod.put("昨早", -6 - 24);
        DateTimePeriod.put("昨清晨", -6 - 24);
        DateTimePeriod.put("昨早晨", -6 - 24);
        DateTimePeriod.put("昨早上", -6 - 24);
        DateTimePeriod.put("昨上午", -6 - 24);
        DateTimePeriod.put("昨中午", -6 - 24);
        DateTimePeriod.put("昨下午", -6 - 24);
        DateTimePeriod.put("昨傍晚", -6 - 24);
        DateTimePeriod.put("昨晚", -21 - 24);
        DateTimePeriod.put("昨晚上", -21 - 24);
        DateTimePeriod.put("昨夜", -21 - 24);
        DateTimePeriod.put("昨午夜", -21 - 24);

        DateTimePeriod.put("今早", 6);
        DateTimePeriod.put("今清晨", 6);
        DateTimePeriod.put("今早晨", 6);
        DateTimePeriod.put("今早上", 6);
        DateTimePeriod.put("今上午", 6);
        DateTimePeriod.put("今中午", 6);
        DateTimePeriod.put("今下午", 6);
        DateTimePeriod.put("今傍晚", 6);
        DateTimePeriod.put("今晚", 21);
        DateTimePeriod.put("今晚上", 21);
        DateTimePeriod.put("今夜", 21);
        DateTimePeriod.put("今午夜", 21);

        DateTimePeriod.put("明早", 6 + 24);
        DateTimePeriod.put("明清晨", 6 + 24);
        DateTimePeriod.put("明早晨", 6 + 24);
        DateTimePeriod.put("明早上", 6 + 24);
        DateTimePeriod.put("明上午", 6 + 24);
        DateTimePeriod.put("明中午", 6 + 24);
        DateTimePeriod.put("明下午", 6 + 24);
        DateTimePeriod.put("明傍晚", 6 + 24);
        DateTimePeriod.put("明晚", 21 + 24);
        DateTimePeriod.put("明晚上", 21 + 24);
        DateTimePeriod.put("明夜", 21 + 24);
        DateTimePeriod.put("明午夜", 21 + 24);
    }

    public static Map<String, Integer> Minute = new HashMap<>();

    static {
        Minute.put("半", 30);
        Minute.put("一刻", 15);
        Minute.put("二刻", 30);
        Minute.put("三刻", 40);
    }


    public static Map<String, String> LunarFestival = new HashMap<>();

    static {
        LunarFestival.put("春节", "正月初一");
        LunarFestival.put("元宵", "正月十五");
        LunarFestival.put("元宵节", "正月十五");
        LunarFestival.put("龙头节", "二月初二");
        LunarFestival.put("龙抬头", "二月初二");
        LunarFestival.put("端午", "五月初五");
        LunarFestival.put("端午节", "五月初五");
        LunarFestival.put("七夕", "七月初七");
        LunarFestival.put("中元节", "七月十五");
        LunarFestival.put("中秋", "八月十五");
        LunarFestival.put("中秋节", "八月十五");
        LunarFestival.put("重阳", "九月初九");
        LunarFestival.put("重阳节", "九月初九");
        LunarFestival.put("下元", "十月十五");
        LunarFestival.put("下元节", "十月十五");
        LunarFestival.put("腊八", "十二月初八");
        LunarFestival.put("小年", "十二月二十三");
        LunarFestival.put("除夕", "十二月三十");
        LunarFestival.put("大年三十", "十二月三十");
    }

    public static Map<String, SolarFestival> SolarFestivalMap = new HashMap<>();

    static {
        SolarFestivalMap.put("元旦", new SolarFestival("元旦", 1, 1));
        SolarFestivalMap.put("湿地日", new SolarFestival("湿地日", 2, 2));
        SolarFestivalMap.put("情人节", new SolarFestival("情人节", 2, 14));
        SolarFestivalMap.put("爱耳日", new SolarFestival("爱耳日", 3, 3));
        SolarFestivalMap.put("妇女节", new SolarFestival("妇女节", 3, 8));
        SolarFestivalMap.put("植树节", new SolarFestival("植树节", 3, 12));
        SolarFestivalMap.put("消费者权益日", new SolarFestival("消费者权益日", 3, 15));
        SolarFestivalMap.put("愚人节", new SolarFestival("愚人节", 4, 1));
        SolarFestivalMap.put("清明节", new SolarFestival("清明节", 4, 4));
        SolarFestivalMap.put("地球日", new SolarFestival("地球日", 4, 22));
        SolarFestivalMap.put("劳动节", new SolarFestival("劳动节", 5, 1));
        SolarFestivalMap.put("青年节", new SolarFestival("清明节", 5, 4));
        //母亲节、父亲节
        SolarFestivalMap.put("护士节", new SolarFestival("护士节", 5, 12));
        SolarFestivalMap.put("儿童节", new SolarFestival("儿童节", 6, 1));
        SolarFestivalMap.put("环境日", new SolarFestival("环境日", 6, 5));
        SolarFestivalMap.put("爱眼日", new SolarFestival("爱眼日", 6, 6));
        SolarFestivalMap.put("奥林匹克日", new SolarFestival("奥林匹克日", 6, 23));
        SolarFestivalMap.put("建党节", new SolarFestival("建党节", 7, 1));
        SolarFestivalMap.put("建军节", new SolarFestival("建军节", 8, 1));
        SolarFestivalMap.put("教师节", new SolarFestival("教师节", 9, 10));
        SolarFestivalMap.put("国庆节", new SolarFestival("国庆节", 10, 1));
    }

    // TODO: 2016/10/21 24节气
    public static String strTimePeriod = StringUtils.join(TimePeriod.keySet(), "|");
    //半年
    public static String strYear = StringUtils.join(Year.keySet(), "|") + "|" + "[0-9十百千万半]+(年|-|/)(半(?!月|个月))?";
    public static String strMonth = StringUtils.join(Month.keySet(), "|") + "|" + "([0-9十百]+个半|[0-9十百]+|半)(月份|月|个月|-|/)(半(?![日天]))?";
    public static String strDay = "(" + StringUtils.join(Day.keySet(), "|") + "|" + "[0-9十百]+[日天号])" + "|"
            + StringUtils.join(DateTimePeriod.keySet(), "|") + "|"
            + "([0-9十百]+个半|半)[日天号]|[0-9十百]+[日天号]半?";
    public static String strWeek = "(" + StringUtils.join(Week.keySet(), "|") + ")" + "([1-7])";
    public static String strSolarFestival = StringUtils.join(SolarFestivalMap.keySet(), "|");
    public static String strHour = "([0-9十百]+个半|[0-9十百]+|半)(点钟|点|时|小时|个小时|个点|:)(半(?!分)|一刻|二刻|三刻)?";
    public static String strMin = "([0-9十百]+个半|[0-9十百]+|半)(分钟|分|:)?(半(?!秒))?";
    public static String strSecond = "([0-9十百]+)(秒|秒钟)?";


    public static Pattern patternYear = Pattern.compile(strYear);
    public static Pattern patternMonth = Pattern.compile(strMonth);
    public static Pattern patternDay = Pattern.compile(strDay);
    public static Pattern patternHour = Pattern.compile(strHour);
    public static Pattern patternMinute = Pattern.compile(strMin);
    public static Pattern patternSecond = Pattern.compile(strSecond);

    public static Pattern patternWeek = Pattern.compile(strWeek);
    public static Pattern patternSolarFestival = Pattern.compile(strSolarFestival);

    public static List<Character> characters = new ArrayList<>();

    static {
        characters.addAll(CoreUtils.numUnitMap.keySet());
        for (Integer integer : CoreUtils.numMap.values()) {
            characters.add((char) (integer + 48));
        }
        characters.add('半');
    }

    //        public static String strDateTime = "(再过|过|再过去|再经过)?" + "(" + StringUtils.join(Year.keySet(), "|") + "|" + "([0-9十百千万半]+)年)" + "?((之后|后|以后|过后)|(之前|前|以前))?[/s/S]?"
//            + "(再过|过|再过去|再经过)?" + "(" + StringUtils.join(Month.keySet(), "|") + "|" + "([0-9十百千万半]+)(月|月份))" + "?((之后|后|以后|过后)|(之前|前|以前))?[/s/S]?"
//            + "(再过|过|再过去|再经过)?" + "((" + StringUtils.join(Day.keySet(), "|") + "|" + StringUtils.join(TimePeriod.keySet(), "|") + "|" + "([0-9十百千万半]+)[号日天])(早上|上午|中午|下午|傍晚|晚上|午夜)?)" + "?((之后|后|以后|过后)|(之前|前|以前))?[/s/S]?"
//            + "(再过|过|再过去|再经过)?" + "(([0-9十百千万半]+)(点钟|点|时|小时|:))" + "?((之后|后|以后|过后)|(之前|前|以前))?[/s/S]?"
//            + "(再过|过|再过去|再经过)?" + "(([0-9十百千万半]+)((分钟|分|:)|(半|一刻|二刻|三刻))?)" + "?((之后|后|以后|过后)|(之前|前|以前))?[/s/S]?"
//            + "(再过|过|再过去|再经过)?" + "(([0-9九十百千万半]+)(秒钟|秒)?)" + "?((之后|后|以后|过后)|(之前|前|以前))?[/s/S]?";

    public static String strDateTime = "(?<afterBegin>再过|过|再过去|再经过)?"
            + "((?<year>" + strYear + ")[的加,]?)?"
            + "((?<month>" + strMonth + ")[的加,]?)?"
            + "((?<week>" + strWeek + ")[的加,]?)?"
            + "((?<day>" + strDay + ")[的加,]?)?"
            + "((?<solarFestival>" + strSolarFestival + ")[的加,]?)?"
            + "((?<timePeriod>" + strTimePeriod + ")[的加,]?)?"
            + "(?<hour>" + strHour + ")?"
            + "(?<minute>" + strMin + ")?"
            + "(?<second>" + strSecond + ")?"
            + "((?<afterEnd>之后|后|以后|过后)|(?<beforeEnd>之前|前|以前))?";

    public static String strDate = "(?<afterBegin>再过|过|再过去|再经过)?"
            + "((?<year>" + strYear + ")[的加,]?)?"
            + "((?<month>" + strMonth + ")[的加,]?)?"
            + "((?<week>" + strWeek + ")[的加,]?)?"
            + "((?<day>" + strDay + ")[的加,]?)?"
            + "((?<solarFestival>" + strSolarFestival + ")[的加,]?)?"
            + "((?<afterEnd>之后|后|以后|过后)|(?<beforeEnd>之前|前|以前))?";

    public static String strTime = "(?<afterBegin>再过|过|再过去|再经过)?"
            + "((?<timePeriod>" + strTimePeriod + ")[的,]?)?"
            + "(?<hour>" + strHour + ")?"
            + "(?<minute>" + strMin + ")?"
            + "(?<second>" + strSecond + ")?"
            + "((?<afterEnd>之后|后|以后|过后)|(?<beforeEnd>之前|前|以前))?";

    public static Map<String, Integer> beforeAfter = new HashMap<>();

    static {
        beforeAfter.put("再过去", 1);
        beforeAfter.put("再过", 1);
        beforeAfter.put("过", 1);
        beforeAfter.put("再经过", 1);
        beforeAfter.put("再等", 1);
        beforeAfter.put("过后", 2);
        beforeAfter.put("之后", 2);
        beforeAfter.put("后", 2);
        beforeAfter.put("以后", 2);
        beforeAfter.put("之前", 3);
        beforeAfter.put("前", 3);
        beforeAfter.put("以前", 3);
    }

    public static List<Character> TimeUnit = new ArrayList<>();

    static {
        TimeUnit.add('年');
        TimeUnit.add('月');
        TimeUnit.add('日');
        TimeUnit.add('天');
        TimeUnit.add('时');
        TimeUnit.add('点');
        TimeUnit.add('分');
        TimeUnit.add('秒');
    }

    public static String unit = "年|月份|月|-|日|天|号|时|点钟|点|小时|时|:|分钟|分|秒钟|秒";

    public static Pattern patternDateTime = Pattern.compile(strDateTime);
    public static Pattern patternDate = Pattern.compile(strDate);
    public static Pattern patternTime = Pattern.compile(strTime);
		
    public static void main(String[] args) {
    	DateTimeResult parseTime = parseDateTime(null,"哈哈");
    	Calendar value = parseTime.getValue();
    	System.out.println(value.getTime());
    	System.out.println(parseTime);
		
    }
    
    public static DateTimeResult parseDateTime(Calendar relative, String string) {
        if (!StringUtils.isEmpty(string)) {
            //预处理
            String newStr = CoreUtils.singleNumZHToALB(string);
            if (relative == null) {
                //利用本地时间初始化
                relative = Calendar.getInstance();
            }
            Matcher matcher = patternDateTime.matcher(newStr);
            while (matcher.find()) {
                //存在且不能全是数字
                if (validate(matcher.group())) {
                    DateTimeResult result = new DateTimeResult();
                    result.setValue(relative);
                    result.setRawValue(string.substring(matcher.start(), matcher.end()));
                    result.setStart(matcher.start());
                    result.setEnd(matcher.end());
                    setDateTime(relative, matcher);
                    return result;
                }
            }
        }
        return null;
    }

    public static DateTimeResult parseDate(Calendar relative, String string) {
        if (!StringUtils.isEmpty(string)) {
            //预处理
            String newStr = CoreUtils.singleNumZHToALB(string);
            if (relative == null) {
                //利用本地时间初始化
                relative = Calendar.getInstance();
            }
            DateTimeResult result = new DateTimeResult();
            result.setValue(relative);
            Matcher matcher = patternDate.matcher(newStr);
            while (matcher.find()) {
                //存在且不能全是数字
                if (validate(matcher.group())) {
                    result.setRawValue(string.substring(matcher.start(), matcher.end()));
                    result.setStart(matcher.start());
                    result.setEnd(matcher.end());
                    setDate(relative, matcher);
                    return result;
                }
            }
        }
        return null;
    }

    public static DateTimeResult parseTime(Calendar relative, String string) {
        if (!StringUtils.isEmpty(string)) {
            //预处理
            String newStr = CoreUtils.singleNumZHToALB(string);
            if (relative == null) {
                //利用本地时间初始化
                relative = Calendar.getInstance();
            }
            DateTimeResult result = new DateTimeResult();
            result.setValue(relative);
            Matcher matcher = patternTime.matcher(newStr);
            while (matcher.find()) {
                //存在且不能全是数字
                if (validate(matcher.group())) {
                    result.setRawValue(string.substring(matcher.start(), matcher.end()));
                    result.setStart(matcher.start());
                    result.setEnd(matcher.end());
                    setTime(relative, matcher);
                    return result;
                }
            }
        }
        return null;
    }

    private static boolean validate(String string) {
        if (string.length() > 1 && !beforeAfter.containsKey(string)) {
            for (char c : string.toCharArray()) {
                if (!characters.contains(c)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void setDateTime(Calendar calendar, Matcher matcher) {
        //判断日期时间是相对还是绝对
        String string = matcher.group();
        int flag = 0;
        if (matcher.group("afterEnd") != null) {
            flag = 2;
        } else if (matcher.group("afterBegin") != null) {
            flag = 1;
        } else if (matcher.group("beforeEnd") != null) {
            flag = 3;
        }
        String year = matcher.group("year");
        if (year != null) {
            //本身就是相对日期时间
            Integer value = Year.get(year);
            if (value != null) {
                calendar.add(Calendar.YEAR, value);
            }
            //依赖flag判断是相对日期还是绝对日期
            else {
                //提取值
                //把year变为数字
                double numYear = getNum(year);
                int partYear = (int) numYear;
                int partMonth = (int) ((numYear - partYear) * 12);
                if (flag == 1 || flag == 2) {
                    calendar.add(Calendar.YEAR, partYear);
                    calendar.add(Calendar.MONTH, partMonth - 1);
                } else if (flag == 3) {
                    calendar.add(Calendar.YEAR, -partYear);
                    calendar.add(Calendar.MONTH, -partMonth - 1);
                } else {
                    //绝对日期
                    calendar.set(Calendar.YEAR, partYear);
                    if (string.endsWith(year)) {
                        //重置月日十分秒
                        calendar.set(Calendar.MONTH, 0);
                        calendar.set(Calendar.DAY_OF_MONTH, 0);
                        calendar.set(Calendar.HOUR_OF_DAY, 0);
                        calendar.set(Calendar.MINUTE, 0);
                        calendar.set(Calendar.SECOND, 0);

                        //以下基本不会出现（2016年半）
                        calendar.set(Calendar.MONTH, partYear - 1);
                        return;
                    }
                }
            }
        }
        String month = matcher.group("month");
        if (month != null) {
            //本身就是相对日期时间
            Integer value = Month.get(month);
            if (value != null) {
                calendar.add(Calendar.MONTH, value);
            } else {
                //提取值
                //把month变为数字
                double num = getNum(month);
                int intNum = (int) num;
                int decNum = (int) ((num - intNum) * 30);
                if (flag == 1 || flag == 2) {
                    calendar.add(Calendar.YEAR, intNum - 1);
                    calendar.add(Calendar.MONTH, decNum);
                } else if (flag == 3) {
                    calendar.add(Calendar.YEAR, -intNum - 1);
                    calendar.add(Calendar.MONTH, -decNum);
                } else {
                    //绝对日期
                    calendar.set(Calendar.MONTH, intNum - 1);
                    if (string.endsWith(month)) {
                        //重置日十分秒
                        calendar.set(Calendar.DAY_OF_MONTH, 0);
                        calendar.set(Calendar.HOUR_OF_DAY, 0);
                        calendar.set(Calendar.MINUTE, 0);
                        calendar.set(Calendar.SECOND, 0);

                        //7月半
                        calendar.set(Calendar.DAY_OF_MONTH, decNum);
                        return;

                    }
                }
            }
        }
        String week = matcher.group("week");
        if (week != null) {
            //本身就是相对日期时间
            Integer value = Week.get(week.substring(0, week.length()));
            if (value != null) {
                calendar.add(Calendar.WEEK_OF_MONTH, value);
                int day = Integer.parseInt(week.substring(week.length() - 1));
                calendar.set(Calendar.DAY_OF_WEEK, day);
            }
            if (string.endsWith(week)) {
                //重置十分秒
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                return;
            }
        }
        if (matcher.group("solarFestival") != null) {
            //节假日为绝对日期
            SolarFestival solarFestival = SolarFestivalMap.get(matcher.group("solarFestival"));
            calendar.set(Calendar.MONTH, solarFestival.month - 1);
            calendar.set(Calendar.DAY_OF_MONTH, solarFestival.day);
        }
        String day = matcher.group("day");
        boolean afternoon = false;
        boolean night = false;
        if (day != null) {
            //本身就是相对日期时间
            Integer value = Day.get(day);
            if (value != null) {
                calendar.add(Calendar.DAY_OF_MONTH, value);
            } else {
                value = DateTimePeriod.get(day);
                if (value != null) {
                    int i = value / 24;
                    int abs = Math.abs(value % 24);
                    if (abs >= 21) {
                        night = true;
                        afternoon = true;
                    } else if (abs > 12) {
                        afternoon = true;
                    }
                    calendar.add(Calendar.DAY_OF_MONTH, i);
                    //此时如果结束，以默认时间初始化（或者通过时间段flag,理解为时间段）
                    if (string.endsWith(day)) {
                        calendar.set(Calendar.HOUR_OF_DAY, abs);
                        calendar.set(Calendar.MINUTE, 0);
                        calendar.set(Calendar.SECOND, 0);
                        return;
                    }
                } else {
                    //提取值
                    //把day变为数字
                    double num = getNum(day);
                    int intNum = (int) num;
                    int decNum = (int) ((num - intNum) * 24);
                    if (flag == 1 || flag == 2) {
                        calendar.add(Calendar.DAY_OF_MONTH, intNum);
                        calendar.add(Calendar.HOUR_OF_DAY, decNum);
                    } else if (flag == 3) {
                        calendar.add(Calendar.DAY_OF_MONTH, -intNum);
                        calendar.add(Calendar.HOUR_OF_DAY, -decNum);
                    } else {
                        //绝对日期
                        calendar.set(Calendar.DAY_OF_MONTH, intNum);
                        //以下基本不会出现（7号半）
                        if (string.endsWith(day)) {
                            //重置十分秒
                            calendar.set(Calendar.HOUR_OF_DAY, 0);
                            calendar.set(Calendar.MINUTE, 0);
                            calendar.set(Calendar.SECOND, 0);
                            calendar.set(Calendar.HOUR_OF_DAY, decNum);
                            return;
                        }
                    }
                }
            }
        }
        if (!afternoon) {
            String timePeriod = matcher.group("timePeriod");
            if (timePeriod != null) {
                Integer value = TimePeriod.get(timePeriod);
                if (value != null) {
                    if (value >= 21) {
                        night = true;
                        afternoon = true;
                    } else if (value > 12) {
                        afternoon = true;
                    }
                    //此时如果结束，以默认时间初始化（或者通过时间段flag,理解为时间段）
                    if (string.endsWith(timePeriod)) {
                        calendar.set(Calendar.HOUR_OF_DAY, value);
                        calendar.set(Calendar.MINUTE, 0);
                        calendar.set(Calendar.SECOND, 0);
                        return;
                    }
                }
            }
        }
        String hour = matcher.group("hour");
        if (hour != null) {
            //本身就是相对日期时间
            //提取值
            //把day变为数字
            boolean returned = false;
            double num = getNum(hour);
            int intNum = (int) num;
            int decNum = (int) ((num - intNum) * 60);
            if (flag == 1 || flag == 2) {
                calendar.add(Calendar.HOUR_OF_DAY, intNum);
                calendar.add(Calendar.MINUTE, decNum);
            } else if (flag == 3) {
                calendar.add(Calendar.HOUR_OF_DAY, -intNum);
                calendar.add(Calendar.MINUTE, -decNum);
            } else {
                //绝对日期
                calendar.set(calendar.HOUR_OF_DAY, intNum);
                if (string.endsWith(hour)) {
                    //重置分秒
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);

                    calendar.set(calendar.MINUTE, decNum);
                    returned = true;
                }
            }
            if (calendar.get(Calendar.HOUR_OF_DAY) < 12) {
                //小于12判断是否为中午之后
                if (afternoon) {
                    //如果是“晚上1-4点”，时间+24，
                    if (night && calendar.get(Calendar.HOUR_OF_DAY) <= 4) {
                        calendar.add(Calendar.HOUR_OF_DAY, 24);
                    } else {
                        calendar.add(Calendar.HOUR_OF_DAY, 12);
                    }
                }
            }
            if (returned) {
                return;
            }
        } else {
            //如果小时为空，默认为相对时间
            flag = 2;
        }
        String minute = matcher.group("minute");
        if (minute != null) {
            //本身就是相对日期时间
            //提取值
            //把day变为数字
            double num = getNum(minute);
            int intNum = (int) num;
            int decNum = (int) ((num - intNum) * 60);
            if (flag == 1 || flag == 2) {
                calendar.add(Calendar.MINUTE, intNum);
                calendar.add(Calendar.SECOND, decNum);
            } else if (flag == 3) {
                calendar.add(Calendar.MINUTE, -intNum);
                calendar.add(Calendar.SECOND, -decNum);
            } else {
                //绝对日期
                calendar.set(calendar.MINUTE, intNum);

                if (string.endsWith(minute)) {
                    //重置秒
                    calendar.set(Calendar.SECOND, decNum);
                    return;
                }
            }
        }
        String second = matcher.group("second");
        if (second != null) {
            //本身就是相对日期时间
            //提取值
            //把day变为数字
            double num = getNum(second);
            int intNum = (int) num;
            int decNum = (int) ((num - intNum) * 12);
            if (flag == 1 || flag == 2) {
                calendar.add(Calendar.SECOND, intNum);
//                calendar.add(Calendar.SECOND, decNum);
            } else if (flag == 3) {
                calendar.add(Calendar.SECOND, -intNum);
//                calendar.add(Calendar.SECOND, -decNum);
            } else {
                //绝对日期
                calendar.set(calendar.SECOND, intNum);
            }
        }
    }

    private static void setDate(Calendar calendar, Matcher matcher) {
        //判断日期时间是相对还是绝对
        String string = matcher.group();
        int flag = 0;
        if (matcher.group("afterEnd") != null) {
            flag = 2;
        } else if (matcher.group("afterBegin") != null) {
            flag = 1;
        } else if (matcher.group("beforeEnd") != null) {
            flag = 3;
        }
        String year = matcher.group("year");
        if (year != null) {
            //本身就是相对日期时间
            Integer value = Year.get(year);
            if (value != null) {
                calendar.add(Calendar.YEAR, value);
            }
            //依赖flag判断是相对日期还是绝对日期
            else {
                //提取值
                //把year变为数字
                double numYear = getNum(year);
                int partYear = (int) numYear;
                int partMonth = (int) ((numYear - partYear) * 12);
                if (flag == 1 || flag == 2) {
                    calendar.add(Calendar.YEAR, partYear);
                    calendar.add(Calendar.MONTH, partMonth - 1);
                } else if (flag == 3) {
                    calendar.add(Calendar.YEAR, -partYear);
                    calendar.add(Calendar.MONTH, -partMonth - 1);
                } else {
                    //绝对日期
                    calendar.set(Calendar.YEAR, partYear);
                    if (string.endsWith(year)) {
                        //重置月日十分秒
                        calendar.set(Calendar.MONTH, 0);
                        calendar.set(Calendar.DAY_OF_MONTH, 0);
                        calendar.set(Calendar.HOUR_OF_DAY, 0);
                        calendar.set(Calendar.MINUTE, 0);
                        calendar.set(Calendar.SECOND, 0);

                        //以下基本不会出现（2016年半）
                        calendar.set(Calendar.MONTH, partYear - 1);
                        return;
                    }
                }
            }
        }
        String month = matcher.group("month");
        if (month != null) {
            //本身就是相对日期时间
            Integer value = Month.get(month);
            if (value != null) {
                calendar.add(Calendar.MONTH, value);
            } else {
                //提取值
                //把month变为数字
                double num = getNum(month);
                int intNum = (int) num;
                int decNum = (int) ((num - intNum) * 30);
                if (flag == 1 || flag == 2) {
                    calendar.add(Calendar.YEAR, intNum - 1);
                    calendar.add(Calendar.MONTH, decNum);
                } else if (flag == 3) {
                    calendar.add(Calendar.YEAR, -intNum - 1);
                    calendar.add(Calendar.MONTH, -decNum);
                } else {
                    //绝对日期
                    calendar.set(Calendar.MONTH, intNum - 1);
                    if (string.endsWith(month)) {
                        //重置日十分秒
                        calendar.set(Calendar.DAY_OF_MONTH, 0);
                        calendar.set(Calendar.HOUR_OF_DAY, 0);
                        calendar.set(Calendar.MINUTE, 0);
                        calendar.set(Calendar.SECOND, 0);

                        //7月半
                        calendar.set(Calendar.DAY_OF_MONTH, decNum);

                        return;

                    }
                }
            }
        }
        String week = matcher.group("week");
        if (week != null) {
            //本身就是相对日期时间
            Integer value = Week.get(week.substring(0, week.length()));
            if (value != null) {
                calendar.add(Calendar.WEEK_OF_MONTH, value);
                int day = Integer.parseInt(week.substring(week.length() - 1));
                calendar.set(Calendar.DAY_OF_WEEK, day);
            }
            if (string.endsWith(week)) {
                //重置十分秒
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                return;
            }
        }
        if (matcher.group("solarFestival") != null) {
            //节假日为绝对日期
            SolarFestival solarFestival = SolarFestivalMap.get(matcher.group("solarFestival"));
            calendar.set(Calendar.MONTH, solarFestival.month - 1);
            calendar.set(Calendar.DAY_OF_MONTH, solarFestival.day);
        }
        String day = matcher.group("day");
        boolean afternoon = false;
        boolean night = false;
        if (day != null) {
            //本身就是相对日期时间
            Integer value = Day.get(day);
            if (value != null) {
                calendar.add(Calendar.DAY_OF_MONTH, value);
            } else {
                value = DateTimePeriod.get(day);
                if (value != null) {
                    int i = value / 24;
                    int abs = Math.abs(value % 24);
                    if (abs >= 21) {
                        night = true;
                        afternoon = true;
                    } else if (abs > 12) {
                        afternoon = true;
                    }
                    calendar.add(Calendar.DAY_OF_MONTH, i);
                    //此时如果结束，以默认时间初始化（或者通过时间段flag,理解为时间段）
                    if (string.endsWith(day)) {
                        calendar.set(Calendar.HOUR_OF_DAY, abs);
                        calendar.set(Calendar.MINUTE, 0);
                        calendar.set(Calendar.SECOND, 0);
                        return;
                    }
                } else {
                    //提取值
                    //把day变为数字
                    double num = getNum(day);
                    int intNum = (int) num;
                    int decNum = (int) ((num - intNum) * 24);
                    if (flag == 1 || flag == 2) {
                        calendar.add(Calendar.DAY_OF_MONTH, intNum);
                        calendar.add(Calendar.HOUR_OF_DAY, decNum);
                    } else if (flag == 3) {
                        calendar.add(Calendar.DAY_OF_MONTH, -intNum);
                        calendar.add(Calendar.HOUR_OF_DAY, -decNum);
                    } else {
                        //绝对日期
                        calendar.set(Calendar.DAY_OF_MONTH, intNum);
                        //以下基本不会出现（7号半）
                        if (string.endsWith(day)) {
                            //重置十分秒
                            calendar.set(Calendar.HOUR_OF_DAY, 0);
                            calendar.set(Calendar.MINUTE, 0);
                            calendar.set(Calendar.SECOND, 0);
                            calendar.set(Calendar.HOUR_OF_DAY, decNum);
                            return;
                        }
                    }
                }
            }
        }
    }

    private static void setTime(Calendar calendar, Matcher matcher) {
        //判断日期时间是相对还是绝对
        String string = matcher.group();
        int flag = 0;
        if (matcher.group("afterEnd") != null) {
            flag = 2;
        } else if (matcher.group("afterBegin") != null) {
            flag = 1;
        } else if (matcher.group("beforeEnd") != null) {
            flag = 3;
        }
        boolean afternoon = false;
        boolean night = false;
        if (!afternoon) {
            String timePeriod = matcher.group("timePeriod");
            if (timePeriod != null) {
                Integer value = TimePeriod.get(timePeriod);
                if (value != null) {
                    if (value >= 21) {
                        night = true;
                        afternoon = true;
                    } else if (value > 12) {
                        afternoon = true;
                    }
                    //此时如果结束，以默认时间初始化（或者通过时间段flag,理解为时间段）
                    if (string.endsWith(timePeriod)) {
                        calendar.set(Calendar.HOUR_OF_DAY, value);
                        calendar.set(Calendar.MINUTE, 0);
                        calendar.set(Calendar.SECOND, 0);
                        return;
                    }
                }
            }
        }
        String hour = matcher.group("hour");
        if (hour != null) {
            //本身就是相对日期时间
            //提取值
            //把day变为数字
            boolean returned = false;
            double num = getNum(hour);
            int intNum = (int) num;
            int decNum = (int) ((num - intNum) * 60);
            if (flag == 1 || flag == 2) {
                calendar.add(Calendar.HOUR_OF_DAY, intNum);
                calendar.add(Calendar.MINUTE, decNum);
            } else if (flag == 3) {
                calendar.add(Calendar.HOUR_OF_DAY, -intNum);
                calendar.add(Calendar.MINUTE, -decNum);
            } else {
                //绝对日期
                calendar.set(calendar.HOUR_OF_DAY, intNum);
                if (string.endsWith(hour)) {
                    //重置分秒
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);

                    calendar.set(calendar.MINUTE, decNum);
                    returned = true;
                }
            }
            if (calendar.get(Calendar.HOUR_OF_DAY) < 12) {
                //小于12判断是否为中午之后
                if (afternoon) {
                    //如果是“晚上1-4点”，时间+24，
                    if (night && calendar.get(Calendar.HOUR_OF_DAY) <= 4) {
                        calendar.add(Calendar.HOUR_OF_DAY, 24);
                    } else {
                        calendar.add(Calendar.HOUR_OF_DAY, 12);
                    }
                }
            }
            if (returned) {
                return;
            }
        } else {
            //如果小时为空，默认为相对时间
            flag = 2;
        }
        String minute = matcher.group("minute");
        if (minute != null) {
            //本身就是相对日期时间
            //提取值
            //把day变为数字
            double num = getNum(minute);
            int intNum = (int) num;
            int decNum = (int) ((num - intNum) * 60);
            if (flag == 1 || flag == 2) {
                calendar.add(Calendar.MINUTE, intNum);
                calendar.add(Calendar.SECOND, decNum);
            } else if (flag == 3) {
                calendar.add(Calendar.MINUTE, -intNum);
                calendar.add(Calendar.SECOND, -decNum);
            } else {
                //绝对日期
                calendar.set(calendar.MINUTE, intNum);

                if (string.endsWith(minute)) {
                    //重置秒
                    calendar.set(Calendar.SECOND, decNum);
                    return;
                }
            }
        }
        String second = matcher.group("second");
        if (second != null) {
            //本身就是相对日期时间
            //提取值
            //把day变为数字
            double num = getNum(second);
            int intNum = (int) num;
            int decNum = (int) ((num - intNum) * 12);
            if (flag == 1 || flag == 2) {
                calendar.add(Calendar.SECOND, intNum);
//                calendar.add(Calendar.SECOND, decNum);
            } else if (flag == 3) {
                calendar.add(Calendar.SECOND, -intNum);
//                calendar.add(Calendar.SECOND, -decNum);
            } else {
                //绝对日期
                calendar.set(calendar.SECOND, intNum);
            }
        }
    }

    private static double getNum(String string) {
        string = string.replaceAll(unit, "");
        double value = 0;
        StringBuilder sb = new StringBuilder();
        //把“数字单位转成数”
        for (char c : string.toCharArray()) {
            Integer numUnit = CoreUtils.numUnitMap.get(c);
            //含有单位则加到数字里面（两千五百）
            if (numUnit != null) {
                //移除sb头部的0（比如两千零一）
                while (sb.length() > 0) {
                    if (sb.charAt(0) != '0') {
                        break;
                    }
                    sb.deleteCharAt(0);
                }
                double v = 1.0;
                if (sb.length() > 0) {
                    v = Double.parseDouble(sb.toString());
                }
                value += v * numUnit;
                sb.setLength(0);
            }
            //非数字单位直接组合放到一起
            else if (c == '半') {
                sb.append(".5");
            } else if (!TimeUnit.contains(c)) {
                sb.append(c);
            }
        }
        //如果sb不为空
        if (sb.length() > 0) {
            value += Double.parseDouble(sb.toString());
        }
        return value;
    }
}

class SolarFestival {
    String name;
    int month;
    int day;

    public SolarFestival(String name, int month, int day) {
        this.name = name;
        this.month = month;
        this.day = day;
    }
}
