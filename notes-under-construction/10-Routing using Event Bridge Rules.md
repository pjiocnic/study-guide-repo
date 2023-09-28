# 10-Routing using Event Bridge Rules

<img src="./images/eventbridge-content-filtering-1.png" title="event Bridge filtering" width="900"/>

# JSON structure of Eventbridge Payload

top-level envelope fields are Source, EventBusName, DetailType, Detail

## AWS Event

```json
{
  "Source": string,
  "EventBusName": string,
  "DetailType": string,
  "Detail": string      // body of the event.  This has to be a string
}
```

## Custom Events

```json
{
  "Source": "custom.myATMapp",
  "EventBusName": "default",
  "DetailType": "transaction",
  "Detail": "{\"action\":\"withdrawal”,\"amount\":300}"
}
```