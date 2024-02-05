
									Multithreading Enhancement Part- 3|| java.util.concurrent.locks
								 ====================================================================

----------------
 ReentrantLock
---------------- 

  -> It is the implementation  class of lock interface and it is the direct child class of object.
  
  -> Reentrant means a thread can acquires same lock multiple without issue.
  
  -> Enternally ReentrantLock increments threads persional count whenever we call lock() method and dicreament count 
	 value whenever thread calls unlock() method and lock will be relieses whenever count recheas zero.
	 
	 
  
  
			ReentrantLock l = new ReentrantLock()
			
			l.lock();
			l.lock();			holdcount = 1
			l.lock();						2
											3
			l.unlock();						2
			l.unlock();						1
			l.unlock();						0	
			
 --------------			
  Constructor
 -------------- 

	1. ReentrantLock l = new ReentrantLock();
	
		- creates an instance of ReentrantLock.
		
	2. ReentrantLock l = new ReentrantLock(boolean fairness);
	
		- Creates ReentrantLock with the given fairness policy.
		
		- If the fairness is true then longest waiting thread can get(acquir) the lock if it is available that is follows
		  first come first serve policy.
		  
		- If a fairness is false then which waiting thread will get the chance we can't except.
		
	Note: 
	
		- The default value for fairness is false.
		
Q. Which of the following declarations are equal ?


	ReentrantLock l = new ReentrantLock();
	ReentrantLock l = new ReentrantLock(true);
	ReentrantLock l = new ReentrantLock(false);
	All the above 
	
	
	frist and third both are equal.
	
	
--------------------------------------
  Important methods of ReentrantLock
--------------------------------------

 1. void lock()
 2. boolean tryLock()
 3. boolean	tryLock(long l, TimeUnit t)
 4. void lockInterruptibly()
 5. void unlock()
 
 6. int getHoldCount()
 
	- returns number of holds in this lock by current thread.
 
 7. boolean isHeldByCurrentThread()
 
	- returns true if and only if lock is hold by current thread.
	
 8. int getQueueLength()
 
	- returns numbers of threads waiting for the lock.
	
 9. Collection getQueuedThreads()
 
	- It returns a collection of thread which are waiting to get the lock.
 
10. boolean hasQueuedThreads()

	- returns true of any thread waiting to get the lock.

11. boolean isLocked()

	- returns true if the lock is required by some thread.
	
12. boolean isFair()

	- returns true if the fairness policy is set with true value.
	
13. Thread getOwner()

	- returns the thread which acquirs the lock.
	
	
	Ex.
	
		import java.util.concurrent.locks.*;
		class ReentrantLock2 {
			
				public static void main(String args[]){
					
					ReentrantLock l = new ReentrantLock();
					l.lock();
					l.lock();
					System.out.println(l.isLocked()); // true
					System.out.println(l.isHeldByCurrentThread()); // true
					System.out.println(l.getQueueLength()); // 0
					l.unlock();
					System.out.println(l.getHoldCount());//1
					System.out.println(l.isLocked());//true
					l.unlock();
					System.out.println(l.isLocked()); // false 
					System.out.println(l.isFair());//false 
					
				}
		}
					
					
 -> 
											Without synchronized 
										 =========================				
	
class Display {								class MyThread extends Thread {	 class SynchronizedDemo {		
	                                        	                             	
	public void wish(String name){          	Display d ;                  	public static void main(String rgs[]){
		                                    	String name;	             		Display d = new Display();
		for(int i = 0; i<10;i++){           	MyThread(Display d, String name){	MyThread t1 = new MyThread(d,"Dhoni");
			                                		this.d= d;               		MyThread t2 = new MyThread(d,"Yuraj");
			System.out.println("Good Morning		this.name=name;");       		t1.start();
			                                	}                            		t2.start();
			try {                           	public void run(){           	}
				                            		                         }
				Thread.sleep(2000);         		d.wish(name);            
			}catch(InterruptedException e){}	}                            
			System.out.println(name);       	                             -----------
		}                                   }                                |		  |<-----t1	
	}                                                                        | Display | .wish("Dhoni");
}                                                                            | Object  |<------t2 
	                                                                         ----------- .wish("Yuraj");
 Here we get Iregular output.	
	
	
	
	
											With synchronized 	
										 =========================					
		
class Display {								class MyThread extends Thread {	 class SynchronizedDemo {			
	                                        	                             		
 public synchronized void wish(String name){	Display d ;                  	public static void main(String rgs[]){	
		                                    	String name;	             		Display d = new Display();	
		for(int i = 0; i<10;i++){           	MyThread(Display d, String name){	MyThread t1 = new MyThread(d,"Dhoni"	
			                                		this.d= d;               		MyThread t2 = new MyThread(d,"Yuraj"	
			System.out.println("Good Morning		this.name=name;");       		t1.start();	
			                                	}                            		t2.start();	
			try {                           	public void run(){           	}	
				                            		                         }	
				Thread.sleep(2000);         		d.wish(name);            	
			}catch(InterruptedException e){}	}                            	
			System.out.println(name);       	                             -----------	
		}                                   }                                |		  |<-----t1		
	}                                                                        | Display | .wish("Dhoni");	
}                                                                            | Object  |<------t2 	
	                                                                         ----------- .wish("Yuraj");	
 Here we get regular output.		
		

 											With ReentrantLock 		
 										 =========================					
 
 
	import java.util.concurrent.locks.*; 							class MyThread extends Thread {	
	class Display {							                    	                             	
		                                                        	Display d ;                  		
		ReentrantLock l = new ReentrantLock();	                	String name;	             	
							                                    	MyThread(Display d, String name){
		public void wish(String name){                          		this.d= d;               	
			l.lock(); //--->line - 1                              		this.name=name;       	
			for(int i = 0; i<10;i++){           	            	}                            	
													            	public void run(){           	
				System.out.print("Good Morning: ");             		                         	
												                		d.wish(name);            		
				try {                                           	}                            		
												                	                             
					Thread.sleep(2000);                         }                               
				}catch(InterruptedException e){}
				System.out.println(name);       
			}
			l.unlock(); //---> line -2 	
		}                                       
	}                                          
							class ReentrantLockDemo1 {			             					
	                        			                               
	                        	public static void main(String rgs[]){				-----------
                            	                                                    |		  |<-----t1	
                            		Display d = new Display();		                | Display | .wish("Dhoni");
                            		MyThread t1 = new MyThread(d,"Dhoni");	        | Object  |<------t2 
                            		MyThread t2 = new MyThread(d,"Yuraj");          ----------- .wish("Yuraj");
                            		MyThread t3 = new MyThread(d,"Kohali");
                            		
                            		t1.start();			
                            		t2.start();			
                            	}			
                            		
                            }	
 
  -> If we comment line - 1 and line - 2 then the thread executed simulteniously and we will get Iregular output.

  -> If we not commenting lines - 1 and -2 then thre threads will be executed one by one and we will get Regular output.

   
 -> Demoe program for tryLock() method ?

	
	import java.util.concurrent.locks.*;
	class MyThread extends Thread {
		
		static ReentrantLock l = new ReentrantLock();
		MyThread(String name){
			super(name);
		}
		public void run(){
			
			if(l.tryLock()){
				
				System.out.println(Thread.currentThread().getName()+"...got lock and perform safe operation");
				try{
					Thread.sleep(2000);
				}catch(InterruptedException e){}
				l.unlock();
			}
			else {
				
				System.out.println(Thread.currentThread().getName()+"...unable to get lock and hence perform alternative 
				operation");
			}
		}
	}
	
	class ReentrantLockDemo3 {
		
		public static void main(String args[]){
			
			MyThread t1 = new MyThread("Firt Name");
			MyThread t2 = new MyThread("Second Name");
			t1.start();
			t2.start();
		}	}
	
	/*output:
	
	Firt Name...got lock and perform safe operation
	Second Name...unable to get lock and hence perform alternative operation */
	
                       
  -> tryLock() method with time piriod ?
  
	
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
	/*
	output:
		
		Firt Name...got Lock
		Second Nameunable to get the lock and will try again
		Second Nameunable to get the lock and will try again
		Second Nameunable to get the lock and will try again
		Second Nameunable to get the lock and will try again
		Second Nameunable to get the lock and will try again
		Second Nameunable to get the lock and will try again
		Second Name...got Lock
		Firt Namereleases lock
		Second Namereleases lock */
	
	
	
	
	
	
  
	
 











































		