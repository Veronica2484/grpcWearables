package service1;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;



import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;


public class SleepPatternClient<SleepPattern> {
	
	private static ServiceInfo serviceInfo;
	private static ManagedChannel channel;
	
	
	
	public SleepPatternClient() {    
		String service_type = "_service1._tcp.local.";
		discoverSleepPatternService(service_type);
		
//		String host = serviceInfo.getHostAddresses()[0];
		//Use the serviceInfo to retrieve the port
//		int port = serviceInfo.getPort();
		int port = 50051;
	    String host = "localhost";
        
		//Create a channel as a transport between the client and the server
				ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
						.usePlaintext()                
						.build();
		 this.channel = channel;
		
	}


	public static void main(String args[]) throws InterruptedException {
		System.out.println("I am a gRPC client");
				
		//Object for the SleepPatternClient
		SleepPatternClient main = new SleepPatternClient();
//       main.run();
		main.UnarySleepPatternService();
		main.ServerStreamingSleepPatternService();
		
		
		//Shutdown the channel
	    System.out.println("Shutting down the channel...");
		try {
			channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			  
	}

	
	public void run() {
	  int port = 50051;
      String host = "localhost";
        
		//Create a channel as a transport between the client and the server
				ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
						.usePlaintext()                
						.build();

		
        try {
			channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				

	}
	
	private void UnarySleepPatternService() {
		//Unary
				//Created a sleep pattern service client (blocking - synchronous)
				SleepPatternServiceGrpc.SleepPatternServiceBlockingStub SleepPatternClient = SleepPatternServiceGrpc.newBlockingStub(channel);
			    
			    
		        // build protocol buffer for the request    
			    SleepPatternRequest sPattern = SleepPatternRequest.newBuilder()
			    		                       .setUserName("Veronica")
			    		                       .build();
			    
			    //Call the RPC and get back the response
			    SleepPatternResponse sleepPatternresponse = SleepPatternClient.sleepPattern(sPattern);
			    	    
			    System.out.println(sleepPatternresponse.getUserName());
			    System.out.println("Your pattern of sleep is: " + sleepPatternresponse.getPattern());
	}
	
	private void ServerStreamingSleepPatternService() {
		//Server Streaming
		//Created a sleep pattern service client (blocking - synchronous)
		SleepPatternServiceGrpc.SleepPatternServiceBlockingStub SleepPatternClient = SleepPatternServiceGrpc.newBlockingStub(channel);
	    //prepare the request
	    WeeklySleepPatternRequest wPattern = WeeklySleepPatternRequest.newBuilder()
	    		                             .setUserName("Veronica")
	    		                             .build();
	    
         //stream the responses (in a blocking manner)
	   
	    SleepPatternClient.weeklySleepPattern(wPattern)
	                      .forEachRemaining(WeeklySleepPatternResponse -> {
	                       System.out.println( WeeklySleepPatternResponse.getUserName() + WeeklySleepPatternResponse.getPattern());
	                       });
	}
	
	
	
        private static void discoverSleepPatternService(String service_type) {
		
		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
		
			jmdns.addServiceListener(service_type, new ServiceListener() {
						
				@Override
				public void serviceResolved(ServiceEvent event) {
					System.out.println("Sleep Pattern Service resolved: " + event.getInfo());

					serviceInfo = event.getInfo();

					int port = serviceInfo.getPort();
					
					System.out.println("resolving " + service_type + " with properties ...");
					System.out.println("\t port: " + port);
					System.out.println("\t type:"+ event.getType());
					System.out.println("\t name: " + event.getName());
					System.out.println("\t description/properties: " + serviceInfo.getNiceTextString());
					System.out.println("\t host: " + serviceInfo.getHostAddresses()[0]);
								
				}
				
				@Override
				public void serviceRemoved(ServiceEvent event) {
					System.out.println("Sleep Pattern Service removed: " + event.getInfo());
					
				}
				
				@Override
				public void serviceAdded(ServiceEvent event) {
					System.out.println("Sleep Pattern Service added: " + event.getInfo());
					
				}
			});
			
			// Wait a bit
			Thread.sleep(2000);
			
			jmdns.close();

		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
}
