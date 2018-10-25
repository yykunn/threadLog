
package casia.isiteam.threadlog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Level;
/**
 * 读取配置文件config/threadLog4j.properties，
 * 未获取到配置文件或未设置参数时使用默认配置。
 * ConversionPattern：%-4r %-5p [%d{yyyy-MM-dd HH:mm:ss}]  %m%n；
 * maxFileSize：100MB；
 * ParentDir：logs/；
 * Level:INFO。
 * @author admin
 *
 */
public class Log4jConfig {

	private static Properties props = new Properties();
	static {
		InputStreamReader reader = null;
		try {
			File dir = new File(System.getProperty("user.dir"));
			File configFile = new File(dir, "config/threadLog4j.properties");
			if (!configFile.exists()) {
				configFile = new File(dir, "src/main/resources/config/threadLog4j.properties");
			}
			reader = new InputStreamReader(new FileInputStream(configFile), "UTF-8");
			props.load(reader);
		} catch (FileNotFoundException e) {
		} catch (Exception e) {
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
		}
	}

	// 日志的格式
	public static String getConversionPattern() {
		String conversionPattern = Log4jConfig.getValue("ConversionPattern");
		if (conversionPattern == null || conversionPattern.trim().equals("")) {
			return "%-4r %-5p [%d{yyyy-MM-dd HH:mm:ss}]  %m%n";
		}
		return conversionPattern.trim();
	}

	// 每个日志文件的大小
	public static String getMaxFileSize() {
		String maxFileSize = Log4jConfig.getValue("maxFileSize");
		if (maxFileSize == null || maxFileSize.trim().equals("")) {
			return "100MB";
		}
		return maxFileSize.trim();
	}

	// 日志文件的父目录
	public static String getParentDir() {
		String parentDir = Log4jConfig.getValue("ParentDir");
		if (parentDir == null || parentDir.trim().equals("")) {
			return "logs/";
		}
		return parentDir.trim();
	}

	// 日志级别
	public static Level getLevel() {
		String level = Log4jConfig.getValue("Level");
		try {
			Level le = Level.toLevel(level);
			return le;
		}catch (Exception e) {
			return Level.INFO;
		}
	}

	public static String getValue(String key) {
		return props.getProperty(key);
	}

	public static void setValue(String key, String value) {
		props.setProperty(key, value);
	}
}
