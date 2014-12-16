package models;
/**
 * Create and fill List of permission for selected app
 * @author Peter Muska
 *
 */
public class PermisionListObject {
	
	private int mColor;
	
	private String mPermisionName;
	
	private String mPermisionDescription;
	
	/**
	 * Function to fill list of App permissions
	 * @param color - color of given permission
	 * @param name - name of given permission
	 * @param description - description of given permission
	 */
	public PermisionListObject(int color, String name, String description) {
		mColor = color;
		mPermisionName = name;
		mPermisionDescription = description;
	}
	
	/**
	 * Function to assign color of permission
	 * @return mColor - color of given permission
	 */
	public int getColor() {
		return mColor;
	}
	/**
	 * Function to assign name of permission 
	 * @return mPermissionName - name of given permission
	 */
	
	public String getPermisionName() {
		return mPermisionName;
	}

	/**
	 * Function to assign description of permission
	 * @return mPermisionDescription - description of given permission
	 */
	public String getPermisionDescription() {
		return mPermisionDescription;
	}
	
	
}
