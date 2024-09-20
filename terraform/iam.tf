resource "aws_iam_user" "web_server" {
  name = "workshops-1-web-server"
  path = "/workshops-1/"
  force_destroy = true
}

resource "aws_iam_user_policy" "web_server" {
  name   = "s3-push"
  user   = aws_iam_user.web_server.name
  policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Effect = "Allow"
        Action = [
          "s3:PutObject",
          "s3:PutObjectTagging"
        ]
        Resource = "${aws_s3_bucket.hashed_files.arn}/hashed-files/*"
      }
    ]
  })
}

resource "aws_iam_user_policy_attachment" "deny_by_ip" {
  user       = aws_iam_user.web_server.name
  policy_arn = "arn:aws:iam::448740566997:policy/deny-all-by-ip"
}
