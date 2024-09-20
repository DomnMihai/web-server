resource "aws_s3_bucket" "hashed_files" {
  bucket = "workshops-1-hashed-files-${data.aws_caller_identity.current.account_id}"
  force_destroy = true
}

resource "aws_s3_bucket_server_side_encryption_configuration" "hashed_files" {
  bucket = aws_s3_bucket.hashed_files.id

  rule {
    apply_server_side_encryption_by_default {
      sse_algorithm     = "AES256"
    }
    bucket_key_enabled = true
  }
}

resource "aws_s3_bucket_lifecycle_configuration" "hashed_files" {
  bucket = aws_s3_bucket.hashed_files.id

  rule {
    id = "delete-all-objects-after-3-days"
    status = "Enabled"
    filter {} # Apply to all objects

    expiration {
      days = 3
    }

    abort_incomplete_multipart_upload {
      days_after_initiation = 1
    }
  }
}
