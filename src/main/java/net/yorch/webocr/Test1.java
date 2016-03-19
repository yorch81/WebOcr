package net.yorch.webocr;

import java.util.ArrayList;

public class Test1 {

    public static void main(String[] args) {        
        int[] arrOr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        
        addElement (arrOr, 3, 99);        	
    }
    
    public static void addElement (int[] arrOr, int position, int element) {
    	ArrayList<Integer> arrRs = new ArrayList<Integer>();
    	
    	if (position >= arrOr.length)
    		System.out.println("Excede el número de Elementos");
    	else {
    		for (int i = 0; i < arrOr.length; i++) {
                if (i == position) {
                	arrRs.add(element);
                	arrRs.add(arrOr[i]);
                }
                else
                	arrRs.add(arrOr[i]);
            }
    		
    		System.out.println(arrRs.toString());
    	}
    }
}
