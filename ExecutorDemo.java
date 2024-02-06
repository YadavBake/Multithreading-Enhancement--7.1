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
				
				ExecutorService service = Executors.newFixedThreadPool(3);
				for(PrintJob job: jobs){
					service.submit(job);
				}
				service.shutdown();
			}
		}
	