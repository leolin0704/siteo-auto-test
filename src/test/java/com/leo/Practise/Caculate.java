package com.leo.Practise;

import java.awt.Button;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import javax.swing.JFrame;

public class Caculate {

	public static void main(String[] args) throws ParseException {

//		Shap rectangle = new Shap(6.0, 8.3, 6.0);
//
//		Shap square = new Shap(5.0, 5.0, 5.0);
//
//		Shap.ifsquare(rectangle);
//
//		Tixing newTx = new Tixing(5.0, 2.0, 4.5);
//		Caculate caOne = new Caculate();
//		caOne.testArea(newTx);
//		System.out.println("梯形的面积是" + newTx.area);
//
//		for (int i = 0; i <= 3; i++) {
//			rectangle.area -= newTx.area;
//			if (rectangle.area >= 0) {
//				System.out.println("形状的的面积还有" + rectangle.area);
//			} else {
//				System.out.println("计算出错");
//			}
//		}
//
//		Shap[] ShapIntegration = { rectangle, newTx, square };
//
////		for (int index = 0; index <= ShapIntegration.length; index++) {
////			System.out.println("形状是：" + (ShapIntegration[index]).toString());
////		}
//
//		Shap[] xingzhuang = new Shap[] { rectangle, newTx, square };
//
//		for (Shap each : xingzhuang) {
//			System.out.println(each);
//		}
//
//		int index = 1;
//
//		do {
//			System.out.println("*");
//			index++;
//		} while (index < 10);
//
//		int c = Math.floorDiv(4, 7);
//		System.out.println("c=" + c);
//
//		int d = Integer.bitCount(4);
//		System.out.println("d=" + d);
//
//		HashMap<Integer, String> array = new HashMap<Integer, String>();
//		array.put(007, "王老虎");
//		array.put(012, "东谷君");
//		array.put(045, "西条一户");
//		System.out.println(array.get(045));
//
//		ArrayList<Shap> shapList = new ArrayList<Shap>();
//		shapList.add(rectangle);
//		shapList.add(square);
//		System.out.println(shapList.get(1));
//
//		double a = 0.325;
//		int b = 0;
//		try {
//			double f = a / b;
//		} catch (Exception e) {
//			System.out.println("0 can not be a divider");
//		}
//
//		int number = 100, result3 = 0;
//
//		result3 = ++number + ++number + number++ + number-- + --number;
//
//		// 101 + 102 + 102 + 103 + 101
//
//		System.out.println("result3的值是：" + result3);
//		System.out.println("number的值是：" + number);
//
//		Scanner sc = new Scanner(System.in);
//		System.out.println("请输入一个整数：");
//		int numbera = sc.nextInt();
//		if (numbera > 32) {
//			boolean result4 = (numbera % 2 == 0);
//			System.out.println("result4的值是：" + result4);
//		} else {
//			System.out.println("numbera的值是：" + numbera);
//		}
//
//		int number1 = 320;
//		int number2 = 200;
//		int max = number1 > number2 ? number1 : number2;
//		System.out.println("max的值是：" + max);
//
//		System.out.println("请输入年份：");
//		int year = sc.nextInt();
//		if (year % 4 == 00 && year % 100 != 0 || year % 400 == 0) {
//			System.out.println("是闰年：");
//		} else {
//			System.out.println("不是闰年：");
//		}
//
//		double sum = 0;
//
//		for (int i = 1; i <= 100; i++) {
//			sum = sum + 1 / i;
//		}
//
//		System.out.println("sum的值是：" + sum);
//
//		for (int i = 1; i <= 9; i++) {
//			for (int k = 1; k <= i; k++) {
//
//				int result = k * i;
//				System.out.print(k + "*" + i + "=" + result + " ");
//			}
//			System.out.println();
//		}
//
//		Scanner sc = new Scanner(System.in);
//		System.out.print("请输入数字：");
//		int number = sc.nextInt();
//
//		Shap newOne = new Tixing(4.0, 2.0, 3.5);
//		double mianJi = newOne.area;
//		System.out.println("梯形的面积" + mianJi);
//
//		String str = "西门吹雪";
//		System.out.println("字符串：" + str.toString());
//		System.out.println("字符串：" + str);

		String today = "2018-12-28";
		SimpleDateFormat dateformate = new SimpleDateFormat("yyyy-MM-dd");
		Date todayDate = dateformate.parse(today);
		System.out.println("日期是：" + todayDate);
		System.out.println("日期是：" + todayDate.getTime());

		Calendar calendar = Calendar.getInstance();

		Date now = new Date();
		String nowTime = dateformate.format(now);
		System.out.println("日期是：" + nowTime);

		int number1 = 1000;
		Integer Number = new Integer(number1);
		System.out.println(Number);

		boolean result = false;
		Boolean Result = new Boolean(result);
		System.out.println(Result);

		Integer Number2 = new Integer(1000);
		int number2 = Integer.valueOf(Number2);
		System.out.println(number2);

		int number3 = 2530;
		String sha = String.valueOf(number3);
		System.out.println(sha.toString());

		String stf = "1200";
		Integer stt = Integer.parseInt(stf);
		System.out.println(stt);

		ArrayList array = new ArrayList();
		array.add("阿玛尼");
		array.add("古驰");
		array.add("兰博基尼");
		array.add("迪奥");
		array.add("范思哲");
		System.out.println(array.get(4));
		array.clear();
		System.out.println(array.isEmpty());

		HashSet newOne = new HashSet();
		newOne.add(new Shap(3.2, 2, 4.5));
		newOne.add(new Tixing(3.2, 5.6, 4.0));

		HashMap what = new HashMap();
		what.put(2, "林立");

		what.put(100, "林婉儿");
		System.out.println(what.get(2));

		JFrame frame = new JFrame();
		Button button = new Button("确定");
		button.setSize(200, 300);
		frame.add(button);
		frame.setVisible(true);
		frame.setSize(500, 450);
		frame.setTitle("带按钮的组件");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public double testArea(Tixing newTx) {
//		newTx = new Tixing(1.0, 1.0, 1.5);
		newTx.area = 32;
		return newTx.area;
	}
}
