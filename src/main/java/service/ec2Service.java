package service;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.CreateKeyPairRequest;
import com.amazonaws.services.ec2.model.DeleteKeyPairRequest;
import com.amazonaws.services.ec2.model.DescribeKeyPairsResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.KeyPairInfo;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import value_entity.MessageModelEC2;

public class ec2Service {
	
	private AmazonEC2 ec2;
	
	public MessageModelEC2 ec2Create(String instance_type, String key_name, String security_group, String regionName, String amiId) {
		MessageModelEC2 messagemodel  = new MessageModelEC2();
		AWSCredentialsProviderChain credentialsProvider = new AWSCredentialsProviderChain(
	            new ProfileCredentialsProvider("default"));

	    ec2    = AmazonEC2ClientBuilder.standard().withCredentials(credentialsProvider).withRegion(regionName).build();
	    //ami-0489c6c0a2c0b6281
	    
		RunInstancesRequest runInstancesRequest = new RunInstancesRequest()
        		  .withImageId(amiId)
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
	
	public List<Instance> listInstance(String peerRegion) {
		List<Instance> instanceList = new ArrayList<Instance>();
		AWSCredentialsProviderChain credentialsProvider = new AWSCredentialsProviderChain(
	            new ProfileCredentialsProvider("default"));

	    ec2    = AmazonEC2ClientBuilder.standard().withCredentials(credentialsProvider).withRegion(peerRegion).build();
	    
	    for (Reservation reservation : ec2.describeInstances().getReservations()) {
	    	for (Instance instance : reservation.getInstances()) {
	    		instanceList.add(instance);
	    	}
	    }
	    return instanceList;
	}
	
	public List<KeyPairInfo> listKeyPair(String peerRegion) {
		List<KeyPairInfo> keyPairList = new ArrayList<KeyPairInfo>();
		AWSCredentialsProviderChain credentialsProvider = new AWSCredentialsProviderChain(
	            new ProfileCredentialsProvider("default"));

	    ec2    = AmazonEC2ClientBuilder.standard().withCredentials(credentialsProvider).withRegion(peerRegion).build();
	    
	    DescribeKeyPairsResult response = ec2.describeKeyPairs();
	    
	    for (KeyPairInfo key_pair : response.getKeyPairs()) {
	    	keyPairList.add(key_pair);
	    }
	    return keyPairList;
	}
	
	public MessageModelEC2 createKeyPair(String keyOption, String keyPairName, String regionName) {
		MessageModelEC2 messagemodel  = new MessageModelEC2();
		AWSCredentialsProviderChain credentialsProvider = new AWSCredentialsProviderChain(
	            new ProfileCredentialsProvider("default"));

	    ec2    = AmazonEC2ClientBuilder.standard().withCredentials(credentialsProvider).withRegion(regionName).build();
	    
	    if (keyOption.equals("Create")) {
	    
		    CreateKeyPairRequest request = new CreateKeyPairRequest().withKeyName(keyPairName);
		    
		    ec2.createKeyPair(request);
		    
	    }
	    
	    else {
	    	
	    	DeleteKeyPairRequest request = new DeleteKeyPairRequest().withKeyName(keyPairName);

	    	ec2.deleteKeyPair(request);
	    	
	    }
	    
	    
	    
	    messagemodel.setStatus_code(1);  
		messagemodel.setMessage("KeyPair created with name: " + keyPairName);	
		return messagemodel;
	}
	
}