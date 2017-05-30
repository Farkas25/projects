package hu.bme.onlab.gcmclient;

public interface Config {

	// used to share GCM regId with application server - using php app server
	//static final String APP_SERVER_URL = "http://192.168.0.67/gcm/gcm.php?shareRegId=1";

	// GCM server using java
	static final String APP_SERVER_URL = "http://192.168.0.66:8082/GCMServerApp/GCMNotification?shareRegId=1";

	// Google Project Number
	static final String GOOGLE_PROJECT_ID = "308081094713";
	static final String MESSAGE_KEY = "message";

}