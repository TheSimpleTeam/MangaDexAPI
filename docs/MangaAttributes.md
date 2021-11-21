

# MangaAttributes

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**title** | **Map&lt;String, String&gt;** |  |  [optional]
**altTitles** | **List&lt;Map&lt;String, String&gt;&gt;** |  |  [optional]
**description** | **Map&lt;String, String&gt;** |  |  [optional]
**isLocked** | **Boolean** |  |  [optional]
**links** | **Map&lt;String, String&gt;** |  |  [optional]
**originalLanguage** | **String** |  |  [optional]
**lastVolume** | **String** |  |  [optional]
**lastChapter** | **String** |  |  [optional]
**publicationDemographic** | [**PublicationDemographicEnum**](#PublicationDemographicEnum) |  |  [optional]
**status** | **String** |  |  [optional]
**year** | **Integer** | Year of release |  [optional]
**contentRating** | [**ContentRatingEnum**](#ContentRatingEnum) |  |  [optional]
**tags** | [**List&lt;Tag&gt;**](Tag.md) |  |  [optional]
**state** | [**StateEnum**](#StateEnum) |  |  [optional]
**version** | **Integer** |  |  [optional]
**createdAt** | **String** |  |  [optional]
**updatedAt** | **String** |  |  [optional]



## Enum: PublicationDemographicEnum

Name | Value
---- | -----
SHOUNEN | &quot;shounen&quot;
SHOUJO | &quot;shoujo&quot;
JOSEI | &quot;josei&quot;
SEINEN | &quot;seinen&quot;



## Enum: ContentRatingEnum

Name | Value
---- | -----
SAFE | &quot;safe&quot;
SUGGESTIVE | &quot;suggestive&quot;
EROTICA | &quot;erotica&quot;
PORNOGRAPHIC | &quot;pornographic&quot;



## Enum: StateEnum

Name | Value
---- | -----
DRAFT | &quot;draft&quot;
SUBMITTED | &quot;submitted&quot;
PUBLISHED | &quot;published&quot;
REJECTED | &quot;rejected&quot;



