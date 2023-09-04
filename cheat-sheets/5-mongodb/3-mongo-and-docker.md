<h1>1-How to run mongo in a docker container</h1>

```javascript {.line-numbers}
docker pull mongo
docker run -d --name mongodb602 \
  -p 27417:27017 \
  -e MONGO_INITDB_ROOT_USERNAME=ruser1 \
  -e MONGO_INITDB_ROOT_PASSWORD=rpassw1 \
  -v $HOME/DOCKER/dockerMongoDB/datafiles602:/data/db \
  mongo:latest
```
For windows

docker volume create --name=mongodata
docker run -d --name mongodb602 ^
  -p 27417:27017 ^
  -e MONGO_INITDB_ROOT_USERNAME=ruser1 ^
  -e MONGO_INITDB_ROOT_PASSWORD=rpassw1 ^
  -v mongodata:/data/db ^
  mongo:latest

```javascript {.line-numbers}
docker ps -a
ONTAINER ID   IMAGE          COMMAND                  CREATED          STATUS          PORTS                      NAMES
d2a3c47476f2   mongo:latest   "docker-entrypoint.s…"   40 seconds ago   Up 36 seconds   0.0.0.0:27417->27017/tcp   mongodb602
b5f53adeeb5a   654c1214396e   "node app.js"            3 minutes ago    Up 3 minutes                               k8s_kiada_kiada-774d4d6486-t4sx2_default_6eba542a-531d-4836-b323-5fbd0329cc6d_10
```

```javascript {.line-numbers}
docker stop mongodb602
docker start mongodb602
```

```javascript {.line-numbers}
docker exec -it mongodb602 bash
root@d2a3c47476f2:/# uname -a
Linux d2a3c47476f2 5.10.104-linuxkit #1 SMP Thu Mar 17 17:08:06 UTC 2022 x86_64 x86_64 x86_64 GNU/Linux
root@d2a3c47476f2:/# cat /proc/version
Linux version 5.10.104-linuxkit (root@buildkitsandbox) (gcc (Alpine 10.2.1_pre1) 10.2.1 20201203, GNU ld (GNU Binutils) 2.35.2) #1 SMP Thu Mar 17 17:08:06 UTC 2022

root@d2a3c47476f2:/# cat /etc/os-release
PRETTY_NAME="Ubuntu 22.04.2 LTS"
NAME="Ubuntu"
VERSION_ID="22.04"
VERSION="22.04.2 LTS (Jammy Jellyfish)"
VERSION_CODENAME=jammy
ID=ubuntu
ID_LIKE=debian
HOME_URL="https://www.ubuntu.com/"
SUPPORT_URL="https://help.ubuntu.com/"
BUG_REPORT_URL="https://bugs.launchpad.net/ubuntu/"
PRIVACY_POLICY_URL="https://www.ubuntu.com/legal/terms-and-policies/privacy-policy"
UBUNTU_CODENAME=jammy
```

```javascript {.line-numbers}
root@d2a3c47476f2:/# mongosh --username ruser1 --password rpassw1
Current Mongosh Log ID: 6445c1113a7fa85bc9386969
Connecting to:          mongodb://<credentials>@127.0.0.1:27017/?directConnection=true&serverSelectionTimeoutMS=2000&appName=mongosh+1.8.0
Using MongoDB:          6.0.5
Using Mongosh:          1.8.0

For mongosh info see: https://docs.mongodb.com/mongodb-shell/


To help improve our products, anonymous usage data is collected and sent to MongoDB periodically (https://www.mongodb.com/legal/privacy-policy).
You can opt-out by running the disableTelemetry() command.

------
   The server generated these startup warnings when booting
   2023-04-23T23:32:20.666+00:00: vm.max_map_count is too low
------

------
   Enable MongoDB's free cloud-based monitoring service, which will then receive and display
   metrics about your deployment (disk utilization, CPU, operation statistics, etc).

   The monitoring data will be available on a MongoDB website with a unique URL accessible to you
   and anyone you share the URL with. MongoDB may use this information to make product
   improvements and to suggest MongoDB products and deployment options to you.

   To enable free monitoring, run the following command: db.enableFreeMonitoring()
   To permanently disable this reminder, run the following command: db.disableFreeMonitoring()
------

test>
```


Instead if you want to connect from macos (after downloading mongosh separately)

```javascript {.line-numbers}
$ mongosh "mongodb://192.168.0.19:27417" --username ruser1 --password rpassw1
```

# References
1. https://medium.com/@zzpzaf.se/mongodb-in-docker-bfa77346b389