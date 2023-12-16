To prevent document deletion, you need to ensure that the user associated with the application or individual has the necessary permissions. Here are the general steps to achieve this:

1. Create a User with Limited Permissions:

Connect to the MongoDB server using a MongoDB client or the MongoDB shell.
Create a user with the necessary permissions. Make sure to grant read and write permissions but exclude delete permissions.

```bash
db.createUser({
  user: "yourUsername",
  pwd: "yourPassword",
  roles: [
    { role: "readWrite", db: "yourDatabase" },
    // Add other roles as needed, but exclude delete-related roles
  ]
})

```

2. Use Role-Based Access Control:

MongoDB supports role-based access control (RBAC). You can create custom roles that have specific privileges. Create a role without the "remove" action to prevent document deletion.

```bash
db.createRole({
  role: "readWriteWithoutDelete",
  privileges: [
    { resource: { db: "yourDatabase", collection: "yourCollection" }, actions: ["find", "insert", "update"] }
    // Add other privileges as needed
  ],
  roles: []
})

```

3. Grant the Custom Role to the User:

```bash
db.grantRolesToUser("yourUsername", ["readWriteWithoutDelete"])
```