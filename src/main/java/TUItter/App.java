package TUItter;

import java.util.List;
import java.util.Scanner;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

public class App 
{
    public static void main( String[] args ){
        menu();
    }

    public static void menu(){
        Scanner input = new Scanner(System.in);
        System.out.println("1) Compose Tweet");
        System.out.println("2) View Timeline");
        System.out.print("Choice: ");
        int menuChoice = input.nextInt();

        switch(menuChoice){
            case 1:
                cTweet();
                break;
            case 2:
                viewTimeline();
            break;
            default:
            System.out.println("Invalid Choice!");
        }
    }

    public static void cTweet(){ //Compose Tweet method.
        Scanner input = new Scanner(System.in);
        String tweet;
        ConfigurationBuilder cb = new ConfigurationBuilder();
        userInfo user = new userInfo();
        cb.setDebugEnabled(true)
            .setOAuthConsumerKey(user.consumerKey)
            .setOAuthConsumerSecret(user.consumerKeySecret)
            .setOAuthAccessToken(user.accessToken)
            .setOAuthAccessTokenSecret(user.accessTokenSecret);
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        while(true){
            System.out.print("Enter tweet: ");
            tweet = input.nextLine();
                switch(tweet){
                    case "EXIT":
                        menu();
                        break;
                    default:
                    try {
                        twitter.updateStatus(tweet);
                    } catch (TwitterException e) {
                        System.out.println("An error occured.");
                        return;
                    } 

                }
        }
    }
    public static void viewTimeline(){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        userInfo user = new userInfo();
        cb.setDebugEnabled(true)
            .setOAuthConsumerKey(user.consumerKey)
            .setOAuthConsumerSecret(user.consumerKeySecret)
            .setOAuthAccessToken(user.accessToken)
            .setOAuthAccessTokenSecret(user.accessTokenSecret);
            TwitterFactory tf = new TwitterFactory(cb.build());

            Twitter twitter = tf.getInstance();
            try {
                //initializes user with creditials from userInfo class.
                User userC = twitter.verifyCredentials();
                List<Status> stati = twitter.getHomeTimeline(); //list of tweets from home timeline, also the plural of status is stati right?
                System.out.printf("Showing tweets from @%s's timeline", userC.getScreenName());
                System.out.println("\n----------------------------------------------------");
                for (Status status : stati) {
                    System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText() + "");
                }
            } catch (TwitterException te) {
                te.printStackTrace();
                System.out.printf("Failed to get timeline: " + te.getMessage());
                System.exit(-1);
            }
        }
}
