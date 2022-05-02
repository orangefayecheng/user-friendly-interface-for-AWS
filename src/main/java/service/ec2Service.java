package service;

import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.RunInstancesRequest;

import entity.user;
import util.Stringnull;
import value_entity.MessageModel;
import value_entity.MessageModelEC2;

public class ec2Service {
	
	private AmazonEC2 ec2;
	
	public MessageModelEC2 ec2Create(String instance_type, String key_name, String security_group) {
		MessageModelEC2 messagemodel  = new MessageModelEC2();
		AWSCredentialsProviderChain credentialsProvider = new AWSCredentialsProviderChain(
	            new InstanceProfileCredentialsProvider(),
	            new ProfileCredentialsProvider("default"));

	    ec2    = AmazonEC2ClientBuilder.standard().withCredentials(credentialsProvider).withRegion("us-east-2").build();
	    
		RunInstancesRequest runInstancesRequest = new RunInstancesRequest()
        		  .withImageId("ami-0489c6c0a2c0b6281")
        		  .withInstanceType(instance_type) 
        		  .withKeyName(key_name) 
        		  .withMinCount(1)
        		  .withMaxCount(1)
        		  .withSecurityGroups(security_group); 
        ec2.runInstances(runInstancesRequest);
        String myInstanceId = ec2.runInstances(runInstancesRequest).getReservation().getInstances().get(0).getInstanceId();
        		
        messagemodel.setStatus_code(1);  
		messagemodel.setMessage("ec2 instance created with id: " + myInstanceId);	
		return messagemodel;
	}
	
}