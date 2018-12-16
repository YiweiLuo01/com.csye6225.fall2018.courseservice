package com.csye6225.fall2018.courseservice.datamodel;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class DynamoDbConnector {

	static AmazonDynamoDB dynamoDb;

	public static void init() {

		if (dynamoDb == null) {
//			ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
			 DefaultAWSCredentialsProviderChain credentialsProvider = DefaultAWSCredentialsProviderChain.getInstance();
			credentialsProvider.getCredentials();

			dynamoDb = AmazonDynamoDBClientBuilder.standard().withCredentials(credentialsProvider)
					.withRegion("us-east-2").build();
			System.out.println("I created the client");
		}

	}

	public AmazonDynamoDB getClient() {
		return dynamoDb;
	}

}