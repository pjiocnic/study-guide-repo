
# Testing

Can you reach the internet? - ping aws.amazon.com
What is your private ip address? - ip address
What is your public ip address? - curl ipinfo.io/json or curl ifconfig.io
What route are in this instance? - ip route
What services or IPs are connected to this instance? sudo netstat -pant

# How to handle existing AWS infrastructure

```bash
cd ~/environment
git clone https://github.com/aws-samples/aws2tf.git

cd ~/environment/aws2tf
./aws2tf.sh

The generated *.tf is here

cd ~/environment/aws2tf/generated/tf.*

# Validate generated files is same as aws state
terraform plan
```