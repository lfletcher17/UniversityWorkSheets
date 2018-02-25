package simpleThreadPool;

class SimpleTesting implements ISimpleTask{
	private static int t0;
	private static int t1;
	private static int t2;
	private static int t3;
    private int i;
    public SimpleTesting(int i){
        this.i = i;
    }
    @Override
    public void run() {
        System.out.println(i + " " + Thread.currentThread().getName());
        if (Thread.currentThread().getName().equals("Thread-0")) {
        		t0++;
        } else if (Thread.currentThread().getName().equals("Thread-1")) {
        		t1++;
        } else if (Thread.currentThread().getName().equals("Thread-2")) {
    			t2++;
        } else if (Thread.currentThread().getName().equals("Thread-3")){
        		t3++;
        }
    }
    public static void main(String args[]){
        // Initialize thread pool
        SimpleThreadPool pool = new SimpleThreadPool();
        // Create 20 tasks
        for(int i = 1; i<=20; i++){
            pool.addTask(new SimpleTesting(i));
        }
        System.out.println("remaining items in queue: " + pool.getQueueSize());
        pool.start();
        for(int i = 21; i<=40; i++){
            pool.addTask(new SimpleTesting(i));
        }
        while(pool.getQueueSize() !=0) {
        		try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }
        pool.stop();
        System.out.println("remaining items in queue: " + pool.getQueueSize());
        System.out.println("Thread-0 executed " + t0 + " tasks");
        System.out.println("Thread-1 executed " + t1 + " tasks");
        System.out.println("Thread-2 executed " + t2 + " tasks");
        System.out.println("Thread-3 executed " + t3 + " tasks");
    }
}
