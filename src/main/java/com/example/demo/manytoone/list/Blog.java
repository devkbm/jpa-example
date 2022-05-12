package com.example.demo.manytoone.list;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 블로그[1] - 댓글[N]				<br>
 * 블로그에 여러댓글을 이력할 수 있다.	<br>
 * 댓글은 한번에 하나의 블로그에만 입력할 수 있다. 
 * 
 * 제약사항
 * OneToMany List 두개이상 있을경우 MultipleBagFetchException 발생
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Entity
@Table(name = "BLOG")
public class Blog  {
	
	@Id		
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="BLOG_ID")
	Long id;
	
	@Comment("블로그명")
	@Column(name="BLOG_NAME")
	String blogName;
	
	@OrderBy("commentId asc")
	@OneToMany(mappedBy="blog", cascade = CascadeType.ALL, orphanRemoval = true)
	List<BlogComment> comments = new ArrayList<BlogComment>();			
	
	public Blog(String blogName) {		
		this.blogName = blogName;		
	}					
	
	public BlogComment getComment(Long commentId) {
		BlogComment comment = null;
		
		// java stream
		comment = this.getComments().stream()
				             .filter(e -> e.commentId.equals(commentId))
							 .findFirst().orElse(null);
		
		return comment;
	}
	
	public void addComment(String comment) {
		this.comments.add(new BlogComment(this, comment));
	}	
	
	public void deleteComment(BlogComment comment) {
		this.comments.remove(comment);
	}
	
	
}
