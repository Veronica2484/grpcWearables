package service2;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

import GUI.Services;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;


public class NoiseMonitorClient {
	
private static ServiceInfo serviceInfo;
private ManagedChannel channel;

	public static void main(String args[]) throws InterruptedException {
		System.out.println("I am a gRPC client");
		
		
		//Object for the NoiseMonitorClient
		NoiseMonitorClient noise = new NoiseMonitorClient();
	   
		String service_type = "_service2._tcp.local.";
		discoverNoiseMonitorService(service_type);
		
		String host = serviceInfo.getHostAddresses()[0];
		int port = serviceInfo.getPort();
//		int port = 50052;
//        String host = "localhost";		
		
		//Create a channel as a transport
		ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
				.usePlaintext()                
				.build();
		
		//call the Client streaming 
		noise.ClientStreamingNoiseMonitorService(channel);
		   
	        
	    //Shutdown the channel
	    System.out.println("Shutting down the channel...");
	    
	    try {
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	
	
	public NoiseMonitorClient() {
		this.channel = channel;
	}



	public void ClientStreamingNoiseMonitorService(ManagedChannel channel) {
		// Client Streaming
		// Created a sleep pattern service client (asynchronous)
		NoiseMonitorServiceGrpc.NoiseMonitorServiceStub noiseMonitorClient = NoiseMonitorServiceGrpc.newStub(channel);

		CountDownLatch latch = new CountDownLatch(1);
		// return type
		StreamObserver<NoiseMonitorRequest> requestObserver = noiseMonitorClient
				.noiseMonitor(new StreamObserver<NoiseMonitorResponse>() {
					@Override
					public void onNext(NoiseMonitorResponse value) {
						// response from the server
						
						String noise = value.getNoisyValues();
						double average = value.getAverage();
						System.out.println("Received a response from the server (noise value)");
						System.out.println("Values of noise received along the night " + value.getNoisyValues());
						System.out.println("Average of noise " + value.getAverage());
					}

					@Override
					public void onError(Throwable t) {
						// error from the server

					}

					@Override
					public void onCompleted() {
						// server completed sending data
						//this method will be called after onNext has been completed
						System.out.println("Server has completed sending us data");
						latch.countDown(); //When the server is completed change the count to cero.
					}
				});

		// we send 20 messages to our server (client streaming)
		for (int i = 0; i < 20; i++) {
			
			Random ran = new Random();
			int low = 50;
			int high = 100;
			int result = ran.nextInt(high-low) + low;
			requestObserver.onNext(NoiseMonitorRequest.newBuilder()
					       .setNumber(result)
					       .build());
		}

		
		// call the completed method as the client is done sending data
		requestObserver.onCompleted();

		try {
			latch.await(3, TimeUnit.SECONDS); //allows to the server to sent the response...we wait for it to terminate 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		

	}
	

	
        private static void discoverNoiseMonitorService(String service_type) {
		
		
		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

				
			jmdns.addServiceListener(service_type, new ServiceListener() {
				
			
				@Override
				public void serviceResolved(ServiceEvent event) {
					System.out.println("Noise Monitor Service resolved: " + event.getInfo());

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
					System.out.println("Noise Monitor Service removed: " + event.getInfo());
					
				}
				
				@Override
				public void serviceAdded(ServiceEvent event) {
					System.out.println("Noise Monitor Service added: " + event.getInfo());
					
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
