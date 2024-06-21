import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static StringTokenizer st;

	public static void main(String[] args) {

		LinkedList<Integer> linked = new LinkedList<Integer>();

		linked.addFirst(1); 	// [1]
		linked.addLast(1); 		// [1, 1]
		linked.addFirst(10);	// [10, 1, 1]
		linked.addLast(-1); 	// [10, 1, 1, -1]
		linked.addFirst(5); 	// [5, 10, 1, 1, -1]
		linked.addLast(7); 		// [5, 10, 1, 1, -1, 7]

		int num;
		num = linked.peekFirst(); 	// 5	[5, 10, 1, 1, -1, 7]
		num = linked.peekLast(); 	// 7	[5, 10, 1, 1, -1, 7]

		num = linked.removeFirst(); // 5	[10, 1, 1, -1, 7]
		num = linked.removeLast(); 	// 7	[10, 1, 1, -1]

		linked.add(1, 5); 	// [10, 5, 1, 1, -1]
		linked.add(0, 10); 	// [10, 10, 5, 1, 1, -1]

		linked.remove(3); 	// [10, 10, 5, 1, -1]

//		일반적인 구조에서 java 는 중간에 data 를 추가, 삭제하는데 효율이 좋지 못함

		ListIterator<Integer> iter; 	// Cursor 와 동일
		iter = linked.listIterator(); 	// 양 방향으로 움직이는 iterator => next(), prev()

		iter.next();
		iter.next();
		iter.next();

		// O(1)
		iter.add(9); // [10, 10, 5, 9, 1, -1]
		iter.add(8); // [10, 10, 5, 9, 8, 1, -1]
		iter.add(7); // [10, 10, 5, 9, 8, 7, 1, -1]
		iter.add(6); // [10, 10, 5, 9, 8, 7, 6, 1, -1]

		// O(1)
		iter.next();
		iter.remove(); // [10, 10, 5, 9, 8, 7, 6, -1]
		iter.previous();
		iter.remove(); // [10, 10, 5, 9, 8, 7, -1]

		while (iter.hasNext()) { 		// iterator.hasNext() : next()가 가능한지
			num = iter.next(); 			// 다음 data 위치로 가라!
			System.out.println(num); 	// -1
		}
		
		ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
		ad.addFirst(1);		// [1]
		ad.addLast(10);		// [1, 10]

		num = ad.peekFirst();	// 1	[1, 10]
		num = ad.peekLast();	// 10	[1, 10]
		num = ad.removeFirst();	// 1	[10]
		num = ad.removeLast();	// 10	[]

		ad.addLast(10);
		ad.addLast(15);
		ad.addLast(7);
		ad.addLast(9);
		ad.addLast(8);

		Iterator<Integer> it = ad.iterator();
		while (it.hasNext())
			System.out.println(it.next());		// [10, 15, 7, 9, 8]

		it = ad.descendingIterator();
		while (it.hasNext())
			System.out.println(it.next());		// [8, 9, 7, 15, 10]

	}

}
