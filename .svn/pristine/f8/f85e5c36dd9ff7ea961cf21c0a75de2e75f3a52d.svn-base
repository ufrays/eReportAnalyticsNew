package org.sap.era.service;

import java.lang.instrument.ClassFileTransformer;

import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;

public class ExtInstrumentationLoadTimeWeaver extends InstrumentationLoadTimeWeaver {

	@Override
	public void addTransformer(ClassFileTransformer transformer) {
		try {
			super.addTransformer(transformer);
		} catch (Exception e) {
		}
	}

}
