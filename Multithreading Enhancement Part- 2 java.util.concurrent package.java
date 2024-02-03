
								 Multithreading Enhancement Part- 2|| java.util.concurrent package
								===================================================================

 ----------------------
  java.util.concurrent
 ----------------------

  -> The problems with traditional synchronized keyword.

	 1. We are not having any flexibility to try for a lock without waiting.
	 
	 2. There is no way to specify meximum waiting time for a thread to get lock so that thread will wait until getting 
		the lock which may creates performance problems, whic may coause DeadLock.
		
	 3. If a thread realises the lock then which waiting will get that lock we are not having any controll on this.
	 
	 4. There is no API to list out all waiting thread for a lock.
	 
	 5. The synchronized keyword compulsory we have to use either at method level or within the method and it is not
		possible to use accros multiple methods.
		
  
  ->  To overcome these problems sun people introduced java.util.concurrent.locks package in 1.5v version, It also 
	  provides several inhancement to the programmer to provides more controll on concurrency.
	  
	  
 -----------------
   Lock interface 
  ----------------
	
	-> Lock object is similar to implicit lock acquire by a thread to execute synchronized method or synchronized block.
	
	-> Lock implementation provide more extensive operation then traditional implicit locks.
	
 ------------------------------------- 
  Important methods of Lock interface 
 -------------------------------------

    1. void lock():- We can use this method to acquired a lock if lock is already available then immeadiatly current 
	   thread will get that lock.
	   
	   If the lock is not already available then it will wait until getting the lock. It is exactly same behaviour of 
	   traditional synchronized keyword.
	   
	2. boolean tryLock():- To acquire the lock with waiting. If the lock is available then thread acquires that lock and 
						   and returns true. If the lock is not available then these method returns false and can continue 
						   its execution without waiting in these case thread never be entered into waiting state.
						   
		
								if(l.try(){
				
									//performe safe operation
								}else {
				
									// performe alternative operations
								}
	
	3. boolean tryLock(long time,TimeUnit unit) :- If lock is available then the thread will get the lock and continue its 
												   execution.
												   
												   
		- If the lock is not available then the  thread will wait until specified amount of time, Still if the lock is not 
		  available then thread can continue its execution.
		  
	
	
	TimeUnit: TimeUnit is an Enum prasent in java.util.concurrent package.
	
			enum TimeUnit {
				
				 NANOSECONDS;
				 MICROSECONDS;							EX. 
				 MILLISECONDS;                          
				 SECONDS;                               	if(l.tryLock(1000,TimeUnit.MILLISECONDS)){
				 MINUTES;                               		
				 HOURS;                                 		
				 DAYS;                                  	}
			}
			
			
		
	4. void lockInterruptibly():- 
	
		- Acquires a lock if it is available and returns immeadiatly.  
		
		- If the lock is not available then it will wait.
		
		- While waiting if the thread interrupted then thread won't get the lock.
		
	5. void Unlock():-

		- To realises the lock 
		
		- To call this method compulsory current thread should be owner of the lock otherwise we will get Runtime 
		  exception saying IllegalMonitorStateException.
		
		
	
	