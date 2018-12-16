package com.amazonaws.lambda.demo.service;

import com.amazonaws.lambda.demo.datamodel.Board;
import com.amazonaws.lambda.demo.datamodel.DynamoDBConnector;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;


public class BoardService {
	static DynamoDBConnector dynamoDb;
	DynamoDBMapper mapper; 
	
	public BoardService(){
		dynamoDb = new DynamoDBConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	
	public Board addBoard(Board board) {
		 Board board2 = new Board();
		 board2.setBoardId(board.getBoardId());
		 board2.setCourseId(board.getCourseId());
		 mapper.save(board2);
			
			    
		 return board2;
	}
}