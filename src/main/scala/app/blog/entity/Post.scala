package app.blog.entity

// Default values while jackson deserialization
// https://github.com/FasterXML/jackson-module-scala/issues/87
case class Post(id: String, author: String, text: String)
