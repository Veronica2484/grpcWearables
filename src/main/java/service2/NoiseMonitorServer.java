package service2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import service2.NoiseMonitorServiceGrpc.NoiseMonitorServiceImplBase;

public class NoiseMonitorServer extends Thread {

	public static void main(String[] args) {
		System.out.println("Hello gRPC");

		NoiseMonitorServer noiseMonitorServer = new NoiseMonitorServer();
		noiseMonitorServer.run();
		

	}
	
	@Override
    public void run() {
		// create instance of the server class
		NoiseMonitorServer monitorserver = new NoiseMonitorServer();

		Properties prop = monitorserver.getProperties();

		// jmDNS method to get service properties
		monitorserver.registerService(prop);

		//port extracted from properties
		int port = Integer.valueOf( prop.getProperty("service_port") );// #.50052;

		try {

			Server server = ServerBuilder.forPort(port)
					.addService(new NoiseMonitorServiceImpl())
					.build()
					.start();  // start the server


			System.out.println("Noisy Monitor server started, listening on " + port);


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

		 try (InputStream input = new FileInputStream("src/main/resources/proto/service2.properties")) {

	            prop = new Properties();

	            // load a properties file
	            prop.load(input);

	            // get the property value and print it out
	            System.out.println("Noise Monitor Service properies ...");
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
	            int service_port = Integer.valueOf( prop.getProperty("service_port") );// #.50052;


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
		static class NoiseMonitorServiceImpl extends NoiseMonitorServiceImplBase {

			
			
			@Override
			//Client streaming
			public StreamObserver<NoiseMonitorRequest> noiseMonitor(
					StreamObserver<NoiseMonitorResponse> responseObserver) {
				//create the stream observer
				StreamObserver<NoiseMonitorRequest> streamRequestObserver = new StreamObserver<NoiseMonitorRequest>() {
		            // running sum and count
		            int sum = 0;
		            int count = 0;
		            int number = 0;
		            String values = "";

		            @Override
		            //it receives the data and increment the sum and count
					public void onNext(NoiseMonitorRequest value) {
						// increment the sum
		                sum += value.getNumber();
		                // increment the count
		                count += 1;
		                // get the value input by the user and add to the string
		                number = (int) value.getNumber();
		                values = values + number + ",";

					}

		            @Override
		            public void onError(Throwable t) {

		            }

		            @Override
		            public void onCompleted() {
		                // compute average
		                double average = (double) sum / count;
		                responseObserver.onNext(NoiseMonitorResponse.newBuilder()
		                                .setAverage(average)
		                                .setNoisyValues(values)
		                                .build()
		                );
		                //the request is completed
		                responseObserver.onCompleted();
		            }


		        };

		        return streamRequestObserver;

			}


		}

}
