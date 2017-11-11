package com.wang.util;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    private static final Pattern holderRegExp = Pattern.compile("\\{\\}");

    private Util(){
    }

    /**
     * 字符合并
     */
    public static String append(Object... arr){
        StringBuilder sb = new StringBuilder();
        for(Object obj : arr){
            sb.append(obj);
        }
        return sb.toString();
    }

    /**
     * 判断对象值是否为空： 若对象为字符串，判断对象值是否为null或空格; 若对象为数组，判断对象值是否为null，或数组个数是否为0; 若对象为List。判断对象值是否为null，或List元素是否个数为0;
     * 其他类型对象，只判断值是否为null.
     * 
     * @param value
     * @return true
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object value){
        if(value == null){
            return true;
        }else if((value instanceof String) && (((String)value).trim().length() < 1)){
            return true;
        }else if(value.getClass().isArray()){
            if(0 == java.lang.reflect.Array.getLength(value)){
                return true;
            }
        }else if(value instanceof Collection){
            if(((Collection)value).isEmpty()){
                return true;
            }
        }else if(value instanceof Map){
            if(((Map)value).isEmpty()){
                return true;
            }
        }
        return false;
    }
    
    public static boolean isNotEmpty(Object value){
        return !isEmpty(value);
    }

    /**
     * 判断字符串是否为整数
     * 
     * @param str
     * @return
     */
    public static boolean isInteger(String str){
        if(str == null || "".equals(str))
            return false;
        return Pattern.matches("^[-+0-9]*$", str.trim());
    }

    /**
     * 获取对象的字节个数.
     * 
     * @param obj
     * @return
     */
    public static int getUtf8BytesLength(Object obj){
        try{
            if(obj instanceof String){
                return ((String)obj).getBytes("UTF-8").length;
            }

            return String.valueOf(obj).getBytes("UTF-8").length;
        }catch(Exception e){
            if(obj instanceof String){
                return ((String)obj).getBytes().length;
            }

            return String.valueOf(obj).getBytes().length;
        }

    }

    /**
     * 取得客户端IP. 注：像移动网关一样，iisforward这个ISAPI过滤器也会对request对象进行再包装，附加一些WLS要用的头信息。这种情况下，直接用
     * request.getRemoteAddr()是无法取到真正的客户IP的。
     * 
     * @param request
     *            客户端的HTTP请求
     * @return
     */
    public static String getRemoteIP(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() <= 0 || ip.equalsIgnoreCase("unknown")){
            ip = request.getHeader("Proxy-Client-IP");
        }

        if(ip == null || ip.length() <= 0 || ip.equalsIgnoreCase("unknown")){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if(ip == null || ip.length() <= 0 || ip.equalsIgnoreCase("unknown")){
            ip = request.getRemoteAddr();
            if(ip.equals("127.0.0.1")){
                // 根据网卡取本机配置的IP
                try{
                    ip = InetAddress.getLocalHost().getHostAddress();
                }catch(UnknownHostException e){
                    e.printStackTrace();
                }
            }
        }

        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照","分割，一个IP地址的最大长度为15
        if(ip != null && ip.length() > 15){
            if(ip.indexOf(",") > 0){
                ip = ip.substring(0, ip.indexOf(","));
            }
        }

        if("0:0:0:0:0:0:0:1".equals(ip)){
            ip = "127.0.0.1";
        }
        return ip;
    }

    /**
     * 产生一个指定位数的随机码数字
     */
    public static String createRandomCode(int randomCodeLen){
        final String str = "0123456789";
        final int maxNum = str.length();
        StringBuffer randomCode = new StringBuffer("");
        Random r = new Random();
        int count = 0;
        int ranDomNum = 0;
        while(count < randomCodeLen){
            // 生成随机数，取绝对值，防止生成负数，
            ranDomNum = Math.abs(r.nextInt(maxNum)); // 生成的数最大为maxNum-1, 最小为0
            randomCode.append(str.charAt(ranDomNum));
            count++;
        }

        return randomCode.toString();
    }

    /**
     * 对字符串进行MD5加密
     * 
     * @param str
     * @return
     * @throws Exception
     */
    public static String encodeMD5(String str) throws Exception{
        if(isEmpty(str)){
            return str;
        }

        MessageDigest mad5 = MessageDigest.getInstance("MD5");
        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for(int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte)charArray[i];
        byte[] md5Bytes = mad5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for(int i = 0; i < md5Bytes.length; i++){
            int val = ((int)md5Bytes[i]) & 0xff;
            if(val < 16){
                hexValue.append("0");
            }

            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static int toInt(String s, int defaultValue){
        try{
            return Integer.parseInt(s);
        }catch(NumberFormatException e){
        }
        return defaultValue;
    }

    public static long toLong(String s, long defaultValue){
        try{
            return Long.parseLong(s);
        }catch(NumberFormatException e){
        }
        return defaultValue;
    }

    public static String formatString(String msg, Object... params){
        if(isEmpty(msg)){
            return null;
        }
        if(isEmpty(params)){
            return msg;
        }
        StringBuffer s = new StringBuffer();
        Matcher m = holderRegExp.matcher(msg);

        int i = 0;
        while(m.find() && i < params.length){
            m.appendReplacement(s, String.valueOf(params[i]));

            i++;
        }

        m.appendTail(s);
        return s.toString();
    }

    public static String percentRate(Number a, Number b, int scale, boolean needUnit){
        double d = div(mul(a, 100), b, 1);
        return isDoubleZero(d)? "0" : (d + (needUnit?"%":""));
    }
    
    public static double div(Number a, Number b, int scale){
        BigDecimal a1 = new BigDecimal(Double.toString(a.doubleValue()));
        BigDecimal b1 = new BigDecimal(Double.toString(b.doubleValue()));
        if(isDoubleZero(b.doubleValue())) {
            return 0;
        }
        return a1.divide(b1, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    public static boolean isDoubleZero(Double d){
        BigDecimal zero = new BigDecimal(0.0D);
        BigDecimal src = new BigDecimal(d);
        return src.compareTo(zero) == 0;
    }
    
    public static double mul(Number a, Number b){
        BigDecimal a1 = new BigDecimal(Double.toString(a.doubleValue()));
        BigDecimal b1 = new BigDecimal(Double.toString(b.doubleValue()));
        return a1.multiply(b1).doubleValue();
    }
    
    public static int mul2(Number a, Number b){
        BigDecimal a1 = new BigDecimal(Double.toString(a.doubleValue()));
        BigDecimal b1 = new BigDecimal(Double.toString(b.doubleValue()));
        return a1.multiply(b1).intValue();
    }
    
    public static String getThrowableStackTrace(Throwable t){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        try{
            t.printStackTrace(pw);
            String str = sw.toString();
            return str;
        }finally{
            try{
                pw.close();
            }catch(Exception e){ 
            }
            
            try{
                sw.close();
            }catch(Exception e){ 
            }
        }
    }
    
    public static void main(String[] args){
        try{
            System.out.println(Util.encodeMD5("jsecode@123"));
            System.out.println(Util.encodeMD5(Util.encodeMD5("123456")));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
