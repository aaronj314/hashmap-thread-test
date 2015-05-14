package org.aaron.haze.test.hashmap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.MapUtils;

public class HashMapThreadSafeTest 
{
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
        //Map<String, String> map = Collections.synchronizedMap(new HashMap<String, String>());
        
        List<String> keys = new ArrayList<String>();
        List<java.lang.Thread> threads = new ArrayList<java.lang.Thread>();
        
        System.out.println("Preloading Map");
        for(int i = 0; i < 40; i++) {
     	   String k = UUID.randomUUID().toString();
     	   keys.add(k);
     	   map.put(k, k);
        }
        
        MapUtils.debugPrint(System.out, "Map", map);
        
        System.out.println("Starting 40 threads to test");
        for (String key : keys) {
        	java.lang.Thread t = new java.lang.Thread(new Thread(key,map));;
        	t.start();
        	threads.add(t);
        }
        
        for (java.lang.Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
			}
		}
	}
}
