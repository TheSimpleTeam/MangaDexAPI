

# MangaCreate

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**title** | **Map&lt;String, String&gt;** |  | 
**altTitles** | **List&lt;Map&lt;String, String&gt;&gt;** |  |  [optional]
**description** | **Map&lt;String, String&gt;** |  |  [optional]
**authors** | **List&lt;UUID&gt;** |  |  [optional]
**artists** | **List&lt;UUID&gt;** |  |  [optional]
**links** | **Map&lt;String, String&gt;** |  |  [optional]
**originalLanguage** | **String** |  | 
**lastVolume** | **String** |  |  [optional]
**lastChapter** | **String** |  |  [optional]
**publicationDemographic** | [**PublicationDemographicEnum**](#PublicationDemographicEnum) |  |  [optional]
**status** | [**StatusEnum**](#StatusEnum) |  | 
**year** | **Integer** | Year of release |  [optional]
**contentRating** | [**ContentRatingEnum**](#ContentRatingEnum) |  | 
**tags** | **List&lt;UUID&gt;** |  |  [optional]
**primaryCover** | **UUID** |  |  [optional]
**version** | **Integer** |  |  [optional]



## Enum: PublicationDemographicEnum

Name | Value
---- | -----
SHOUNEN | &quot;shounen&quot;
SHOUJO | &quot;shoujo&quot;
JOSEI | &quot;josei&quot;
SEINEN | &quot;seinen&quot;



## Enum: StatusEnum

Name | Value
---- | -----
ONGOING | &quot;ongoing&quot;
COMPLETED | &quot;completed&quot;
HIATUS | &quot;hiatus&quot;
CANCELLED | &quot;cancelled&quot;



## Enum: ContentRatingEnum

Name | Value
---- | -----
SAFE | &quot;safe&quot;
SUGGESTIVE | &quot;suggestive&quot;
EROTICA | &quot;erotica&quot;
PORNOGRAPHIC | &quot;pornographic&quot;


