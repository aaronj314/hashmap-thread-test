package org.aaron.haze.test.hashmap;

import java.util.Map;
import java.util.Random;


public class Thread implements Runnable {
	private Map<String, String> map;
	private String key;
	private long cycles = 0;
	
	public Thread(String key, Map<String, String> map) {
		this.map = map;
		this.key = key;
	}

	public void run() {
		while(true) {
			sleep();
			if(map.containsKey(key)){
				// this will modify the map structure which is
				// not thread-safe
				map.remove(key);
				map.put(key, key);
			} else{
				// no mapping for the key because another thread
				// has modified the map structure and it's not thread-safe
				break;
			}
			cycles++;
		}
		printStats();
	}

	private void printStats() {
		String name = java.lang.Thread.currentThread().getName();
		System.out.println("KEY NOT FOUND IN MAP::Thread["+name+"]\tKey["+key+"]\tNum-Cycles["+cycles+"]");
	}
	
	private void sleep() {
		Random r = new Random();
		int Low = 100;
		int High = 300;
		int R = r.nextInt(High-Low) + Low;
		try {
			java.lang.Thread.sleep(R);
		} catch (InterruptedException e) {
		}
	}

}
