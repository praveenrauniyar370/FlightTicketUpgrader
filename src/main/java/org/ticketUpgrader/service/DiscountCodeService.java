package org.ticketUpgrader.service;

import java.util.regex.Pattern;

public class DiscountCodeService {
    public static String getDiscountCode(String fareClass){

     if(Pattern.compile("^[A-E]$").matcher(fareClass).matches()){
         return "OFFER_20";
     }
     if(Pattern.compile("^[F-K]$").matcher(fareClass).matches()){
         return "OFFER_30";
     }

     if(Pattern.compile("^[L-R]$").matcher(fareClass).matches()){
         return "OFFER_25";
     } else {
            return "";
     }
    }
}
