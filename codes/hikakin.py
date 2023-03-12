import google.auth
from googleapiclient.discovery import build

# APIキーを指定してAPIクライアントを生成する
api_key = "your_api_key_here"
youtube = build("youtube", "v3", developerKey=api_key)

# HIKAKINさんのチャンネルIDを指定する
channel_id = "UCZf__ehlCEBPop-_sldpBUQ"

# チャンネルの動画情報を取得するためのリクエストを作成する
request = youtube.search().list(
    part="snippet",
    channelId=channel_id,
    maxResults=50  # 最大50件の動画情報を取得する
)

# 動画情報を取得し、タイトルを表示する
while request is not None:
    response = request.execute()

    for item in response["items"]:
        print(item["snippet"]["title"])

    request = youtube.search().list_next(request, response)
