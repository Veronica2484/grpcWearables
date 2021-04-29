package service3;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import javax.swing.JOptionPane;

import GUI.Services;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import service3.SnoreMeterServiceGrpc.SnoreMeterServiceImplBase;


public class SnoreMeterServer extends Thread {
	public static void main(String[] args) {
		System.out.println("Hello gRPC");
		SnoreMeterServer snoreMeterServer = new SnoreMeterServer();
		snoreMeterServer.run();
		
	}
	
	@Override
    public void run() {
		// create instance of the server class 
				SnoreMeterServer meterserver = new SnoreMeterServer();

				Properties prop = meterserver.getProperties();
				
				// jmDNS method to get service properties
				meterserver.registerService(prop);
				
				//port extracted from properties 
				int port = Integer.valueOf( prop.getProperty("service_port") );// #.50051;

				try {

					Server server = ServerBuilder.forPort(port)
							.addService(new SnoreMeterServiceImpl())
							.build()
							.start();  // start the server


					System.out.println("Snore Meter server started, listening on " + port);

					
					Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			            System.out.println("Received Shutdown Request");
			            server.shutdown();
			            System.out.println("Successfully stopped the server");
			        }));
					
					server.awaitTermination();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

	
	private Properties getProperties() {
		
		Properties prop = null;		
		
		 try (InputStream input = new FileInputStream("src/main/resources/proto/service3.properties")) {

	            prop = new Properties();

	            // load a properties file
	            prop.load(input);

	            // get the property value and print it out
	            System.out.println("Snore Meter Service properies ...");
	            System.out.println("\t service_type: " + prop.getProperty("service_type"));
	            System.out.println("\t service_name: " +prop.getProperty("service_name"));
	            System.out.println("\t service_description: " +prop.getProperty("service_description"));
		        System.out.println("\t service_port: " +prop.getProperty("service_port"));

	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	
		 return prop;
	}
	
	
	//jmDNS register
	private  void registerService(Properties prop) {
		
		 try {
	            // Create a JmDNS instance
	            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
	            
	            String service_type = prop.getProperty("service_type") ;//"_http._tcp.local.";
	            String service_name = prop.getProperty("service_name")  ;// "example";
	           // int service_port = 1234;
	            int service_port = Integer.valueOf( prop.getProperty("service_port") );// #.50051;

	            
	            String service_description_properties = prop.getProperty("service_description")  ;//"path=index.html";
	            
	            // Register a service
	            ServiceInfo serviceInfo = ServiceInfo.create(service_type, service_name, service_port, service_description_properties);
	            jmdns.registerService(serviceInfo);
	            
	            System.out.printf("registrering service with type %s and name %s \n", service_type, service_name);
	            
	            // Wait a bit
	            Thread.sleep(1000);

	            // Unregister all services
	            //jmdns.unregisterAllServices();

	        } catch (IOException e) {
	            System.out.println(e.getMessage());
	        } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    
	}
	
	//Extend abstract base class
		static class SnoreMeterServiceImpl extends SnoreMeterServiceImplBase {			
			
			@Override
			public StreamObserver<SnoreMeterRequest> snoreMeter(StreamObserver<SnoreMeterResponse> responseObserver) {

				StreamObserver<SnoreMeterRequest> requestObserver = new StreamObserver<SnoreMeterRequest>() {
					
					

					// Measure the level of snore and send the response
					@Override
					public void onNext(SnoreMeterRequest value) {
						int maxValue = 65;
						String snoreAlert = "You are snoring";
						
						
							int currentNumber = value.getNumber();
							
							if (currentNumber >= maxValue) {
								responseObserver.onNext(SnoreMeterResponse.newBuilder().setSnoreAlert(snoreAlert).build());
					                
						    }
					}

					@Override
					public void onError(Throwable t) {
					}

					@Override
					public void onCompleted() {
						// complete the RPC call
						responseObserver.onCompleted();
					}
				};

				return requestObserver;

			}
	 }
}
