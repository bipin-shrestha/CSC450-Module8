/*
Name: 				Bipin Shrestha
Project Name: 		CSC450-CT1-Module8
Project Purpose:    To create Java concurrency multi-threaded application that implements two counters,
					one counts up to 20 and other count 20 to 0.
Algorithm Used:		Program utilizes two synchronized methods("countUp()" and "countDown()") to execute the counting task concurrently.
					Each thread iterates through a loop from specified starting value to an ending value and print current count to console with sleep operation. 
Program Inputs:		Program doesn't require any user inputs. 
Program Outputs:	Program outputs the count values while it progresses in both countUp and countDown methods. 
Program Limitations:Program has fixed counting range and it's error handling mechanism does not handle the interruption of threads gracefully. 
Program Errors:		The program might encounter errors related to interrupted exception during the sleep operation. Also the lack of proper synchronization
					mechanisms might introduce inconsistencies in the output. Also, lack of proper synchronization mechanisms might introduce 
					inconsistencies in the output. 

====================================
*/
public class NumberCounter {
	public synchronized void countUp() {
		for(int i=1; i<=20; i++) {
			System.out.println("Counting Up : " + i);
			try {
				//Simulating some processing time
				Thread.sleep(100);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public synchronized void countDown() {
		for(int i=20; i>=0; i--) {
			System.out.println("Counting Down : " + i);
			try {
				//Simulating some processing time
				Thread.sleep(100);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		NumberCounter counter = new NumberCounter();
		
		Thread countUpThread = new Thread(() ->{
			counter.countUp();
		});
		
		Thread countDownThread = new Thread(()->{
			try {
				countUpThread.join();
				counter.countDown();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		});
		countDownThread.start();
		countUpThread.start();
	}
}
