package com.shangzf.oauth.multi;

public class MultiAuthenticationContext {

    private static ThreadLocal<MultiAuthentication> threadLocal = new ThreadLocal<>();

   public static MultiAuthentication get(){
       return threadLocal.get();
   }

   public static void set(MultiAuthentication authentication){
       threadLocal.set(authentication);
   }

   public static void clear(){
       threadLocal.remove();
   }
}
