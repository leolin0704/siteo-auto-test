package com.leo.Practise;

public class Tixing extends Shap {

	public Tixing(double length, double width, double height) {
		super(length, width, height);
		this.area = (length + width) * height / 2;
	}

}
