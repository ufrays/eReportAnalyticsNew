package org.sap.era.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

public final class EnumHelper {

	@SuppressWarnings("unchecked")
	public static <TEnum> ArrayList<TEnum> getOptionsFromEnum(Class<TEnum> enumType) {
		if (!enumType.isEnum()) {
			// throw new Exception("Only enum type is allowed!");
			return null;
		}
		ArrayList<TEnum> enumOptions = new ArrayList<TEnum>();
		for (Field field : enumType.getFields()) {
			if (field.isEnumConstant()) {
				try {
					enumOptions.add((TEnum) field.get(null));
				} catch (IllegalArgumentException | IllegalAccessException ex) {
					// throw new Exception(ex.getMessage());
					return null;
				}
			}
		}
		return enumOptions;
	}

	@SuppressWarnings("unchecked")
	public static <TEnum> TEnum getRandomEnum(Class<TEnum> enumType) {
		final Random rInst = new Random();
		if (!enumType.isEnum()) {
			// throw new Exception("Only enum type is allowed!");
			return null;
		}
		ArrayList<TEnum> allEnumFields = new ArrayList<TEnum>();
		for (Field field : enumType.getFields()) {
			if (field.isEnumConstant()) {
				try {
					allEnumFields.add((TEnum) field.get(null));
				} catch (IllegalArgumentException | IllegalAccessException ex) {
					// throw new Exception(ex.getMessage());
					return null;
				}
			}
		}
		if (allEnumFields.size() > 0) {
			return allEnumFields.get(rInst.nextInt(allEnumFields.size()));
		} else {
			return null;
		}
	}
}
