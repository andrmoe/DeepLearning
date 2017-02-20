from __future__ import print_function
import httplib2
import os

from apiclient import discovery
from oauth2client import client
from oauth2client import tools
from oauth2client.file import Storage

import datetime

try:
    import argparse
    flags = argparse.ArgumentParser(parents=[tools.argparser]).parse_args()
except ImportError:
    flags = None

# If modifying these scopes, delete your previously saved credentials
# at ~/.credentials/calendar-python-quickstart.json
SCOPES = 'https://www.googleapis.com/auth/calendar'
CLIENT_SECRET_FILE = 'client_secret.json'
APPLICATION_NAME = 'DeepLearning'


def get_credentials():
    """Gets valid user credentials from storage.

    If nothing has been stored, or if the stored credentials are invalid,
    the OAuth2 flow is completed to obtain the new credentials.

    Returns:
        Credentials, the obtained credential.
    """
    home_dir = os.path.expanduser('~')
    credential_dir = os.path.join(home_dir, '.credentials')
    if not os.path.exists(credential_dir):
        os.makedirs(credential_dir)
    credential_path = os.path.join(credential_dir,
                                   'calendar-python-quickstart.json')

    store = Storage(credential_path)
    credentials = store.get()
    if not credentials or credentials.invalid:
        flow = client.flow_from_clientsecrets(CLIENT_SECRET_FILE, SCOPES)
        flow.user_agent = APPLICATION_NAME
        if flags:
            credentials = tools.run_flow(flow, store, flags)
        else: # Needed only for compatibility with Python 2.6
            credentials = tools.run(flow, store)
        print('Storing credentials to ' + credential_path)
    return credentials

def createEvent(start, end):
    credentials = get_credentials()
    http = credentials.authorize(httplib2.Http())
    service = discovery.build('calendar', 'v3', http=http)

    event = {
        'summary': 'Study Session',
        'description': 'Study using DeepLearning',
        'start': {
            'dateTime': '2017-05-28T09:00:00-07:00',
            'timeZone': 'Europe/Oslo',
        },
        'end': {
            'dateTime': '2015-05-28T17:00:00-07:00',
            'timeZone': 'Europe/Oslo',
        },
        'recurrence': [
            'RRULE:FREQ=WEEKLY;COUNT=2'
        ],
        'reminders': {
            'useDefault': False,
            'overrides': [
                {'method': 'email', 'minutes': 60},
                {'method': 'popup', 'minutes': 10},
            ],
        },
    }

    event['start']['dateTime'] = start.isoformat()
    event['end']['dateTime'] = end.isoformat()
    event = service.events().insert(calendarId='r8mu15s0t2a0p3fipn5gmjeom4@group.calendar.google.com', body=event).execute()
    print ('Event created: %s' % (event.get('htmlLink')))

def main():
    """Shows basic usage of the Google Calendar API.

    Creates a Google Calendar API service object and outputs a list of the next
    10 events on the user's calendar.
    """
    now = datetime.datetime.utcnow()

    s = [True, True, False, True, False, False, False]

    for day in range (7):
        if not s[day]:
            continue
        if (day < now.weekday()):
            date = now + datetime.timedelta(days=7-now.weekday()+day)
        else:
            date = now + datetime.timedelta(days=day-now.weekday())

        print(date)
        createEvent(date, date + datetime.timedelta(minutes=20))

if __name__ == '__main__':
    main()
