package casia.isiteam.threadlog;
 
import java.io.IOException;

import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
 
public class ThreadLogger {
 
	private static String conversionPattern = Log4jConfig.getConversionPattern();
	private static String parentDir = Log4jConfig.getParentDir();
	private static Level level = Log4jConfig.getLevel();
	private static String maxSize = Log4jConfig.getMaxFileSize();
	
	public static Logger getLogger(String logName) {
		Logger logger = null;
		logger = Logger.getLogger(logName);
		PatternLayout layout = new PatternLayout(conversionPattern);
 
		String logPath = parentDir;
 
		// 文件输出
		ThreadLogger.ThreadFileAppender fileAppender = null;
 
		try {
			fileAppender = new ThreadFileAppender(layout, logPath, logName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		fileAppender.setAppend(false);
		fileAppender.setImmediateFlush(true);
		fileAppender.setThreshold(level);
		fileAppender.setMaxFileSize(maxSize);
		// 绑定到logger
		logger.setLevel(level);
		logger.addAppender(fileAppender);
		return logger;
	}
 
	/*
	 * 继承了log4j类的内部类
	 */
	public static class ThreadFileAppender extends RollingFileAppender {
		public ThreadFileAppender(Layout layout, String filePath, String fileName)
				throws IOException {
			super(layout, filePath + fileName + ".log");
		}
	}
}
