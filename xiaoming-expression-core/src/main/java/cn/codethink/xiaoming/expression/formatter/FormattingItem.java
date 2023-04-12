package cn.codethink.xiaoming.expression.formatter;

import java.util.List;

/**
 * <h1>格式串中的单元</h1>
 *
 * @author Chuanwise
 */
public interface FormattingItem {
    static FormattingItem parse(String text) {
        final int length = text.length();
        
        int countOfSpacesBeforeIt = 0;
        while (countOfSpacesBeforeIt < length && text.charAt(countOfSpacesBeforeIt) == ' ') {
            countOfSpacesBeforeIt++;
        }
        
        if (countOfSpacesBeforeIt == length) {
            return FormattingSpaceItem.of(length);
        }
        
        int countOfSpacesAfterIt = 0;
        while (countOfSpacesAfterIt >= 0 && text.charAt(length - countOfSpacesAfterIt - 1) == ' ') {
            countOfSpacesAfterIt--;
        }
        
        return new FormattingTextItem(countOfSpacesBeforeIt, text.substring(countOfSpacesBeforeIt, length - countOfSpacesAfterIt), countOfSpacesAfterIt);
    }
    
    static String toString(List<FormattingItem> items, boolean minimize) {
        final StringBuilder stringBuilder = new StringBuilder();
        
        if (minimize) {
            int count = 0;
            for (FormattingItem item : items) {
                if (item instanceof FormattingSpaceItem) {
                    count = Math.max(count, ((FormattingSpaceItem) item).getCount());
                } else {
                    final FormattingTextItem textItem = (FormattingTextItem) item;
                    count = Math.max(count, textItem.getCountOfSpacesBeforeContent());
                    for (int i = 0; i < count; i++) {
                        stringBuilder.append(' ');
                    }
                    count = textItem.getCountOfSpacesAfterContent();
                    stringBuilder.append(textItem.getContent());
                }
            }
            for (int i = 0; i < count; i++) {
                stringBuilder.append(' ');
            }
        } else {
            for (FormattingItem item : items) {
                if (item instanceof FormattingSpaceItem) {
                    final int count = ((FormattingSpaceItem) item).getCount();
                    for (int i = 0; i < count; i++) {
                        stringBuilder.append(' ');
                    }
                } else {
                    final FormattingTextItem textItem = (FormattingTextItem) item;
                    for (int i = 0; i < textItem.getCountOfSpacesBeforeContent(); i++) {
                        stringBuilder.append(' ');
                    }
                    stringBuilder.append(textItem.getContent());
                    for (int i = 0; i < textItem.getCountOfSpacesAfterContent(); i++) {
                        stringBuilder.append(' ');
                    }
                }
            }
        }
        
        return stringBuilder.toString();
    }
}
