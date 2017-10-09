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
	LIQUID("Portal Liquid"), CANISTER("Liquid Canister"), CPU("CPU"), GEARS("Gears");

	private String type;

	ComponentType(String type) {
		this.type = type;
	}

	public ComponentType getComponentType() {
		return this;
	}

	public String getType() {
		return type;
	}
}