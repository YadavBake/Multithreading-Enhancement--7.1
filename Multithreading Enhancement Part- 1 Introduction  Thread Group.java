								
									Multithreading Enhancement Part- 1|| Introduction || Thread Group
								 ======================================================================

 --------------
  Thread Group 
 --------------

 -> Based on the funcationality we can group threads into a single unit which nothing but thread group. That is thread 
    group contains a group of Threads.

 -> In addition to threads, Thread group can also contain sub-Thread groups.

			
			---------------------------------
			|	t1 		t2 .....	tn 		|
			|	|		|			|       |
			|	|       |           |       |
			|	|       |           |       |
			|	|	    |           |		|	
			|	                            |
			|	                            |
			| -------------  -------------  |
			||t1  t2...tn|  |t1  t2...tn|	|
			|||	  |    | |  ||	 |    | |   |
            |||   |    | |  ||   |    | |   |
            |||   |    | |  ||   |    | |   |
			|-------------	-------------   |    
			|    Sub-Thread-Group           |
			--------------------------------|
					Thred Groups 										
												
 -> The main advantage of maintaining threads in the form of thread group is we can perform comman operations very easlly.

 -> Every thread in java belongs to some group.
 
 -> Main thread belongs to main group.
 
 -> Every thread group in java is the child group of SystemGroup either directly or indirectly hence a SystemGroup access
    root for all thread groups in java.
	
 -> SystemGroup containes several system level threads like a :
 
		Finalizer.
		
		Reference Handler.
		
		single Dispatcher. 
		
		Attach Listener.

									---------------	
									|	System    |
									---------------	
			|<<----------------------|  |  |  |  |---------------------------------------		
			|				|<<---------|  |  |											|
			|				|		       |  |---------------							|
			|				|			   |			     |							|	
	--------------------  ----------- ------------------ --------------------- --------------------	
    |Main thread Groupd|  |Finalizer| |	Attach Listener| |	Reference Handler| | Single Dispatcher|
    --------------------- ----------- ------------------ --------------------- --------------------
		|	|		|
	    |   |       |
	    |   |       |-------------------------
	    |   |---------------				 |
		|				   |	             |
		|				   |	             |
	---------------	  ---------------	-------------------			
    | main Thread |   | Thread - 0  |   |Sub-Thread-Group |
    ---------------	  ---------------	-------------------	
										  t1  t2...tn
                                          |	  |    | 
                                          |   |    | 
                                          |   |    | 
										  
										  
	Ex. 									  
    
    class Tg {
    	
    	public static void main(String args[]){
    		
    		System.out.println(Thread.currentThread().getThreadGroup().getName()); //main 
    		System.out.println(Thread.currentThread().getThreadGroup().      getParent().     getName()); System
    						// ---------------------  -----------------  -------------------- -----------		
    								//Main Thread 	  Main Thread group  System Thread group    System			
    					
    	}
    }
	
 
 -> Thread Group is a java class prasent in java.lang package and it is the direct child class object.

									Object 
									  ^
									  |
									  |
								 ThreadGroup  

 ---------------								 
   Constructors 
 ---------------

  1. ThreadGroup pg = new ThreadGroup(String gname);


	- Creates a new thread group with the specified group name.
	
	- Thre parent of these new group is the thread group of currently executing thread.
	

	Eg.
	
		ThreadGroup g = new ThreadGroup("First Group");
		
		
	class Tg {
		
		public static void main(String args[]){
			
			ThreadGroup g = new ThreadGroup("First Group");
			System.out.println(g.getParent().getName()); //Main 
						
		}
	}
	
	
  2. ThreadGroup g = new ThreadGroup(ThreadGroup pg, String groupName);	
	
	- Creates a new Thread group with specified group name.
	
	- The parent of these new thread group is specified parent group.
	
	Eg

		ThreadGroup g1 = new ThreadGroup(g, "SecondGroup");
		
		
		class Tg {
		
		public static void main(String args[]){
			
			ThreadGroup g1 = new ThreadGroup("First Group");
			System.out.println(g1.getParent().getName()); //main
			ThreadGroup g2 = new ThreadGroup(g1,"Second Group");				
			System.out.println(g2.getParent().getName()); // First Group
			
		}
	}
					---------------	
					|	System    |			
		            ---------------	
						|
					 ---|
					 |
					 |	
		---------------	
	    |	Main      |<---------------
	    ---------------				  |	
									  |
									  |
									  |
								---------------	
	                            | First Group |
	                            ---------------	
									|
					----------------|	
					|	
		---------------	
	    | Second Group|
	    ---------------	
	
	
 ---------------------------------------	
  Important method of ThreadGroup class 
 ---------------------------------------

  1. String getName()
  
		- retunrs name of the ThreadGroup.
		
  2. int getMaxPriority()
  
		- returns max priority of ThreadGroup.
		
  3. void setMaxPriority(int p)
																
		- To set Maximum priority of ThreadGroup.				 				
		- The default max priority is 10.						
		
	- Threads in the ThreadGroup that already have higher priority won't be effected but for newly added thread this max 
	  priority is applicable.
	  
	  Ex. 
	  
		class ThreadGroupDemo2 {

			public static void main(String args[]) {
																	---------------------------	
				ThreadGroup g1 = new ThreadGroup("tg");				| t1(p=5)  t2(p=5) t3(p-3)|	
				Thread t1 = new Thread(g1, "First Thread");			|  |		|		|	  |	
				Thread t2 = new Thread(g1, "Second Thread");		|  | 		|		|	  |	
				g1.setMaxPriority(3)								|						  |
				Thread t3 = new Thread(g1, "Third Thread");         --------------------------
				System.out.println(t1.getPriority()); //5           			tg 	
				System.out.println(t2.getPriority()); //5          		 Max Priority = 10 
				System.out.println(t3.getPriority()); //5           					3
			}                                                     
		}	  

  4. ThreadGroup getParent()
  
	  - retunrs parent group of current thread.								
																						
  5. void list()															
																							
		- It prints information about ThreadGroup to the console.
		
  6. int activeCount()
  
		- returns number of active thread prasent in the ThreadGroup.
		
  7. int activeGroupCount()	
  
		- It retunrs number of active groups prasent in the currentThread group.
		
  8. int enumerate(Thread[] t)
  
		- To copy all active thread of this ThreadGroup into provided thread array (Thread[]).
		- In these case sub-ThreadGroup threads also will be considard.
		
						------------
		            	| t1  t2   |
		            g-->| |	  |    |
		            	------------
				Thread [] t = new Thread[10];
				g.enumerate(t);
				for(Thread t1 :t){
					System.out.println(t1.getName());
				}
			
		
  9. int enumerate(ThreadGroup[] g)
  
		- To copy all active sub-ThreadGroup into ThreadGroup[] array.
		
 10. boolean isDaemon()
 
		- To check whether the  ThreadGroup is the Daemon or not. 
		
 11. void setDaemon(boolean b);
 12. void interrupt()
 
		-To interrupt all waiting or sleeping threads prasent inthe ThreadGroup.
		
 13. void destroy() 
	
		- To destroy ThreadGroup and it's sub-ThreadGroups.
   
  
	Ex.

  
   class MyThread extends Thread {

		MyThread(ThreadGroup g, String name){
			
			super(g,name);
		}
		public void run(){
			
			System.out.println("Child Thread");
			try{
				Thread.sleep(5000);
			}catch(InterruptedException e) {}
		}
   }
	
  class ThreadGroupDemo3 {

		public static void main(String args[])throws InterruptedException {
			
			ThreadGroup pg = new ThreadGroup("ParentGroup");
			ThreadGroup cg = new ThreadGroup(pg,"ChildGruop");
			MyThread t1 = new MyThread(pg,"ChildThread1");
			MyThread t2 = new MyThread(pg,"ChildThread2");
			t1.start();
			t2.start();
			System.out.println(pg.activeCount());
			System.out.println(pg.activeGroupCount());
			pg.list();
			Thread.sleep(10000); // line -1 
			System.out.println(pg.activeCount()); //0 line -2
			System.out.println(pg.activeGroupCount());//1 line -3
			pg.list();// line -4
		}
  }
	
  - Output if we commenting line 1,2, 3 and 4 

	Child Thread
	2																		---------------	
	Child Thread                                                			|	System    |			
	1                                                                       ---------------	
	java.lang.ThreadGroup[name=ParentGroup,maxpri=10]           				|
    Thread[#20,ChildThread1,5,ParentGroup]                      			 ---|
    Thread[#21,ChildThread2,5,ParentGroup]                      			 |
    java.lang.ThreadGroup[name=ChildGruop,maxpri=10]            			 |	
                                                                ---------------	
  - Output if we not 	commenting line 1,2, 3 and 4 	        |	Main      |<---------------
	                                                            ---------------				  |	
	                                                            							  |
	Child Thread                                                							  |
	Child Thread                                                							  |
	2                                                           						---------------	
	1                                                                                   | parent group |
	java.lang.ThreadGroup[name=ParentGroup,maxpri=10]                                   ---------------	
    Thread[#20,ChildThread1,5,ParentGroup]                      							|   |     |
    Thread[#21,ChildThread2,5,ParentGroup]                      			----------------|	|     |----
    java.lang.ThreadGroup[name=ChildGruop,maxpri=10]            			|					|		  |
	0                                                           ---------------		---------------- ---------------			
	1                                                           | child  Group|     | child Thread1| |child Thread2 |
	java.lang.ThreadGroup[name=ParentGroup,maxpri=10]           ---------------	    ---------------- ---------------	
    java.lang.ThreadGroup[name=ChildGruop,maxpri=10]
	
	
 -> Write a program to display all active thread names belongs SystemGroup and it's ChildGruops ?

	class ThreadGroupDemo4 {
		
		public static void main(String args[]){
			
			ThreadGroup system = Thread.currentThread().getThreadGroup().getParent();
			Thread [] t = new Thread[system.activeCount()];
			system.enumerate(t);
				for(Thread t1 : t){
					System.out.println(t1.getName()+"..."+t1.isDaemon());
			}
		}
	}
	
	/*
	output:
		
		main...false
		Reference Handler...true
		Finalizer...true
		Signal Dispatcher...true
		Attach Listener...true
		Notification Thread...true
		Common-Cleaner...true */
			
