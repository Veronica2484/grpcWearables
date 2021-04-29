package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.jmdns.ServiceInfo;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;


import javax.swing.JLabel;
import javax.swing.JTextArea;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import service1.SleepPatternRequest;
import service1.SleepPatternResponse;
import service1.SleepPatternServer;
import service1.SleepPatternServiceGrpc;
import service1.WeeklySleepPatternRequest;
import service2.NoiseMonitorRequest;
import service2.NoiseMonitorResponse;
import service2.NoiseMonitorServer;
import service2.NoiseMonitorServiceGrpc;
import service3.SnoreMeterRequest;
import service3.SnoreMeterResponse;
import service3.SnoreMeterServer;
import service3.SnoreMeterServiceGrpc;

import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class GUI {

	private ServiceInfo serviceInfoSleepPattern;
	private ServiceInfo serviceInfoNoiseMonitor;
	private ServiceInfo serviceInfoSnoreMeter;
	private JFrame frmSleepTrackerApp;
	private SleepPatternServiceGrpc.SleepPatternServiceBlockingStub SleepPatternClient;
	private JComboBox comboBox = new JComboBox();
	private JTextArea textResponse;
	private JTextArea textResponse_1;
	private JTextArea textResponse_2;
	private JTextArea textResponse_3;
	private JPanel contentPane;

	


	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmSleepTrackerApp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	
	
    //Constructor
	public GUI() {
		run();
				
		new Thread()
		{
		    public void run() {
		    	service1discover();
		    }
		}.start();
		

		new Thread()
		{
		    public void run() {
		    	service2discover();
		    }
		}.start();
		
		new Thread()
		{
		    public void run() {
		service3discover();
		    }
		}.start();
		  	
		
		initialize();
		
	}
	
	//This function will start the servers
	public void run() {

        Thread sleepPatternServer = new Thread(new SleepPatternServer());
        Thread noiseMonitorServer = new Thread(new NoiseMonitorServer());
        Thread snoreMeterServer = new Thread(new SnoreMeterServer());
        
        sleepPatternServer.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        noiseMonitorServer.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        snoreMeterServer.start();

    }
	
	public void service1discover() {
		try {
		    
			Runnable task = new Runnable() {

				@Override
				public void run() {
					
					
					String service_type = "_service1._tcp.local.";
					//Now retrieve the service info and save it in a serviceinfo instance - all we are supplying is the service type
					serviceInfoSleepPattern = SimpleServiceDiscovery.run(service_type);
					//Use the serviceInfo to retrieve the host and port
					
//					String host = serviceInfoSleepPattern.getHostAddresses()[0];
//					int port = serviceInfoSleepPattern.getPort();								
//					int port = 50051;
//			        String host = "localhost";
					
					//Unary
					
//					ManagedChannel channel = ManagedChannelBuilder
//							.forAddress(host, port)
//							.usePlaintext()                
//							.build();
//
//					//Created a sleep pattern service client (blocking - synchronous)
//					SleepPatternClient = SleepPatternServiceGrpc.newBlockingStub(channel);
					
									
				}
				
			};
			
						
			new Thread(task).start();
						
			// jmDNS error handling. Unable to discover the service
		    } catch (NullPointerException jmdns) {
		        System.out.println("Unable to discover the service");
		        // gRPC error handling. Unable to connect with the server
		    } catch (
		            StatusRuntimeException grpc) {
		        System.out.println("grpc server not running");
		    }
		
	}
	public void service2discover(){
try {
		    
			Runnable task = new Runnable() {

				@Override
				public void run() {
					
					
					String service_type = "_service2._tcp.local.";
					//Now retrieve the service info and save it in a serviceinfo instance - all we are supplying is the service type
					serviceInfoNoiseMonitor = SimpleServiceDiscovery.run(service_type);
					//Use the serviceInfo to retrieve the port
//					String host = serviceInfoNoiseMonitor.getHostAddresses()[0];
//					int port = serviceInfoNoiseMonitor.getPort();
											
//					int port = 50052;
//			        String host = "localhost";
									
								
//					ManagedChannel channel2 = ManagedChannelBuilder
//							.forAddress(host, port)
//							.usePlaintext()                
//							.build();
//
//					// Client Streaming
//					// Created a sleep pattern service client (asynchronous)
//					NoiseMonitorServiceGrpc.NoiseMonitorServiceStub noiseMonitorClient = NoiseMonitorServiceGrpc.newStub(channel2);
					
				}
				
			};
			
			
			
			new Thread(task).start();
			
			
			// jmDNS error handling. Unable to discover the service
		    } catch (NullPointerException jmdns) {
		        System.out.println("Unable to discover the service");
		        // gRPC error handling. Unable to connect with the server
		    } catch (
		            StatusRuntimeException grpc) {
		        System.out.println("grpc server not running");
		    }
	}
	
	public void service3discover(){
		try {
				    
					Runnable task = new Runnable() {

						@Override
						public void run() {
							
							
							String service_type = "_service3._tcp.local.";
							//Now retrieve the service info - all we are supplying is the service type
							serviceInfoSnoreMeter = SimpleServiceDiscovery.run(service_type);
							//Use the serviceInfo to retrieve the port
//							String host = serviceInfoSnoreMeter.getHostAddresses()[0];
//							int port = serviceInfoSnoreMeter.getPort();
//													
////							int port = 50053;
////					        String host = "localhost";
//											
//										
//							ManagedChannel channel3 = ManagedChannelBuilder
//									.forAddress(host, port)
//									.usePlaintext()                
//									.build();
//                           
//							// Bi directional Streaming
//							// Created a snore meter service client (asynchronous)
//							SnoreMeterServiceGrpc.SnoreMeterServiceStub snoreMeterClient = SnoreMeterServiceGrpc.newStub(channel3);
							
							
						}
						
					};
									
					new Thread(task).start();
										
					// jmDNS error handling. Unable to discover the service
				    } catch (NullPointerException jmdns) {
				        System.out.println("Unable to discover the service");
				        // gRPC error handling. Unable to connect with the server
				    } catch (
				            StatusRuntimeException grpc) {
				        System.out.println("grpc server not running");
				    }
			}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	
	//Create and initialize components
	private void initialize() {
		
       
		frmSleepTrackerApp = new JFrame();
		frmSleepTrackerApp.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\nika_\\eclipse-workspace\\grpcCA\\src\\main\\java\\GUI\\resources\\sleep.icon.png"));
		frmSleepTrackerApp.setTitle("Sleep tracker App");
		frmSleepTrackerApp.setBounds(100, 100, 745, 679);
		frmSleepTrackerApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
				
		JLabel userNameLabel = new JLabel("   UserName");
		userNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JTextArea username = new JTextArea();
		
		JButton btnNewButton = new JButton("Start Session");
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name;
				try {
				name= username.getText().toLowerCase(); //get the text from the text area
				Object type = comboBox.getSelectedItem();
							    
			    if (name.equals("veronica")) { //check if the username if correct
			    
			    username.setText(""); //set the text area to empty
			        
				JOptionPane.showMessageDialog(null, "Hi " + " "+ name);
				
				//open the new frame of Services
//				Services Options = new Services();
//		        Options.setVisible(true); //open the new frame of the services
		        
		        //Hide the Start GUI once the username is validated
//		        frmSleepTrackerApp.setVisible(false);
		        
		        
			    }
			    else {
			    	throw new Exception(); //if the username is not correct it will throw an exception to be caught
			    }
				}
				catch (Exception i) {
					JOptionPane.showMessageDialog(null, "Please enter valid username Veronica");
				}
				
				
				
			}
		});
		
		JLabel lblNewLabel = new JLabel("Sleep Tracker Services");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel lblNewLabel_1 = new JLabel("Please select what service do you want to check");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
		
		JButton btnNewButton_1 = new JButton("Daily Sleep Pattern");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Unary
				//Use the serviceInfo to retrieve the host and port
//				String host = serviceInfoSleepPattern.getHostAddresses()[0];
//				int port = serviceInfoSleepPattern.getPort();
				int port = 50051;
		        String host = "localhost";
				ManagedChannel channel = ManagedChannelBuilder
						.forAddress(host, port)
						.usePlaintext()                
						.build();

				//Created a sleep pattern service client (blocking - synchronous)
				SleepPatternClient = SleepPatternServiceGrpc.newBlockingStub(channel);
						    
		        // build protocol buffer for the request    
			    SleepPatternRequest sPattern = SleepPatternRequest.newBuilder()
			    		                       .setUserName("Veronica")
			    		                       .build();
			    
			    //Call the RPC and get back the response
			    SleepPatternResponse sleepPatternresponse = SleepPatternClient.sleepPattern(sPattern);
			    	    
			    System.out.println(sleepPatternresponse.getUserName());
			    System.out.println("Your pattern of sleep is: " + sleepPatternresponse.getPattern());
		
				textResponse.append(sleepPatternresponse.getUserName()+"\n" + "Your pattern of sleep is: " + sleepPatternresponse.getPattern());
					
				
			}
		});
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 11));
		
		JButton btnNewButton_3 = new JButton("Weekly Sleep Pattern");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Server Streaming
				//Use the serviceInfo to retrieve the host and port
//				String host = serviceInfoSleepPattern.getHostAddresses()[0];
//				int port = serviceInfoSleepPattern.getPort();
				int port = 50051;
		        String host = "localhost";
				ManagedChannel channel = ManagedChannelBuilder
						.forAddress(host, port)
						.usePlaintext()                
						.build();
				//Created a sleep pattern service client (blocking - synchronous)
				SleepPatternClient = SleepPatternServiceGrpc.newBlockingStub(channel);
				//prepare the request
				
				
				
			    WeeklySleepPatternRequest wPattern = WeeklySleepPatternRequest.newBuilder()
			    		                             .setUserName("Veronica")
			    		                             .build();
			    			    
		         //stream the responses (in a blocking manner)
			   
			    new Thread()
			    {
			        public void run() {
			        	SleepPatternClient.weeklySleepPattern(wPattern)
	                      .forEachRemaining(WeeklySleepPatternResponse -> {
	                      System.out.println( WeeklySleepPatternResponse.getUserName() + WeeklySleepPatternResponse.getPattern());
	                       textResponse_1.append(WeeklySleepPatternResponse.getUserName() + WeeklySleepPatternResponse.getPattern()+"\n");
	                       });
			        	
			        }
			    }.start();
			    
			    			    
			}
		});
		btnNewButton_3.setFont(new Font("Arial", Font.BOLD, 11));
		
		JButton btnNewButton_1_1 = new JButton("Noise Monitor");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Client Streaming				   
				int port = 50052;
		        String host = "localhost";	
				//Use the serviceInfo to retrieve the host and port
//		        String host = serviceInfoNoiseMonitor.getHostAddresses()[0];
//				int port = serviceInfoNoiseMonitor.getPort();
				
				//Create a channel as a transport
				ManagedChannel channel1 = ManagedChannelBuilder.forAddress(host, port)
						.usePlaintext()                
						.build();
				
				// Created a sleep pattern service client (asynchronous)
				NoiseMonitorServiceGrpc.NoiseMonitorServiceStub noiseMonitorClient = NoiseMonitorServiceGrpc.newStub(channel1);

				CountDownLatch latch = new CountDownLatch(1);
				
				
				new Thread()
				{
				    public void run() {
				    	
						// return type
						StreamObserver<NoiseMonitorRequest> requestObserver = noiseMonitorClient
								.noiseMonitor(new StreamObserver<NoiseMonitorResponse>() {
									@Override
									public void onNext(NoiseMonitorResponse value) {
										// response from the server
										
										System.out.println("Received a response from the client (noise value)");
										System.out.println("Values of noise received along the night " + value.getNoisyValues());
										System.out.println("Average of noise " + value.getAverage());
										  
										textResponse_2.append("Values of noise received along the night: " + value.getNoisyValues()+ "\n"+ "Average of noise for last night: " + value.getAverage());
									}

									@Override
									public void onError(Throwable t) {
										// error from the server

									}

									@Override
									public void onCompleted() {
										// server completed sending data
										//this method will be called after onNetx has been completed
										System.out.println("Client has completed sending us data");
										latch.countDown();
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

						
				        // the client is done sending data
						requestObserver.onCompleted();

						try {
							latch.await(3, TimeUnit.SECONDS); //allows to the server to sent the response...we wait for it to terminate 
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					      
				    }
				}.start();
			}
		});
		btnNewButton_1_1.setFont(new Font("Arial", Font.BOLD, 11));
		
		JButton btnNewButton_2 = new JButton("Snore Meter");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Bi directional Streaming
				int port = 50053;
		        String host = "localhost";		
				//Use the serviceInfo to retrieve the host and port
//		        String host = serviceInfoSnoreMeter.getHostAddresses()[0];
//				int port = serviceInfoSnoreMeter.getPort();
				
				//Create a channel as a transport
				ManagedChannel channel2 = ManagedChannelBuilder.forAddress(host, port)
						.usePlaintext()                
						.build();
				
				// Bi directional Streaming
				// Created a snore meter service client (asynchronous)
				SnoreMeterServiceGrpc.SnoreMeterServiceStub snoreMeterClient = SnoreMeterServiceGrpc.newStub(channel2);

				CountDownLatch latch = new CountDownLatch(1);
				
				
				new Thread()
				{
				    public void run() {
				    	StreamObserver<SnoreMeterRequest> requestObserver = snoreMeterClient.snoreMeter(new StreamObserver<SnoreMeterResponse>() {
							
							@Override
									public void onNext(SnoreMeterResponse value) {
										// response from the server
										System.out.println(value.getSnoreAlert());
										 textResponse_3.append(value.getSnoreAlert() + "\n");
										
									}

									@Override
									public void onError(Throwable t) {
										latch.countDown();

									}

									@Override
									public void onCompleted() {
										// server completed sending data
										//this method will be called after onNext has been completed
										System.out.println("Server has completed sending us data");
										
									}
								});
						
						// we send 20 messages to our server (client streaming)
						for (int i = 0; i < 20; i++) {
							
							Random ran = new Random();
							int low = 50;
							int high = 90;
							int result = ran.nextInt(high-low) + low;
							requestObserver.onNext(SnoreMeterRequest.newBuilder()
									       .setNumber(result)
									       .build());
						}
								

				        // the client is done sending data
						requestObserver.onCompleted();

						try {
							latch.await(3, TimeUnit.SECONDS); //allows to the server to sent the response...we wait for it to terminate 
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				       
				    }
				}.start();
				
			}
		});
		btnNewButton_2.setFont(new Font("Arial", Font.BOLD, 11));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		JScrollPane scrollPane_3 = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frmSleepTrackerApp.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(270)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(270, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(65)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(userNameLabel, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
							.addGap(20)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(username, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
									.addGap(119))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
									.addContainerGap(193, Short.MAX_VALUE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
									.addGap(80)
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 332, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
									.addGap(80)
									.addComponent(scrollPane_1))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
									.addGap(80)
									.addComponent(scrollPane_2))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
									.addGap(80)
									.addComponent(scrollPane_3)))
							.addContainerGap(40, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(username, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)))
						.addComponent(userNameLabel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
					.addGap(265))
		);
		
		textResponse_3 = new JTextArea(20, 20);
		scrollPane_3.setViewportView(textResponse_3);
		
		textResponse_2 = new JTextArea(20, 20);
		scrollPane_2.setViewportView(textResponse_2);
		
		textResponse_1 = new JTextArea(20, 20);
		scrollPane_1.setViewportView(textResponse_1);
		
		textResponse = new JTextArea(20, 20);
		scrollPane.setViewportView(textResponse);
		frmSleepTrackerApp.getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		frmSleepTrackerApp.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Clear");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textResponse.setText(null);
				textResponse_1.setText(null);
				textResponse_2.setText(null);
				textResponse_3.setText(null);
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
	
		
	
	}
	
	

	//This method contains the code for creating events
	private void createEvents() {
		// TODO Auto-generated method stub
		
	}
}
