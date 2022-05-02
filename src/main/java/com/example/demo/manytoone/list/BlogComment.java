package com.example.demo.manytoone.list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


/* ToString() 호출시 무한루프 제거를 위해 team 제외*/
@ToString(exclude = {"blog"})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"commentId"})
@Table(name = "BLOGCOMMENT")
@Entity
public class BlogComment  {	
		
	@Id		
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="COMMENT_ID")
	Long commentId;
	
	@ManyToOne
	@JoinColumn(name="BLOG_ID", nullable = false)
	Blog blog;
	
	@Column(name="COMMENT")
	String comment;
				
	BlogComment(Blog blog		       
		  	   ,String comment) {		
		this.blog = blog;		
		this.comment = comment;
	}
			
}
