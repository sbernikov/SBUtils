package com.sbtools.utils;

public final class FastTokenizer {
    
    private static final String EMPTY = "";
    private static ThreadLocal<String[]> sArray = new ThreadLocal<String[]>();
    
    public static int count(String source, int delimiter) {
        int index = -1;
        int wordCount = 1;
        
        while ((index = source.indexOf(delimiter, index + 1)) != -1) {
            wordCount++;
        }
        
        return wordCount;
    }
    
    public static int count(String source, String delimiter) {
        int delimiterLength = delimiter.length();
        
        int index = -delimiterLength;
        int wordCount = 1;
        
        while ((index = source.indexOf(delimiter, index + delimiterLength)) != -1) {
            wordCount++;
        }
        
        return wordCount;
    }
    
    public static String[] split(String source, int delimiter) {
        String[] array = sArray.get();
        int arrayLength = source.length();
        
        if (array == null || array.length < (arrayLength + 1)) {
            array = new String[arrayLength + 1];
            sArray.set(array);
        }
        
        int index = 0;
        int wordCount = 0;
        
        while (index < arrayLength) {
            array[wordCount] = nextToken(source, arrayLength, delimiter, index);
            
            index += array[wordCount].length() + 1;
            wordCount++;
        }
        
        if (index == arrayLength) {
            array[wordCount++] = EMPTY;
        }
        
        String[] result = new String[wordCount];
        System.arraycopy(array, 0, result, 0, wordCount);
        
        return result;
    }
    
    public static String[] split(String source, String delimiter) {
        String[] array = sArray.get();
        
        int arrayLength = source.length();
        int delimiterLength = delimiter.length();
        
        if (array == null || array.length < (arrayLength + 1)) {
            array = new String[arrayLength + 1];
            sArray.set(array);
        }
        
        int index = 0;
        int wordCount = 0;
        
        while (index < arrayLength) {
            array[wordCount] = nextToken(source, arrayLength, delimiter, index);
            
            index += array[wordCount].length() + delimiterLength;
            wordCount++;
        }
        
        if (index == arrayLength) {
            array[wordCount++] = EMPTY;
        }
        
        String[] result = new String[wordCount];
        System.arraycopy(array, 0, result, 0, wordCount);
        
        return result;
    }
    
    public static String nextToken(String source, int delimiter, int start) {
        return nextToken(source, source.length(), delimiter, start);
    }
    
    private static String nextToken(String source, int sourceLength, int delimiter, int start) {
        String token = null;
        int index = source.indexOf(delimiter, start);
        
        if (index == start) {
            token = EMPTY;
        } else if (index > 0) {
            token = source.substring(start, index);
        } else {
            token = source.substring(start, sourceLength);
        }
        
        return token;
    }
    
    public static String nextToken(String source, String delimiter, int start) {
        return nextToken(source, source.length(), delimiter, start);
    }
    
    private static String nextToken(String source, int sourceLength, String delimiter, int start) {
        String token = null;
        int index = source.indexOf(delimiter, start);
        
        if (index == start) {
            token = EMPTY;
        } else if (index > 0) {
            token = source.substring(start, index);
        } else {
            token = source.substring(start, sourceLength);
        }
        
        return token;
    }
}