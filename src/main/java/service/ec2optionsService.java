package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.RebootInstancesRequest;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;

import value_entity.MessageModelEC2Options;

public class ec2optionsService {
	
	private AmazonEC2 ec2;
	
	
	public MessageModelEC2Options ec2Options(String optionName, String instance_id, String regionName) throws FileNotFoundException {
		MessageModelEC2Options messagemodel  = new MessageModelEC2Options();
		
		File file = new File("/Users/aashishpokhrel/user-friendly-interface-for-AWS/src/test/key.txt");
		
		Scanner scan = new Scanner(file);
		String usermasterkey ="";
		String useraccesskey ="";
		
		while (scan.hasNextLine()) {
			usermasterkey = scan.nextLine();
			useraccesskey = scan.nextLine();
		}
		scan.close();
		
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(usermasterkey, useraccesskey);

	    ec2    = AmazonEC2ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion(regionName).build();
	    
		if (optionName.equals("Start")) {
			StartInstancesRequest request = new StartInstancesRequest()
				    .withInstanceIds(instance_id);

			ec2.startInstances(request);
			messagemodel.setStatus_code(1);  
			messagemodel.setMessage(instance_id + " is started");
		}
		
		else if (optionName.equals("Stop")) {
			StopInstancesRequest request = new StopInstancesRequest()
				    .withInstanceIds(instance_id);

			ec2.stopInstances(request);
			messagemodel.setStatus_code(1);  
			messagemodel.setMessage(instance_id + " is stopped");
		}
		
		else if (optionName.equals("Reboot")) {
			RebootInstancesRequest request = new RebootInstancesRequest()
				    .withInstanceIds(instance_id);

			ec2.rebootInstances(request);
			messagemodel.setStatus_code(1);  
			messagemodel.setMessage(instance_id + " is rebooted");
		}
		
		else {
			TerminateInstancesRequest request = new TerminateInstancesRequest()
                    .withInstanceIds(instance_id);

            ec2.terminateInstances(request);
            messagemodel.setStatus_code(1);  
    		messagemodel.setMessage(instance_id + " is terminated");
		}
        		
        	
		return messagemodel;
	}
	
}