

# Relationship

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **UUID** |  |  [optional]
**type** | **String** |  |  [optional]
**related** | [**RelatedEnum**](#RelatedEnum) | Related Manga type, only present if you are on a Manga entity and a Manga relationship |  [optional]
**attributes** | **Object** | If Reference Expansion is applied, contains objects attributes |  [optional]



## Enum: RelatedEnum

Name | Value
---- | -----
MONOCHROME | &quot;monochrome&quot;
MAIN_STORY | &quot;main_story&quot;
ADAPTED_FROM | &quot;adapted_from&quot;
BASED_ON | &quot;based_on&quot;
PREQUEL | &quot;prequel&quot;
SIDE_STORY | &quot;side_story&quot;
DOUJINSHI | &quot;doujinshi&quot;
SAME_FRANCHISE | &quot;same_franchise&quot;
SHARED_UNIVERSE | &quot;shared_universe&quot;
SEQUEL | &quot;sequel&quot;
SPIN_OFF | &quot;spin_off&quot;
ALTERNATE_STORY | &quot;alternate_story&quot;
PRESERIALIZATION | &quot;preserialization&quot;
COLORED | &quot;colored&quot;
SERIALIZATION | &quot;serialization&quot;



