package entity;

public abstract class AbstractPerson {

	protected int ID;
	protected String Name;
	protected String familyName;
	protected String email;
	protected String address;
	protected String gender;
	public AbstractPerson() {

	}

	/**
	 * getEmail
	 * @return email
	 */
	
	public String getEmail() {
		return email;
	}

	/**
	 * setName
	 * @param name that is equals to this.name
	 */
	public void setName(String name) {
		this.Name = name;
	}

	/**
	 * getName
	 * @return Name
	 */
	
	public String getName() {
		return Name;
	}

	/**
	 * setFamilyName
	 * @param familyName that is equals to this.familyName
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	/**
	 * getFamilyName
	 * @return familyName
	 */
	public String getFamilyName() {
		return this.familyName;
	}

	/**
	 * setID
	 * @param ID that is equals to this.ID
	 */
	public void setID(int ID) {
		this.ID = ID;
	}

	/**
	 * getID
	 * @return ID
	 */
	public int getID() {
		return ID;
	}

	/**
	 * setAddress
	 * @param adress that is equals to this.address
	 */
	public void setAddress(String adress) {
		this.address = adress;
	}

	/**
	 * getAddress
	 * @return address
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * setGender
	 * @param gender  that is equals to this.gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * getGender
	 * @return Gender
	 */
	public String getGender() {
		return this.gender;
	}

}