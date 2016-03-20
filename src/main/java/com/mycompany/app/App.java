package com.mycompany.app;

import com.microsoft.azure.iot.service.exceptions.IotHubException;
import com.microsoft.azure.iot.service.exceptions.IotHubExceptionManager;
import com.microsoft.azure.iot.service.sdk.Device;
import com.microsoft.azure.iot.service.sdk.RegistryManager;

import java.io.IOException;
import java.net.URISyntaxException;

public class App 
{
	private static final String connectionString = 
		"HostName=dimitarnodejs.azure-devices.net;SharedAccessKeyName=iothubowner;SharedAccessKey=Pay9Qxs9AZgkEfCU38Zl0htyLNqy8kIOOsQmiQWarpI=";
	private static final String deviceId = "javadevice";
	
	public static void main( String[] args ) throws IOException, URISyntaxException, Exception
    {
        Device device = App.createOrGetDevice();
        
        System.out.println("Device id: " + device.getDeviceId());
        System.out.println("Device key: " + device.getPrimaryKey());
    }
	
	/**
	 * Get IoT device information from Azure.
	 * 
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws Exception
	 */
	public static Device createOrGetDevice() throws IOException, URISyntaxException, Exception {
		RegistryManager registryManager = RegistryManager.createFromConnectionString(connectionString);
		
		Device device = Device.createFromId(deviceId, null, null);
		
		try {
        	device = registryManager.addDevice(device);
        } catch (IotHubException iotf) {
        	try {
        		device = registryManager.getDevice(deviceId);
        	} catch (IotHubException iotHubException) {
        		iotHubException.printStackTrace();
        	}
        }
		
		return device;
	}
}
