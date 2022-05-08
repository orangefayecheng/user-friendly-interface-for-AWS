package service;

import java.util.ArrayList;

import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.RebootInstancesRequest;
import com.amazonaws.services.ec2.model.RebootInstancesResult;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;
import com.amazonaws.services.ec2.model.TerminateInstancesResult;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import value_entity.MessageModelEC2;
import value_entity.MessageModelEC2Options;
import value_entity.MessageModelS3;

public class ec2optionsService {
	
	private AmazonEC2 ec2;
	
	public MessageModelEC2Options ec2Options(String optionName, String instance_id) {
		MessageModelEC2Options messagemodel  = new MessageModelEC2Options();
		AWSCredentialsProviderChain credentialsProvider = new AWSCredentialsProviderChain(
	            new InstanceProfileCredentialsProvider(),
	            new ProfileCredentialsProvider("default"));

	    ec2    = AmazonEC2ClientBuilder.standard().withCredentials(credentialsProvider).withRegion("us-east-2").build();
	    
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

			RebootInstancesResult response = ec2.rebootInstances(request);
			messagemodel.setStatus_code(1);  
			messagemodel.setMessage(instance_id + " is rebooted");
		}
		
		else {
			TerminateInstancesRequest request = new TerminateInstancesRequest()
                    .withInstanceIds(instance_id);

            TerminateInstancesResult response = ec2.terminateInstances(request);
            messagemodel.setStatus_code(1);  
    		messagemodel.setMessage(instance_id + " is terminated");
		}
        		
        	
		return messagemodel;
	}
	
}