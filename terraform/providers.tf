terraform {
  required_version = "= 1.9.6"

  required_providers {
    aws = {
      source = "hashicorp/aws"
      version = "~> 5.68.0"
    }
  }

#   backend "s3" {
#     bucket = "workshops-1-terraform-state-eun1-448740566997"
#     key = "web-server.tfstate"
#     dynamodb_table = "workshops-1-terraform-state-lock"
#     region = "eu-north-1"
#   }
}

provider "aws" {
  region = "eu-north-1"
  default_tags {
    tags = {
      Creator = "terraform"
    }
  }
}
