//Anisha Aggarwal	CIS 212	Assignment 7
import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Main_7 {
	private static final int MAX_COUNT = 2000000;

	public static void main(String[] args) throws InterruptedException {
		LinkedBlockingQueue queue = new LinkedBlockingQueue(100000);
		Producer producer = new Producer(queue);
		Consumer consumer_1 = new Consumer(queue, producer, 1);
		Consumer consumer_2 = new Consumer(queue, producer, 2);

		ExecutorService executor = Executors.newCachedThreadPool();
		executor.execute(producer);
		executor.execute(consumer_1);
		executor.execute(consumer_2);
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.MINUTES);
		
		//extra credit LinkedList
		LinkedList<String> list = new LinkedList<String>();
		SyncProducer sync_producer = new SyncProducer(list);
		SyncConsumer sync_consumer_1 = new SyncConsumer(list, sync_producer, 1);
		SyncConsumer sync_consumer_2 = new SyncConsumer(list, sync_producer, 1);
		ExecutorService executor_linked = Executors.newCachedThreadPool();
		executor_linked.execute(sync_producer);
		executor_linked.execute(sync_consumer_1);
		executor_linked.execute(sync_consumer_2);
		executor_linked.shutdown();
		executor_linked.awaitTermination(1, TimeUnit.MINUTES);
		
	}

	// creates strings to be put into queue
	private static class Producer implements Runnable {
		private LinkedBlockingQueue<String> queue;
		private boolean running;
		
		//constructor
		public Producer(LinkedBlockingQueue<String> q) {
			this.queue = q;
			running = true;
		}
		
		// check if producer is running
		public boolean isRunning() {
			return running;
		}
		
		//adds 2000000 random Strings to the queue
		@Override
		public void run() {
			int i;
			for (i = 1; i <= MAX_COUNT; ++i) {
				try {
					//System.out.println("producing: " + i);
					queue.put(UUID.randomUUID().toString());
				} catch (InterruptedException ex) {
					System.out.println("Producer InterruptedException!!");
				}
				if ((i % 1000) == 0) {
					System.out.println("Producer has produced: " + i);
				}
			}
			System.out.println("Producer finished! " + (i - 1) + " produced total");
			running = false;
		}
		
	}
	
	// consumes inputs by producer
	private static class Consumer implements Runnable {
		private LinkedBlockingQueue<String> queue;
		private String current_max = "";
		private Producer producer;
		private int instance; 
		
		//constructor
		public Consumer(LinkedBlockingQueue<String> q, Producer p, int i) {
			this.queue = q;
			this.producer = p;
			this.instance = i;
		}
		
		@Override
		public void run() {
			int i = 0;
			
			//while statement producer is running AND queue peek
			while ((producer.isRunning()) || (queue.peek() != null)) {
				
				try {
					long sleepDelay = (long)(Math.random() * 10);
					Thread.sleep(sleepDelay);
					
					String s = queue.poll(1, TimeUnit.SECONDS);
					if (s != null) {
						//System.out.println(s);
						if (current_max.compareTo(s) < 0) {
							current_max = s;
						}
						++i;
					}
				} catch (InterruptedException e){
					System.out.println("Consumer InterruptedException!!");
				}
				//print every 1000 strings consumed
				if ((i % 1000) == 0) {
					System.out.println("Consumer " + instance + " consumed: " + i);
				}
			}
			System.out.println("Consumer " + instance +" max string " + current_max);
			System.out.println("Consumer " + instance +  " finished!! " + i + " consumed");
		}
		
	}
	
	// extra credit LinkedList Producer
	// Synchronized version of producer using LinkedList
	private static class SyncProducer implements Runnable {
		private LinkedList<String> list;
		private boolean running;
		
		//constructor
		public SyncProducer(LinkedList<String> l) {
			this.list = l;
			running = true;
		}
		
		// check if producer is running
		public boolean isRunning() {
			return running;
		}
		
		//adds 2000000 random Strings to the queue
		@Override
		public void run() {
			int i;
			for (i = 1; i <= MAX_COUNT; ++i) {
				try {
					//System.out.println("producing: " + i);
					blockingPut(UUID.randomUUID().toString());
				} catch (InterruptedException ex) {
					System.out.println("Producer InterruptedException!!");
				}
				//print every 1000 strings produced
				if ((i % 1000) == 0) {
					System.out.println("Producer has produced: " + i);
				}
			}
			System.out.println("Producer finished! " + (i - 1) + " produced total");
			running = false;
		}

		public synchronized void blockingPut(String string) throws InterruptedException {
			if (list.size() == 5) {
				wait();
			}
			list.add(string);
			notifyAll();
		}
	}
	
	// extra credit LinkedList Consumer
	// Synchronized version of consumer using LinkedList
	private static class SyncConsumer implements Runnable {
		private LinkedList<String> list;
		private String current_max = "";
		private SyncProducer producer;
		private int instance; 
		
		//constructor
		public SyncConsumer(LinkedList<String> l, SyncProducer p, int i) {
			this.list = l;
			this.producer = p;
			this.instance = i;
		}
		
		@Override
		public void run() {
			int i = 0;
			
			//while statement producer is running AND list peek
			while (producer.isRunning() || (list.peek() != null)) {
				
				try {
					long sleepDelay = (long)(Math.random() * 10);
					Thread.sleep(sleepDelay);
					
					String s = blockingGet();
					if (s != null) {
						//System.out.println(s);
						if (current_max.compareTo(s) < 0) {
							current_max = s;
						}
						++i;
					}
				} catch (InterruptedException e){
					System.out.println("Consumer InterruptedException!!");
				}
				//print every 1000 strings consumed
				if ((i % 1000) == 0) {
					System.out.println("Consumer " + instance + " consumed: " + i);
				}
			}
			System.out.println("Consumer " + instance +" max string " + current_max);
			System.out.println("Consumer " + instance +  " finished!! " + i + " consumed");
		}

		public synchronized String blockingGet() throws InterruptedException {
			if (list.size() == 0 ) {
				wait();
			}
			String s = list.poll();
			notifyAll();
			return s;
		}
		
	}

}
