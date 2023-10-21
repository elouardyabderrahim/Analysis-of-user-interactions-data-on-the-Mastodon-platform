

from mastodon import Mastodon;
from hdfs import InsecureClient;
import outils
mastodon = Mastodon(
    client_id=outils.CLIENT_ID,
    client_secret=outils.CLIENT_SECRET,
    access_token=outils.CLIENT_TOKEN,
    api_base_url="https://mastodon.social"
)


# Function to handle errors
def handle_error(exception):
    print(f"An error occurred: {str(exception)}")

# Connect to HDFS
hdfs_client = InsecureClient('http://localhost:9870', user='abderrahim')
print("hdfs_client",hdfs_client)

try:
    # Retrieve a list of trending hashtags
    trending_tags = mastodon.trending_tags()

    # Iterate through the trending tags and fetch posts for each tag
    for trending_tag in trending_tags:
        hashtag = trending_tag['name']
        hashtag_posts = mastodon.timeline_hashtag(hashtag)
        print("hashtag_posts",hashtag_posts)
        # Process the posts for the current trending tag and extract information as needed
        for post in hashtag_posts:
            # TODO :
            post_id = post['id']
            user_id = post['account']['id']

            # Process the post and user information

            # Store the data in HDFS
            hdfs_directory = '/usr/local/hadoop/row'
            hdfs_file_name = 'data.json'

            with hdfs_client.write(f'{hdfs_directory}/{hdfs_file_name}', overwrite=True) as writer:
                # Write the data to the HDFS file
                data_to_write = f"Post ID: {post_id}, User ID: {user_id}"
                writer.write(data_to_write)
                print("writing is done!!")
   
except Exception as e:
    handle_error(e)


