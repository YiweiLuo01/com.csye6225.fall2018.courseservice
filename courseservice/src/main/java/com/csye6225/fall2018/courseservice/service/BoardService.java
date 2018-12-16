package com.csye6225.fall2018.courseservice.service;
import java.util.*;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.courseservice.datamodel.DynamoDbConnector;
import com.csye6225.fall2018.courseservice.datamodel.Board;
import com.csye6225.fall2018.courseservice.datamodel.Course;

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
		HashMap<String, AttributeValue> map = new HashMap<String, AttributeValue>();
		map.put(":v1", new AttributeValue().withS(boardId));

		DynamoDBQueryExpression<Board> queryExpression = new DynamoDBQueryExpression<Board>()
				.withIndexName("boardId-index").withConsistentRead(false).withKeyConditionExpression("boardId = :v1")
				.withExpressionAttributeValues(map);

		List<Board> list = mapper.query(Board.class, queryExpression);
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
	public Board updateBoard(String boardId, Board newBoard) {
		HashMap<String, AttributeValue> map = new HashMap<String, AttributeValue>();
		map.put(":v1", new AttributeValue().withS(boardId));

		DynamoDBQueryExpression<Board> queryExpression = new DynamoDBQueryExpression<Board>()
				.withIndexName("boardId-index").withConsistentRead(false).withKeyConditionExpression("boardId = :v1")
				.withExpressionAttributeValues(map);

		List<Board> list = mapper.query(Board.class, queryExpression);

		Board oldBoard = null;

		if (list.size() != 0) {

			oldBoard = list.get(0);
			oldBoard.setBoardId(newBoard.getBoardId());
			//oldCourse.setStudentId(newCourse.getStudentId());
			oldBoard.setCourseId(newBoard.getCourseId());
			
			

			mapper.save(oldBoard);
			System.out.println("board updated.");
			System.out.println(oldBoard.toString());
		}

		return oldBoard;
	}

	// delete boardById
	public Board deleteBoardById(String boardId) {
//		List<Board> list = getBoardFromDynamoDB(boardId);
//		Board board = null;
//		if (list.size() != 0) {
//			board = list.get(0);
//			String deleteBId = board.getBoardId();
//			mapper.delete(board);
//
//			// we also need to delete corresponding announce in Announce entity
//			AnnounceService announce = new AnnounceService();
//			announce.deleteAnnouncementsByBoardId(deleteBId);
//
//			Board deletedBoard = mapper.load(Board.class, board.getId());
//			if (deletedBoard == null) {
//				System.out.println("Board is deleted.");
//				System.out.println(board.toString());
//			}
//		}
//
//		return board;
		HashMap<String, AttributeValue> map = new HashMap<String, AttributeValue>();
		map.put(":v1", new AttributeValue().withS(boardId));

		DynamoDBQueryExpression<Board> queryExpression = new DynamoDBQueryExpression<Board>()
				.withIndexName("boardId-index").withConsistentRead(false).withKeyConditionExpression("boardId = :v1")
				.withExpressionAttributeValues(map);

		List<Board> list = mapper.query(Board.class, queryExpression);
		Board boardToDelete = null;
		if (list.size() != 0) {
			boardToDelete = list.get(0);
			mapper.delete(boardToDelete);
			Course deletedCourse = mapper.load(Course.class, boardToDelete.getBoardId());

			if (deletedCourse == null) {
				System.out.println("board is deleted.");
				System.out.println(boardToDelete.toString());
			}
		}

		return boardToDelete;
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