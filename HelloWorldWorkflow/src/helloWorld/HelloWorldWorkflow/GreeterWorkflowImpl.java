package helloWorld.HelloWorldWorkflow;

import com.amazonaws.services.simpleworkflow.flow.core.Promise;

public class GreeterWorkflowImpl implements GreeterWorkflow {
   private GreeterActivitiesClient operations = new GreeterActivitiesClientImpl();

   public void greet() {
	 // If you pass a returned Promise to an activity client method or an asynchronous workflow method,
	 // it defers execution until object is ready.
     Promise<String> name = operations.getName();
     Promise<String> greeting = operations.getGreeting(name);
     operations.say(greeting);
   }
}