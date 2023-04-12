package cn.codethink.xiaoming.expression.formatter;

import com.google.common.base.Preconditions;

public class FormattingSpaceItem
    implements FormattingItem {
    
    private static final FormattingSpaceItem ZERO = new FormattingSpaceItem(0);
    
    public static FormattingSpaceItem of(int count) {
        if (count == 0) {
            return ZERO;
        } else {
            return new FormattingSpaceItem(count);
        }
    }
    
    private final int count;
    
    private FormattingSpaceItem(int count) {
        Preconditions.checkArgument(count >= 0, "Count of spaces must be greater than or equals to 0!");
        this.count = count;
    }
    
    public int getCount() {
        return count;
    }
}
