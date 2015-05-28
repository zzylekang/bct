package com.zzy.study.mode;

public class LazySingleton {
	private static LazySingleton m_instance = null;

	/**
	 * ˽�е�Ĭ�Ϲ����ӣ���֤����޷�ֱ��ʵ����
	 */
	private LazySingleton() {
	}

	/**
	 * ��̬�������������������Ωһʵ��
	 */
	synchronized public static LazySingleton getInstance() {
		if (m_instance == null) {
			m_instance = new LazySingleton();
		}
		return m_instance;
	}

}
