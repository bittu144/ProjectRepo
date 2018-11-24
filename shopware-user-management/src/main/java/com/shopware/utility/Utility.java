package com.shopware.utility;

import java.util.Random;

public class Utility {

	public static String getRandomValue() {
		Random random = new Random();
		String randomValue = random.nextInt() + "";
		return randomValue.startsWith("-") ? randomValue.substring(1, randomValue.length()) : randomValue;

	}

}
