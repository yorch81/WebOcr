package net.yorch.webocr;

import java.util.ArrayList;
import java.util.Iterator;

public class Test2 {
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("71,1");
		list.add("72,2");
		list.add("73,3");
		list.add("74,4");
		list.add("75,5");
		list.add("76,2");
		list.add("77,7");
		
		ArrayList<String> listTmp = new ArrayList<String>();
		
		Iterator<String> it = list.iterator();
		
		while (it.hasNext()) {
			String item = it.next();
			
			String[] objList = item.split(",");
				
			if (listTmp.size() > 1){
				if (listTmp.contains(objList[1])) {
					System.out.println("Ciclada posición: " + objList[1]);
				}
			}
			
			listTmp.add(objList[1]);				
		}
	}
}
