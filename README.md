# ucsb-cs56-anon-search-engine

# FALL 2018


Members:

Joshua Andrews, joshandrews43 

Kevin Mata, Mata17

Michael Zhou, MichaelZyh

Kevin Villanueva, VillanuevaK

Thomas Parker, tparker48

Kyle Stanfield, kylejstanfield

Matt Moss, matthewmoss

Website: https://ucsb-cs56-f18-anonsearchengine.herokuapp.com/

Summary:
  A web app that allows users to interact with well known search engines anonymously, which maintains their privacy and prevents search engines from collecting their data.

1)
BING SEARCH
Instructions on obtaining the Bing Search Key: https://docs.microsoft.com/en-us/azure/cognitive-services/cognitive-services-apis-create-account 
NOTE: After clicking AI + ML, click on Bing Search V7 on the right. This is the only part that differs in the tutorial. 

GOOGLE Search:
To get the Api Key
Go to https://developers.google.com/custom-search/v1/overview and under API key click on Get A Key.
To get a SearhEngine ID go to https://developers.google.com/custom-search/v1/overview
Click on the "Custom Search Engine Control panel"
Click on add to make a new serch engine and follow instructions.
Click on the "tutorial" for more instruciton on how to customize the search engine
You can click on the search engine and your Search Engine ID will be given

DUCK DUCK GO: No extra work required

2)
Copy the contents of localhost.json.SAMPLE into a new file called localhost.json and the contents of heroku.json.SAMPLE into a new file called heroku.json
You should have copied the following into the files
{
    "bingSubscriptionKey":"Enter the Bing subscription key here",
    "googleAPIKey":"Enter the Google API",
    "googleSearchEngineID":"Enter the Googe search engine ID"
}
Replace "Enter the Bing subscription key here" with the Bing key 
Replace "Enter the Google API" with the Google API key
Replace "Enter the Googe search engine ID" with the Google search engine key

If done correctly the app should run.
Summary: Get keys, put keys in JSON files.
