package simpleThreadPool;

/**
 *   An implementation of ISimpleThreadPool. This SimpleThreadPool is either constructed
 *   by passing no parameters or by passing an int 'numberOfThreads'. If no param is passed
 *   field variable 'threads' is set to 4, if 'numberOfThreads' is specified the caller can 
 *   create a SimpleThreadPool consisting of as many Threads as they desire. SimpleThreadPool has a number 
 *   of methods; start() will instantiate and start all Threads as SimplePoolThreads,
 *   stop() interrupts all SimplePoolThreads, addTask() add's an implementation of ISimpletask to this SimpleThreadPool's 
 *   queue of tasks 'queue', runTask() will call the run() method on the next ISimpleTask in the 
 *   queue, getQueueSize() returns the remaining number of ISimpleTasks contained within 'queue' as an int.
 *   SimpleThreadPool consists of the following field variables:
 *   queue - a LinkedBlockingQueue<ISimpleTasks> which is used to store tasks which SimplePoolThread's will execute.
 *   threads - an array of Threads.
 *   numberOfThreads - the number of Threads this SimpleThreadPool consists of.
 *   @author lxf736
 *   @version 2018-02-22
 */

import java.util.concurrent.LinkedBlockingQueue;

public class SimpleThreadPool implements ISimpleThreadPool {
	
	private LinkedBlockingQueue<ISimpleTask> queue;
	private Thread[] threads;
	private int numberOfThreads;
	
	/**
	 * Constructs an instance of SimpleThreadPool consisting of the default value of 4 Threads.
	 */
	public SimpleThreadPool() {
		numberOfThreads = 4;
		threads = new Thread[numberOfThreads];
		queue = new LinkedBlockingQueue<ISimpleTask>();
		System.out.println("ThreadPool created");
	}
	
	/**
	 * Constructs an instance of SimpleThreadPool consisting of a numberOfThreads specified by the caller.
	 * This constructor uses param 'numberOfThreads' to identify how many Threads to instantiate.
	 * consisting of the number of Threads
	 * @param numberOfThreads - the numberOfThreads for this SimpleThreadPool to consist of as an int.
	 */
	public SimpleThreadPool(int numberOfThreads) {
		this.numberOfThreads = numberOfThreads;
		threads = new Thread[this.numberOfThreads];
		queue = new LinkedBlockingQueue<ISimpleTask>();
		System.out.println("ThreadPool created");
	}

	/**
	 * instantiates and start all Threads as SimplePoolThreads
	 */
	@Override
	public void start() {
		for (int i = 0; i < numberOfThreads; i++) {
			 threads[i] = new Thread(new SimplePoolThread(this));
			 threads[i].start();
			 System.out.println(threads[i].getName() + " started");
		}
	}

	/**
	 * interrupts all SimplePoolThreads that are currently running
	 */
	@Override
	public void stop() {
		for (int i = 0; i < numberOfThreads; i++) {
			 threads[i].interrupt();
			 System.out.println(threads[i].getName() + " has been stopped");
       }
		System.out.println("ThreadPool stopped");
	}

	/**
	 * add's an implementation of ISimpletask to this SimpleThreadPool's queue of tasks 'queue'
	 */
	@Override
	public void addTask(ISimpleTask task) {
		try {
			queue.put(task);
		} catch (InterruptedException e) {
			System.out.println(this + " Thread interupted while adding task " + task + " to queue");
			e.printStackTrace();
		}
	}
	
	/**
	 * call the run() method on the next ISimpleTask in the queue
	 * @throws InterruptedException
	 */
	public void runTask() throws InterruptedException  {
		queue.take().run();
	}
	
	/**
	 * returns the remaining number of ISimpleTasks contained within 'queue' as an int
	 * @return remaining number of ISimpleTasks contained within 'queue' as an int
	 */
	public int getQueueSize() {
		return queue.size();
	}

}
