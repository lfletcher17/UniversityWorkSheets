/**
 * A class for a ClubMember created from a ClubMember name, their dateOfBirth
 * their registrationNumber and their membershipType.
 * ClubMember consists of the following field variables:
 * 	name - the name of the ClubMember
 *	dateOfBirth - the date of birth of the ClubMember
 *	registrationNumber- the registration number of the ClubMember 
 * 	membershipType - the type of membership of the ClubMember
 * @author lxf736
 * @version 2017-10-05
 *
 */

public class ClubMember {

	private String name;
	private String dateOfBirth;
	private String registrationNumber;
	private String membershipType;

	/**
	 * @param name is the name as String
	 * @param dateOfBirth is the dateOfBirth as String
	 * @param registrationNumber is the registrationNumber as String
	 * @param membershipType is the membershipType as String
	 */
	public ClubMember (String name, String dateOfBirth, String registrationNumber, String membershipType) {
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.registrationNumber = registrationNumber;
		this.membershipType = membershipType;
	}

	/** 
     *  @return the name of the ClubMember as String
     */
	public String getName() {
		return name;
	}

	/** 
     *  @return the dateOfBirth of the ClubMember as String
     */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/** 
     *  @return the registrationNumber of the ClubMember as String
     */
	public String getRegistrationNumber() {
		return registrationNumber;
	}

	/** 
     *  @return the membershipType of the ClubMember as String
     */
	public String getMembershipType() {
		return membershipType;
	}
	
    /** 
     *  toString defines how to print a ClubMember
     *  @return  the print type of a ClubMember
     */
	public String toString() {
		return "[" + this.name + ", " + this.dateOfBirth +
				", ID: " + this.registrationNumber + ", " +
				this.membershipType + "]";
				
	}
	
}

