IF (SRCE == AS)
  NEW-SRCE-AS = CREATE SRCE-AS + JL-SRCE
  IF COLLECTION EMPTY
    NEW-SRCE-AS -> MAKE ACTIVE, ADD ONLY IF ABSENT
  ELSE
    LATEST-EXISTING-ED = findLatestActive (srce= ED)
    LATEST-EXISTING-ED exists THEN
      NEW-SRCE-AS -> MAKE INACTIVE, ADD ONLY IF ABSENT
    ELSE
      LATEST-EXISTING-AS = findLatestActive (srce= AS)
      IF LATEST-EXISTING-AS AFTER NEW-SRCE-AS
        NEW-SRCE-AS -> MAKE INACTIVE, ADD ONLY IF ABSENT
      ELSE
        MAKE LATEST-EXISTING-AS + JL-SRCE INACTIVE,
        NEW-SRCE-AS -> MAKE ACTIVE, ADD ONLY IF ABSENT
ELSE (SRCE == ED)
  NEW-SRCE-ED = CREATE SRCE-ED + JL-SRCE
  IF COLLECTION EMPTY
    NEW-SRCE-ED, MAKE ACTIVE, ADD ONLY IF ABSENT
  ELSE
    LATEST-EXISTING-AS = findLatestActive (srce= AS)
      MAKE LATEST-EXISTING-AS + JL-SRCE INACTIVE
    FIND LATEST-EXISTING-ED = findLatestActive (srce= ED)
    IF LATEST-EXISTING-ED AFTER NEW-SRCE-ED
      NEW-SRCE-ED -> MAKE INACTIVE, ADD ONLY IF ABSENT
    ELSE LATEST-EXISTING-ED BEFORE NEW-SRCE-ED
      MAKE LATEST-EXISTING-ED + JL-SRCE INACTIVE
      NEW-SRCE-ED -> MAKE ACTIVE, ADD ONLY IF ABSENT

create JL + JL-SRCE from srce
makeActive / makeInactive (takes in JL with JL-SRCE)
findLatestActive(srce=AS/ED)
isBefore()
isAfter()
addIfAbsent (DP + ARVL not same)

# Staged material

Find - If Found proceed to handle sources
Find - If NOT Found, create and add to collection

Adding to collection -

AS - if collection empty, Add and make self active
ED - if collection empty, Add and make self active

AS - if ED present, Add and end date self
AS - if ED NOT present, make self active if latest transmission.
AS - if ED NOT present, end date self if older transmission

ED - Add, make active and end date any previous active

--------------------------------------------------------------

Step 1: FIND JL
  - Use exact match of DPRT/DEP-DTTM, APRT/ARVL-DTTM
  - IF NOT FOUND proceed to Step # 2
  - IF FOUND proceed to Step # 3

Step 2: Adding to JL collection -
