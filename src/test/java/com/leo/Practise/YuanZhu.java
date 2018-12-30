package com.leo.Practise;

public class YuanZhu extends Circle {

	double height;
	final double π = 3.14;

	public YuanZhu(double r, double height) {
		this.r = r;
		this.height = height;
	}

	@Override
	double area() {
		double area = π * r * r;
		return area;
	}

	double volume() {
		double volume = π * r * r * height;
		return volume;
	}

	YuanZhu little = new YuanZhu(3.0, 2.5);
	double mianJi = little.area();
}
