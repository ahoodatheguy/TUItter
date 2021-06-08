import tweepy
import json

# This class is for managing API keys 

class HandleKeys():
	def __init__(self):
		with open('keys.json', 'r') as keys_json:
			self.keys = json.load(keys_json)  # Get API keys from keys.json and assign it to self.keys

	def get_auth(self):
		auth = tweepy.OAuthHandler(self.keys['CONSUMER KEY'], self.keys['CONSUMER SECRET'])
		auth.set_access_token(self.keys['ACCESS TOKEN'], self.keys['ACCESS SECRET'])
		return auth
