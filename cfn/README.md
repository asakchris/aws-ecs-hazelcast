### Deploy in AWS ECS
###### Create new VPC, 2 public subnet and 2 private subnet
```
aws cloudformation deploy --template-file vpc.yml --stack-name TEST-VPC
```
###### Deploy application in ECS cluster
When message service container comes up, it goes and register in AWS service discovery and it can be accessed using 'messageservice.messagesvcnamespace' in the same VPC.
```
aws cloudformation deploy --template-file service.yml --stack-name HZ-SERVICE 
    --capabilities CAPABILITY_NAMED_IAM 
    --parameter-overrides VpcId=vpc-******* 
    PublicSubnetList="subnet-************, subnet-***********" 
    PrivateSubnetList="subnet-***********, subnet-***********"
```