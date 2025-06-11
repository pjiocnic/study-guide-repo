# 1. Journey Leg management

## 1.1 Step 1: Adding Journey Legs

- If Journey Leg from MANADD, end date

- If different Itinerary just add NEW JL + JLSRCE
- If same Itinerary, only add NEW sources

- Create utility methods like:
    - addJLIfAbsent
    - addJLSrceIfAbsent
    - findJLWithMatchingItinerary

## 1.2. Step 2: Deactivating (OLD VERSION)

- If collection size == 1. Do nothing (since this is the first add)
- Get all ACTIVE ENOADs. Deactivate the one before the latest IF ANY FOUND. LOG and End date all APISs if ANY.
- If No ENOADs, get all ACTIVE APISs, If more than 2 LOG. End date everything before the latest.

## 1.3. Step 2: Deactivating (New Version)

1. if (active APIS ONLY > 0  && active ENOAD > 0)  THEN deactivate ALL APIS JLs
2. if (active ENOAD > 0) THEN except for latest deactivate all ENOAD JLs
3. if (active APIS ONLY > 0 && active ENOAD == 0) THEN except for latest deactivate all APIS JLs

## 1.4. Best Rep

- Any time either ENOAD or APIS should be Active. Log if an anamoly seen.

## Utility methods like:
    - addJLIfAbsent
    - addJLSrceIfAbsent
    - findJLWithMatchingItinerary
    - findActiveENOADJLs
    - findActiveAPISJLs
    - findActiveJLsPriorToJL
    - findActiveJLSrceBySrceIdAndSrceNm

# 2. Name management

## 2.1. Step 1: Adding Names

- If NAME from MANADD, set Augmentation as MANADD ELSE FEEDS
- If same Name, only add Srce
- If different name, add NEW Name + Srce

## 2.2. Step 2: Deactivating (OLD VERSION)

1. If collection size == 1. Do nothing (since this is the first add)
1. Get all ACTIVE ENOADs and ACTIVE APISs (when latest ADD is an ENOAD so #ENOADs = 1, #APISs = 2)
    - Deactivate ENOADs if Active APIS > 0
    - Deactivate the one before the latest if Active APIS == 0 (when #ENOADs = 2, #APISs = 0)
    - LOG if ACTIVE ENOADs size > 2
2. Handle MANADDs - Get Active APISs
    - IF MANADD FOUND, Deactive the REST of APIS, Deativate older MANAdd
3. Handle Regular APISs -  group by APIS IDs (at this stage # ENOADs = 0, # MANADDs = 0)
  - For each APIS Id, if more than 2 ACTIVE THEN log.
  - If Only 2, then deactivate one more the latest

## 2.2 Deactivating (NEW VERSION)

1. if (active APIS == 0 && active ENOAD ONLY > 0) THEN make ONLY latest ENOAD active
2. if (active APIS > 0 && active ENOAD ONLY > 0)  THEN deactivate all ENOADs
3. if (active APIS > 0) THEN group by APIS IDs AND for each ACTIVE group deactivate ALL except the latest

NOTE: dont worry about MAN ADDs here and consider them only during BestInd determination

## Utility methods like:
    - addNmIfAbsent
    - addNmSrceIfAbsent
    - findNmWithMatchingName
    - findActiveENOADNms
    - findActiveAPISNms
    - findActiveManAddNms
    - findActiveNmsPriorToNm
    - findActiveNmSrceBySrceIdAndSrceNm

