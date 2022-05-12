package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.CreateKeyPairRequest;
import com.amazonaws.services.ec2.model.DeleteKeyPairRequest;
import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.DescribeKeyPairsResult;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.Image;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.KeyPairInfo;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import value_entity.MessageModelEC2;

public class ec2Service {
	
	private AmazonEC2 ec2;
	
	public MessageModelEC2 ec2Create(String instance_type, String key_name, String regionName, String amiId) throws FileNotFoundException {
		MessageModelEC2 messagemodel  = new MessageModelEC2();
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
	    //ami-0489c6c0a2c0b6281
	    
		RunInstancesRequest runInstancesRequest = new RunInstancesRequest()
        		  .withImageId(amiId)
        		  .withInstanceType(instance_type) 
        		  .withKeyName(key_name) 
        		  .withMinCount(1)
        		  .withMaxCount(1)
        		  .withSecurityGroups("default"); 
        String myInstanceId = ec2.runInstances(runInstancesRequest).getReservation().getInstances().get(0).getInstanceId();
        		
        messagemodel.setStatus_code(1);  
		messagemodel.setMessage("ec2 instance created with id: " + myInstanceId);	
		return messagemodel;
	}
	
	public List<Instance> listInstance(String peerRegion) throws FileNotFoundException {
		List<Instance> instanceList = new ArrayList<Instance>();
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

	    ec2    = AmazonEC2ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion(peerRegion).build();
	    
	    for (Reservation reservation : ec2.describeInstances().getReservations()) {
	    	for (Instance instance : reservation.getInstances()) {
	    		instanceList.add(instance);
	    	}
	    }
	    return instanceList;
	}
	
	public List<KeyPairInfo> listKeyPair(String peerRegion) throws FileNotFoundException {
		List<KeyPairInfo> keyPairList = new ArrayList<KeyPairInfo>();
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

	    ec2    = AmazonEC2ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion(peerRegion).build();
	    
	    DescribeKeyPairsResult response = ec2.describeKeyPairs();
	    
	    for (KeyPairInfo key_pair : response.getKeyPairs()) {
	    	keyPairList.add(key_pair);
	    }
	    return keyPairList;
	}
	
	public MessageModelEC2 createKeyPair(String keyOption, String keyPairName, String regionName) throws FileNotFoundException {
		MessageModelEC2 messagemodel  = new MessageModelEC2();
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
	
	public Collection<Image> listAmis(String regionName, String platform) throws FileNotFoundException {
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
	    
	    DescribeImagesRequest response = new DescribeImagesRequest().withFilters(new LinkedList<Filter>()).withOwners("amazon");
	    
		response.getFilters().add(new Filter().withName("architecture").withValues("x86_64"));
		    
		response.getFilters().add(new Filter().withName("description").withValues("*" + platform + "*"));
		    
		Collection<Image> images = ec2.describeImages(response).getImages();
		
		return images;
	}
	
}