package kr.or.ddit;

import java.util.HashMap;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", "brown");
		
		Map<String, String> otherMap = map;
		otherMap.put("userid", "sally");
		
		System.out.println(map.get("userid"));
		
		// 동지, 동일
		// 객체 동일비교
		System.out.println(map == otherMap);
		
		Map<String, String> anotherMap = new HashMap<String, String>();
		anotherMap.put("userid", "sally");
		System.out.println(map == anotherMap);
		
		System.out.println("equals : "+map.equals(anotherMap));
		
		
		
	}
	
}
