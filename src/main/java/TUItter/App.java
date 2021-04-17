package TUItter;

import java.util.Scanner;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
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
            System.out.println("Coming Soon!");
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
            System.out.print("Enter tweet:");
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
}
