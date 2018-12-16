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

public class EmailAnnouncement implements RequestHandler<DynamodbEvent, String> {
	private static AmazonSNS SNS_CLIENT = AmazonSNSClientBuilder.standard().withRegion(Regions.US_EAST_2).build();

	@Override
	public String handleRequest(DynamodbEvent ddbEvent, Context context) {

		for (DynamodbStreamRecord record : ddbEvent.getRecords()) {
			context.getLogger().log("record: " + record);
			String courseId = record.getDynamodb().getNewImage().get("courseId").getS();
			String topicArn = getTopicArnByCourseId(courseId);
			context.getLogger().log("topicArn: " + topicArn);
			String message = record.getDynamodb().getNewImage().get("announcementContent").getS();
			context.getLogger().log("message: " + message);
			sendEmailNotification(topicArn, message, "new announcement");
		}

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
		System.out.println("After creating sending email request : " + publishRequest);
		SNS_CLIENT.publish(publishRequest);
		System.out.println("After publish");
	}

	private String getTopicArnByCourseId(String courseId) {
		CourseService courseSer = new CourseService();
		return courseSer.getCourseById(courseId).getSNSTopicArn();
	}

}