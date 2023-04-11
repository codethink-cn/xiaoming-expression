package cn.codethink.xiaoming.expression.util;

import com.google.common.base.Preconditions;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class Strings {
    private Strings() {
        throw new NoSuchElementException("No " + String.class.getName() + " instances for you!");
    }
    
    public static String spaces(int count) {
        Preconditions.checkArgument(count >= 0, "Count must be greater than or equals to 0!");
        
        if (count == 0) {
            return "";
        }
        final char[] chars = new char[count];
        Arrays.fill(chars, ' ');
        return new String(chars);
    }
}
