package service1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;
import java.util.Random;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import service1.SleepPatternResponse.Pattern;
import service1.SleepPatternServiceGrpc.SleepPatternServiceImplBase;

public class SleepPatternServer extends Thread {
	
	
	
	public static void main(String[] args) {
		
		System.out.println("Hello gRPC");
		SleepPatternServer sleepPatternServer = new SleepPatternServer();
		sleepPatternServer.run();
	
	}
	
	@Override
    public void run() {
		// create instance of the server class 
		SleepPatternServer patserver = new SleepPatternServer();

		Properties prop = patserver.getProperties();
		
		// jmDNS method to get service properties
		patserver.registerService(prop);
		
		//port extracted from properties 
		int port = Integer.valueOf( prop.getProperty("service_port") );// #.50051;
	    

		try {

			Server server = ServerBuilder.forPort(port)
					.addService(new SleepPatternServiceImpl())
					.build()
					.start();  // start the server


			System.out.println("Sleep Pattern server started, listening on " + port);

			
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
		
		 try (InputStream input = new FileInputStream("src/main/resources/proto/service1.properties")) {

	            prop = new Properties();

	            // load a properties file
	            prop.load(input);

	            // get the property value and print it out
	            System.out.println("Sleep Pattern Service properies ...");
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
	            
	            //get the properties of the service	            
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
		static class SleepPatternServiceImpl extends SleepPatternServiceImplBase {//extends the class ImplBase that has been created in order to override the sleep pattern service
			
			@Override
			//Unary RPC
			public void sleepPattern(SleepPatternRequest request, StreamObserver<SleepPatternResponse> responseObserver) {//function to be implemented that takes an input sleeppatternrequest defined in the proto and the responseObserver that is the response to be sent to the user
			

			    // get the fields we need from the input	        
		        String userName = request.getUserName();
		        
		        // create the response
		        String result = "Hello " + userName;
		        SleepPatternResponse response = SleepPatternResponse.newBuilder()
		                .setUserName(result)
		                .setPattern(Pattern.WELL)
		                .build();

		        
		        // send the response back to the client. Because the server is synchronous the response is sent by a reponseObserver instead of a return statement. 
		        responseObserver.onNext(response);

		        // complete the RPC call
		        responseObserver.onCompleted();
		    }

			@Override
			//Server Streaming RPC
			public void weeklySleepPattern(WeeklySleepPatternRequest request,
					StreamObserver<WeeklySleepPatternResponse> responseObserver) {
				Random rand = new Random();

				// get the fields we need from the input
				String userName = request.getUserName();

				try {
					for (int i = 1; i <= 7; i++) {
						String result = "Hello " + userName + " your sleep pattern of night number " + i + " is: ";
						int p = 1 + rand.nextInt(4);
						WeeklySleepPatternResponse response = WeeklySleepPatternResponse.newBuilder()
								.setUserName(result)
								.setPatternValue(p)
								.build();
						
						
						// send the response back to the client
						responseObserver.onNext(response);
						Thread.sleep(1000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					// complete the RPC call
					responseObserver.onCompleted();
				}
			}
		}
}
			
			
		

	