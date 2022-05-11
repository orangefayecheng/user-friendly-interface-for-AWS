package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import value_entity.MessageModelS3;

public class s3Service {
	
	private AmazonS3 s3;
	
	private AmazonS3 s3Client;
	
	
	public MessageModelS3 s3CreateBucket(String bucketName, String regionName) throws FileNotFoundException {
		MessageModelS3 messagemodel  = new MessageModelS3();
		

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

	    s3   = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion(regionName).build();
	    
		s3.createBucket(bucketName);
        		
        messagemodel.setStatus_code(1);  
		messagemodel.setMessage("S3 bucket created with name " + bucketName);	
		return messagemodel;
	}
	
	public List<String> s3ListBuckets(String regionName) throws FileNotFoundException {
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

	    s3   = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion(regionName).build();
		List<String> bucketList = new ArrayList<String>(); 
		for (Bucket bucket : s3.listBuckets()) {
			bucketList.add(bucket.getName());
		}
		return bucketList;
	}
	
	public List<String> s3ListObjects(String regionName, String bucketName) throws FileNotFoundException {
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

	    s3   = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion(regionName).build();
		List<String> objectList = new ArrayList<String>();
		String regionNaam = s3.getBucketLocation(bucketName);
		String getRegion = null;
		if (regionNaam == "US") {
			getRegion = "us-east-1";
		}
		else {
			getRegion = regionNaam;
		}
		s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion(getRegion).build();
		ObjectListing objectListing = s3Client.listObjects(new ListObjectsRequest().withBucketName(bucketName));
		for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
			objectList.add(objectSummary.getKey());
		}
		return objectList;
	}
	
	public MessageModelS3 s3AddOrDeleteObject(String bucketOption, String objectName, String bucketName, String filename, String filepath) throws FileNotFoundException {
		MessageModelS3 messagemodel = new MessageModelS3();
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

	    s3   = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-2").build();
		if (bucketOption.equals("Delete")) {
			s3.deleteObject(new DeleteObjectRequest(bucketName, objectName));
			messagemodel.setStatus_code(1);  
			messagemodel.setMessage("S3 bucket created with name " + bucketName);
		}
		else {
			s3.putObject(bucketName, filename, filepath);
			messagemodel.setStatus_code(1);  
			messagemodel.setMessage("S3 bucket deleted with name " + bucketName);
		}
		return messagemodel;
	}
	
}