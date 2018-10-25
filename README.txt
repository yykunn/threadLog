每个线程生成一个日志文件，方便多线程情况下查看日志
每个线程实例化一个 
	Logger logger = ThreadLogger.getLogger(logName)
对象，logName为该线程日志文件的文件名(不需要加后缀，后缀默认.log)