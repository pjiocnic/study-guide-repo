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

IF (SRCE == AS)
  IF COLLECTION EMPTY
    MAKE SRCE-AS ACTIVE AND ADD
  ELSE
    FIND-ACTIVE-ED: IF COLLECTION HAS ACTIVE SRCE == ED
      CREATE SRCE-AS, MAKE INACTIVE AND ADD
    ELSE
      FIND-ACTIVE-AS: FIND LATEST-EXISTING-AS
      IF LATEST-EXISTING-AS AFTER SRCE-AS
        CREATE SRCE-AS, MAKE INACTIVE, CREATE INACTIVE JL-SRCE AND ADD
      ELSE LATEST-EXISTING-AS BEFORE SRCE-AS
        MAKE LATEST-EXISTING-AS INACTIVE; MAKE JL-SRCE INACTIVE, CREATE ACIIVE SRCE-AS, CREATE ACTIVE JL-SRCE AND ADD
ELSE (SRCE == ED)
  IF COLLECTION EMPTY
    MAKE SRCE-ED ACTIVE AND ADD
  ELSE
    FIND LATEST-EXISTING-AS
      MAKE LATEST-EXISTING-AS INACTIVE; MAKE INACTIVE JL-SRCE
    FIND LATEST-EXISTING-ED
    IF LATEST-EXISTING-ED AFTER SRCE-ED
      CREATE SRCE-ED, MAKE INACTIVE AND ADD
    ELSE LATEST-EXISTING-ED BEFORE SRCE-ED
      MAKE LATEST-EXISTING-ED INACTIVE; MAKE SRCE-ED AND ADD

