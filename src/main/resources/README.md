SmiddleCampaignManager database migrator from ver. 1.3 to 1.4.
Updates table CM_RESULT, set CM_RESULT.LAST_RESULT=1 for last CM_RESULT for such CM_ABONENT and CM_RESULT_CODE.
Required JRE/JDK 1.8. 
Start from command line: java -jar ./scm_migrator.jar [id1,id2...]
[id1,id2...] - list CM_RESULT_CODE.ID should be skipped.
11.07.2017