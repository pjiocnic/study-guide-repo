# How versioning is used

src: https://aws.amazon.com/blogs/compute/building-a-difference-checker-with-amazon-s3-and-aws-lambda/#Life-cycling earlier versions of an S3 object

When versioning is enabled on a bucket, S3 adds a `VersionId` attribute to an object when it is created. This identifier is a `random string` instead of a sequential identifier. Listing the versions of an object also returns a `LastModified` attribute, which can be used to determine the order of the versions. The length of the response array also indicates the number of versions available for an object

```json
[
  {
    Key: 'test.txt',
    VersionId: 'IX_tyuQrgKpMFfq5YmLOlrtaleRBQRE', // Not a sequential number
    IsLatest: false,
    LastModified: 2021-08-01T18:48:50.000Z, // used to determine the order of the versions
  },
  {
    Key: 'test.txt',
    VersionId: 'XNpxNgUYhcZDcI9Q9gXCO9_VRLlx1i.',
    IsLatest: false,
    LastModified: 2021-08-01T18:52:58.000Z,
  },
  {
    Key: 'test.txt',
    VersionId: 'RBk2BUIKcYYt4hNA5hrTVdNit.MDNMZ',
    IsLatest: true,
    LastModified: 2021-08-01T18:53:26.000Z,
  }
]
```