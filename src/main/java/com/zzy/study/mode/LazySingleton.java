package com.zzy.study.mode;

public class LazySingleton {
	private static LazySingleton m_instance = null;

	/**
	 * 私有的默认构造子，保证外界无法直接实例化
	 */
	private LazySingleton() {
	}

	/**
	 * 静态工厂方法，返还此类的惟一实例
	 */
	synchronized public static LazySingleton getInstance() {
		if (m_instance == null) {
			m_instance = new LazySingleton();
		}
		return m_instance;
	}

}
