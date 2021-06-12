import easykeys
import npyscreen
import tweepy
import easykeys

class Homepage(npyscreen.FormWithMenus):
	def create(self):
		api = tweepy.API(easykeys.HandleKeys().get_auth())
		tl = api.home_timeline()
		for tweet in tl:
			self.add(npyscreen.TitleText, name = f'{tweet.user.screen_name}', value = f'{tweet.text}', editable=False)

class MainApp(npyscreen.NPSAppManaged):
	
	def onStart(self):
		self.addForm('MAIN', Homepage, name = 'Timeline')

if __name__ == '__main__':
	MainApp().run()
