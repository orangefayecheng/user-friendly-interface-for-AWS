package service;

import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.AcceptVpcPeeringConnectionRequest;
import com.amazonaws.services.ec2.model.CreateVpcPeeringConnectionRequest;
import com.amazonaws.services.ec2.model.CreateVpcPeeringConnectionResult;
import com.amazonaws.services.ec2.model.CreateVpcRequest;
import com.amazonaws.services.ec2.model.CreateVpcResult;
import com.amazonaws.services.ec2.model.Vpc;

import entity.user;
import util.Stringnull;
import value_entity.MessageModel;
import value_entity.MessageModelEC2;

public class vpcService {
	
	private AmazonEC2 ec2;
	
	public MessageModelEC2 vpcCreate(String cidrBlock, String regionName) {
		MessageModelEC2 messagemodel  = new MessageModelEC2();
		AWSCredentialsProviderChain credentialsProvider = new AWSCredentialsProviderChain(
	            new InstanceProfileCredentialsProvider(),
	            new ProfileCredentialsProvider("default"));

	    ec2    = AmazonEC2ClientBuilder.standard().withCredentials(credentialsProvider).withRegion(regionName).build();
	    
		CreateVpcRequest newVPC = new CreateVpcRequest("In");
		newVPC.setCidrBlock(cidrBlock);
		CreateVpcResult res = ec2.createVpc(newVPC);
		Vpc vp = res.getVpc();
		String vpcId = vp.getVpcId();
        messagemodel.setStatus_code(1);  
		messagemodel.setMessage("VPC created with id: " + vpcId);	
		return messagemodel;
	}
	
	public MessageModelEC2 vpcPeering(String peerId, String peerRegion, String vpcId) {
		MessageModelEC2 messagemodel  = new MessageModelEC2();
		AWSCredentialsProviderChain credentialsProvider = new AWSCredentialsProviderChain(
	            new InstanceProfileCredentialsProvider(),
	            new ProfileCredentialsProvider("default"));

	    ec2    = AmazonEC2ClientBuilder.standard().withCredentials(credentialsProvider).withRegion(peerRegion).build();
	    
	    CreateVpcPeeringConnectionRequest vpc = new CreateVpcPeeringConnectionRequest()
	    		.withPeerVpcId(peerId)
        		//.withPeerRegion(peerRegion)
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
	
}