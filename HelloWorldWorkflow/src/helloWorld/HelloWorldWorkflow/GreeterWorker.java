package helloWorld.HelloWorldWorkflow;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflowClient;
import com.amazonaws.services.simpleworkflow.flow.ActivityWorker;
import com.amazonaws.services.simpleworkflow.flow.WorkflowWorker;

public class GreeterWorker {
	public static void main(String[] args) throws Exception {
		ClientConfiguration config = new ClientConfiguration().withSocketTimeout(70 * 1000);

		String swfAccessId = "AKIAJJKH3L7AJTWLCMFQ";
		String swfSecretKey = "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";
		AWSCredentials awsCredentials = new BasicAWSCredentials(swfAccessId,swfSecretKey);

		AmazonSimpleWorkflow service = new AmazonSimpleWorkflowClient(awsCredentials, config);
		service.setEndpoint("https://swf.us-east-1.amazonaws.com");

		String domain = "somnath-poc";
		String taskListToPoll = "HelloWorldList";

		ActivityWorker aw = new ActivityWorker(service, domain, taskListToPoll);
		aw.addActivitiesImplementation(new GreeterActivitiesImpl());
		aw.start();

		WorkflowWorker wfw = new WorkflowWorker(service, domain, taskListToPoll);
		wfw.addWorkflowImplementationType(GreeterWorkflowImpl.class);
		wfw.start();
	}
}