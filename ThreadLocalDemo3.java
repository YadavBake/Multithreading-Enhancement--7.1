/*class ParentThread extends Thread {
			
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
		
		
		class ParentThread extends Thread {
			
			static InheritableThreadLocal  tl = new InheritableThreadLocal();
			
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
		*/
		
		
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