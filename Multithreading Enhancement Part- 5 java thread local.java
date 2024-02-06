
								    	Multithreading Enhancement Part- 5|| java thread local
								  	 ===========================================================
									 
 ----------------
   Thread Local 									 
 ----------------

  -> ThreadLocal class provides ThreadLocal variables.

  -> ThreadLocal class maintaines values per thread bases.

  -> Each ThreadLocal object maintaines a separate value like userId,transactionID ect. for each thread that access 
	 that object.
	 
  -> Thread access it's local value, manuplate it's value and even can removes it's value.

  -> In every part of the code which is executed by the thread we can access it's local variable.

  Ex. 

	-> Considard a servlet which invokes some business methods.
	
	-> We have a requirement to generate a unique transactionID for each and every requiest and we have pass this
	   transactionID to business methods for these requirement we can use ThreadLocal to maintain a separate 
	   transactionID for every requiest that is for every thread.
	   
	   
 Note: 
 
	1. ThreadLocal class introduced in 1.2v version and Inhanced in 1.5v version.
	
	2. ThreadLocal can be associated with Thread scope.
	
	3. Total code which is executed by the thread has access to the currusponding ThreadLocal variables.
	
	4. A thread can access it's own local variables and can't access other thread local variables.
	
	5. Once a thread entered into dead state all it's local variables are by defualt eligible for garbage collection.
	
	
  -------------	
   Constructor 
  -------------

   ThreadLocal tl = new ThreadLocal(); 
	
	- Creates a ThreadLocal variable.
	
 ---------	
  Methods 
 ---------

  1. Object get():- returns the value of ThreadLocal variable associated with currentThread.

  2. Object initialValue():- returns initialValue of ThreadLocal variable associated with currentThread.
		
		- The defualt impletation of this method returns ull.
		
		- To customize our own initialValue we have to override this method.
		
  3. void set(Object newValue):- To set a new value.
  
  4. void remove():- To remove the value of ThreadLocal variable associated with currentThread.
  
  5. It is newly added in 1.5v version.

  6. After removal if we are trying to access it will re-initialized once again by invoking its initialValue() method.


  EX. 

		class ThreadLocalDemo1 {
			
			public static void main(String args[]) {
				
				ThreadLocal tl = new ThreadLocal();
				System.out.println(tl.get()); //null
				tl.set("Durga");	
				System.out.println(tl.get()); //Durga
				tl.remove();
				System.out.println(tl.get()); //null
			}
		}
  
  -> Overriding of initialValue() method ?


	Ex. 
	
		class ThreadLocalDemo1A {
			
			public static void main(String args[]) {
				
				ThreadLocal tl = new ThreadLocal()
				{
					public Object initialValue(){
						
						return "abc";
					}
				};
				System.out.println(tl.get()); //abc
				tl.set("Durga");	
				System.out.println(tl.get()); //Durga
				tl.remove();
				System.out.println(tl.get()); //abc
			}
		}
  
 ------------------------------	
   ThreadLocal Vs Inheritance 
 ------------------------------

	-> ParentThreads ThreadLocal variable by defualt not available to the child thread.
	
	-> If we want to make ParentThreads ThreadLocal variable value available to the child thread then we should go for 
	   InheritableThreadLocal class.
	   
	-> By defualt child thread value is exactly same as parent thread values but we can provide customized value for 
	   child Thread by overriding childValue() method.
	   
	-> Constructor:-
	
		1. InheritableThreadLocal tl = new InheritableThreadLocal();
	
	-> InheritableThreadLocal is the child class of ThreadLocal and hence a all method prasent in ThreadLocal by defualt
	   available to InheritableThreadLocal ThreadLocal.
	
	-> In addition to these methods it containes only one method:-


		1. public Object childValue(Object parentValue){}
		
		
	-> 
		class CustomerThread extends Thread {
			
			static Integer cusId = 0;
			private static ThreadLocal tl = new ThreadLocal(){
				
				protected Integer initialValue(){
					
					return ++cusId;
				}
			};
			CustomerThread(String name){
				
				super(name);
			}
			public void run() {
				
				System.out.println(Thread.currentThread().getName()+ " Executing with Customer id " + tl.get());
			}
		}
	
	

		
		class ThreadLocalDemo2 {
			
			public static void main(String args[]) {
				
				CustomerThread c1 = new CustomerThread("Customer Thread-1");
				CustomerThread c2 = new CustomerThread("Customer Thread-2");
				CustomerThread c3 = new CustomerThread("Customer Thread-3");
				CustomerThread c4 = new CustomerThread("Customer Thread-4");
				c1.start();
				c2.start();
				c3.start();
				c4.start();
			}
		}
		
		/*
		outPut:
		
		Customer Thread-1 Executing with Customer id 1
		Customer Thread-4 Executing with Customer id 4
		Customer Thread-3 Executing with Customer id 3
		Customer Thread-2 Executing with Customer id 2*/ 
		
		- In the above program for every customer thread a separate customer Id will be maintianed by thread local object.
		
		
	-> 
		Ex.1

		class ParentThread extends Thread {
			
			static ThreadLocal tl = new ThreadLocal();
			
			public void run() {
				
				tl.set("PP");
				System.out.println("Parent  Thread value : " + tl.get()); //PP
				
				ChildThread ct = new ChildThread();
				ct.start();
			}
		}
		
		class ChildThread extends Thread {
			
			public void run(){
				
				System.out.println("child Thread  value : 	"  + ParentThread.tl.get()); //null 
			}
		}
		
		
		class ThreadLocalDemo3 {
			
			public static void main(String args[]) {
				
				ParentThread pt = new ParentThread();
				pt.start();
			}
			
		}
		
	output:
	
		Parent  Thread value : PP
		child Thread  value :   null
		
	-> In the above program if we replace InheritableThreadLocal with ThreadLocal and if are not overriding childValue()
	   method then the output is parent Thread value 'PP' and child Thread value null.
	  	
 	
	Ex. 2


		class ParentThread extends Thread {
			
			static InheritableThreadLocal tl = new InheritableThreadLocal();
			
			public void run() {
				
				tl.set("PP");
				System.out.println("Parent  Thread value : " + tl.get()); //PP
				
				ChildThread ct = new ChildThread();
				ct.start();
			}
		}
		
		class ChildThread extends Thread {
			
			public void run(){
				
				System.out.println("child Thread  value : 	"  + ParentThread.tl.get()); //null 
			}
		}
		
		
		class ThreadLocalDemo3 {
			
			public static void main(String args[]) {
				
				ParentThread pt = new ParentThread();
				pt.start();
			}
			
		}
	
		output:
	
		Parent  Thread value : PP
		child Thread  value :  PP
		
	-> In the above program If we are maitaining InheritableThreadLocal and if we are not overriding childValue() method 
	   then we will output is Parent  Thread value : PP child Thread  value :  PP.
	   
	 	
		
	Ex.3	
		
		class ParentThread extends Thread {
			
			static InheritableThreadLocal tl = new InheritableThreadLocal(){
				public Object childValue(Object p){
					return "CC";
				}
			};
			
			public void run() {
				
				tl.set("PP");
				System.out.println("Parent  Thread value : " + tl.get()); //PP
				
				ChildThread ct = new ChildThread();
				ct.start();
			}
		}
		
		class ChildThread extends Thread {
			
			public void run(){
				
				System.out.println("child Thread  value : 	"  + ParentThread.tl.get()); //null 
			}
		}
		
		
		class ThreadLocalDemo3 {
			
			public static void main(String args[]) {
				
				ParentThread pt = new ParentThread();
				pt.start();
			}
			
		}
		
		
		
		
	
	  
	  