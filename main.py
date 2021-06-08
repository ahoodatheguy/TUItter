import easykeys
from rich import console
import tweepy
import easykeys
from rich.console import Console


class MainApp():
	
	def __init__(self):
		self.api = tweepy.API(easykeys.HandleKeys().get_auth())
		self.console = Console()
	
	def menu(self):
		self.console.print('[bold]1 - [/bold] View Timeline')
		self.console.print('[bold]2 - [/bold] Compose Tweet')
		self.console.print('[bold]3 - [/bold] Exit Program')
		choice = input('Choose choice number: ')
		if choice == '1':
			self.view_timeline()
		elif choice == '2':
			self.compose_tweet()
		elif choice == '3':
			exit()

	def view_timeline(self):
		timeline = self.api.home_timeline()
		for tweet in timeline:
			self.console.print(f'[bold]@{tweet.user.screen_name}[/bold] - {tweet.text}')

	def compose_tweet(self):
		exit_check = False
		self.console.print('Type [bold]EXIT[/bold] to go back to menu. Type [bold]\\EXIT[/bold] to tweet EXIT')
		while not exit_check:
			print('Compose Tweet:', end=' ') 
			tweet = input()
			if tweet == 'EXIT':
				exit_check = True
			elif tweet == '\\EXIT':
				self.api.update_status('EXIT')
				print('Tweeted "EXIT"')
			else:
				self.api.update_status(tweet)
				print(f'Tweeted "{tweet}"')
		self.menu()

if __name__ == '__main__':
	MainApp().menu()
