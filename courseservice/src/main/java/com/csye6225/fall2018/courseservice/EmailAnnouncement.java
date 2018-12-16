package com.csye6225.fall2018.courseservice;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent.DynamodbStreamRecord;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.csye6225.fall2018.courseservice.service.BoardService;
import com.csye6225.fall2018.courseservice.service.CourseService;

public class EmailAnnouncement implements RequestHandler<DynamodbEvent, String>{
	private static AmazonSNS SNS_CLIENT = AmazonSNSClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
	
	@Override
	public String handleRequest(DynamodbEvent ddbEvent, Context context) {
//		System.out.println(ddbEvent.getRecords().size());
		
		for (DynamodbStreamRecord record : ddbEvent.getRecords()){
//			if(record.getEventName().equals("INSERT")) {
				//context.getLogger().log(record.getDynamodb().toString());
				// String boardId = record.getDynamodb().getNewImage().get("boardId").getS();
				// String topicArn = getTopicArnByBoardId(boardId);
			context.getLogger().log("record: " + record);
				String courseId = record.getDynamodb().getNewImage().get("courseId").getS();
				String topicArn = getTopicArnByCourseId(courseId);
				context.getLogger().log("topicArn: "+topicArn);
				String message = record.getDynamodb().getNewImage().get("announcementContent").getS();
				context.getLogger().log("message: " + message);
				sendEmailNotification(topicArn, message, "new announcement");
			}
//			sendEmailNotification("arn:aws:sns:us-east-1:599961460399:course006","test006","test");
//	           System.out.println(record.getEventID());
//	           System.out.println(record.getEventName());
//	           System.out.println(record.getDynamodb().toString()); 
//	        }
		
	    return "Successfully processed " + ddbEvent.getRecords().size() + " records.";
			
	}
	
	public static String createTopic(String topicName) {
		return SNS_CLIENT.createTopic(topicName).getTopicArn();
	}
	
	public static void subscribe(String topicArn, String email) {
		SNS_CLIENT.subscribe(topicArn, "email", email);
		System.out.println("Subscribe succesfully for email: " + email);
	}
	
	public void sendEmailNotification(String topicArn, final String message, final String subject) {
		// Message Object
		System.out.println("Begin to send email");
		PublishRequest publishRequest = new PublishRequest(topicArn, message, subject);
		System.out.println("After creating sending email request : "+publishRequest);
		SNS_CLIENT.publish(publishRequest);
		System.out.println("After publish");
	}
	
	private String getTopicArnByCourseId(String courseId) {
		CourseService courseSer = new CourseService();
		return courseSer.getCourseById(courseId).getSNSTopicArn();
	}
//	
//	private String getTopicArnByBoardId(String boardId) {
//		BoardService boardSer = new BoardService();
//		CourseService courseSer = new CourseService();
//		String courseId = boardSer.getBoardFromDynamoDB(boardId).get(0).getCourseId();
////		System.out.println(courseId);
////		System.out.println(courseSer.getCourseFromDDB(courseId).size());
//		return courseSer.getCourseFromDDB(courseId).get(0).getSNSTopicArn();
//	}
}