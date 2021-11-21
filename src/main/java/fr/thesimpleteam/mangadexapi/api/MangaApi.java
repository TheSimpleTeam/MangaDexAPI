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


package fr.thesimpleteam.mangadexapi.api;

import fr.thesimpleteam.mangadexapi.ApiCallback;
import fr.thesimpleteam.mangadexapi.ApiClient;
import fr.thesimpleteam.mangadexapi.ApiException;
import fr.thesimpleteam.mangadexapi.Configuration;
import fr.thesimpleteam.mangadexapi.ApiResponse;
import fr.thesimpleteam.mangadexapi.Pair;

import com.google.gson.reflect.TypeToken;


import fr.thesimpleteam.mangadexapi.model.ChapterList;
import fr.thesimpleteam.mangadexapi.model.InlineObject3;
import fr.thesimpleteam.mangadexapi.model.InlineResponse200;
import fr.thesimpleteam.mangadexapi.model.InlineResponse2006;
import fr.thesimpleteam.mangadexapi.model.InlineResponse2007;
import fr.thesimpleteam.mangadexapi.model.MangaCreate;
import fr.thesimpleteam.mangadexapi.model.MangaList;
import fr.thesimpleteam.mangadexapi.model.MangaRelationCreate;
import fr.thesimpleteam.mangadexapi.model.MangaRelationList;
import fr.thesimpleteam.mangadexapi.model.MangaRelationResponse;
import fr.thesimpleteam.mangadexapi.model.MangaResponse;
import fr.thesimpleteam.mangadexapi.model.Order;
import fr.thesimpleteam.mangadexapi.model.Order7;
import fr.thesimpleteam.mangadexapi.model.Order8;
import fr.thesimpleteam.mangadexapi.model.Response;
import fr.thesimpleteam.mangadexapi.model.TagResponse;
import org.openapitools.client.model.UNKNOWN_BASE_TYPE;
import java.util.UUID;
import fr.thesimpleteam.mangadexapi.model.UpdateMangaStatus;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MangaApi {
    private ApiClient localVarApiClient;

    public MangaApi() {
        this(Configuration.getDefaultApiClient());
    }

    public MangaApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for commitMangaDraft
     * @param id  (required)
     * @param inlineObject3  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> BadRequest </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call commitMangaDraftCall(UUID id, InlineObject3 inlineObject3, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = inlineObject3;

        // create path and map variables
        String localVarPath = "/manga/draft/{id}/commit"
            .replaceAll("\\{" + "id" + "\\}", localVarApiClient.escapeString(id.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "Bearer" };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call commitMangaDraftValidateBeforeCall(UUID id, InlineObject3 inlineObject3, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling commitMangaDraft(Async)");
        }
        

        okhttp3.Call localVarCall = commitMangaDraftCall(id, inlineObject3, _callback);
        return localVarCall;

    }

    /**
     * Submit a Manga Draft
     * 
     * @param id  (required)
     * @param inlineObject3  (optional)
     * @return MangaResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> BadRequest </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public MangaResponse commitMangaDraft(UUID id, InlineObject3 inlineObject3) throws ApiException {
        ApiResponse<MangaResponse> localVarResp = commitMangaDraftWithHttpInfo(id, inlineObject3);
        return localVarResp.getData();
    }

    /**
     * Submit a Manga Draft
     * 
     * @param id  (required)
     * @param inlineObject3  (optional)
     * @return ApiResponse&lt;MangaResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> BadRequest </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<MangaResponse> commitMangaDraftWithHttpInfo(UUID id, InlineObject3 inlineObject3) throws ApiException {
        okhttp3.Call localVarCall = commitMangaDraftValidateBeforeCall(id, inlineObject3, null);
        Type localVarReturnType = new TypeToken<MangaResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Submit a Manga Draft (asynchronously)
     * 
     * @param id  (required)
     * @param inlineObject3  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> BadRequest </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call commitMangaDraftAsync(UUID id, InlineObject3 inlineObject3, final ApiCallback<MangaResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = commitMangaDraftValidateBeforeCall(id, inlineObject3, _callback);
        Type localVarReturnType = new TypeToken<MangaResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for deleteMangaId
     * @param id Manga ID (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Manga has been deleted. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteMangaIdCall(UUID id, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/manga/{id}"
            .replaceAll("\\{" + "id" + "\\}", localVarApiClient.escapeString(id.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "Bearer" };
        return localVarApiClient.buildCall(localVarPath, "DELETE", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call deleteMangaIdValidateBeforeCall(UUID id, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling deleteMangaId(Async)");
        }
        

        okhttp3.Call localVarCall = deleteMangaIdCall(id, _callback);
        return localVarCall;

    }

    /**
     * Delete Manga
     * 
     * @param id Manga ID (required)
     * @return Response
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Manga has been deleted. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public Response deleteMangaId(UUID id) throws ApiException {
        ApiResponse<Response> localVarResp = deleteMangaIdWithHttpInfo(id);
        return localVarResp.getData();
    }

    /**
     * Delete Manga
     * 
     * @param id Manga ID (required)
     * @return ApiResponse&lt;Response&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Manga has been deleted. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Response> deleteMangaIdWithHttpInfo(UUID id) throws ApiException {
        okhttp3.Call localVarCall = deleteMangaIdValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<Response>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Delete Manga (asynchronously)
     * 
     * @param id Manga ID (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Manga has been deleted. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteMangaIdAsync(UUID id, final ApiCallback<Response> _callback) throws ApiException {

        okhttp3.Call localVarCall = deleteMangaIdValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<Response>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for deleteMangaIdFollow
     * @param id  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteMangaIdFollowCall(UUID id, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/manga/{id}/follow"
            .replaceAll("\\{" + "id" + "\\}", localVarApiClient.escapeString(id.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "Bearer" };
        return localVarApiClient.buildCall(localVarPath, "DELETE", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call deleteMangaIdFollowValidateBeforeCall(UUID id, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling deleteMangaIdFollow(Async)");
        }
        

        okhttp3.Call localVarCall = deleteMangaIdFollowCall(id, _callback);
        return localVarCall;

    }

    /**
     * Unfollow Manga
     * 
     * @param id  (required)
     * @return Response
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public Response deleteMangaIdFollow(UUID id) throws ApiException {
        ApiResponse<Response> localVarResp = deleteMangaIdFollowWithHttpInfo(id);
        return localVarResp.getData();
    }

    /**
     * Unfollow Manga
     * 
     * @param id  (required)
     * @return ApiResponse&lt;Response&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Response> deleteMangaIdFollowWithHttpInfo(UUID id) throws ApiException {
        okhttp3.Call localVarCall = deleteMangaIdFollowValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<Response>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Unfollow Manga (asynchronously)
     * 
     * @param id  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteMangaIdFollowAsync(UUID id, final ApiCallback<Response> _callback) throws ApiException {

        okhttp3.Call localVarCall = deleteMangaIdFollowValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<Response>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for deleteMangaRelationId
     * @param mangaId  (required)
     * @param id  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Manga relation has been deleted. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteMangaRelationIdCall(UUID mangaId, UUID id, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/manga/{mangaId}/relation/{id}"
            .replaceAll("\\{" + "mangaId" + "\\}", localVarApiClient.escapeString(mangaId.toString()))
            .replaceAll("\\{" + "id" + "\\}", localVarApiClient.escapeString(id.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "Bearer" };
        return localVarApiClient.buildCall(localVarPath, "DELETE", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call deleteMangaRelationIdValidateBeforeCall(UUID mangaId, UUID id, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'mangaId' is set
        if (mangaId == null) {
            throw new ApiException("Missing the required parameter 'mangaId' when calling deleteMangaRelationId(Async)");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling deleteMangaRelationId(Async)");
        }
        

        okhttp3.Call localVarCall = deleteMangaRelationIdCall(mangaId, id, _callback);
        return localVarCall;

    }

    /**
     * Delete Manga relation
     * 
     * @param mangaId  (required)
     * @param id  (required)
     * @return Response
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Manga relation has been deleted. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public Response deleteMangaRelationId(UUID mangaId, UUID id) throws ApiException {
        ApiResponse<Response> localVarResp = deleteMangaRelationIdWithHttpInfo(mangaId, id);
        return localVarResp.getData();
    }

    /**
     * Delete Manga relation
     * 
     * @param mangaId  (required)
     * @param id  (required)
     * @return ApiResponse&lt;Response&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Manga relation has been deleted. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Response> deleteMangaRelationIdWithHttpInfo(UUID mangaId, UUID id) throws ApiException {
        okhttp3.Call localVarCall = deleteMangaRelationIdValidateBeforeCall(mangaId, id, null);
        Type localVarReturnType = new TypeToken<Response>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Delete Manga relation (asynchronously)
     * 
     * @param mangaId  (required)
     * @param id  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Manga relation has been deleted. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteMangaRelationIdAsync(UUID mangaId, UUID id, final ApiCallback<Response> _callback) throws ApiException {

        okhttp3.Call localVarCall = deleteMangaRelationIdValidateBeforeCall(mangaId, id, _callback);
        Type localVarReturnType = new TypeToken<Response>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getMangaDrafts
     * @param limit  (optional, default to 10)
     * @param offset  (optional)
     * @param user  (optional)
     * @param state  (optional)
     * @param order  (optional, default to {&quot;createdAt&quot;:&quot;desc&quot;})
     * @param includes  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getMangaDraftsCall(Integer limit, Integer offset, UUID user, String state, Order8 order, List<String> includes, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/manga/draft";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (limit != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("limit", limit));
        }

        if (offset != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("offset", offset));
        }

        if (user != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("user", user));
        }

        if (state != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("state", state));
        }

        if (order != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("order", order));
        }

        if (includes != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "includes[]", includes));
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "Bearer" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getMangaDraftsValidateBeforeCall(Integer limit, Integer offset, UUID user, String state, Order8 order, List<String> includes, final ApiCallback _callback) throws ApiException {
        

        okhttp3.Call localVarCall = getMangaDraftsCall(limit, offset, user, state, order, includes, _callback);
        return localVarCall;

    }

    /**
     * Get a list of Manga Drafts
     * 
     * @param limit  (optional, default to 10)
     * @param offset  (optional)
     * @param user  (optional)
     * @param state  (optional)
     * @param order  (optional, default to {&quot;createdAt&quot;:&quot;desc&quot;})
     * @param includes  (optional)
     * @return MangaResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public MangaResponse getMangaDrafts(Integer limit, Integer offset, UUID user, String state, Order8 order, List<String> includes) throws ApiException {
        ApiResponse<MangaResponse> localVarResp = getMangaDraftsWithHttpInfo(limit, offset, user, state, order, includes);
        return localVarResp.getData();
    }

    /**
     * Get a list of Manga Drafts
     * 
     * @param limit  (optional, default to 10)
     * @param offset  (optional)
     * @param user  (optional)
     * @param state  (optional)
     * @param order  (optional, default to {&quot;createdAt&quot;:&quot;desc&quot;})
     * @param includes  (optional)
     * @return ApiResponse&lt;MangaResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<MangaResponse> getMangaDraftsWithHttpInfo(Integer limit, Integer offset, UUID user, String state, Order8 order, List<String> includes) throws ApiException {
        okhttp3.Call localVarCall = getMangaDraftsValidateBeforeCall(limit, offset, user, state, order, includes, null);
        Type localVarReturnType = new TypeToken<MangaResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get a list of Manga Drafts (asynchronously)
     * 
     * @param limit  (optional, default to 10)
     * @param offset  (optional)
     * @param user  (optional)
     * @param state  (optional)
     * @param order  (optional, default to {&quot;createdAt&quot;:&quot;desc&quot;})
     * @param includes  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getMangaDraftsAsync(Integer limit, Integer offset, UUID user, String state, Order8 order, List<String> includes, final ApiCallback<MangaResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = getMangaDraftsValidateBeforeCall(limit, offset, user, state, order, includes, _callback);
        Type localVarReturnType = new TypeToken<MangaResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getMangaId
     * @param id Manga ID (required)
     * @param includes  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Manga no content </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getMangaIdCall(UUID id, List<String> includes, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/manga/{id}"
            .replaceAll("\\{" + "id" + "\\}", localVarApiClient.escapeString(id.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (includes != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "includes[]", includes));
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getMangaIdValidateBeforeCall(UUID id, List<String> includes, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling getMangaId(Async)");
        }
        

        okhttp3.Call localVarCall = getMangaIdCall(id, includes, _callback);
        return localVarCall;

    }

    /**
     * View Manga
     * View Manga.
     * @param id Manga ID (required)
     * @param includes  (optional)
     * @return MangaResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Manga no content </td><td>  -  </td></tr>
     </table>
     */
    public MangaResponse getMangaId(UUID id, List<String> includes) throws ApiException {
        ApiResponse<MangaResponse> localVarResp = getMangaIdWithHttpInfo(id, includes);
        return localVarResp.getData();
    }

    /**
     * View Manga
     * View Manga.
     * @param id Manga ID (required)
     * @param includes  (optional)
     * @return ApiResponse&lt;MangaResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Manga no content </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<MangaResponse> getMangaIdWithHttpInfo(UUID id, List<String> includes) throws ApiException {
        okhttp3.Call localVarCall = getMangaIdValidateBeforeCall(id, includes, null);
        Type localVarReturnType = new TypeToken<MangaResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * View Manga (asynchronously)
     * View Manga.
     * @param id Manga ID (required)
     * @param includes  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Manga no content </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getMangaIdAsync(UUID id, List<String> includes, final ApiCallback<MangaResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = getMangaIdValidateBeforeCall(id, includes, _callback);
        Type localVarReturnType = new TypeToken<MangaResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getMangaIdDraft
     * @param id  (required)
     * @param includes  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getMangaIdDraftCall(UUID id, List<String> includes, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/manga/draft/{id}"
            .replaceAll("\\{" + "id" + "\\}", localVarApiClient.escapeString(id.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (includes != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "includes[]", includes));
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "Bearer" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getMangaIdDraftValidateBeforeCall(UUID id, List<String> includes, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling getMangaIdDraft(Async)");
        }
        

        okhttp3.Call localVarCall = getMangaIdDraftCall(id, includes, _callback);
        return localVarCall;

    }

    /**
     * Get a specific Manga Draft
     * 
     * @param id  (required)
     * @param includes  (optional)
     * @return MangaResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public MangaResponse getMangaIdDraft(UUID id, List<String> includes) throws ApiException {
        ApiResponse<MangaResponse> localVarResp = getMangaIdDraftWithHttpInfo(id, includes);
        return localVarResp.getData();
    }

    /**
     * Get a specific Manga Draft
     * 
     * @param id  (required)
     * @param includes  (optional)
     * @return ApiResponse&lt;MangaResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<MangaResponse> getMangaIdDraftWithHttpInfo(UUID id, List<String> includes) throws ApiException {
        okhttp3.Call localVarCall = getMangaIdDraftValidateBeforeCall(id, includes, null);
        Type localVarReturnType = new TypeToken<MangaResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get a specific Manga Draft (asynchronously)
     * 
     * @param id  (required)
     * @param includes  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getMangaIdDraftAsync(UUID id, List<String> includes, final ApiCallback<MangaResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = getMangaIdDraftValidateBeforeCall(id, includes, _callback);
        Type localVarReturnType = new TypeToken<MangaResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getMangaIdFeed
     * @param id Manga ID (required)
     * @param limit  (optional, default to 100)
     * @param offset  (optional)
     * @param translatedLanguage  (optional)
     * @param originalLanguage  (optional)
     * @param excludedOriginalLanguage  (optional)
     * @param contentRating  (optional)
     * @param includeFutureUpdates  (optional, default to &quot;1&quot;)
     * @param createdAtSince  (optional)
     * @param updatedAtSince  (optional)
     * @param publishAtSince  (optional)
     * @param order  (optional)
     * @param includes  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getMangaIdFeedCall(UUID id, Integer limit, Integer offset, List<String> translatedLanguage, List<String> originalLanguage, List<String> excludedOriginalLanguage, List<String> contentRating, String includeFutureUpdates, String createdAtSince, String updatedAtSince, String publishAtSince, Order7 order, List<String> includes, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/manga/{id}/feed"
            .replaceAll("\\{" + "id" + "\\}", localVarApiClient.escapeString(id.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (limit != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("limit", limit));
        }

        if (offset != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("offset", offset));
        }

        if (translatedLanguage != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "translatedLanguage[]", translatedLanguage));
        }

        if (originalLanguage != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "originalLanguage[]", originalLanguage));
        }

        if (excludedOriginalLanguage != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "excludedOriginalLanguage[]", excludedOriginalLanguage));
        }

        if (contentRating != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "contentRating[]", contentRating));
        }

        if (includeFutureUpdates != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("includeFutureUpdates", includeFutureUpdates));
        }

        if (createdAtSince != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("createdAtSince", createdAtSince));
        }

        if (updatedAtSince != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("updatedAtSince", updatedAtSince));
        }

        if (publishAtSince != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("publishAtSince", publishAtSince));
        }

        if (order != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("order", order));
        }

        if (includes != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "includes[]", includes));
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getMangaIdFeedValidateBeforeCall(UUID id, Integer limit, Integer offset, List<String> translatedLanguage, List<String> originalLanguage, List<String> excludedOriginalLanguage, List<String> contentRating, String includeFutureUpdates, String createdAtSince, String updatedAtSince, String publishAtSince, Order7 order, List<String> includes, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling getMangaIdFeed(Async)");
        }
        

        okhttp3.Call localVarCall = getMangaIdFeedCall(id, limit, offset, translatedLanguage, originalLanguage, excludedOriginalLanguage, contentRating, includeFutureUpdates, createdAtSince, updatedAtSince, publishAtSince, order, includes, _callback);
        return localVarCall;

    }

    /**
     * Manga feed
     * 
     * @param id Manga ID (required)
     * @param limit  (optional, default to 100)
     * @param offset  (optional)
     * @param translatedLanguage  (optional)
     * @param originalLanguage  (optional)
     * @param excludedOriginalLanguage  (optional)
     * @param contentRating  (optional)
     * @param includeFutureUpdates  (optional, default to &quot;1&quot;)
     * @param createdAtSince  (optional)
     * @param updatedAtSince  (optional)
     * @param publishAtSince  (optional)
     * @param order  (optional)
     * @param includes  (optional)
     * @return ChapterList
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
     </table>
     */
    public ChapterList getMangaIdFeed(UUID id, Integer limit, Integer offset, List<String> translatedLanguage, List<String> originalLanguage, List<String> excludedOriginalLanguage, List<String> contentRating, String includeFutureUpdates, String createdAtSince, String updatedAtSince, String publishAtSince, Order7 order, List<String> includes) throws ApiException {
        ApiResponse<ChapterList> localVarResp = getMangaIdFeedWithHttpInfo(id, limit, offset, translatedLanguage, originalLanguage, excludedOriginalLanguage, contentRating, includeFutureUpdates, createdAtSince, updatedAtSince, publishAtSince, order, includes);
        return localVarResp.getData();
    }

    /**
     * Manga feed
     * 
     * @param id Manga ID (required)
     * @param limit  (optional, default to 100)
     * @param offset  (optional)
     * @param translatedLanguage  (optional)
     * @param originalLanguage  (optional)
     * @param excludedOriginalLanguage  (optional)
     * @param contentRating  (optional)
     * @param includeFutureUpdates  (optional, default to &quot;1&quot;)
     * @param createdAtSince  (optional)
     * @param updatedAtSince  (optional)
     * @param publishAtSince  (optional)
     * @param order  (optional)
     * @param includes  (optional)
     * @return ApiResponse&lt;ChapterList&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<ChapterList> getMangaIdFeedWithHttpInfo(UUID id, Integer limit, Integer offset, List<String> translatedLanguage, List<String> originalLanguage, List<String> excludedOriginalLanguage, List<String> contentRating, String includeFutureUpdates, String createdAtSince, String updatedAtSince, String publishAtSince, Order7 order, List<String> includes) throws ApiException {
        okhttp3.Call localVarCall = getMangaIdFeedValidateBeforeCall(id, limit, offset, translatedLanguage, originalLanguage, excludedOriginalLanguage, contentRating, includeFutureUpdates, createdAtSince, updatedAtSince, publishAtSince, order, includes, null);
        Type localVarReturnType = new TypeToken<ChapterList>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Manga feed (asynchronously)
     * 
     * @param id Manga ID (required)
     * @param limit  (optional, default to 100)
     * @param offset  (optional)
     * @param translatedLanguage  (optional)
     * @param originalLanguage  (optional)
     * @param excludedOriginalLanguage  (optional)
     * @param contentRating  (optional)
     * @param includeFutureUpdates  (optional, default to &quot;1&quot;)
     * @param createdAtSince  (optional)
     * @param updatedAtSince  (optional)
     * @param publishAtSince  (optional)
     * @param order  (optional)
     * @param includes  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getMangaIdFeedAsync(UUID id, Integer limit, Integer offset, List<String> translatedLanguage, List<String> originalLanguage, List<String> excludedOriginalLanguage, List<String> contentRating, String includeFutureUpdates, String createdAtSince, String updatedAtSince, String publishAtSince, Order7 order, List<String> includes, final ApiCallback<ChapterList> _callback) throws ApiException {

        okhttp3.Call localVarCall = getMangaIdFeedValidateBeforeCall(id, limit, offset, translatedLanguage, originalLanguage, excludedOriginalLanguage, contentRating, includeFutureUpdates, createdAtSince, updatedAtSince, publishAtSince, order, includes, _callback);
        Type localVarReturnType = new TypeToken<ChapterList>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getMangaIdStatus
     * @param id  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getMangaIdStatusCall(UUID id, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/manga/{id}/status"
            .replaceAll("\\{" + "id" + "\\}", localVarApiClient.escapeString(id.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "Bearer" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getMangaIdStatusValidateBeforeCall(UUID id, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling getMangaIdStatus(Async)");
        }
        

        okhttp3.Call localVarCall = getMangaIdStatusCall(id, _callback);
        return localVarCall;

    }

    /**
     * Get a Manga reading status
     * 
     * @param id  (required)
     * @return InlineResponse2007
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public InlineResponse2007 getMangaIdStatus(UUID id) throws ApiException {
        ApiResponse<InlineResponse2007> localVarResp = getMangaIdStatusWithHttpInfo(id);
        return localVarResp.getData();
    }

    /**
     * Get a Manga reading status
     * 
     * @param id  (required)
     * @return ApiResponse&lt;InlineResponse2007&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<InlineResponse2007> getMangaIdStatusWithHttpInfo(UUID id) throws ApiException {
        okhttp3.Call localVarCall = getMangaIdStatusValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<InlineResponse2007>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get a Manga reading status (asynchronously)
     * 
     * @param id  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getMangaIdStatusAsync(UUID id, final ApiCallback<InlineResponse2007> _callback) throws ApiException {

        okhttp3.Call localVarCall = getMangaIdStatusValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<InlineResponse2007>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getMangaRandom
     * @param includes  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getMangaRandomCall(List<String> includes, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/manga/random";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (includes != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "includes[]", includes));
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getMangaRandomValidateBeforeCall(List<String> includes, final ApiCallback _callback) throws ApiException {
        

        okhttp3.Call localVarCall = getMangaRandomCall(includes, _callback);
        return localVarCall;

    }

    /**
     * Get a random Manga
     * 
     * @param includes  (optional)
     * @return MangaResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     </table>
     */
    public MangaResponse getMangaRandom(List<String> includes) throws ApiException {
        ApiResponse<MangaResponse> localVarResp = getMangaRandomWithHttpInfo(includes);
        return localVarResp.getData();
    }

    /**
     * Get a random Manga
     * 
     * @param includes  (optional)
     * @return ApiResponse&lt;MangaResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<MangaResponse> getMangaRandomWithHttpInfo(List<String> includes) throws ApiException {
        okhttp3.Call localVarCall = getMangaRandomValidateBeforeCall(includes, null);
        Type localVarReturnType = new TypeToken<MangaResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get a random Manga (asynchronously)
     * 
     * @param includes  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getMangaRandomAsync(List<String> includes, final ApiCallback<MangaResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = getMangaRandomValidateBeforeCall(includes, _callback);
        Type localVarReturnType = new TypeToken<MangaResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getMangaRelation
     * @param mangaId  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Manga relation list </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getMangaRelationCall(UUID mangaId, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/manga/{mangaId}/relation"
            .replaceAll("\\{" + "mangaId" + "\\}", localVarApiClient.escapeString(mangaId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getMangaRelationValidateBeforeCall(UUID mangaId, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'mangaId' is set
        if (mangaId == null) {
            throw new ApiException("Missing the required parameter 'mangaId' when calling getMangaRelation(Async)");
        }
        

        okhttp3.Call localVarCall = getMangaRelationCall(mangaId, _callback);
        return localVarCall;

    }

    /**
     * Manga relation list
     * 
     * @param mangaId  (required)
     * @return MangaRelationList
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Manga relation list </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
     </table>
     */
    public MangaRelationList getMangaRelation(UUID mangaId) throws ApiException {
        ApiResponse<MangaRelationList> localVarResp = getMangaRelationWithHttpInfo(mangaId);
        return localVarResp.getData();
    }

    /**
     * Manga relation list
     * 
     * @param mangaId  (required)
     * @return ApiResponse&lt;MangaRelationList&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Manga relation list </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<MangaRelationList> getMangaRelationWithHttpInfo(UUID mangaId) throws ApiException {
        okhttp3.Call localVarCall = getMangaRelationValidateBeforeCall(mangaId, null);
        Type localVarReturnType = new TypeToken<MangaRelationList>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Manga relation list (asynchronously)
     * 
     * @param mangaId  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Manga relation list </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getMangaRelationAsync(UUID mangaId, final ApiCallback<MangaRelationList> _callback) throws ApiException {

        okhttp3.Call localVarCall = getMangaRelationValidateBeforeCall(mangaId, _callback);
        Type localVarReturnType = new TypeToken<MangaRelationList>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getMangaStatus
     * @param status Used to filter the list by given status (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getMangaStatusCall(String status, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/manga/status";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (status != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("status", status));
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "Bearer" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getMangaStatusValidateBeforeCall(String status, final ApiCallback _callback) throws ApiException {
        

        okhttp3.Call localVarCall = getMangaStatusCall(status, _callback);
        return localVarCall;

    }

    /**
     * Get all Manga reading status for logged User
     * 
     * @param status Used to filter the list by given status (optional)
     * @return InlineResponse2006
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     </table>
     */
    public InlineResponse2006 getMangaStatus(String status) throws ApiException {
        ApiResponse<InlineResponse2006> localVarResp = getMangaStatusWithHttpInfo(status);
        return localVarResp.getData();
    }

    /**
     * Get all Manga reading status for logged User
     * 
     * @param status Used to filter the list by given status (optional)
     * @return ApiResponse&lt;InlineResponse2006&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<InlineResponse2006> getMangaStatusWithHttpInfo(String status) throws ApiException {
        okhttp3.Call localVarCall = getMangaStatusValidateBeforeCall(status, null);
        Type localVarReturnType = new TypeToken<InlineResponse2006>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get all Manga reading status for logged User (asynchronously)
     * 
     * @param status Used to filter the list by given status (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getMangaStatusAsync(String status, final ApiCallback<InlineResponse2006> _callback) throws ApiException {

        okhttp3.Call localVarCall = getMangaStatusValidateBeforeCall(status, _callback);
        Type localVarReturnType = new TypeToken<InlineResponse2006>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getMangaTag
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getMangaTagCall(final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/manga/tag";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getMangaTagValidateBeforeCall(final ApiCallback _callback) throws ApiException {
        

        okhttp3.Call localVarCall = getMangaTagCall(_callback);
        return localVarCall;

    }

    /**
     * Tag list
     * 
     * @return TagResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     </table>
     */
    public TagResponse getMangaTag() throws ApiException {
        ApiResponse<TagResponse> localVarResp = getMangaTagWithHttpInfo();
        return localVarResp.getData();
    }

    /**
     * Tag list
     * 
     * @return ApiResponse&lt;TagResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<TagResponse> getMangaTagWithHttpInfo() throws ApiException {
        okhttp3.Call localVarCall = getMangaTagValidateBeforeCall(null);
        Type localVarReturnType = new TypeToken<TagResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Tag list (asynchronously)
     * 
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getMangaTagAsync(final ApiCallback<TagResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = getMangaTagValidateBeforeCall(_callback);
        Type localVarReturnType = new TypeToken<TagResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getSearchManga
     * @param limit  (optional, default to 10)
     * @param offset  (optional)
     * @param title  (optional)
     * @param authors  (optional)
     * @param artists  (optional)
     * @param year Year of release (optional)
     * @param includedTags  (optional)
     * @param includedTagsMode  (optional, default to AND)
     * @param excludedTags  (optional)
     * @param excludedTagsMode  (optional, default to OR)
     * @param status  (optional)
     * @param originalLanguage  (optional)
     * @param excludedOriginalLanguage  (optional)
     * @param availableTranslatedLanguage  (optional)
     * @param publicationDemographic  (optional)
     * @param ids Manga ids (limited to 100 per request) (optional)
     * @param contentRating  (optional)
     * @param createdAtSince  (optional)
     * @param updatedAtSince  (optional)
     * @param order  (optional, default to {&quot;latestUploadedChapter&quot;:&quot;desc&quot;})
     * @param includes  (optional)
     * @param hasAvailableChapters  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Manga list </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getSearchMangaCall(Integer limit, Integer offset, String title, List<UUID> authors, List<UUID> artists, Integer year, List<UUID> includedTags, String includedTagsMode, List<UUID> excludedTags, String excludedTagsMode, List<String> status, List<String> originalLanguage, List<String> excludedOriginalLanguage, List<String> availableTranslatedLanguage, List<String> publicationDemographic, List<UUID> ids, List<String> contentRating, String createdAtSince, String updatedAtSince, Order order, List<String> includes, String hasAvailableChapters, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/manga";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (limit != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("limit", limit));
        }

        if (offset != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("offset", offset));
        }

        if (title != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("title", title));
        }

        if (authors != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "authors[]", authors));
        }

        if (artists != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "artists[]", artists));
        }

        if (year != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("year", year));
        }

        if (includedTags != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "includedTags[]", includedTags));
        }

        if (includedTagsMode != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("includedTagsMode", includedTagsMode));
        }

        if (excludedTags != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "excludedTags[]", excludedTags));
        }

        if (excludedTagsMode != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("excludedTagsMode", excludedTagsMode));
        }

        if (status != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "status[]", status));
        }

        if (originalLanguage != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "originalLanguage[]", originalLanguage));
        }

        if (excludedOriginalLanguage != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "excludedOriginalLanguage[]", excludedOriginalLanguage));
        }

        if (availableTranslatedLanguage != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "availableTranslatedLanguage[]", availableTranslatedLanguage));
        }

        if (publicationDemographic != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "publicationDemographic[]", publicationDemographic));
        }

        if (ids != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "ids[]", ids));
        }

        if (contentRating != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "contentRating[]", contentRating));
        }

        if (createdAtSince != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("createdAtSince", createdAtSince));
        }

        if (updatedAtSince != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("updatedAtSince", updatedAtSince));
        }

        if (order != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("order", order));
        }

        if (includes != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "includes[]", includes));
        }

        if (hasAvailableChapters != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("hasAvailableChapters", hasAvailableChapters));
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getSearchMangaValidateBeforeCall(Integer limit, Integer offset, String title, List<UUID> authors, List<UUID> artists, Integer year, List<UUID> includedTags, String includedTagsMode, List<UUID> excludedTags, String excludedTagsMode, List<String> status, List<String> originalLanguage, List<String> excludedOriginalLanguage, List<String> availableTranslatedLanguage, List<String> publicationDemographic, List<UUID> ids, List<String> contentRating, String createdAtSince, String updatedAtSince, Order order, List<String> includes, String hasAvailableChapters, final ApiCallback _callback) throws ApiException {
        

        okhttp3.Call localVarCall = getSearchMangaCall(limit, offset, title, authors, artists, year, includedTags, includedTagsMode, excludedTags, excludedTagsMode, status, originalLanguage, excludedOriginalLanguage, availableTranslatedLanguage, publicationDemographic, ids, contentRating, createdAtSince, updatedAtSince, order, includes, hasAvailableChapters, _callback);
        return localVarCall;

    }

    /**
     * Manga list
     * Search a list of Manga.
     * @param limit  (optional, default to 10)
     * @param offset  (optional)
     * @param title  (optional)
     * @param authors  (optional)
     * @param artists  (optional)
     * @param year Year of release (optional)
     * @param includedTags  (optional)
     * @param includedTagsMode  (optional, default to AND)
     * @param excludedTags  (optional)
     * @param excludedTagsMode  (optional, default to OR)
     * @param status  (optional)
     * @param originalLanguage  (optional)
     * @param excludedOriginalLanguage  (optional)
     * @param availableTranslatedLanguage  (optional)
     * @param publicationDemographic  (optional)
     * @param ids Manga ids (limited to 100 per request) (optional)
     * @param contentRating  (optional)
     * @param createdAtSince  (optional)
     * @param updatedAtSince  (optional)
     * @param order  (optional, default to {&quot;latestUploadedChapter&quot;:&quot;desc&quot;})
     * @param includes  (optional)
     * @param hasAvailableChapters  (optional)
     * @return MangaList
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Manga list </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
     </table>
     */
    public MangaList getSearchManga(Integer limit, Integer offset, String title, List<UUID> authors, List<UUID> artists, Integer year, List<UUID> includedTags, String includedTagsMode, List<UUID> excludedTags, String excludedTagsMode, List<String> status, List<String> originalLanguage, List<String> excludedOriginalLanguage, List<String> availableTranslatedLanguage, List<String> publicationDemographic, List<UUID> ids, List<String> contentRating, String createdAtSince, String updatedAtSince, Order order, List<String> includes, String hasAvailableChapters) throws ApiException {
        ApiResponse<MangaList> localVarResp = getSearchMangaWithHttpInfo(limit, offset, title, authors, artists, year, includedTags, includedTagsMode, excludedTags, excludedTagsMode, status, originalLanguage, excludedOriginalLanguage, availableTranslatedLanguage, publicationDemographic, ids, contentRating, createdAtSince, updatedAtSince, order, includes, hasAvailableChapters);
        return localVarResp.getData();
    }

    /**
     * Manga list
     * Search a list of Manga.
     * @param limit  (optional, default to 10)
     * @param offset  (optional)
     * @param title  (optional)
     * @param authors  (optional)
     * @param artists  (optional)
     * @param year Year of release (optional)
     * @param includedTags  (optional)
     * @param includedTagsMode  (optional, default to AND)
     * @param excludedTags  (optional)
     * @param excludedTagsMode  (optional, default to OR)
     * @param status  (optional)
     * @param originalLanguage  (optional)
     * @param excludedOriginalLanguage  (optional)
     * @param availableTranslatedLanguage  (optional)
     * @param publicationDemographic  (optional)
     * @param ids Manga ids (limited to 100 per request) (optional)
     * @param contentRating  (optional)
     * @param createdAtSince  (optional)
     * @param updatedAtSince  (optional)
     * @param order  (optional, default to {&quot;latestUploadedChapter&quot;:&quot;desc&quot;})
     * @param includes  (optional)
     * @param hasAvailableChapters  (optional)
     * @return ApiResponse&lt;MangaList&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Manga list </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<MangaList> getSearchMangaWithHttpInfo(Integer limit, Integer offset, String title, List<UUID> authors, List<UUID> artists, Integer year, List<UUID> includedTags, String includedTagsMode, List<UUID> excludedTags, String excludedTagsMode, List<String> status, List<String> originalLanguage, List<String> excludedOriginalLanguage, List<String> availableTranslatedLanguage, List<String> publicationDemographic, List<UUID> ids, List<String> contentRating, String createdAtSince, String updatedAtSince, Order order, List<String> includes, String hasAvailableChapters) throws ApiException {
        okhttp3.Call localVarCall = getSearchMangaValidateBeforeCall(limit, offset, title, authors, artists, year, includedTags, includedTagsMode, excludedTags, excludedTagsMode, status, originalLanguage, excludedOriginalLanguage, availableTranslatedLanguage, publicationDemographic, ids, contentRating, createdAtSince, updatedAtSince, order, includes, hasAvailableChapters, null);
        Type localVarReturnType = new TypeToken<MangaList>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Manga list (asynchronously)
     * Search a list of Manga.
     * @param limit  (optional, default to 10)
     * @param offset  (optional)
     * @param title  (optional)
     * @param authors  (optional)
     * @param artists  (optional)
     * @param year Year of release (optional)
     * @param includedTags  (optional)
     * @param includedTagsMode  (optional, default to AND)
     * @param excludedTags  (optional)
     * @param excludedTagsMode  (optional, default to OR)
     * @param status  (optional)
     * @param originalLanguage  (optional)
     * @param excludedOriginalLanguage  (optional)
     * @param availableTranslatedLanguage  (optional)
     * @param publicationDemographic  (optional)
     * @param ids Manga ids (limited to 100 per request) (optional)
     * @param contentRating  (optional)
     * @param createdAtSince  (optional)
     * @param updatedAtSince  (optional)
     * @param order  (optional, default to {&quot;latestUploadedChapter&quot;:&quot;desc&quot;})
     * @param includes  (optional)
     * @param hasAvailableChapters  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Manga list </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getSearchMangaAsync(Integer limit, Integer offset, String title, List<UUID> authors, List<UUID> artists, Integer year, List<UUID> includedTags, String includedTagsMode, List<UUID> excludedTags, String excludedTagsMode, List<String> status, List<String> originalLanguage, List<String> excludedOriginalLanguage, List<String> availableTranslatedLanguage, List<String> publicationDemographic, List<UUID> ids, List<String> contentRating, String createdAtSince, String updatedAtSince, Order order, List<String> includes, String hasAvailableChapters, final ApiCallback<MangaList> _callback) throws ApiException {

        okhttp3.Call localVarCall = getSearchMangaValidateBeforeCall(limit, offset, title, authors, artists, year, includedTags, includedTagsMode, excludedTags, excludedTagsMode, status, originalLanguage, excludedOriginalLanguage, availableTranslatedLanguage, publicationDemographic, ids, contentRating, createdAtSince, updatedAtSince, order, includes, hasAvailableChapters, _callback);
        Type localVarReturnType = new TypeToken<MangaList>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for mangaIdAggregateGet
     * @param id Manga ID (required)
     * @param translatedLanguage  (optional)
     * @param groups  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call mangaIdAggregateGetCall(UUID id, List<String> translatedLanguage, List<UUID> groups, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/manga/{id}/aggregate"
            .replaceAll("\\{" + "id" + "\\}", localVarApiClient.escapeString(id.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (translatedLanguage != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "translatedLanguage[]", translatedLanguage));
        }

        if (groups != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "groups[]", groups));
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call mangaIdAggregateGetValidateBeforeCall(UUID id, List<String> translatedLanguage, List<UUID> groups, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling mangaIdAggregateGet(Async)");
        }
        

        okhttp3.Call localVarCall = mangaIdAggregateGetCall(id, translatedLanguage, groups, _callback);
        return localVarCall;

    }

    /**
     * Get Manga volumes &amp; chapters
     * 
     * @param id Manga ID (required)
     * @param translatedLanguage  (optional)
     * @param groups  (optional)
     * @return InlineResponse200
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     </table>
     */
    public InlineResponse200 mangaIdAggregateGet(UUID id, List<String> translatedLanguage, List<UUID> groups) throws ApiException {
        ApiResponse<InlineResponse200> localVarResp = mangaIdAggregateGetWithHttpInfo(id, translatedLanguage, groups);
        return localVarResp.getData();
    }

    /**
     * Get Manga volumes &amp; chapters
     * 
     * @param id Manga ID (required)
     * @param translatedLanguage  (optional)
     * @param groups  (optional)
     * @return ApiResponse&lt;InlineResponse200&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<InlineResponse200> mangaIdAggregateGetWithHttpInfo(UUID id, List<String> translatedLanguage, List<UUID> groups) throws ApiException {
        okhttp3.Call localVarCall = mangaIdAggregateGetValidateBeforeCall(id, translatedLanguage, groups, null);
        Type localVarReturnType = new TypeToken<InlineResponse200>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get Manga volumes &amp; chapters (asynchronously)
     * 
     * @param id Manga ID (required)
     * @param translatedLanguage  (optional)
     * @param groups  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call mangaIdAggregateGetAsync(UUID id, List<String> translatedLanguage, List<UUID> groups, final ApiCallback<InlineResponse200> _callback) throws ApiException {

        okhttp3.Call localVarCall = mangaIdAggregateGetValidateBeforeCall(id, translatedLanguage, groups, _callback);
        Type localVarReturnType = new TypeToken<InlineResponse200>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for postManga
     * @param contentType  (required)
     * @param mangaCreate The size of the body is limited to 16KB. (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Manga Created </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call postMangaCall(String contentType, MangaCreate mangaCreate, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = mangaCreate;

        // create path and map variables
        String localVarPath = "/manga";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (contentType != null) {
            localVarHeaderParams.put("Content-Type", localVarApiClient.parameterToString(contentType));
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "Bearer" };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call postMangaValidateBeforeCall(String contentType, MangaCreate mangaCreate, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'contentType' is set
        if (contentType == null) {
            throw new ApiException("Missing the required parameter 'contentType' when calling postManga(Async)");
        }
        

        okhttp3.Call localVarCall = postMangaCall(contentType, mangaCreate, _callback);
        return localVarCall;

    }

    /**
     * Create Manga
     * Create a new Manga.
     * @param contentType  (required)
     * @param mangaCreate The size of the body is limited to 16KB. (optional)
     * @return MangaResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Manga Created </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
     </table>
     */
    public MangaResponse postManga(String contentType, MangaCreate mangaCreate) throws ApiException {
        ApiResponse<MangaResponse> localVarResp = postMangaWithHttpInfo(contentType, mangaCreate);
        return localVarResp.getData();
    }

    /**
     * Create Manga
     * Create a new Manga.
     * @param contentType  (required)
     * @param mangaCreate The size of the body is limited to 16KB. (optional)
     * @return ApiResponse&lt;MangaResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Manga Created </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<MangaResponse> postMangaWithHttpInfo(String contentType, MangaCreate mangaCreate) throws ApiException {
        okhttp3.Call localVarCall = postMangaValidateBeforeCall(contentType, mangaCreate, null);
        Type localVarReturnType = new TypeToken<MangaResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Create Manga (asynchronously)
     * Create a new Manga.
     * @param contentType  (required)
     * @param mangaCreate The size of the body is limited to 16KB. (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Manga Created </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call postMangaAsync(String contentType, MangaCreate mangaCreate, final ApiCallback<MangaResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = postMangaValidateBeforeCall(contentType, mangaCreate, _callback);
        Type localVarReturnType = new TypeToken<MangaResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for postMangaIdFollow
     * @param id  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call postMangaIdFollowCall(UUID id, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/manga/{id}/follow"
            .replaceAll("\\{" + "id" + "\\}", localVarApiClient.escapeString(id.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "Bearer" };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call postMangaIdFollowValidateBeforeCall(UUID id, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling postMangaIdFollow(Async)");
        }
        

        okhttp3.Call localVarCall = postMangaIdFollowCall(id, _callback);
        return localVarCall;

    }

    /**
     * Follow Manga
     * 
     * @param id  (required)
     * @return Response
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public Response postMangaIdFollow(UUID id) throws ApiException {
        ApiResponse<Response> localVarResp = postMangaIdFollowWithHttpInfo(id);
        return localVarResp.getData();
    }

    /**
     * Follow Manga
     * 
     * @param id  (required)
     * @return ApiResponse&lt;Response&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Response> postMangaIdFollowWithHttpInfo(UUID id) throws ApiException {
        okhttp3.Call localVarCall = postMangaIdFollowValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<Response>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Follow Manga (asynchronously)
     * 
     * @param id  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call postMangaIdFollowAsync(UUID id, final ApiCallback<Response> _callback) throws ApiException {

        okhttp3.Call localVarCall = postMangaIdFollowValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<Response>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for postMangaIdStatus
     * @param id  (required)
     * @param contentType  (required)
     * @param updateMangaStatus Using a &#x60;null&#x60; value in &#x60;status&#x60; field will remove the Manga reading status. The size of the body is limited to 2KB. (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call postMangaIdStatusCall(UUID id, String contentType, UpdateMangaStatus updateMangaStatus, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = updateMangaStatus;

        // create path and map variables
        String localVarPath = "/manga/{id}/status"
            .replaceAll("\\{" + "id" + "\\}", localVarApiClient.escapeString(id.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (contentType != null) {
            localVarHeaderParams.put("Content-Type", localVarApiClient.parameterToString(contentType));
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "Bearer" };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call postMangaIdStatusValidateBeforeCall(UUID id, String contentType, UpdateMangaStatus updateMangaStatus, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling postMangaIdStatus(Async)");
        }
        
        // verify the required parameter 'contentType' is set
        if (contentType == null) {
            throw new ApiException("Missing the required parameter 'contentType' when calling postMangaIdStatus(Async)");
        }
        

        okhttp3.Call localVarCall = postMangaIdStatusCall(id, contentType, updateMangaStatus, _callback);
        return localVarCall;

    }

    /**
     * Update Manga reading status
     * 
     * @param id  (required)
     * @param contentType  (required)
     * @param updateMangaStatus Using a &#x60;null&#x60; value in &#x60;status&#x60; field will remove the Manga reading status. The size of the body is limited to 2KB. (optional)
     * @return Response
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public Response postMangaIdStatus(UUID id, String contentType, UpdateMangaStatus updateMangaStatus) throws ApiException {
        ApiResponse<Response> localVarResp = postMangaIdStatusWithHttpInfo(id, contentType, updateMangaStatus);
        return localVarResp.getData();
    }

    /**
     * Update Manga reading status
     * 
     * @param id  (required)
     * @param contentType  (required)
     * @param updateMangaStatus Using a &#x60;null&#x60; value in &#x60;status&#x60; field will remove the Manga reading status. The size of the body is limited to 2KB. (optional)
     * @return ApiResponse&lt;Response&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Response> postMangaIdStatusWithHttpInfo(UUID id, String contentType, UpdateMangaStatus updateMangaStatus) throws ApiException {
        okhttp3.Call localVarCall = postMangaIdStatusValidateBeforeCall(id, contentType, updateMangaStatus, null);
        Type localVarReturnType = new TypeToken<Response>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Update Manga reading status (asynchronously)
     * 
     * @param id  (required)
     * @param contentType  (required)
     * @param updateMangaStatus Using a &#x60;null&#x60; value in &#x60;status&#x60; field will remove the Manga reading status. The size of the body is limited to 2KB. (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call postMangaIdStatusAsync(UUID id, String contentType, UpdateMangaStatus updateMangaStatus, final ApiCallback<Response> _callback) throws ApiException {

        okhttp3.Call localVarCall = postMangaIdStatusValidateBeforeCall(id, contentType, updateMangaStatus, _callback);
        Type localVarReturnType = new TypeToken<Response>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for postMangaRelation
     * @param mangaId  (required)
     * @param contentType  (required)
     * @param mangaRelationCreate The size of the body is limited to 8KB. (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Manga relation created </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call postMangaRelationCall(UUID mangaId, String contentType, MangaRelationCreate mangaRelationCreate, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = mangaRelationCreate;

        // create path and map variables
        String localVarPath = "/manga/{mangaId}/relation"
            .replaceAll("\\{" + "mangaId" + "\\}", localVarApiClient.escapeString(mangaId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (contentType != null) {
            localVarHeaderParams.put("Content-Type", localVarApiClient.parameterToString(contentType));
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "Bearer" };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call postMangaRelationValidateBeforeCall(UUID mangaId, String contentType, MangaRelationCreate mangaRelationCreate, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'mangaId' is set
        if (mangaId == null) {
            throw new ApiException("Missing the required parameter 'mangaId' when calling postMangaRelation(Async)");
        }
        
        // verify the required parameter 'contentType' is set
        if (contentType == null) {
            throw new ApiException("Missing the required parameter 'contentType' when calling postMangaRelation(Async)");
        }
        

        okhttp3.Call localVarCall = postMangaRelationCall(mangaId, contentType, mangaRelationCreate, _callback);
        return localVarCall;

    }

    /**
     * Create Manga relation
     * Create a new Manga relation.
     * @param mangaId  (required)
     * @param contentType  (required)
     * @param mangaRelationCreate The size of the body is limited to 8KB. (optional)
     * @return MangaRelationResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Manga relation created </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
     </table>
     */
    public MangaRelationResponse postMangaRelation(UUID mangaId, String contentType, MangaRelationCreate mangaRelationCreate) throws ApiException {
        ApiResponse<MangaRelationResponse> localVarResp = postMangaRelationWithHttpInfo(mangaId, contentType, mangaRelationCreate);
        return localVarResp.getData();
    }

    /**
     * Create Manga relation
     * Create a new Manga relation.
     * @param mangaId  (required)
     * @param contentType  (required)
     * @param mangaRelationCreate The size of the body is limited to 8KB. (optional)
     * @return ApiResponse&lt;MangaRelationResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Manga relation created </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<MangaRelationResponse> postMangaRelationWithHttpInfo(UUID mangaId, String contentType, MangaRelationCreate mangaRelationCreate) throws ApiException {
        okhttp3.Call localVarCall = postMangaRelationValidateBeforeCall(mangaId, contentType, mangaRelationCreate, null);
        Type localVarReturnType = new TypeToken<MangaRelationResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Create Manga relation (asynchronously)
     * Create a new Manga relation.
     * @param mangaId  (required)
     * @param contentType  (required)
     * @param mangaRelationCreate The size of the body is limited to 8KB. (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Manga relation created </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call postMangaRelationAsync(UUID mangaId, String contentType, MangaRelationCreate mangaRelationCreate, final ApiCallback<MangaRelationResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = postMangaRelationValidateBeforeCall(mangaId, contentType, mangaRelationCreate, _callback);
        Type localVarReturnType = new TypeToken<MangaRelationResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for putMangaId
     * @param id Manga ID (required)
     * @param contentType  (required)
     * @param UNKNOWN_BASE_TYPE The size of the body is limited to 16KB. (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call putMangaIdCall(UUID id, String contentType, UNKNOWN_BASE_TYPE UNKNOWN_BASE_TYPE, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = UNKNOWN_BASE_TYPE;

        // create path and map variables
        String localVarPath = "/manga/{id}"
            .replaceAll("\\{" + "id" + "\\}", localVarApiClient.escapeString(id.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (contentType != null) {
            localVarHeaderParams.put("Content-Type", localVarApiClient.parameterToString(contentType));
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "Bearer" };
        return localVarApiClient.buildCall(localVarPath, "PUT", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call putMangaIdValidateBeforeCall(UUID id, String contentType, UNKNOWN_BASE_TYPE UNKNOWN_BASE_TYPE, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling putMangaId(Async)");
        }
        
        // verify the required parameter 'contentType' is set
        if (contentType == null) {
            throw new ApiException("Missing the required parameter 'contentType' when calling putMangaId(Async)");
        }
        

        okhttp3.Call localVarCall = putMangaIdCall(id, contentType, UNKNOWN_BASE_TYPE, _callback);
        return localVarCall;

    }

    /**
     * Update Manga
     * 
     * @param id Manga ID (required)
     * @param contentType  (required)
     * @param UNKNOWN_BASE_TYPE The size of the body is limited to 16KB. (optional)
     * @return MangaResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public MangaResponse putMangaId(UUID id, String contentType, UNKNOWN_BASE_TYPE UNKNOWN_BASE_TYPE) throws ApiException {
        ApiResponse<MangaResponse> localVarResp = putMangaIdWithHttpInfo(id, contentType, UNKNOWN_BASE_TYPE);
        return localVarResp.getData();
    }

    /**
     * Update Manga
     * 
     * @param id Manga ID (required)
     * @param contentType  (required)
     * @param UNKNOWN_BASE_TYPE The size of the body is limited to 16KB. (optional)
     * @return ApiResponse&lt;MangaResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<MangaResponse> putMangaIdWithHttpInfo(UUID id, String contentType, UNKNOWN_BASE_TYPE UNKNOWN_BASE_TYPE) throws ApiException {
        okhttp3.Call localVarCall = putMangaIdValidateBeforeCall(id, contentType, UNKNOWN_BASE_TYPE, null);
        Type localVarReturnType = new TypeToken<MangaResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Update Manga (asynchronously)
     * 
     * @param id Manga ID (required)
     * @param contentType  (required)
     * @param UNKNOWN_BASE_TYPE The size of the body is limited to 16KB. (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call putMangaIdAsync(UUID id, String contentType, UNKNOWN_BASE_TYPE UNKNOWN_BASE_TYPE, final ApiCallback<MangaResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = putMangaIdValidateBeforeCall(id, contentType, UNKNOWN_BASE_TYPE, _callback);
        Type localVarReturnType = new TypeToken<MangaResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
