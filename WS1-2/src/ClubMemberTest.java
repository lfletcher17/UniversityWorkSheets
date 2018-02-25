import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

/**
 * lxf736 tests for Ex2.
 * @author lxf736
 * @version 2017-10-12
 * 
 */

public class ClubMemberTest {
	
	private ClubMember testClubMember;
	private ClubMember testClubMember2;
	@Before
	public void setup(){
	testClubMember= new ClubMember("John Smith", "5 October 1993", "C212121", "Gold");
	testClubMember2 = new ClubMember("Alice", "29 June 1992", "0001", "Bronze");
	}
	
	@Test
    public void test1() {
		assertEquals(testClubMember.toString(), "[John Smith, 5 October 1993, ID: C212121, Gold]");
	}
	
	@Test
    public void test2() {
		assertEquals(testClubMember2.toString(), "[Alice, 29 June 1992, ID: 0001, Bronze]");
	}
	
	@Test
	public void test3() {
		assertEquals("John Smith", testClubMember.getName());
		assertEquals("5 October 1993", testClubMember.getDateOfBirth());
		assertEquals("C212121", testClubMember.getRegistrationNumber());
		assertEquals("Gold", testClubMember.getMembershipType());
	}
	
	@Test
	public void test4() {
		assertEquals("Alice", testClubMember2.getName());
		assertEquals("29 June 1992", testClubMember2.getDateOfBirth());
		assertEquals("0001", testClubMember2.getRegistrationNumber());
		assertEquals("Bronze", testClubMember2.getMembershipType());
	}
	
	@Test
	public void test5() {
		assertEquals(testClubMember.toString(), "[John Smith, 5 October 1993, ID: C212121, Gold]");
		assertEquals("John Smith", testClubMember.getName());
		assertEquals("5 October 1993", testClubMember.getDateOfBirth());
		assertEquals("C212121", testClubMember.getRegistrationNumber());
		assertEquals("Gold", testClubMember.getMembershipType());
	}
	
	@Test
	public void test6() {
		assertEquals(testClubMember2.toString(), "[Alice, 29 June 1992, ID: 0001, Bronze]");
		assertEquals("Alice", testClubMember2.getName());
		assertEquals("29 June 1992", testClubMember2.getDateOfBirth());
		assertEquals("0001", testClubMember2.getRegistrationNumber());
		assertEquals("Bronze", testClubMember2.getMembershipType());
	}
		
}
