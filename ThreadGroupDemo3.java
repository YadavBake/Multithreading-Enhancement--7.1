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
 /*	
  - Output if we commenting line 1,2, 3 and 4 

	Child Thread
	2
	Child Thread
	1
	java.lang.ThreadGroup[name=ParentGroup,maxpri=10]
    Thread[#20,ChildThread1,5,ParentGroup]
    Thread[#21,ChildThread2,5,ParentGroup]
    java.lang.ThreadGroup[name=ChildGruop,maxpri=10]

  - Output if we not 	commenting line 1,2, 3 and 4 	
	
	
	Child Thread
	Child Thread
	2
	1
	java.lang.ThreadGroup[name=ParentGroup,maxpri=10]
    Thread[#20,ChildThread1,5,ParentGroup]
    Thread[#21,ChildThread2,5,ParentGroup]
    java.lang.ThreadGroup[name=ChildGruop,maxpri=10]
	0
	1
	java.lang.ThreadGroup[name=ParentGroup,maxpri=10]
    java.lang.ThreadGroup[name=ChildGruop,maxpri=10] */