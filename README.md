# chessbase_selenium_testing
Selenium testing website for SQUAT course assignment
Tested website: https://play.chessbase.com/en/

In order to make it work, copy your credentials on your account to tests/src/test/resources/secrets.json in the following format:
```json
{
    "email": "example@email.com",
    "password": "yourpassword",
    "username": "username",
    "cookies": {
        "cookie1": "cookie1",
        "AccountToken": "xyz",
        "AccountMode": "1",
        "AccountName64": "abc="
    }
}
```

You need to get the cookies from a logged in session to bypass antibot

