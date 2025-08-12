terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }
}

provider "aws" {
  region = var.region
}

variable "region" {
  type    = string
  default = "us-east-1"
}

variable "function_name" {
  type    = string
  default = "lambda-config-sample"
}

variable "param_db_url" {
  type    = string
  default = "/myapp/prod/db/url"
}

variable "secret_db" {
  type    = string
  default = "myapp/prod/dbCredentials"
}

data "aws_iam_policy_document" "assume" {
  statement {
    actions = ["sts:AssumeRole"]
    principals {
      type        = "Service"
      identifiers = ["lambda.amazonaws.com"]
    }
  }
}

resource "aws_iam_role" "lambda_role" {
  name               = "${var.function_name}-role"
  assume_role_policy = data.aws_iam_policy_document.assume.json
}

data "aws_iam_policy_document" "policy" {
  statement {
    actions = [
      "ssm:GetParameter",
      "ssm:GetParameters",
      "ssm:GetParametersByPath"
    ]
    resources = ["*"]
  }
  statement {
    actions = ["secretsmanager:GetSecretValue"]
    resources = ["*"]
  }
  statement {
    actions = [
      "logs:CreateLogGroup",
      "logs:CreateLogStream",
      "logs:PutLogEvents"
    ]
    resources = ["*"]
  }
}

resource "aws_iam_role_policy" "lambda_inline" {
  name   = "${var.function_name}-policy"
  role   = aws_iam_role.lambda_role.id
  policy = data.aws_iam_policy_document.policy.json
}

resource "aws_lambda_function" "fn" {
  function_name = var.function_name
  role          = aws_iam_role.lambda_role.arn
  handler       = "com.example.lambda.Handler::handleRequest"
  runtime       = "java17"
  filename      = "${path.module}/../target/lambda-config-sample-0.0.2-shaded.jar"
  source_code_hash = filebase64sha256("${path.module}/../target/lambda-config-sample-0.0.2-shaded.jar")

  memory_size = 512
  timeout     = 30

  environment {
    variables = {
      PARAM_DB_URL = var.param_db_url
      SECRET_DB    = var.secret_db
    }
  }
}
