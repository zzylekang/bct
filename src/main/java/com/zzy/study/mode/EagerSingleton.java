package com.zzy.study.mode;

public class EagerSingleton {
	private static final EagerSingleton m_instance = new EagerSingleton();

	/**
	 * ˽�е�Ĭ�Ϲ�����
	 */
	private EagerSingleton() {
	}

	/**
	 * ��̬��������
	 */
	public static EagerSingleton getInstance() {
		return m_instance;
	}

}
