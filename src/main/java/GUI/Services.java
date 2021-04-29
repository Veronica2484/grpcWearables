package GUI;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import service1.SleepPatternRequest;
import service1.SleepPatternResponse;
import service1.SleepPatternServiceGrpc;
import service1.WeeklySleepPatternRequest;
import service2.NoiseMonitorRequest;
import service2.NoiseMonitorResponse;
import service2.NoiseMonitorServiceGrpc;
import service3.SnoreMeterRequest;
import service3.SnoreMeterResponse;
import service3.SnoreMeterServiceGrpc;

import javax.swing.JLabel;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


public class Services extends JFrame {
	
	private ServiceInfo serviceInfo;
	private JFrame frmSleepTrackerApp;
	private static SleepPatternServiceGrpc.SleepPatternServiceBlockingStub SleepPatternClient;
	private JComboBox comboBox = new JComboBox();
	private JTextArea textResponse;
	private JTextArea textResponse_1;
	private JTextArea textResponse_2;
	private JTextArea textResponse_3;
	private ServiceInfo serviceInfoSleepPattern;
	private ServiceInfo serviceInfoNoiseMonitor;
	private ServiceInfo serviceInfoSnoreMeter;
	

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Services frame = new Services();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Services() {
		 
        
		initialize();
		createEvents();
		
	}
	
	protected void discoverSleepPatternService(String service_type) {
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
	
	

	
	//Create and initialize components
	private <WeeklySleepPatternResponse> void initialize() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\nika_\\eclipse-workspace\\grpcCA\\src\\main\\java\\GUI\\resources\\sleep.icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 722, 593);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Sleep Tracker Services");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		
		JButton btnNewButton = new JButton("Daily Sleep Pattern");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Unary
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
		
				textResponse.append(sleepPatternresponse.getUserName()+"\n" + "Your pattern of sleep is: " + sleepPatternresponse.getPattern() );
					
					
			}
		});
		
		JButton btnNewButton_3 = new JButton("Weekly Sleep Pattern");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Server Streaming
				int port = 50051;
		        String host = "localhost";
	        
//		        String host = serviceInfoSleepPattern.getHostAddresses()[0];
//				int port = serviceInfoSleepPattern.getPort();
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
		
		JButton btnNewButton_1 = new JButton("Noise Monitor");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Client Streaming
							   
				int port = 50052;
		        String host = "localhost";		
				
				//Create a channel as a transport
				ManagedChannel channel1 = ManagedChannelBuilder.forAddress(host, port)
						.usePlaintext()                
						.build();
				//call the Client streaming 
		    	// Client Streaming
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
										
										String noise = value.getNoisyValues();
										double average = value.getAverage();
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
		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JButton btnNewButton_2 = new JButton("Snore Meter");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int port = 50053;
		        String host = "localhost";		
				
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
		
		btnNewButton_2.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JLabel lblNewLabel_1 = new JLabel("Please select what service do you want to check");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));

		
		

		
		btnNewButton_3.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		JScrollPane scrollPane_3 = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(161)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(187, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(256, Short.MAX_VALUE)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
					.addGap(254))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnNewButton_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
						.addComponent(btnNewButton_3, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE))
					.addGap(82))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(73, Short.MAX_VALUE))
		);
		
		JTextArea textResponse_3_1 = new JTextArea();
		scrollPane_1.setViewportView(textResponse_3_1);
		
		
		textResponse = new JTextArea(20,20);
		scrollPane.setViewportView(textResponse);
		
		textResponse_1 = new JTextArea(20,20);
		scrollPane_1.setViewportView(textResponse_1);

		textResponse_2 = new JTextArea(20,20);
		scrollPane_2.setViewportView(textResponse_2);
		
		textResponse_3 = new JTextArea(20,20);
		scrollPane_3.setViewportView(textResponse_3);
		
		contentPane.setLayout(gl_contentPane);
	
	}
	
	//This method contains the code for creating events
		private void createEvents() {
			// TODO Auto-generated method stub
			
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			
			JMenu mnNewMenu = new JMenu("File");
			menuBar.add(mnNewMenu);
			
			JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
			mntmNewMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int conf = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit");
					if(conf==JOptionPane.YES_OPTION)
					System.exit(0);
				}
			});
			mnNewMenu.add(mntmNewMenuItem);
			
		}
}

