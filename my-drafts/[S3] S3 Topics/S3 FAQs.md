
1. For an application in the same account as the bucket access can be granted by either an identity-based policy or a resource-based policy
2. you can’t attach a bucket policy to an S3 object. bucket policy apply to all of the objects in the bucket
3. can also specify permissions at the object level by putting an object as the resource in the bucket policy

4. By default in S3, when you upload an object with the same name as an existing object, the new object overwrites the existing one. However, when you enable versioning in a S3 bucket, the service stores every version of an object.