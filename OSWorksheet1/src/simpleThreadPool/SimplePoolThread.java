package simpleThreadPool;

/**
 *   An implementation of ISimplePoolThread. This SimplePoolThread is constructed
 *   using a SimpleThreadPool. This SimplePoolThread has a run method which loops until the
 *   SimplePoolThread is interrupted, within the loop the runTask() method is called on the SimpleThreadPool
 *   that was used to construct this SimplePoolThread. This executes SimpleTasks from the SimpleThreadPool's
 *   queue.
 *   @author lxf736
 *   @version 2018-02-22
 */

public class SimplePoolThread implements ISimplePoolThread {
	
	private SimpleThreadPool pool;
	private boolean interrupted = false;
	
	/**
	 * Constructs an instance of SimplePoolThread using a SimpleThreadPool to satisfy param 'pool'.
	 * @param pool is the SimpleThreadPool that we wish this SimplePoolThread to executes tasks from.
	 */
	public SimplePoolThread(SimpleThreadPool pool) {
		this.pool = pool;
	}
	
	/**
	 * loops until the SimplePoolThread is interrupted, within the loop the runTask() method is called 
	 * on the SimpleThreadPool that was used to construct this SimplePoolThread (field variable 'pool'). 
	 * This executes SimpleTasks from the SimpleThreadPool's queue.
	 */
	public void run() {
		while(true) {
			try {
				if (!interrupted) {
					pool.runTask();
				} else {
					break;
				}
			} catch (InterruptedException e) {
				interrupted = true;
			}
		}
	}

}
