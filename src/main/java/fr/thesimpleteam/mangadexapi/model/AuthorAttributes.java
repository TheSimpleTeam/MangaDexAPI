/*
 * MangaDex API
 * MangaDex is an ad-free manga reader offering high-quality images!  This document details our API as it is right now. It is in no way a promise to never change it, although we will endeavour to publicly notify any major change.  # Acceptable use policy  Usage of our services implies acceptance of the following: - You should credit us - You shouldn't run ads on your website  These may change at any time for any and no reason and it is up to you check for updates from time to time.  # Authentication  You can login with the `/auth/login` endpoint. On success, it will return a JWT that remains valid for 15 minutes along with a session token that allows refreshing without re-authenticating for 1 month.  # Rate limits  The API enforces rate-limits to protect our servers against malicious and/or mistaken use. The API keeps track of the requests on an IP-by-IP basis. Hence, if you're on a VPN, proxy or a shared network in general, the requests of other users on this network might affect you.  At first, a **global limit of 5 requests per second per IP address** is in effect.  > This limit is enforced across multiple load-balancers, and thus is not an exact value but rather a lower-bound that we guarantee. The exact value will be somewhere in the range `[5, 5*n]` (with `n` being the number of load-balancers currently active). The exact value within this range will depend on the current traffic patterns we are experiencing.  On top of this, **some endpoints are further restricted** as follows:  | Endpoint                           | Requests per time period    | Time period in minutes | |------------------------------------|--------------------------   |------------------------| | `POST   /account/create`           | 5                           | 60                     | | `POST   /account/activate/{code}`  | 30                          | 60                     | | `POST   /account/activate/resend`  | 5                           | 60                     | | `POST   /account/recover`          | 5                           | 60                     | | `POST   /account/recover/{code}`   | 5                           | 60                     | | `POST   /auth/login`               | 30                          | 60                     | | `POST   /auth/refresh`             | 60                          | 60                     | | `POST   /author`                   | 10                          | 60                     | | `PUT    /author`                   | 10                          | 1                      | | `DELETE /author/{id}`              | 10                          | 10                     | | `POST   /captcha/solve`            | 10                          | 10                     | | `POST   /chapter/{id}/read`        | 300                         | 10                     | | `PUT    /chapter/{id}`             | 10                          | 1                      | | `DELETE /chapter/{id}`             | 10                          | 1                      | | `POST   /manga`                    | 10                          | 60                     | | `PUT    /manga/{id}`               | 10                          | 60                     | | `DELETE /manga/{id}`               | 10                          | 10                     | | `POST   /manga/draft/{id}/commit`  | 10                          | 60                     | | `POST   /cover`                    | 10                          | 1                      | | `PUT    /cover/{id}`               | 10                          | 1                      | | `DELETE /cover/{id}`               | 10                          | 10                     | | `POST   /group`                    | 10                          | 60                     | | `PUT    /group/{id}`               | 10                          | 1                      | | `DELETE /group/{id}`               | 10                          | 10                     | | `GET    /at-home/server/{id}`      | 40                          | 1                      | | `POST   /report`                   | 10                          | 1                      | | `POST   /upload/begin`             | 30                          | 1                      | | `POST   /upload/begin/{id}`        | 30                          | 1                      | | `DELETE /user/{id}`                | 5                           | 60                     |  Calling these endpoints will further provide details via the following headers about your remaining quotas:  | Header                    | Description                                                                 | |---------------------------|-----------------------------------------------------------------------------| | `X-RateLimit-Limit`       | Maximal number of requests this endpoint allows per its time period         | | `X-RateLimit-Remaining`   | Remaining number of requests within your quota for the current time period  | | `X-RateLimit-Retry-After` | Timestamp of the end of the current time period, as UNIX timestamp          |  # Result Limit  Most of our listing endpoints will return a maximum number of total results that is currently capped at 10.000 items. Beyond that you will not receive any more items no matter how far you paginate and the results will become empty instead. This is for performance reasons and a limitation we will not lift.  Note that the limit is applied to a search query and list endpoints with or without any filters are search queries. If you need to retrieve more items, use filters to narrow down your search.  # Reference Expansion  Reference Expansion is a feature of certain endpoints where relationships of a resource are expanded with their attributes, which reduces the amount of requests that need to be sent to the API to retrieve a complete set of data.  It works by appending a list of includes to the query with the type names from the relationships. If an endpoint supports this feature is indicated by the presence of the optional `includes` parameter.  ## Example  To fetch a specific manga with `author`, `artist` and `cover_art` expanded, you can send the following request: `GET /manga/f9c33607-9180-4ba6-b85c-e4b5faee7192?includes[]=author&includes[]=artist&includes[]=cover_art`. You will now find the objects attributes inside the returned relationships array.  ## Note  Your current user needs `*.view` permission on each type of reference you want to expand. Guests have most of these permissions and for logged-in user accounts you can check `GET /auth/check` to list all permissions you have been granted. For example, to be able to expand `cover_art`, you need to have been granted the `cover.view` permission, for `author` and `artist` types you need the `author.view` permission and so on. If a reference can't be expanded, the request will still succeed and no error indication will be visible.  # Captchas  Some endpoints may require captchas to proceed, in order to slow down automated malicious traffic. Legitimate users might also be affected, based on the frequency of write requests or due certain endpoints being particularly sensitive to malicious use, such as user signup.  Once an endpoint decides that a captcha needs to be solved, a 403 Forbidden response will be returned, with the error code `captcha_required_exception`. The sitekey needed for recaptcha to function is provided in both the `X-Captcha-Sitekey` header field, as well as in the error context, specified as `siteKey` parameter.  The captcha result of the client can either be passed into the repeated original request with the `X-Captcha-Result` header or alternatively to the `POST /captcha/solve` endpoint. The time a solved captcha is remembered varies across different endpoints and can also be influenced by individual client behavior.  Authentication is not required for the `POST /captcha/solve` endpoint, captchas are tracked both by client ip and logged in user id. If you are logged in, you want to send the session token along, so you validate the captcha for your client ip and user id at the same time, but it is not required.  # Reading a chapter using the API  ## Retrieving pages from the MangaDex@Home network  A valid [MangaDex@Home network](https://mangadex.network) page URL is in the following format: `{server-specific base url}/{temporary access token}/{quality mode}/{chapter hash}/{filename}`  There are currently 2 quality modes: - `data`: Original upload quality - `data-saver`: Compressed quality  Upon fetching a chapter from the API, you will find 4 fields necessary to compute MangaDex@Home page URLs:  | Field                        | Type     | Description                       | |------------------------------|----------|-----------------------------------| | `.data.id`                   | `string` | API Chapter ID                    | | `.data.attributes.hash`      | `string` | MangaDex@Home Chapter Hash        | | `.data.attributes.data`      | `array`  | data quality mode filenames       | | `.data.attributes.dataSaver` | `array`  | data-saver quality mode filenames |  Example ```json GET /chapter/{id}  {   ...,   \"data\": {     \"id\": \"e46e5118-80ce-4382-a506-f61a24865166\",     ...,     \"attributes\": {       ...,       \"hash\": \"e199c7d73af7a58e8a4d0263f03db660\",       \"data\": [         \"x1-b765e86d5ecbc932cf3f517a8604f6ac6d8a7f379b0277a117dc7c09c53d041e.png\",         ...       ],       \"dataSaver\": [         \"x1-ab2b7c8f30c843aa3a53c29bc8c0e204fba4ab3e75985d761921eb6a52ff6159.jpg\",         ...       ]     }   } } ```  From this point you miss only the base URL to an assigned MangaDex@Home server for your client and chapter. This is retrieved via a `GET` request to `/at-home/server/{ chapter .data.id }`.  Example: ```json GET /at-home/server/e46e5118-80ce-4382-a506-f61a24865166  {   \"baseUrl\": \"https://abcdefg.hijklmn.mangadex.network:12345/some-token\" } ```  The full URL is the constructed as follows ``` { server .baseUrl }/{ quality mode }/{ chapter .data.attributes.hash }/{ chapter .data.attributes.{ quality mode }.[*] }  Examples  data quality: https://abcdefg.hijklmn.mangadex.network:12345/some-token/data/e199c7d73af7a58e8a4d0263f03db660/x1-b765e86d5ecbc932cf3f517a8604f6ac6d8a7f379b0277a117dc7c09c53d041e.png        base url: https://abcdefg.hijklmn.mangadex.network:12345/some-token   quality mode: data   chapter hash: e199c7d73af7a58e8a4d0263f03db660       filename: x1-b765e86d5ecbc932cf3f517a8604f6ac6d8a7f379b0277a117dc7c09c53d041e.png   data-saver quality: https://abcdefg.hijklmn.mangadex.network:12345/some-token/data-saver/e199c7d73af7a58e8a4d0263f03db660/x1-ab2b7c8f30c843aa3a53c29bc8c0e204fba4ab3e75985d761921eb6a52ff6159.jpg        base url: https://abcdefg.hijklmn.mangadex.network:12345/some-token   quality mode: data-saver   chapter hash: e199c7d73af7a58e8a4d0263f03db660       filename: x1-ab2b7c8f30c843aa3a53c29bc8c0e204fba4ab3e75985d761921eb6a52ff6159.jpg ```  If the server you have been assigned fails to serve images, you are allowed to call the `/at-home/server/{ chapter id }` endpoint again to get another server.  Whether successful or not, **please do report the result you encountered as detailed below**. This is so we can pull the faulty server out of the network.  # Manga Creation  Similar to how the Chapter Upload works, after a Mangas creation with the Manga Create endpoint it is in a \"draft\" state, needs to be submitted (committed) and get either approved or rejected by Staff.  The purpose of this is to force at least one CoverArt uploaded for the Manga Draft and to discourage troll entries. You can use the list-drafts endpoint to investigate the status of your submitted Manga Drafts. Rejected Drafts are occasionally cleaned up at an irregular interval. You can edit Drafts at any time, even after they have been submitted.  Because a Manga that is in the draft state is not available through the search, there are slightly different endpoints to list or retrieve Manga Drafts, but outside from that the schema is identical to a Manga that is published.  # Language Codes & Localization  To denote Chapter Translation language, translated fields such as Titles and Descriptions, the API expects a 2-letter language code in accordance with the [ISO 639-1 standard](https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes). Additionally, some cases require the [5-letter extension](https://en.wikipedia.org/wiki/IETF_language_tag) if the alpha-2 code is not sufficient to determine the correct sub-type of a language, in the style of $language-$region, e.g. `zh-hk` or `pt-br`.  Because there is no standardized method of denoting romanized translations, we chose to append the `-ro` suffix. For example the romanized version of `五等分の花嫁` is `5Toubun no Hanayome` or `Gotoubun no Hanayome`. Both would have the `ja-ro` language code, alternative versions are inserted as alternative titles. This is a clear distinction from the localized `en` translation `The Quintessential Quintuplets`  Notable exceptions are in the table below, otherwise ask a staff member if unsure.  | alpha-5 | Description            | |---------|------------------------| | `zh`    | Simplified Chinese     | | `zh-hk` | Traditional Chinese    | | `pt-br` | Brazilian Portugese    | | `es`    | Castilian Spanish      | | `es-la` | Latin American Spanish | | `ja-ro` | Romanized Japanese     | | `ko-ro` | Romanized Korean       | | `zh-ro` | Romanized Chinese      |  # Report  In order to keep track of the health of the servers in the network and to improve the quality of service and reliability, we ask that you call the MangaDex@Home report endpoint after each image you retrieve, whether successfully or not.  It is a `POST` request against `https://api.mangadex.network/report` and expects the following payload with our example above:  | Field                       | Type       | Description                                                                         | |-----------------------------|------------|-------------------------------------------------------------------------------------| | `url`                       | `string`   | The full URL of the image                                                           | | `success`                   | `boolean`  | Whether the image was successfully retrieved                                        | | `cached `                   | `boolean`  | `true` iff the server returned an `X-Cache` header with a value starting with `HIT` | | `bytes`                     | `number`   | The size in bytes of the retrieved image                                            | | `duration`                  | `number`   | The time in miliseconds that the complete retrieval (not TTFB) of this image took   |  Examples herafter.  **Success:** ```json POST https://api.mangadex.network/report Content-Type: application/json  {   \"url\": \"https://abcdefg.hijklmn.mangadex.network:12345/some-token/data/e199c7d73af7a58e8a4d0263f03db660/x1-b765e86d5ecbc932cf3f517a8604f6ac6d8a7f379b0277a117dc7c09c53d041e.png\",   \"success\": true,   \"bytes\": 727040,   \"duration\": 235,   \"cached\": true } ```  **Failure:** ```json POST https://api.mangadex.network/report Content-Type: application/json  {  \"url\": \"https://abcdefg.hijklmn.mangadex.network:12345/some-token/data/e199c7d73af7a58e8a4d0263f03db660/x1-b765e86d5ecbc932cf3f517a8604f6ac6d8a7f379b0277a117dc7c09c53d041e.png\",  \"success\": false,  \"bytes\": 25,  \"duration\": 235,  \"cached\": false } ```  While not strictly necessary, this helps us monitor the network's healthiness, and we appreciate your cooperation towards this goal. If no one reports successes and failures, we have no way to know that a given server is slow/broken, which eventually results in broken image retrieval for everyone.  # Retrieving Covers from the API  ## Construct Cover URLs  ### Source (original/best quality)  `https://uploads.mangadex.org/covers/{ manga.id }/{ cover.filename }`<br/> The extension can be png, jpeg or gif.  Example: `https://uploads.mangadex.org/covers/8f3e1818-a015-491d-bd81-3addc4d7d56a/4113e972-d228-4172-a885-cb30baffff97.jpg`  ### <=512px wide thumbnail  `https://uploads.mangadex.org/covers/{ manga.id }/{ cover.filename }.512.jpg`<br/> The extension is always jpg.  Example: `https://uploads.mangadex.org/covers/8f3e1818-a015-491d-bd81-3addc4d7d56a/4113e972-d228-4172-a885-cb30baffff97.jpg.512.jpg`  ### <=256px wide thumbnail  `https://uploads.mangadex.org/covers/{ manga.id }/{ cover.filename }.256.jpg`<br/> The extension is always jpg.  Example: `https://uploads.mangadex.org/covers/8f3e1818-a015-491d-bd81-3addc4d7d56a/4113e972-d228-4172-a885-cb30baffff97.jpg.256.jpg`  ## ℹ️ Where to find Cover filename ?  Look at the [Get cover operation](#operation/get-cover) endpoint to get Cover information. Also, if you get a Manga resource, you'll have, if available a `covert_art` relationship which is the main cover id.  # Static data  ## Manga publication demographic  | Value            | Description               | |------------------|---------------------------| | shounen          | Manga is a Shounen        | | shoujo           | Manga is a Shoujo         | | josei            | Manga is a Josei          | | seinen           | Manga is a Seinen         |  ## Manga status  | Value            | Description               | |------------------|---------------------------| | ongoing          | Manga is still going on   | | completed        | Manga is completed        | | hiatus           | Manga is paused           | | cancelled        | Manga has been cancelled  |  ## Manga reading status  | Value            | |------------------| | reading          | | on_hold          | | plan\\_to\\_read   | | dropped          | | re\\_reading      | | completed        |  ## Manga content rating  | Value            | Description               | |------------------|---------------------------| | safe             | Safe content              | | suggestive       | Suggestive content        | | erotica          | Erotica content           | | pornographic     | Pornographic content      |  ## CustomList visibility  | Value            | Description               | |------------------|---------------------------| | public           | CustomList is public      | | private          | CustomList is private     |  ## Relationship types  | Value            | Description                    | |------------------|--------------------------------| | manga            | Manga resource                 | | chapter          | Chapter resource               | | cover_art        | A Cover Art for a manga `*`    | | author           | Author resource                | | artist           | Author resource (drawers only) | | scanlation_group | ScanlationGroup resource       | | tag              | Tag resource                   | | user             | User resource                  | | custom_list      | CustomList resource            |  `*` Note, that on manga resources you get only one cover_art resource relation marking the primary cover if there are more than one. By default this will be the latest volume's cover art. If you like to see all the covers for a given manga, use the cover search endpoint for your mangaId and select the one you wish to display.  ## Manga links data  In Manga attributes you have the `links` field that is a JSON object with some strange keys, here is how to decode this object:  | Key   | Related site  | URL                                                                                           | URL details                                                    | |-------|---------------|-----------------------------------------------------------------------------------------------|----------------------------------------------------------------| | al    | anilist       | https://anilist.co/manga/`{id}`                                                               | Stored as id                                                   | | ap    | animeplanet   | https://www.anime-planet.com/manga/`{slug}`                                                   | Stored as slug                                                 | | bw    | bookwalker.jp | https://bookwalker.jp/`{slug}`                                                                | Stored has \"series/{id}\"                                       | | mu    | mangaupdates  | https://www.mangaupdates.com/series.html?id=`{id}`                                            | Stored has id                                                  | | nu    | novelupdates  | https://www.novelupdates.com/series/`{slug}`                                                  | Stored has slug                                                | | kt    | kitsu.io      | https://kitsu.io/api/edge/manga/`{id}` or https://kitsu.io/api/edge/manga?filter[slug]={slug} | If integer, use id version of the URL, otherwise use slug one  | | amz   | amazon        | N/A                                                                                           | Stored as full URL                                             | | ebj   | ebookjapan    | N/A                                                                                           | Stored as full URL                                             | | mal   | myanimelist   | https://myanimelist.net/manga/{id}                                                            | Store as id                                                    | | cdj   | CDJapan       | N/A                                                                                           | Stored as full URL                                             | | raw   | N/A           | N/A                                                                                           | Stored as full URL, untranslated stuff URL (original language) | | engtl | N/A           | N/A                                                                                           | Stored as full URL, official english licenced URL              |  ## Manga related enum  This data is used in the \"related\" field of a Manga relationships  | Value            | |------------------| | monochrome       | | main_story       | | adapted_from     | | based_on         | | prequel          | | side_story       | | doujinshi        | | same_franchise   | | shared_universe  | | sequel           | | spin_off         | | alternate_story  | | preserialization | | colored          | | serialization    |  # Chapter Upload  ## In A Nutshell  To upload a chapter, you need to start an upload-session, upload files to this session and once done, commit the session with a ChapterDraft. Uploaded Chapters will generally be put into a queue for staff approval and image processing before it is available to the public.  ## Limits  - 1 Active Upload Session per user. Before opening a second session, either commit or abandon your current session - 10 files per one PUT request is max - 500 files per upload session is max - 20 MB max uploaded session filesize - 150 MB max total sum of all uploaded session filesizes - Allowed file extensions: jpg, jpeg, png, gif - Image must fit into max resolution of 10000x10000 px  ## Example  You need to be logged in for any upload operation. Which Manga you're allowed to upload to and which contributing Scanlation Groups you're free to credit depend on your individual user state.  To start an upload session, we pick the manga-id we want to upload to and the group-ids we have to credit. We use the official test manga `f9c33607-9180-4ba6-b85c-e4b5faee7192` and the group \"Unknown\" with id `145f9110-0a6c-4b71-8737-6acb1a3c5da4`. If no group can be credited, we would not send any group-id at all, otherwise we can credit up to 5 groups. Note that crediting all involved groups is mandatory, doing otherwise will lead to a rejection of your upload.  The first step is optional, but because only one upload session is allowed per user, we check if we have any open upload sessions by doing `GET /upload`. We expect a 404 response with error detail 'No upload session found'.  Next step is to begin our upload session. We send a `POST /upload/begin` with json data. (If you want to edit an existing chapter, append the chapter id after it `POST /upload/begin/db99d333-76e9-4e66-9c97-4831c43ac96c` with its version as the json payload)  Request: ```json {   'manga' => 'f9c33607-9180-4ba6-b85c-e4b5faee7192',   'groups': [     '145f9110-0a6c-4b71-8737-6acb1a3c5da4'   ] } ``` Response: ```json {   'result': 'ok',   'data': {     'id': '113b7724-dcc2-4fbc-968f-9d775fcb1cd6',     'type': 'upload_session',     'attributes': {       'isCommitted': false,       'isProcessed': false,       'isDeleted': false     },     'relationships': [       {         'id': '41ce3e1a-8325-45b5-af8e-06aaf648a0df',         'type': 'user'       },       {         'id': 'f9c33607-9180-4ba6-b85c-e4b5faee7192',         'type': 'manga'       },       {         'id': '145f9110-0a6c-4b71-8737-6acb1a3c5da4',         'type': 'scanlation_group'       }     ]   } } ```  the `data.id` is what you want to store because you will need it for the following steps. We will refer to it as the `uploadSessionId` from here on out.  Remember the `GET /upload` request from the beginning? Try it again and you will see that it will return the same uploadSessionId. You can only have one upload session per user until you commit or abandon it, which makes it easy for you to continue uploading at a later time.  Now that we have a `uploadSessionId`, we can upload images. Any .jpg, .jpeg, .png or .gif files are fine, archives like .zip, .cbz or .rar are not. You will have to extract those archives beforehand if you want to make this work.  For each file, send a POST request like `POST /upload/{uploadSessionId}` with the image data. FormData seems to work best with `Content-Type: multipart/form-data; boundary=boundary`, mileage might vary depending on your programming language. Join our discord and ask for advice if in doubt.  You can upload a number of files in a single request (currently max. 10). The response body will be successful (response.result == 'ok') but might also contain errors if one or more files failed to validate. It's up to you to handle bad uploads and retry or reupload as you see fit. Successful uploads will be returned in the data array as type `upload_session_file`  A successful response could look like this: ```json {   'result': 'ok',   'errors': [],   'data': [     {       'id': '12cc211a-c3c3-4f64-8493-f26f9b98c6f6',       'type': 'upload_session_file',       'attributes': {         'originalFileName': 'testimage1.png',         'fileHash': 'bbf9b9548ee4605c388acb09e8ca83f625e5ff8e241f315eab5291ebd8049c6f',         'fileSize': 18920,         'mimeType': 'image/png',         'version': 1       }     }   ] } ``` Store the data[{index}].id attribute as the `uploadSessionFileId`, this will be the unique identifier for the file you just uploaded.  If you change your mind and want to remove a previously uploaded image, you can send a request like `DELETE /upload/{uploadSessionId}/{uploadSessionFileId}`, expecting a response like ```json {   'response': 'ok' } ```  Finally you can commit your upload session. We opened with a manga-id and group-ids but what we actually want is to upload a chapter. For that we have to build a payload consisting of two things: a chapterDraft and a pageOrder. The payload will look similar to the following:  ```json {   'chapterDraft': {     'volume': '1',     'chapter': '2.5',     'title': 'Read Online',     'translatedLanguage': 'en'   },   'pageOrder': [       '12cc211a-c3c3-4f64-8493-f26f9b98c6f6'   ] } ```  the `chapterDraft` is the chapter data you would like to create, the pageOrder is an ordered list of uploadSessionFileIds you uploaded earlier.  Order didnt matter, now it does. Any files you uploaded but do not specify in this pageOrder array will be deleted.  An example response is: ```json {   'result': 'ok',   'data': {     'id': '14d4639b-5a8f-4f42-a277-b222412930ca',     'type': 'chapter',     'attributes': {       'volume': '1',       'chapter': '2.5',       'title': 'Read Online',       'translatedLanguage': 'en',       'hash': '',       'data': [],       'dataSaver': [],       'publishAt': null,       'createdAt': '2021-06-16T00:40:22+00:00',       'updatedAt': '2021-06-16T00:40:22+00:00',       'version': 1     },     'relationships': [       {         'id': '145f9110-0a6c-4b71-8737-6acb1a3c5da4',         'type': 'scanlation_group'       },       {         'id': 'f9c33607-9180-4ba6-b85c-e4b5faee7192',         'type': 'manga'       },       {         'id': '41ce3e1a-8325-45b5-af8e-06aaf648a0df',         'type': 'user'       }     ]   } } ```  You just uploaded a chapter. Congratz!  The returned chapter has empty data and dataSaver attributes where otherwise the pages would be. This is because the image processing happens asynchroniously. Depending on how many chapters need to be processed at a given time, it might take a while for this to be updated.  The first time you upload a chapter in a language you didn't upload before, it will have to be approved by staff. Until both imageprocessing and possible approval have happened, your chapter will be held back and not appear on the website or be found in the list and search endpoints.  As mentioned in the beginning, to edit a chapter use the `POST /upload/begin/{chapterId}` endpoint [`begin-edit-session`](#operation/begin-edit-session) with the current chapter version as the json POST-body payload, and the UploadSession will come pre-filled with the remote existing UploadSessionFiles which you can reorder, remove, upload new images and commit your changes afterward as if it was a new chapter.
 *
 * The version of the OpenAPI document: 5.3.12
 * Contact: support@mangadex.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.thesimpleteam.mangadexapi.model;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * AuthorAttributes
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-11-21T19:33:31.304532600+01:00[Europe/Paris]")
public class AuthorAttributes {
  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name;

  public static final String SERIALIZED_NAME_IMAGE_URL = "imageUrl";
  @SerializedName(SERIALIZED_NAME_IMAGE_URL)
  private String imageUrl;

  public static final String SERIALIZED_NAME_BIOGRAPHY = "biography";
  @SerializedName(SERIALIZED_NAME_BIOGRAPHY)
  private Map<String, String> biography = null;

  public static final String SERIALIZED_NAME_TWITTER = "twitter";
  @SerializedName(SERIALIZED_NAME_TWITTER)
  private URI twitter;

  public static final String SERIALIZED_NAME_PIXIV = "pixiv";
  @SerializedName(SERIALIZED_NAME_PIXIV)
  private URI pixiv;

  public static final String SERIALIZED_NAME_MELON_BOOK = "melonBook";
  @SerializedName(SERIALIZED_NAME_MELON_BOOK)
  private URI melonBook;

  public static final String SERIALIZED_NAME_FAN_BOX = "fanBox";
  @SerializedName(SERIALIZED_NAME_FAN_BOX)
  private URI fanBox;

  public static final String SERIALIZED_NAME_BOOTH = "booth";
  @SerializedName(SERIALIZED_NAME_BOOTH)
  private URI booth;

  public static final String SERIALIZED_NAME_NICO_VIDEO = "nicoVideo";
  @SerializedName(SERIALIZED_NAME_NICO_VIDEO)
  private URI nicoVideo;

  public static final String SERIALIZED_NAME_SKEB = "skeb";
  @SerializedName(SERIALIZED_NAME_SKEB)
  private URI skeb;

  public static final String SERIALIZED_NAME_FANTIA = "fantia";
  @SerializedName(SERIALIZED_NAME_FANTIA)
  private URI fantia;

  public static final String SERIALIZED_NAME_TUMBLR = "tumblr";
  @SerializedName(SERIALIZED_NAME_TUMBLR)
  private URI tumblr;

  public static final String SERIALIZED_NAME_YOUTUBE = "youtube";
  @SerializedName(SERIALIZED_NAME_YOUTUBE)
  private URI youtube;

  public static final String SERIALIZED_NAME_WEBSITE = "website";
  @SerializedName(SERIALIZED_NAME_WEBSITE)
  private URI website;

  public static final String SERIALIZED_NAME_VERSION = "version";
  @SerializedName(SERIALIZED_NAME_VERSION)
  private Integer version;

  public static final String SERIALIZED_NAME_CREATED_AT = "createdAt";
  @SerializedName(SERIALIZED_NAME_CREATED_AT)
  private String createdAt;

  public static final String SERIALIZED_NAME_UPDATED_AT = "updatedAt";
  @SerializedName(SERIALIZED_NAME_UPDATED_AT)
  private String updatedAt;


  public AuthorAttributes name(String name) {
    
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public AuthorAttributes imageUrl(String imageUrl) {
    
    this.imageUrl = imageUrl;
    return this;
  }

   /**
   * Get imageUrl
   * @return imageUrl
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getImageUrl() {
    return imageUrl;
  }


  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }


  public AuthorAttributes biography(Map<String, String> biography) {
    
    this.biography = biography;
    return this;
  }

  public AuthorAttributes putBiographyItem(String key, String biographyItem) {
    if (this.biography == null) {
      this.biography = new HashMap<String, String>();
    }
    this.biography.put(key, biographyItem);
    return this;
  }

   /**
   * Get biography
   * @return biography
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Map<String, String> getBiography() {
    return biography;
  }


  public void setBiography(Map<String, String> biography) {
    this.biography = biography;
  }


  public AuthorAttributes twitter(URI twitter) {
    
    this.twitter = twitter;
    return this;
  }

   /**
   * Get twitter
   * @return twitter
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public URI getTwitter() {
    return twitter;
  }


  public void setTwitter(URI twitter) {
    this.twitter = twitter;
  }


  public AuthorAttributes pixiv(URI pixiv) {
    
    this.pixiv = pixiv;
    return this;
  }

   /**
   * Get pixiv
   * @return pixiv
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public URI getPixiv() {
    return pixiv;
  }


  public void setPixiv(URI pixiv) {
    this.pixiv = pixiv;
  }


  public AuthorAttributes melonBook(URI melonBook) {
    
    this.melonBook = melonBook;
    return this;
  }

   /**
   * Get melonBook
   * @return melonBook
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public URI getMelonBook() {
    return melonBook;
  }


  public void setMelonBook(URI melonBook) {
    this.melonBook = melonBook;
  }


  public AuthorAttributes fanBox(URI fanBox) {
    
    this.fanBox = fanBox;
    return this;
  }

   /**
   * Get fanBox
   * @return fanBox
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public URI getFanBox() {
    return fanBox;
  }


  public void setFanBox(URI fanBox) {
    this.fanBox = fanBox;
  }


  public AuthorAttributes booth(URI booth) {
    
    this.booth = booth;
    return this;
  }

   /**
   * Get booth
   * @return booth
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public URI getBooth() {
    return booth;
  }


  public void setBooth(URI booth) {
    this.booth = booth;
  }


  public AuthorAttributes nicoVideo(URI nicoVideo) {
    
    this.nicoVideo = nicoVideo;
    return this;
  }

   /**
   * Get nicoVideo
   * @return nicoVideo
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public URI getNicoVideo() {
    return nicoVideo;
  }


  public void setNicoVideo(URI nicoVideo) {
    this.nicoVideo = nicoVideo;
  }


  public AuthorAttributes skeb(URI skeb) {
    
    this.skeb = skeb;
    return this;
  }

   /**
   * Get skeb
   * @return skeb
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public URI getSkeb() {
    return skeb;
  }


  public void setSkeb(URI skeb) {
    this.skeb = skeb;
  }


  public AuthorAttributes fantia(URI fantia) {
    
    this.fantia = fantia;
    return this;
  }

   /**
   * Get fantia
   * @return fantia
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public URI getFantia() {
    return fantia;
  }


  public void setFantia(URI fantia) {
    this.fantia = fantia;
  }


  public AuthorAttributes tumblr(URI tumblr) {
    
    this.tumblr = tumblr;
    return this;
  }

   /**
   * Get tumblr
   * @return tumblr
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public URI getTumblr() {
    return tumblr;
  }


  public void setTumblr(URI tumblr) {
    this.tumblr = tumblr;
  }


  public AuthorAttributes youtube(URI youtube) {
    
    this.youtube = youtube;
    return this;
  }

   /**
   * Get youtube
   * @return youtube
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public URI getYoutube() {
    return youtube;
  }


  public void setYoutube(URI youtube) {
    this.youtube = youtube;
  }


  public AuthorAttributes website(URI website) {
    
    this.website = website;
    return this;
  }

   /**
   * Get website
   * @return website
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public URI getWebsite() {
    return website;
  }


  public void setWebsite(URI website) {
    this.website = website;
  }


  public AuthorAttributes version(Integer version) {
    
    this.version = version;
    return this;
  }

   /**
   * Get version
   * minimum: 1
   * @return version
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Integer getVersion() {
    return version;
  }


  public void setVersion(Integer version) {
    this.version = version;
  }


  public AuthorAttributes createdAt(String createdAt) {
    
    this.createdAt = createdAt;
    return this;
  }

   /**
   * Get createdAt
   * @return createdAt
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getCreatedAt() {
    return createdAt;
  }


  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }


  public AuthorAttributes updatedAt(String updatedAt) {
    
    this.updatedAt = updatedAt;
    return this;
  }

   /**
   * Get updatedAt
   * @return updatedAt
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getUpdatedAt() {
    return updatedAt;
  }


  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuthorAttributes authorAttributes = (AuthorAttributes) o;
    return Objects.equals(this.name, authorAttributes.name) &&
        Objects.equals(this.imageUrl, authorAttributes.imageUrl) &&
        Objects.equals(this.biography, authorAttributes.biography) &&
        Objects.equals(this.twitter, authorAttributes.twitter) &&
        Objects.equals(this.pixiv, authorAttributes.pixiv) &&
        Objects.equals(this.melonBook, authorAttributes.melonBook) &&
        Objects.equals(this.fanBox, authorAttributes.fanBox) &&
        Objects.equals(this.booth, authorAttributes.booth) &&
        Objects.equals(this.nicoVideo, authorAttributes.nicoVideo) &&
        Objects.equals(this.skeb, authorAttributes.skeb) &&
        Objects.equals(this.fantia, authorAttributes.fantia) &&
        Objects.equals(this.tumblr, authorAttributes.tumblr) &&
        Objects.equals(this.youtube, authorAttributes.youtube) &&
        Objects.equals(this.website, authorAttributes.website) &&
        Objects.equals(this.version, authorAttributes.version) &&
        Objects.equals(this.createdAt, authorAttributes.createdAt) &&
        Objects.equals(this.updatedAt, authorAttributes.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, imageUrl, biography, twitter, pixiv, melonBook, fanBox, booth, nicoVideo, skeb, fantia, tumblr, youtube, website, version, createdAt, updatedAt);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AuthorAttributes {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    imageUrl: ").append(toIndentedString(imageUrl)).append("\n");
    sb.append("    biography: ").append(toIndentedString(biography)).append("\n");
    sb.append("    twitter: ").append(toIndentedString(twitter)).append("\n");
    sb.append("    pixiv: ").append(toIndentedString(pixiv)).append("\n");
    sb.append("    melonBook: ").append(toIndentedString(melonBook)).append("\n");
    sb.append("    fanBox: ").append(toIndentedString(fanBox)).append("\n");
    sb.append("    booth: ").append(toIndentedString(booth)).append("\n");
    sb.append("    nicoVideo: ").append(toIndentedString(nicoVideo)).append("\n");
    sb.append("    skeb: ").append(toIndentedString(skeb)).append("\n");
    sb.append("    fantia: ").append(toIndentedString(fantia)).append("\n");
    sb.append("    tumblr: ").append(toIndentedString(tumblr)).append("\n");
    sb.append("    youtube: ").append(toIndentedString(youtube)).append("\n");
    sb.append("    website: ").append(toIndentedString(website)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

