package com.amazonaws.lambda.demo.datamodel;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class DynamoDBConnector {

	static AmazonDynamoDB dynamoDb;

	public static void init() {
		if (dynamoDb == null) {
			DefaultAWSCredentialsProviderChain credentialsProvider = DefaultAWSCredentialsProviderChain.getInstance();
			credentialsProvider.getCredentials();

			dynamoDb = AmazonDynamoDBClientBuilder.standard().withCredentials(credentialsProvider)
					.withRegion("us-east-2").build();
			System.out.println("client created");
		}
	}
	public AmazonDynamoDB getClient() {
		return dynamoDb;
	}
}