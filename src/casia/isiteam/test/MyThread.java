package casia.isiteam.test;

import org.apache.log4j.Logger;

import casia.isiteam.threadlog.ThreadLogger;

public class MyThread implements Runnable {

	String logName;

	public MyThread(String logName) {
		this.logName = logName;
	}

	public void run() {
		// 在run方法内实现线程独立的logger实例
		// 若使用DailyThreadLogger.getLogger()则日志文件按每天分文件夹存放
		Logger logger = ThreadLogger.getLogger(logName);
		while (true) {

			logger.info(logName + "_" + Thread.currentThread().getName() + " started!");

			logger.error("this is error!");

			logger.info(logName + "_" + Thread.currentThread().getName() + " finished!");
			
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
