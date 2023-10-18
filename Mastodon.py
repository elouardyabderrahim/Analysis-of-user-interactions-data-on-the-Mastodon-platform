from mastodon import Mastodon;
import outils
mastodon = Mastodon(
    client_id=outils.CLIENT_ID,
    client_secret=outils.CLIENT_SECRET,
    access_token=outils.CLIENT_TOKEN,
    api_base_url="https://mastodon.social"
)

#get my account id
my_account = mastodon.account_verify_credentials()
print('this is the account id :',my_account.id)

# Replace "target_username" with the username you want to search for
account = mastodon.account_search("NanoRaptor")

if account:
    print("Account ID:", account[0].id)
else:
    print("User not found.")

