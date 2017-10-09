/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL.item;

/**
 * It categorizes the component to a type.
 * @author farti
 */
public enum ComponentType {
LIQUID, CANISTER, CPU, GEAR;

	private String type;

	/**
	 * Constructor
	 * @param type
	 */
	ComponentType(String type) {
		this.type = type;
	}

	/**
	 * Gets the ComponentType.
	 * @return the ComponentType of instance.
	 */
	public ComponentType getComponentType() {
		return this;
	}

	/**
	 * Gets the 'correct' type/name as a String.
	 * @return the text from the enum, with correct capitals.
	 */
	public String getType() {
		return type;
	}
}