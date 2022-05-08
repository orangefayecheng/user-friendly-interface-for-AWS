package service;

import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import value_entity.MessageModelS3;

public class s3Service {
	
	private AmazonS3 s3;
	
	public MessageModelS3 s3CreateBucket(String bucketName, String regionName) {
		MessageModelS3 messagemodel  = new MessageModelS3();
		AWSCredentialsProviderChain credentialsProvider = new AWSCredentialsProviderChain(
	            new InstanceProfileCredentialsProvider(),
	            new ProfileCredentialsProvider("default"));

	    s3    = AmazonS3ClientBuilder.standard().withCredentials(credentialsProvider).withRegion(regionName).build();
	    
		s3.createBucket(bucketName);
        		
        messagemodel.setStatus_code(1);  
		messagemodel.setMessage("S3 bucket created with name " + bucketName);	
		return messagemodel;
	}
	
}