
										Multithreading Enhancement Part- 4|| java thread pools
									=============================================================

----------------------------------
 Thread Pools[Executor Framework]
----------------------------------

 -> Creating a new thread for every job may create performance and memory problem.

 -> To overcome this we should go for thread pool.

 -> Thread pool is a pool of already created thread ready to do our job.

 -> Java 1.5v version intorduces ThreadPool framework to implement ThreadPools.

 -> ThreadPool framework also known as Executor Framework.

 -> We can create a ThreadPool as follows:


		ExecutorService services = Executors.newFixedThreadPool(3);
 
  -> We can submit a Runnable job by using submit() method.
  
		service.submit(job);
		
  -> We can shutdown executor service by using shutdown() method.
  
		service.shutdown();
		
  
Ex.
	
	import java.util.concurrent.*;
	class PrintJob implements Runnable {
		
		
			
			String name;
			PrintJob(String name){
			
				this.name=name;
			}
			public void run(){
				
				System.out.println(name+"...job started by 	Thread: "+ Thread.currentThread().getName());
				try{
					
					Thread.sleep(1000);
				}
				catch(InterruptedException e){
				
				}
				System.out.println(name+" Job completed by Thread: "+Thread.currentThread().getName());
			}
		}
		
		class ExecutorDemo {
			
			public static void main(String args[]){
				
				PrintJob[] jobs = {new PrintJob("Durga"),
									new PrintJob("Ravi"),
									new PrintJob("Shiva"),
									new PrintJob("Kalliya"),
									new PrintJob("Gabbar"),
									new PrintJob("Shamba")};
				
				ExecutorService service = Executors.newFixedThreadPool(4);
				for(PrintJob job: jobs){
					service.submit(job);
				}
				service.shutdown();
			}
		}
	
 -> In the above example three threads ar responsible to execute six job so that a single thread can be reused for 
	multiple jobs.
	
 Note: 

	-> While designing|developing  web server and Application server we can use ThreadPool concept.
	
---------------------	
 Callable and Future
--------------------

 -> In the case Runnable job thread won't return anything after compliting the job.

 -> If a thread is required to return some result after execution then we should go for callable.

 -> Callable interface containes only one method call(): public Object call() throws Exception .

 -> If we submit callable object to executor then after compliting the job thread returns an object of the type 'Future' 
	that is future object can used to retrive the result from callable job.
	

 Ex.
 
	import java.util.concurrent.*;
	class MyCallable implements Callable {
		
		int num;
		MyCallable(int num){
			this.num=num;
		}
		public Object call() throws Exception {
			
		System.out.print(Thread.currentThread().getName()+ 	" is...responsible to find sum of first " + num + " number 	");
		int sum = 0;
		for(int i =1; i<=num; i++) {
				
			sum= sum+i;
		}
		return sum;
	}
 }
	
	class 	CallableFutureDemo {
		
		public static void main(String args[])throws Exception  {
			
			MyCallable [] jobs = {  new MyCallable(10),
									new MyCallable(20),
									new MyCallable(30),
									new MyCallable(40),
									new MyCallable(50),
									new MyCallable(60)};
			
			ExecutorService service = Executors.newFixedThreadPool(3);
			for(MyCallable job : jobs){
				
				Future f = service.submit(job);
				System.out.println(f.get());
			}
			service.shutdown();
		}
	}
	
  output:		
	
	pool-1-thread-1 is...responsible to find sum of first 10 number 55
	pool-1-thread-2 is...responsible to find sum of first 20 number 210
	pool-1-thread-3 is...responsible to find sum of first 30 number 465
	pool-1-thread-1 is...responsible to find sum of first 40 number 820
	pool-1-thread-2 is...responsible to find sum of first 50 number 1275
	pool-1-thread-3 is...responsible to find sum of first 60 number 1830
	
	

 -> Differences between Runnable and Callable ?

	-------------------------------------------------------------------------------------------------------------------
	|			Runnable								|								Callable					   |
	|---------------------------------------------------|--------------------------------------------------------------|	
	|1. If a thread is not required to return any thing	| 1. If a thread required to return something after            |
	|   after compliting the job then we should go for 	|    compliting the job then we should go for                  |
	|   Runnable.										|    Callable.			                                       |
	|													|                                                              |
	|													|                                                              |
	|2. Runnable interface containe only one method 	| 2. Callable interface containes only one method is           |
	|   is run() method.								|	 call() method 		                                       |
	|													|                                                              |
	|													|                                                              |
	|3. Runnable job not required to return anything	| 3. Callable job is required to something and hence a 	       |
	|   and hence a return of run() method is void.		|    return of call() method is Object.                        |
	|													|                                                              |
	|4. Within the run() method if there is any 		| 4. Within call() method if there is any chance of checked	   |
	|   chance of rising checked exception 				|    exception we are not required to handle by using try      |
	|   compulsory we should handle by using 			|  	 catch because call() method allready throws exception     |
	|   try-catch because we can't use throws keyword   |                                                              |
	|   for run() method.                               |                                                              |
	|													|                                                              |
	|													|                                                              |
	|5. Runnable interface prasent in java.lang package.| 5. Callable interface prasent in java.util.concurrent package|
	|													|                                                              |
	|6. Introduced in 1.0v version.						| 6. Introduced in 1.5v version.                               |
	--------------------------------------------------------------------------------------------------------------------
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
									