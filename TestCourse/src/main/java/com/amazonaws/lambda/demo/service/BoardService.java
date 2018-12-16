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
		 Board newBoard = new Board();
		 newBoard.setBoardId(board.getBoardId());
		 newBoard.setCourseId(board.getCourseId());
		 mapper.save(newBoard);
			
			    
		 return newBoard;
	}
}
