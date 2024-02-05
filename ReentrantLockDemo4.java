import java.util.concurrent.locks.*;
	import java.util.concurrent.*;

	class MyThread extends Thread {
		
		static ReentrantLock l = new ReentrantLock();
		MyThread(String name){
			super(name);
		}
		public void run(){
			do {
				
				try{
					if(l.tryLock(5000,TimeUnit.MILLISECONDS))
					{
						System.out.println(Thread.currentThread().getName()+"...got Lock");
						Thread.sleep(30000);
						l.unlock();
						
						System.out.println(Thread.currentThread().getName()+"releases lock");
						break;
					}
					else {
						
						System.out.println(Thread.currentThread().getName()+"unable to get the lock and will try again");
					}
				}catch(InterruptedException e){}
			}
			while(true);
		}
	}
	
	class ReentrantLockDemo4 {
		
		public static void main(String args[]){
			
			MyThread t1 = new MyThread("Firt Name");
			MyThread t2 = new MyThread("Second Name");
			t1.start();
			t2.start();
		}
	}
	