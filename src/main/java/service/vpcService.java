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
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.AcceptVpcPeeringConnectionRequest;
import com.amazonaws.services.ec2.model.CreateVpcPeeringConnectionRequest;
import com.amazonaws.services.ec2.model.CreateVpcPeeringConnectionResult;
import com.amazonaws.services.ec2.model.CreateVpcRequest;
import com.amazonaws.services.ec2.model.CreateVpcResult;
import com.amazonaws.services.ec2.model.DeleteVpcRequest;
import com.amazonaws.services.ec2.model.DescribeVpcsResult;
import com.amazonaws.services.ec2.model.Vpc;

import value_entity.MessageModelEC2;

public class vpcService {
	
	private AmazonEC2 ec2;
	
	public MessageModelEC2 vpcCreate(String vpcId, String vpcOption, String cidrBlock, String regionName) throws FileNotFoundException {
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
	    
	    if (vpcOption.equals("Add")) {
	    
			CreateVpcRequest newVPC = new CreateVpcRequest("In");
			newVPC.setCidrBlock(cidrBlock);
			ec2.createVpc(newVPC);
			
	    }
	    
	    else {
	    	
	    	DeleteVpcRequest request = new DeleteVpcRequest().withVpcId(vpcId);
	    	
	    	ec2.deleteVpc(request);
	    	
	    }
        messagemodel.setStatus_code(1);  
		messagemodel.setMessage("VPC created or deleted with id: " + vpcId);	
		return messagemodel;
	}
	
	public MessageModelEC2 vpcPeering(String peerId, String peerRegion, String vpcId) throws FileNotFoundException {
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

	    ec2    = AmazonEC2ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion(peerRegion).build();
	    
	    CreateVpcPeeringConnectionRequest vpc = new CreateVpcPeeringConnectionRequest()
	    		.withPeerVpcId(peerId)
        		.withVpcId(vpcId);
	    
	    CreateVpcPeeringConnectionResult id = ec2.createVpcPeeringConnection(vpc);
        String id_string = id.toString();
        String vpcid = id_string.substring(id_string.length()-23, id_string.length()-2);
        
        AcceptVpcPeeringConnectionRequest accept = new AcceptVpcPeeringConnectionRequest()
           		.withVpcPeeringConnectionId(vpcid);
            
          ec2.acceptVpcPeeringConnection(accept);
          
          messagemodel.setStatus_code(1);  
  		messagemodel.setMessage("VPC connection created with id: " + id_string);	
  		return messagemodel;
	}
	
	public List<Vpc> listVpcs(String peerRegion) throws FileNotFoundException {
		List<Vpc> vpcList = new ArrayList<Vpc>();
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
	    
	    DescribeVpcsResult result = ec2.describeVpcs();
	    
	    for (Vpc vpc : result.getVpcs()) {
	    	vpcList.add(vpc);
	    }
	    return vpcList;
	}
	
}