/*class ThreadLocalDemo1 {
			
			public static void main(String args[]) {
				
				ThreadLocal tl = new ThreadLocal();
				System.out.println(tl.get());
				tl.set("Durga");	
				System.out.println(tl.get());
				tl.remove();
				System.out.println(tl.get());
			}
		}
*/

class ThreadLocalDemo1 {
			
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
  
	
   		
		
		
  