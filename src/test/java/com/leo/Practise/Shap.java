package com.leo.Practise;

public class Shap {

	double length;
	double width;
	double height;
	double area;
	double volume;

	public Shap(double length, double width, double height) {
		this.length = length;
		this.width = width;
		this.height = height;
		this.area = length * width;
		this.volume = length * width * height;
	}

	public static boolean ifsquare(Shap shap) {
		if (shap.length == shap.width) {
			System.out.println("是正方形");
			return true;
		} else {
			System.out.println("不是正方形");
			return false;
		}
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

}
