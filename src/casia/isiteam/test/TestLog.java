package casia.isiteam.test;

import org.apache.log4j.Logger;

import casia.isiteam.threadlog.ThreadLogger;

public class TestLog {

	// 这是主线程的Logger
	static Logger logger = ThreadLogger.getLogger("main_log");

	public static void main(String[] args) {
		logger.info("任务开始执行");

		for (int i = 0; i < 10; i++) {
			MyThread thread = new MyThread(String.valueOf(i));
			new Thread(thread).start();
		}

		logger.info("任务结束！");

	}
}
