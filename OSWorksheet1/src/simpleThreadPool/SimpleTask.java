package simpleThreadPool;

/**
 *   An implementation of ISimpleTask. This SimpleTask is added to a queue of SimpleTasks by
 *   SimpleThreadPool where it is delegated to a SimplePoolThread
 *   @author lxf736
 *   @version 2018-02-22
 */

public class SimpleTask implements ISimpleTask {

	/**
	 * runs when a SimplePoolThread executes this SimpleTask. Prints the executing Threads name.
	 */
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "is performing a simple task");
	}

}
