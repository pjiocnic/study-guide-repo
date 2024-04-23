# From Google AI

Geoproximity routing in Amazon Route 53 directs traffic to the closest resource based on the geographic location of the user and the resource. You can also choose to route more or less traffic to a resource by specifying a value, known as a bias. For example, if you have web servers in London and Frankfurt, and a user originates from France, the geolocation policy will redirect the user to London because France is closer.

Geoproximity is different from geolocation, which directs traffic based on the geographic location of the request. Geoproximity is a specific technique to route traffic to web servers, while geolocation refers to the process of identifying the location of your users by means of digital information that is available on the internet (for example, their IP addresses)

- https://www.abstractapi.com/guides/geoproximity-vs-geolocation