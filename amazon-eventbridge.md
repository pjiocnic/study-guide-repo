
<!-- TOC -->

- [Design](#design)
- [1. Eventbridge Rules](#1-eventbridge-rules)
- [2. Event Broker](#2-event-broker)
- [3. Integration](#3-integration)
  - [3.1. ECS](#31-ecs)
  - [3.2. Glue](#32-glue)
  - [3.3. DynamoDBStreams](#33-dynamodbstreams)
- [4. Patterns](#4-patterns)
  - [4.1. Webhooks](#41-webhooks)
  - [4.2. Orchestrator](#42-orchestrator)
  - [4.3. Transactional outbox pattern](#43-transactional-outbox-pattern)
  - [4.4. Content enrichment pattern](#44-content-enrichment-pattern)
  - [4.5. Claim Check Pattern](#45-claim-check-pattern)
- [5. SkillBuilder Course](#5-skillbuilder-course)
- [6. Schema validation](#6-schema-validation)
- [7. Scheduling](#7-scheduling)

<!-- /TOC -->

# Design

1.[Icons for Eventbridge, Eventbridge event and Eventbridge Rule](https://aws.amazon.com/blogs/industries/building-a-modern-event-driven-application-for-insurance-claims-processing-part-2/)

# 1. Eventbridge Rules

1. [Reducing custom code by using advanced rules in Amazon EventBridge by James Beswick](https://aws.amazon.com/blogs/compute/reducing-custom-code-by-using-advanced-rules-in-amazon-eventbridge/)

# 2. Event Broker

1. [Building a modern, event-driven application for insurance claims processing – Part 1 by Emily Shea and Dhiraj Mahapatro](https://aws.amazon.com/blogs/industries/building-a-modern-event-driven-application-for-insurance-claims-processing-part-1/)
2. [Building a modern, event-driven application for insurance claims processing – Part 2 by Emily Shea, Vaibhav Jain, and Dhiraj Mahapatro](https://aws.amazon.com/blogs/industries/building-a-modern-event-driven-application-for-insurance-claims-processing-part-2/)
3. [Extending a serverless, event-driven architecture to existing container workloads by Dhiraj Mahapatro and Sascha Moellering](https://aws.amazon.com/blogs/compute/extending-a-serverless-event-driven-architecture-to-existing-container-workloads/)

# 3. Integration

## 3.1. ECS

1. [Integrating Amazon EventBridge and Amazon ECS By Jakub Narloch](https://aws.amazon.com/blogs/compute/integrating-amazon-eventbridge-and-amazon-ecs/)

## 3.2. Glue

1. [Build a serverless event-driven workflow with AWS Glue and Amazon EventBridge by Noritaka Sekiyama, Keerthi Chadalavada, and Karan Vishwanathan](https://aws.amazon.com/blogs/big-data/build-a-serverless-event-driven-workflow-with-aws-glue-and-amazon-eventbridge/)
- /Volumes/Lexar/git-repos/aws-repo/my-aws-samples/eventbridge/event-driven-workflow-tutorial
- [event_driven_workflow_tutorial.yaml](./templates/event_driven_workflow_tutorial.yaml)]

## 3.3. DynamoDBStreams
1. [Implementing the transactional outbox pattern with Amazon EventBridge Pipes By Sayan Moitra](https://aws.amazon.com/blogs/compute/implementing-the-transactional-outbox-pattern-with-amazon-eventbridge-pipes/)

# 4. Patterns

## 4.1. Webhooks

1. [[WebHooks] Amazon Aurora & Amazon EventBridge Webhooks Sample](https://github.com/aws-samples/amazon-aurora-eventbridge-webhooks)

## 4.2. Orchestrator

1. [{Orchestrator] Insurance Claims Processing using Serverless and Event-Driven Architecture](https://github.com/aws-samples/serverless-eda-insurance-claims-processing)

## 4.3. Transactional outbox pattern

1. [[Transactional outbox pattern ] Implementing the transactional outbox pattern with Amazon EventBridge Pipes By Sayan Moitra](https://aws.amazon.com/blogs/compute/implementing-the-transactional-outbox-pattern-with-amazon-eventbridge-pipes/)

## 4.4. Content enrichment pattern

1. [Enriching events with Amazon EventBridge and AWS Lambda](https://www.boyney.io/blog/2022-11-01-eventbridge-enrichment-with-lambda)

## 4.5. Claim Check Pattern

1. [S3 to EventBridge claim check with presigned urls](https://serverlessland.com/patterns/s3-to-eventbridge-claim-check-pattern)
1. [How to publish large events with Amazon EventBridge using the claim check pattern](https://www.boyney.io/blog/2022-11-01-eventbridge-claim-check)
1. [Claim Check Pattern by David Boyne](https://serverlessland.com/event-driven-architecture/visuals/claim-check-pattern)

# 5. SkillBuilder Course

1. [Building Event-Driven Applications With Amazon EventBridge](https://explore.skillbuilder.aws/learn/course/15008/building-event-driven-applications-with-amazon-eventbridge)

# 6. Schema validation

1. [Working with events and the Amazon EventBridge schema registry by Talia Nassi ](https://aws.amazon.com/blogs/compute/working-with-events-and-amazon-eventbridge-schema-registry/)
1. [Introducing Amazon EventBridge schema registry and discovery – In preview by Julian Wood](https://aws.amazon.com/blogs/compute/introducing-amazon-eventbridge-schema-registry-and-discovery-in-preview/)
1. [Event Bridge by Lumigo](https://lumigo.io/aws-serverless-ecosystem/amazon-eventbridge/#eventbridge_schema_registry)
1. [Sending and Receiving Custom Events with AWS EventBridge Schema Registry By Dunith Dhanushka](https://medium.com/event-driven-utopia/sending-and-receiving-custom-events-with-aws-eventbridge-schema-registry-b73198b7a140)

# 7. Scheduling

1. [Introducing Amazon EventBridge Scheduler by Marcia Villalba](https://aws.amazon.com/blogs/compute/introducing-amazon-eventbridge-scheduler/)
2. [Serverless Scheduling with Amazon EventBridge, AWS Lambda, and Amazon DynamoDB by Peter Grman](https://aws.amazon.com/blogs/architecture/serverless-scheduling-with-amazon-eventbridge-aws-lambda-and-amazon-dynamodb/)
- see /Volumes/Lexar/git-repos/aws-repo/my-aws-samples/event-bridge/scheduling