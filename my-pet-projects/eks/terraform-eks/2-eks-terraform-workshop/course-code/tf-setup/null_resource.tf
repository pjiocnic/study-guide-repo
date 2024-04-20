resource "null_resource" "gen_backend" {
  triggers = {
    always_run = timestamp() # resource is re-run every time the timestamp changes ie make the resource always run
  }
  # run after following dependencies have been created or updated
  depends_on = [aws_dynamodb_table.terraform_locks,aws_s3_bucket_server_side_encryption_configuration.terraform_state]
  provisioner "local-exec" {
    when    = create # Specifies that the provisioner should only run during the resource creation phase, not during updates
    # sleeps for 6 seconds and then runs the gen-backend.sh script
    command = <<EOT
        sleep 6
        ./gen-backend.sh
    EOT
  }
}
