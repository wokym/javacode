package dingshi;
import java.util.Calendar;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

/**
 * DateTimeResult
 *
 * @author 储兵兵
 * @date 2016/10/24
 */
public class DateTimeResult {
    private int start;
    private int end;
    private Calendar value;
    private String rawValue;
    private int level;

    public DateTimeResult() {
        start = -1;
        end = -1;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public Calendar getValue() {
        return value;
    }

    public void setValue(Calendar value) {
        this.value = value;
    }

    public String getRawValue() {
        return rawValue;
    }

    public void setRawValue(String rawValue) {
        this.rawValue = rawValue;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
    @Override
    public String toString() {
    	return JSONObject.fromObject(this).toString();
    }
}
