package util;


import java.util.Arrays;
import java.util.List;

public class NameUtil {

    public static List<String> firstNameList = null;
    public static String chenghu = "哥哥|姐姐|弟弟|妹妹|爸爸|老爸|老妈|妈妈|叔叔|阿姨|公公|婆婆|奶奶|小孩|大侠|大哥|小妹|小弟|小姐|大姐|美女|帅哥|老板|老头|老人|老舅|老师|老公|老婆|老丈|小朋友|学生|学长|学妹|学姐|学弟|学霸|小学生|大学生|大人|小人|老大|老二|老三|老四|老五|老六|老七|老八|老九|老十";
    public static String chenghu2 = "高手|高富帅|白富美|那么|那个|那样|那里|程序|简单|董事|文科|帅哥|公主|王子|班长|班主任|吕孩|单身|后来|商人|管理|农村";
    static {
        String firstName = "赵|钱|孙|李|周|吴|郑|王|冯|陈|楮|卫|蒋|沈|韩|杨|朱|秦|尤|许|何|吕|施|张|孔|曹|严|华|金|魏|陶|姜|戚|谢|邹|喻|柏|水|窦|章|云|苏|潘|葛|奚|范|彭|郎|鲁|韦|昌|马|苗|凤|花|方|俞|任|袁|柳|酆|鲍|史|唐|费|廉|岑|薛|雷|贺|倪|汤|滕|殷|罗|毕|郝|邬|安|常|乐|于|时|傅|皮|卞|齐|康|伍|余|元|卜|顾|孟|平|黄|和|穆|萧|尹|姚|邵|湛|汪|祁|毛|禹|狄|米|贝|明|臧|计|伏|成|戴|谈|宋|茅|庞|熊|纪|舒|屈|项|祝|董|梁|杜|阮|蓝|闽|席|季|麻|强|贾|路|娄|危|江|童|颜|郭|梅|盛|林|刁|锺|徐|丘|骆|高|夏|蔡|田|樊|胡|凌|霍|虞|万|支|柯|昝|管|卢|莫|经|房|裘|缪|干|解|应|宗|丁|宣|贲|邓|郁|单|杭|洪|包|诸|左|石|崔|吉|钮|龚|程|嵇|邢|滑|裴|陆|荣|翁|荀|羊|於|惠|甄|麹|家|封|芮|羿|储|靳|汲|邴|糜|松|井|段|富|巫|乌|焦|巴|弓|牧|隗|山|谷|车|侯|宓|蓬|全|郗|班|仰|秋|仲|伊|宫|宁|仇|栾|暴|甘|斜|厉|戎|祖|武|符|刘|景|詹|束|龙|叶|幸|司|韶|郜|黎|蓟|薄|印|宿|白|怀|蒲|邰|从|鄂|索|咸|籍|赖|卓|蔺|屠|蒙|池|乔|阴|郁|胥|能|苍|双|闻|莘|党|翟|谭|贡|劳|逄|姬|申|扶|堵|冉|宰|郦|雍|郤|璩|桑|桂|濮|牛|寿|通|边|扈|燕|冀|郏|浦|尚|农|温|别|庄|晏|柴|瞿|阎|充|慕|连|茹|习|宦|艾|鱼|容|向|古|易|慎|戈|廖|庾|终|暨|居|衡|步|都|耿|满|弘|匡|国|文|寇|广|禄|阙|东|欧|殳|沃|利|蔚|越|夔|隆|师|巩|厍|聂|晁|勾|敖|融|冷|訾|辛|阚|那|简|饶|空|曾|毋|沙|乜|养|鞠|须|丰|巢|关|蒯|相|查|后|荆|红|游|竺|权|逑|盖|益|桓|公|万俟|司马|上官|欧阳|夏侯|诸葛|闻人|东方|赫连|皇甫|尉迟|公羊|澹台|公冶|宗政|濮阳|淳于|单于|太叔|申屠|公孙|仲孙|轩辕|令狐|锺离|宇文|长孙|慕容|鲜于|闾丘|司徒|司空|丌官|司寇|仉|督|子车|颛孙|端木|巫马|公西|漆雕|乐正|壤驷|公良|拓拔|夹谷|宰父|谷梁|晋|楚|阎|法|汝|鄢|涂|钦|段干|百里|东郭|南门|呼延|归|海|羊舌|微生|岳|帅|缑|亢|况|后|琴|梁丘|左丘|东门|西门|商|牟|佘|佴|伯|赏|南宫|墨|哈|谯|笪|年|爱|阳|佟|邱";
        firstNameList = Arrays.asList(firstName.split("\\|"));

    }
    private static boolean isName(String str){
        if (str == null || str.length() < 2 ){
            return false;
        }
        if (chenghu2.contains(str)){
            return false;
        }

        if (firstNameList.contains(str.substring(0, 1)) && str.length() <= 3){
            return true;
        }

        if (firstNameList.contains(str.substring(0, 2)) && str.length() <=4){
            return true;
        }

        return false;

    }

    private static boolean isNickName(String str){
        if (str == null || str.length() < 2 || str.length() > 3){
            return false;
        }

        //排除类似 "我是中国的"、 "我是中国人" 句式
        if (str.endsWith("的") || str.endsWith("人")){
            return false;
        }

        if (chenghu2.contains(str)){
            return false;
        }

        if (str.startsWith("小") || str.startsWith("阿")){
            return true;
        }

        if (str.length() == 2 && str.charAt(0) == str.charAt(1) ){
            return true;
        }
        return false;
    }
public static void main(String[] args) {
	System.out.println(checkUserName2("张三丰"));
}
    public static Object checkUserName2(String cmd){
        //名字长度不符合
        if (cmd.length() < 2 || cmd.length() > 4){
            return 0;
        }

        //排除类似 "我是中国的"、 "我是中国人" 句式
        if (cmd.endsWith("的") || cmd.endsWith("人")){
            return 0;
        }


        if (isName(cmd) || isNickName(cmd)){
            return cmd;
        }

        return 0;
    }
}
