
1. How to use this technique in circuit breaker pattern?
https://youtu.be/6X4lSPkn4ps?t=2399

# Key Points
src: https://youtu.be/6X4lSPkn4ps?t=2482
1. event Bridge does not guarantee the order of replayed events
1. There is no option to control the speed of replay
1. Use multiple single-purpose archives instead of one archive for all events.
1. Use the archive retention days as appropriate for the use case.
1. There can be a delay of 5 mins before an event appears in the replay.

# Addition material

1. [[WORKSHOP] Archive and Replay](https://catalog.us-east-1.prod.workshops.aws/workshops/63320e83-6abc-493d-83d8-f822584fb3cb/en-US/eventbridge/archive-replay/replay)
2. [Archiving and replaying events with Amazon EventBridge by James Beswick](https://aws.amazon.com/blogs/compute/archiving-and-replaying-events-with-amazon-eventbridge/)
3. [Amazon EventBridge: Archive & Replay Events In Tandem With A Circuit-Breaker By Sheen Brisals](https://sbrisals.medium.com/amazon-eventbridge-archive-replay-events-in-tandem-with-a-circuit-breaker-c049a4c6857f)