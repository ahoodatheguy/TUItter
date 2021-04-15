import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        userInfo user = new userInfo();
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
          .setOAuthConsumerKey(user.consumerKey)
          .setOAuthConsumerSecret(user.consumerKeySecret)
          .setOAuthAccessToken(user.accessToken)
          .setOAuthAccessTokenSecret(user.accessTokenSecret);
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();


    Scanner input = new Scanner(System.in);
    while(true){
        String tweet;
        System.out.print("Enter tweet: ");
        tweet = input.nextLine();
        twitter.updateStatus(tweet);
    }
    }
}
