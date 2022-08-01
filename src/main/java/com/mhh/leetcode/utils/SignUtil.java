package com.mhh.leetcode.utils;

import org.apache.commons.codec.binary.Base64;


import java.util.Map;



/**
 * @author zeming.fan@swiftpass.cn
 *
 */
public class SignUtil {
    //private final static Logger log = LoggerFactory.getLogger(SignUtil.class);
	 String priKey = "MIIEowIBAAKCAQEAn1PL+AVK/NUipvx9GoW3UGYxKTvJzMpPsbmEmrR8J1OP/5uO0fuBtmAeGejx7CYuPknHyaULrRYSqxcE12ekw6i72qmQw5CauRtu9YPj5q8xae3+NPxPZtKo3i+uESa2tK2apdt3bz1uL1aJjEYEXKDzp9GFlafjOk+T2hYBODPHYeJTGc+e4FcDnXdPBffWfdn90IGbRIyPvxF7zHp18W/2kaHzZLflnZNhCyejCD9HfVFOb91oC7rjb+UJ0GM4/0TK/Bg/sKw2IjUCcbGk5hPzhCYILLpoQ+HmGOWJUcthCDedOHLo2FSlNcDkmYKvaCQspxkdGj7kFsefCB8RLQIDAQABAoIBAB3xBXfKptj0xNpUwL3nOxfk3IA/OCFAM9HWZ1EbcemCDqGefix/81Ruo7mCyVPzqOUTwHPojFUuvLtiTAqe85vBbewdyZK27hyzRxrkekOz0LlzfD5A3Dsag5mdpDyc5eD2bwWURNGm3boPrTGrRs6RgLic61nAfzdKFSL616fT5zqn6P2OZ8ZqThQiJiaaSEtwC9aX7TbUGaPRteQjxw2LaVG1z7Q9jI41uDi8Srj0aB0sFIKOi1vV0sDV33LivpW8yy9AYZVg/SFf/sghFBG5TfO8scX4aUBWgIV4+Vvgd2LDDmbivd/lzDqWJsPBKVAzDmzp78G9DUh1K1dkVAECgYEA06I5ZzUtXu/rYz8xbToRj49bggTwBCHhwHJz4HfNGxIro8TCwpxmgL5p06zLuF6NHuXwD+Be0elA/iFLH69wgALuwmnUOPU1vkqLwL3IsvQGbklVSMMp7ukHWIBcjyntS2WABKuZPpgdVYeatEaMTDQAOaRmCoPPuDJprCYu1+0CgYEAwLpwjqiIh9dvG7P9VUSIQ6c7kVuUK/0kWztrGlYOUUh5zv5xk9jrsUk05HPMq8e6nfUpgMV5eZEdt/A4TDkDp0QaJpXZlbQCbiYK3UMrdMF33kFUKrIaglGhP8YRG6UgnWz0mWymWXaZVdMujub1yIccPtagL1y3McOOrN0fdkECgYEApUPno8sJFTwyCxzMEknocL04bn5AmFmFG23FQ6MKTnuQDKGYnbTY7uzDY4Nqyx07Asf5Aczq/u0xzcJeEe5E4hJoNPXDvn7uax13qRoLYoPhibpGmT2ev2zGSbxji4DoR5qJsBv43cKYFbSBgQoegVuSWXEiJk/spCDqGH7w0bkCgYAoMDh8ZwRhXzkaC8RN3YaUpPC09OXG7niAVbmVulAt/vD3ivNgIyGyzFMtOEB7qaBug8rtwT/9a5dI3dy/eDwcywSw+xpuLyU6ltA5J2KObDF0tSa56H3Cowb+52x12U8dxLSUsaVWjnaXAPx6bArgj9wB9ntcpPbGd8LupQWEgQKBgAmDOtI9QlUTUmcVDUYIsH1s0uvaiNXnsz3O8UBIGp6VQSPUmXEzadZKQypkIpFyeGG+V1p9dhwmP7BXgVukrt67jasb4NV4lD2x+gOp8XeQd0F9JkvfsA5ujjcrDy0XSm3xTtJnT+Ansrhj3bwyDFz+HSXS4TFC+KXCSyNAe4AR";

	//请求时根据不同签名方式去生成不同的sign
    public static String getSign(String signType,String preStr){
    	String priKey2 = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCfU8v4BUr81SKm/H0ahbdQZjEpO8nMyk+xuYSatHwnU4//m47R+4G2YB4Z6PHsJi4+ScfJpQutFhKrFwTXZ6TDqLvaqZDDkJq5G271g+PmrzFp7f40/E9m0qjeL64RJra0rZql23dvPW4vVomMRgRcoPOn0YWVp+M6T5PaFgE4M8dh4lMZz57gVwOdd08F99Z92f3QgZtEjI+/EXvMenXxb/aRofNkt+Wdk2ELJ6MIP0d9UU5v3WgLuuNv5QnQYzj/RMr8GD+wrDYiNQJxsaTmE/OEJggsumhD4eYY5YlRy2EIN504cujYVKU1wOSZgq9oJCynGR0aPuQWx58IHxEtAgMBAAECggEAHfEFd8qm2PTE2lTAvec7F+TcgD84IUAz0dZnURtx6YIOoZ5+LH/zVG6juYLJU/Oo5RPAc+iMVS68u2JMCp7zm8Ft7B3JkrbuHLNHGuR6Q7PQuXN8PkDcOxqDmZ2kPJzl4PZvBZRE0abdug+tMatGzpGAuJzrWcB/N0oVIvrXp9PnOqfo/Y5nxmpOFCImJppIS3AL1pftNtQZo9G15CPHDYtpUbXPtD2MjjW4OLxKuPRoHSwUgo6LW9XSwNXfcuK+lbzLL0BhlWD9IV/+yCEUEblN87yxxfhpQFaAhXj5W+B3YsMOZuK93+XMOpYmw8EpUDMObOnvwb0NSHUrV2RUAQKBgQDTojlnNS1e7+tjPzFtOhGPj1uCBPAEIeHAcnPgd80bEiujxMLCnGaAvmnTrMu4Xo0e5fAP4F7R6UD+IUsfr3CAAu7CadQ49TW+SovAvciy9AZuSVVIwynu6QdYgFyPKe1LZYAEq5k+mB1Vh5q0RoxMNAA5pGYKg8+4MmmsJi7X7QKBgQDAunCOqIiH128bs/1VRIhDpzuRW5Qr/SRbO2saVg5RSHnO/nGT2OuxSTTkc8yrx7qd9SmAxXl5kR238DhMOQOnRBomldmVtAJuJgrdQyt0wXfeQVQqshqCUaE/xhEbpSCdbPSZbKZZdplV0y6O5vXIhxw+1qAvXLcxw46s3R92QQKBgQClQ+ejywkVPDILHMwSSehwvThufkCYWYUbbcVDowpOe5AMoZidtNju7MNjg2rLHTsCx/kBzOr+7THNwl4R7kTiEmg09cO+fu5rHXepGgtig+GJukaZPZ6/bMZJvGOLgOhHmomwG/jdwpgVtIGBCh6BW5JZcSImT+ykIOoYfvDRuQKBgCgwOHxnBGFfORoLxE3dhpSk8LT05cbueIBVuZW6UC3+8PeK82AjIbLMUy04QHupoG6Dyu3BP/1rl0jd3L94PBzLBLD7Gm4vJTqW0DknYo5sMXS1JrnofcKjBv7nbHXZTx3EtJSxpVaOdpcA/HpsCuCP3AH2e1yk9sZ3wu6lBYSBAoGACYM60j1CVRNSZxUNRgiwfWzS69qI1eezPc7xQEganpVBI9SZcTNp1kpDKmQikXJ4Yb5XWn12HCY/sFeBW6Su3ruNqxvg1XiUPbH6A6nxd5B3QX0mS9+wDm6ONysPLRdKbfFO0mdP4CeyuGPdvDIMXP4dJdLhMUL4pcJLI0B7gBE=";
    	String priKey3= "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCPzouiIGTw3tdnfYLksNSxOH4OTWJu53H/QuJ/IGaXe8oyF9LVepbLIexMs64Z52JNB/9glYTwjEK54t+T/X0S+p0oOenu6wwAjsggGjTG9cAFBnvph0OLHf9oogqOdxJmfah56h+NBkO1G0mNkhM9e3Be7s9WhPfborcLTJfHnlq9HgRLYUGfFy+eMRq5axMfk2X1HD8c4IsOyECMC1WZxSavTExe07MRo+ToK/RpYIykxpCLVftK0VETRrYDNpGmQ8VwVscfAaFxQbz1J26K2aqo3DzanrQYQyg3F51MW/0bQIE5gNTVyNsoJXGRaTILjYSfH/NnGtM7/0a0lkYtAgMBAAECggEBAIlXG2glRAH+8P23ktDSSO9QIqOKKF3+0JKW0DXVPeWipgKdWg/j2iv6I/FFERtQLkOfRbh6U9JgQkHBeQGfcFo12u3WEaLJ/FZ0BpESNiFmBpNGFAV913X/mjPVvfs9oOcdT0/P134Z44QNoXuBbLqlLzabII1ixEyQ4/v4lGRhW0yV/erowRtNVYXdvO8jG2UKcrtblkObNi5m0pnXw5b5DPu3qBfh1XBja1N/badgm1VfGfGRRHvD6V0cgB8mjHPqKd8uoLPwVs5qQf3RcgRUzK7yyjhOvRIWuz4lYOtXVvBusgUR8DOD2yttPKCZfXAQ8MHppCWzjsWXHBqp7kECgYEAxyVFRGP/2LfO/Ye6noOXECpumfuxAUCQT9zY4bisktlErf0GofZQuEMHU+0QcDmLKI5NWFosenRsDMJZDVrYIv/8KdzI7xEvkaQuv9z41yj9PiZFvsYn5lEiTr8FQvX5KYfFcNUTSJRrWCLOh85eVuaHMU1Fs1K58GRiHIWlTjUCgYEAuNzK37igt2imgIXhZbxRqm4+jqXhliDj7Q0N88CFfPzvR+oh2noj647ooI96LFP26zVxKi9M3bxr+FNsgzwPXSRXsvdgEWMHlwy8idW4Uoe/m+YvJ7mcUiN722/XAEMYlOZyLa/Grrqg7i0vBrIsn7NmX860oy1viPHyUF6bdxkCgYEAomFzj4ZykYWfE/9eXe4yeQ4ATTJ7a+f3RAdyGOt6W+spIy2UAPBVdUnCad59buSc4Z7W1loAtoDDscuuzULrxe4w8PmEJds/jGPxcqR9iwNzKwAOEraExjoV3VvIsIbcec6pYY18QcwzkxQej4HoTrd+OOcnFEc6MhfIIDWhSpkCgYAnfvTRS676wJ8iQnhfthZ0pU00SPijjK9lzC44798VTy0IXluRRBs8jrHg7AacRewxYJ+m9agkj8c5rQk4GLLnoYYjNHsP++p5OT1m7YSODXbApjNY920Ql4g03/Y9lhhAZ+PpIu7+Ed2kRX8QnSyvijLLMWyimGiGB7tPtIbesQKBgH+ez7/0uc8L6DlDxiIyLtZsIhJA6gv2MYQaCOTm1mIITbY8zcNu/875YCQKdiFmoN8LQLTgvJLOQP0qw/7EdzceTcuY7rC9GrlBGBEgMU186UkgdGa+twriQ/+RcMwfm/qhC6/VWmIjBtFfM6t9s0D6GgtpEQe8RC+5FHOezHSy";
    	String priKey4 = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDZXJbeyjF+ULC6HLZu82ZnZvnirbDCHKj075AjcvFBkSfV4vzJ1584+8+AhgPemzHg4myiQHWbia3A6atZ/siGYTYj6AyLjBLLrUKASO2KAQiJ3yTIvgV3e5SxfYgF+NweT0HIHZxNkj/Sm0ut4FAc7Ifce/3yJQIPe5diftBLobw9ON9jWINz2FEhL1qstl/lNR5i6KKAEPYSSQHGdgpEnogccVw/IcSyRjFTHl5kLQ8n5Iu7mZM4Zu3Ap1pnH2Q8ir5e62ATBOu1Gf9QMkRgue5pk+nJjLK6P4C1lmf7Vt5D2WzfbdbiGMRUnNjzQT1QAdGTYoubAZDQx66HMyhhAgMBAAECggEBAKcdY7WIuQLH4QhGWRD2vA3WA1t/dP3Y8SHYKfbHQavKgVkVuoso53b1S3UiFEmLAo9HJoSckI90DA3c2exIUj3W8bwrhbGokS7dV9qvt0m6P6lY0bwxNJzHe+v2eqU0z2/SOd40vd56Ce3PjiDx7EZAqLgg8H7Ca1IiEKMeouDx3cCdz/M2sQKuic9XQJ2FCvmgwUOF4kHKdvxRoDeBhO0H9saGSPDzeQR8BOrjAM9mgDQeri9D7XOGuI8iGmAFxckptXpScloiGyctDM9MR+L4lhS9WS9pNoopRl0EXhCmVgj4RBvUbAdkBnzXMeFfebHmO4/wNO6+/Q6cN6HX0kECgYEA/hWGlglgwU96y3d1jEn9fSFvRu0f2bsE/KgljNio3juBdSA1YNzmQKsGZZQFaSnP9FC8vXZX9Wa0hRddAVf/mx1IUyGwXZaLpgpKaHeUIvKUeKXdxId0J0ZN1GY5QLeUUqutSzqs6n/iaLw6L/Zzvqj9uD1RBR/sB+E90eGd0gkCgYEA2wAtEsuF0WhXi3SgmedGEKpMuAjiZaW7HINUmXK5MptxUqn7ogIjuml+eWEIxb3vtbJAA3xdpzDPtWpxtdtjC2pDUJ8RyByEi3GckwIsDaEsmmoyrvYBcr+uv21XWxzmoT3bVUH2VlT4ySPwG4dI7e1t8T0X0NtnaoPAJxvf2ZkCgYBNAwlAAKeq8AQwF40xcFu8yZ2IyeaVf/ILAowfHKxSb4bVkWWcblf70dqmfy8EqP+29aG/a1dNskuU63ftKFb5FO8PA28Jbs/1C7lx4Iyt6wjFaMDHCKdUICMG9dT0oK/8PrzPyIEQsqJVTHf2gaSjbWU5qpwcCYjo+oRaUSAcwQKBgBgaa9uOtNq4XjJGEsr9jlOVOr5ik/BWDkL0HWKKIuH0A8PyW3SYQbBpnfbj/CAvaGuaA7uee3xEPggcgAlg7QS7nE8KQt3AbcIfLflsGschPxR7rbpd7wjfGe+NrDil9Kg4c6RtjxwvW/ZfUzTkbd1p7VjIvY9KJpati3+CbN+5AoGAXPVoyiHgIQpQvuffW9/n1JDUL0OZRFyKnkdispehrVU2ebDw0uv3xCBtrJcgQnzylt30HryD/zITmELt+Ot7wuvZ3uYM+Hvb6OC2HMYWXDezEKuQ6vWdFqqmAzjF5BQ+z9/ecLfjN2Qaf/9qZu68UyMXt41HUsrmqrMLnklDCqE=";
    	if("RSA_1_256".equals(signType)){
        	try {
        		return SignUtil.sign(preStr,"RSA_1_256",priKey4);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }else{
        	//return MD5.sign(preStr, "&key=" + SwiftpassConfig.key, "utf-8");
        }
    	return null;
    }
    
    //对返回参数的验证签名
    public static boolean verifySign(String sign,String signType,Map<String,String> resultMap) throws Exception{
    	if("RSA_1_256".equals(signType)){
    		Map<String,String> Reparams = SignUtils.paraFilter(resultMap);
            StringBuilder Rebuf = new StringBuilder((Reparams.size() +1) * 10);
            SignUtils.buildPayParams(Rebuf,Reparams,false);
            String RepreStr = Rebuf.toString();
            if(SignUtil.verifySign(RepreStr,sign, "RSA_1_256", "MIIEowIBAAKCAQEAn1PL+AVK/NUipvx9GoW3UGYxKTvJzMpPsbmEmrR8J1OP/5uO0fuBtmAeGejx7CYuPknHyaULrRYSqxcE12ekw6i72qmQw5CauRtu9YPj5q8xae3+NPxPZtKo3i+uESa2tK2apdt3bz1uL1aJjEYEXKDzp9GFlafjOk+T2hYBODPHYeJTGc+e4FcDnXdPBffWfdn90IGbRIyPvxF7zHp18W/2kaHzZLflnZNhCyejCD9HfVFOb91oC7rjb+UJ0GM4/0TK/Bg/sKw2IjUCcbGk5hPzhCYILLpoQ+HmGOWJUcthCDedOHLo2FSlNcDkmYKvaCQspxkdGj7kFsefCB8RLQIDAQABAoIBAB3xBXfKptj0xNpUwL3nOxfk3IA/OCFAM9HWZ1EbcemCDqGefix/81Ruo7mCyVPzqOUTwHPojFUuvLtiTAqe85vBbewdyZK27hyzRxrkekOz0LlzfD5A3Dsag5mdpDyc5eD2bwWURNGm3boPrTGrRs6RgLic61nAfzdKFSL616fT5zqn6P2OZ8ZqThQiJiaaSEtwC9aX7TbUGaPRteQjxw2LaVG1z7Q9jI41uDi8Srj0aB0sFIKOi1vV0sDV33LivpW8yy9AYZVg/SFf/sghFBG5TfO8scX4aUBWgIV4+Vvgd2LDDmbivd/lzDqWJsPBKVAzDmzp78G9DUh1K1dkVAECgYEA06I5ZzUtXu/rYz8xbToRj49bggTwBCHhwHJz4HfNGxIro8TCwpxmgL5p06zLuF6NHuXwD+Be0elA/iFLH69wgALuwmnUOPU1vkqLwL3IsvQGbklVSMMp7ukHWIBcjyntS2WABKuZPpgdVYeatEaMTDQAOaRmCoPPuDJprCYu1+0CgYEAwLpwjqiIh9dvG7P9VUSIQ6c7kVuUK/0kWztrGlYOUUh5zv5xk9jrsUk05HPMq8e6nfUpgMV5eZEdt/A4TDkDp0QaJpXZlbQCbiYK3UMrdMF33kFUKrIaglGhP8YRG6UgnWz0mWymWXaZVdMujub1yIccPtagL1y3McOOrN0fdkECgYEApUPno8sJFTwyCxzMEknocL04bn5AmFmFG23FQ6MKTnuQDKGYnbTY7uzDY4Nqyx07Asf5Aczq/u0xzcJeEe5E4hJoNPXDvn7uax13qRoLYoPhibpGmT2ev2zGSbxji4DoR5qJsBv43cKYFbSBgQoegVuSWXEiJk/spCDqGH7w0bkCgYAoMDh8ZwRhXzkaC8RN3YaUpPC09OXG7niAVbmVulAt/vD3ivNgIyGyzFMtOEB7qaBug8rtwT/9a5dI3dy/eDwcywSw+xpuLyU6ltA5J2KObDF0tSa56H3Cowb+52x12U8dxLSUsaVWjnaXAPx6bArgj9wB9ntcpPbGd8LupQWEgQKBgAmDOtI9QlUTUmcVDUYIsH1s0uvaiNXnsz3O8UBIGp6VQSPUmXEzadZKQypkIpFyeGG+V1p9dhwmP7BXgVukrt67jasb4NV4lD2x+gOp8XeQd0F9JkvfsA5ujjcrDy0XSm3xTtJnT+Ansrhj3bwyDFz+HSXS4TFC+KXCSyNAe4AR")){
            	return true;
            }
    	}else if("MD5".equals(signType)){
    	//	if(SignUtils.checkParam(resultMap, SwiftpassConfig.key)){
    		//	return true;
    		//}
    	}
    	return false;
    }
	public static boolean verifySign(String preStr,String sign,String signType, String platPublicKey) throws Exception {
		// 调用这个函数前需要先判断是MD5还是RSA
		// 商户的验签函数要同时支持MD5和RSA
		RSAUtil.SignatureSuite suite = null;
		
		if ("RSA_1_1".equals(signType)) {
			suite = RSAUtil.SignatureSuite.SHA1;
		} else if ("RSA_1_256".equals(signType)) {
			suite = RSAUtil.SignatureSuite.SHA256;
		} else {
			throw new Exception("不支持的签名方式");
		}
        
		boolean result = RSAUtil.verifySign(suite, preStr.getBytes("UTF8"), Base64.decodeBase64(sign.getBytes("UTF8")),
                platPublicKey);
        
		return result;
    }

    public static String sign(String preStr, String signType, String mchPrivateKey) throws Exception {
		RSAUtil.SignatureSuite suite = null;
		if ("RSA_1_1".equals(signType)) {
			suite = RSAUtil.SignatureSuite.SHA1;
		} else if ("RSA_1_256".equals(signType)) {
			suite = RSAUtil.SignatureSuite.SHA256;
		} else {
			throw new Exception("不支持的签名方式");
		}
        byte[] signBuf = RSAUtil.sign(suite, preStr.getBytes("UTF8"),
                mchPrivateKey);
        return new String(Base64.encodeBase64(signBuf), "UTF8");
    }
}
