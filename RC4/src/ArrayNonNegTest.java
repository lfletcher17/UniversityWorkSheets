import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;

public class ArrayNonNegTest {
	
	private ArrayNonNeg test;
	
	@Test
	public void test1() {
		test = new ArrayNonNeg(0);
		System.out.println(test.a.length);
		for (int el: test.a) {
			System.out.println(el);
		}
	}

}
