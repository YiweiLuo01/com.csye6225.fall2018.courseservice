package com.csye6225.fall2018.courseservice.service;
import java.util.*;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.courseservice.datamodel.DynamoDbConnector;
import com.csye6225.fall2018.courseservice.datamodel.Board;

public class BoardService {
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper;

	public BoardService() {
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}

	//get all boards
	public List<Board> getAllBoards() {
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withIndexName("boardId-index")
				.withConsistentRead(false);

		List<Board> list = mapper.scan(Board.class, scanExpression);
		return list;
	}

	// get board by ID
	public Board getBoardById(String boardId) {
		List<Board> list = getBoardFromDynamoDB(boardId);
		return list.size() != 0 ? list.get(0) : null;
	}

	// add new board
	public Board addBoard(Board board) {
		Board newBoard = new Board(board.getBoardId(), board.getCourseId());
		mapper.save(newBoard);

		System.out.println("board added..");
		System.out.println(newBoard.toString());
		return newBoard;
	}

	// Update board
	public Board updateBoard(String boardId, Board board) {
		List<Board> list = getBoardFromDynamoDB(boardId);
		Board oldB = null;
		if (list.size() != 0) {
			oldB = list.get(0);
			oldB.setCourseId(board.getCourseId());
			mapper.save(oldB);
			System.out.println("old board update");
			System.out.println(oldB.toString());
		}
		return oldB;
	}

	// delete boardById
	public Board deleteBoardById(String boardId) {
		List<Board> list = getBoardFromDynamoDB(boardId);
		Board board = null;
		if (list.size() != 0) {
			board = list.get(0);
			String deleteBId = board.getBoardId();
			mapper.delete(board);

			// we also need to delete corresponding announce in Announce entity
			AnnounceService announce = new AnnounceService();
			announce.deleteAnnouncementsByBoardId(deleteBId);

			Board deletedBoard = mapper.load(Board.class, board.getId());
			if (deletedBoard == null) {
				System.out.println("Board is deleted.");
				System.out.println(board.toString());
			}
		}

		return board;
	}

	public List<Board> getBoardFromDynamoDB(String boardId) {
		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(boardId));

		DynamoDBQueryExpression<Board> queryExpression = new DynamoDBQueryExpression<Board>()
				.withIndexName("boardId-index").withConsistentRead(false).withKeyConditionExpression("boardId = :v1")
				.withExpressionAttributeValues(eav);

		List<Board> list = mapper.query(Board.class, queryExpression);
		return list;
	}

}