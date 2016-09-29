package mdb;

import java.io.Serializable;

public class Order implements Serializable {
	
	private int partNum;
	private float weight;
	private int quantity;
	
	public Order(int partNum, float weight, int quantity) {
		this.partNum = partNum;
		this.weight = weight;
		this.quantity = quantity;
	}
	
	public int getPartNum() {
		return partNum;
	}
	public void setPartNum(int partNum) {
		this.partNum = partNum;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

}
