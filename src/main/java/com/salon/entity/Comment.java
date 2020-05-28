package com.salon.entity;

import java.util.Date;
import java.util.Objects;

public class Comment {

  private final Integer id;

  private User master;

  private User commentator;

  private String commentText;

  private int serviceMark;

  private Date commentDate;

  public Comment(CommentBuilder builder) {
    this.id = builder.id;
    this.master = builder.master;
    this.commentator = builder.commentator;
    this.commentText = builder.commentText;
    this.serviceMark = builder.serviceMark;
    this.commentDate = builder.commentDate;
  }

  public Integer getId() {
    return id;
  }

  public User getMaster() {
    return master;
  }

  public Date getCommentDate() {
    return commentDate;
  }

  public void setCommentDate(Date commentDate) {
    this.commentDate = commentDate;
  }

  public void setMaster(User master) {
    this.master = master;
  }

  public User getCommentator() {
    return commentator;
  }

  public void setCommentator(User commentator) {
    this.commentator = commentator;
  }

  public String getCommentText() {
    return commentText;
  }

  public void setCommentText(String commentText) {
    this.commentText = commentText;
  }

  public int getServiceMark() {
    return serviceMark;
  }

  public void setServiceMark(int serviceMark) {
    this.serviceMark = serviceMark;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Comment)) return false;
    Comment comment = (Comment) o;
    return serviceMark == comment.serviceMark
        && Objects.equals(id, comment.id)
        && Objects.equals(master, comment.master)
        && Objects.equals(commentator, comment.commentator)
        && Objects.equals(commentText, comment.commentText)
            && Objects.equals(commentDate, comment.commentDate);

  }

  @Override
  public int hashCode() {
    return Objects.hash(id, master, commentator, commentText, serviceMark,commentDate);
  }

  @Override
  public String toString() {
    return "Comment{" +
            "id=" + id +
            ", master=" + master +
            ", commentator=" + commentator +
            ", commentText='" + commentText + '\'' +
            ", serviceMark=" + serviceMark +
            ", commentDate=" + commentDate +
            '}';
  }

  public static final class CommentBuilder {
    private Integer id;
    private User master;
    private User commentator;
    private String commentText;
    private int serviceMark;
    private Date commentDate;

    private CommentBuilder() {}

    public static CommentBuilder aComment() {
      return new CommentBuilder();
    }

    public CommentBuilder withId(Integer id) {
      this.id = id;
      return this;
    }

    public CommentBuilder withMaster(User master) {
      this.master = master;
      return this;
    }

    public CommentBuilder withCommentator(User commentator) {
      this.commentator = commentator;
      return this;
    }

    public CommentBuilder withCommentText(String commentText) {
      this.commentText = commentText;
      return this;
    }

    public CommentBuilder withServiceMark(int serviceMark) {
      this.serviceMark = serviceMark;
      return this;
    }

    public CommentBuilder withCommentDate(Date commentDate){
      this.commentDate = commentDate;
      return this;
    }

    public Comment build() {
      return new Comment(this);
    }
  }
}
