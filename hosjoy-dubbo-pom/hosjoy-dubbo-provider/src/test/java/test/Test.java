package test;

import java.math.BigDecimal;

class ThreadMy implements Runnable{
	public void run(){    
        for(int i = 0; i < 2; i++){  
            try {  
                System.out.println(Thread.currentThread().getName()+ "休眠0.5秒!");  
                Thread.sleep(5000);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
            System.out.println("当前运行的线程名称： "+ Thread.currentThread().getName());      
        }  
              
    }
	
}
public class Test   {  
	public static void main(String[] args){  
		ThreadMy mt1 = new ThreadMy();  
		Thread th = new Thread(mt1, "线程A");  
		System.out.println("\n Thread is starting");  
		th.start();  
		try {  
			Thread.sleep(1000);  
		} catch (InterruptedException e) {  
			
			e.printStackTrace();  
		}  
		System.out.println("\n主线程已经休眠1s "+ Thread.currentThread().getName());    
		
		
//		public static void main(String[] args) {
//	        double b = 12.34;
//	        BigDecimal a = new BigDecimal(b);
//	        System.out.println(a.multiply(new BigDecimal(100.01)).setScale(2,BigDecimal.ROUND_HALF_UP));
	        System.out.println(new BigDecimal("100.45").setScale(1, BigDecimal.ROUND_HALF_DOWN));
	        System.out.println(new BigDecimal("100.45").setScale(1, BigDecimal.ROUND_HALF_UP));
	        System.out.println(String.format("Hi,%s:%s.%s", "王南","王力",213.45)); 
//	        BigDecimal C = new BigDecimal(1.455);
//	        System.out.println("down="+C.setScale(1,BigDecimal.ROUND_HALF_DOWN)+"/tup="+C.setScale(1,BigDecimal.ROUND_HALF_UP));
	        
//	    }

	} 
}